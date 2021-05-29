package com.project.connector;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Read from file to string connector impl
 * 
 * @author shivam
 *
 */
public class FileSrcConnector implements SrcConnector<String> {
	private final String directory;
	private final String fileName;

	public FileSrcConnector(String directory, String fileName) {
		this.directory = directory;
		this.fileName = fileName;
	}

	/**
	 * Main method
	 * 
	 * @return
	 */
	public String getFileContent() {
		File file = new File(directory + File.separator + fileName);
		StringBuilder sb = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String st;
			while ((st = br.readLine()) != null) {
				sb.append(st);
			}
			return sb.toString();
		} catch (IOException e) {
			System.out.println("Error while writing file");
			return null;
		}
	}

}
