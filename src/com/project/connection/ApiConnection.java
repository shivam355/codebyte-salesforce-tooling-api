package com.project.connection;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * 
 */
import com.project.exception.AppRuntimeException;

public class ApiConnection implements ConnReader {
	private String url;
	private String accessToken;

	public ApiConnection(String url, String accessToken) {
		this.url = url;
		this.accessToken = accessToken;
	}

	@Override
	public String getContent() {
		try {
			URLConnection connection = new URL(url).openConnection();
			connection.setRequestProperty("Authorization", "Bearer " + accessToken);
			InputStream is = connection.getInputStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine = null;
			StringBuilder sb = new StringBuilder();
			while ((inputLine = in.readLine()) != null) {
				sb.append(inputLine);
			}
			return sb.toString();
		} catch (Exception e) {
			throw new AppRuntimeException(e);
		}
	}

}
