package com.project.connector;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.exception.AppRuntimeException;
import com.project.pojo.SObject;

public class ConsoleSinkConnector implements SinkConnector {
	private final String data;

	public ConsoleSinkConnector(String data) {
		this.data = data;
	}

	public void print() {
		if (data == null) {
			return;
		}
		String[] str = data.split("##");
		String entityName = str[0];
		String desc = str[1];
		StringBuilder fields = new StringBuilder();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			JsonNode jsonNode = objectMapper.readTree(desc);
			final List<SObject> list = new ArrayList<SObject>();
			jsonNode.get("fields").forEach((r) -> {
				fields.append(",").append(r.get("name").asText());
			});
			System.out.println(entityName + " [" + fields.substring(1) + "]");
		} catch (Exception e) {
			throw new AppRuntimeException(e);
		}

	}

}
