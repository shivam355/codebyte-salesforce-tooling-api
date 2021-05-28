package com.project.helper;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.exception.AppRuntimeException;
import com.project.pojo.SObject;

public final class ContextHelper {
	public List<SObject> getSObjects(String json) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			JsonNode jsonNode = objectMapper.readTree(json);
			final List<SObject> list = new ArrayList<SObject>();
			jsonNode.get("sobjects").forEach((r) -> {
				list.add(new SObject(r.get("name").asText()));
			});
			return list;
		} catch (Exception e) {
			throw new AppRuntimeException(e);
		}
	}

	public int getPoolSize(int noofentity, int maxPoolSize) {
		return noofentity <= maxPoolSize ? noofentity : maxPoolSize;
	}

	public String getAllEntityUrl(String domain) {
		return "https://" + domain + "/services/data/v50.0/tooling/sobjects";
	}

	public String getEntityDescUrl(String domain, String entityName) {
		return "https://" + domain + "/services/data/v50.0/tooling/sobjects/" + entityName + "/describe";
	}
}
