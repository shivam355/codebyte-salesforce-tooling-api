package com.project.connector;

import com.project.connection.ApiConnection;
import com.project.connection.ConnReader;

public class SalesforceSrcConnector implements SrcConnector<String> {
	private final String url;
	private final String accessToken;

	public SalesforceSrcConnector(String url, String accessToken) {
		this.url = url;
		this.accessToken = accessToken;
	}

	public String getContent() {
		ConnReader connReader = new ApiConnection(url, accessToken);
		return connReader.getContent();
	}

}
