package com.project;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.project.connection.ApiConnection;
import com.project.connection.ConnReader;
import com.project.connector.ConsoleSinkConnector;
import com.project.connector.FileSinkConnector;
import com.project.connector.FileSrcConnector;
import com.project.connector.SalesforceSrcConnector;
import com.project.helper.ContextHelper;
import com.project.pojo.SObject;

public class ProjectContext {
	private final String domain;
	private final String accessToken;
	private final int maxPoolSize;
	private final String baseDirectory;

	private final ContextHelper contextHelper = new ContextHelper();
	private final Random random = new Random();

	public ProjectContext(String domain, String accessToken, int maxPoolSize, String baseDirectory) {
		this.domain = domain;
		this.accessToken = accessToken;
		this.maxPoolSize = maxPoolSize <= 0 ? 1 : maxPoolSize;
		this.baseDirectory = baseDirectory;
	}

	public void start() throws Exception {
		initialize();

		//
		final String allEntityUrl = contextHelper.getAllEntityUrl(domain);
		ConnReader connReader = new ApiConnection(allEntityUrl, accessToken);
		List<SObject> sobjects = contextHelper.getSObjects(connReader.getContent());

		//
		int poolSize = contextHelper.getPoolSize(sobjects.size(), maxPoolSize);
		ExecutorService threadPool = Executors.newFixedThreadPool(poolSize);

		List<CompletableFuture<Void>> cfList = new ArrayList<CompletableFuture<Void>>();
		for (SObject obj : sobjects) {
			final String entityName = obj.getName();
			CompletableFuture<Void> cf = CompletableFuture.supplyAsync(() -> getContent(entityName), threadPool)
					.thenApply((data) -> save(entityName, data)).thenApply(fileName -> getContentFromFile(fileName))
					.thenAccept((x) -> show(x));
			cfList.add(cf);
		}

		//
		for (CompletableFuture<Void> cf : cfList) {
			cf.get();
		}

		//
		threadPool.shutdown();

	}

	private String getContent(String entityName) {
		String entityDescUrl = contextHelper.getEntityDescUrl(domain, entityName);
		return new SalesforceSrcConnector(entityDescUrl, accessToken).getContent();
	}

	private String save(String entityName, String data) {
		String fileName = entityName + "_" + random.nextInt(10000);
		new FileSinkConnector(baseDirectory, fileName, data).save();
		return fileName;
	}

	private String getContentFromFile(String fileName) {
		String content = fileName.split("_")[0] + "##" + new FileSrcConnector(baseDirectory, fileName).getFileContent();
		return content;
	}

	private void show(String desc) {
		new ConsoleSinkConnector(desc).print();
	}

	private void initialize() {
		File dir = new File(baseDirectory);
		if (!dir.exists()) {
			dir.mkdirs();
			return;
		}
		clearDirectory();
	}

	private void clearDirectory() {
		File dir = new File(baseDirectory);
		for (File file : dir.listFiles()) {
			file.delete();
		}
	}

}
