package com.project.connector;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileSinkConnector implements SinkConnector {
	private final String directory;
	private final String fileName;
	private final String data;

	public FileSinkConnector(String directory, String fileName, String data) {
		this.directory = directory;
		this.fileName = fileName;
		this.data = data;
	}

	public void save() {
		File file = new File(directory + File.separator + fileName);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				System.out.println("Error while creating file");
				return;
			}
		}
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			writer.write(data);
		} catch (IOException e) {
			System.out.println("Error while writing file");
		}
	}

}
