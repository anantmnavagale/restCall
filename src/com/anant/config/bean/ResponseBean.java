package com.anant.config.bean;

import java.util.LinkedHashMap;

public class ResponseBean {
	private LinkedHashMap<String, Object> response = null;

	public ResponseBean() {
		response = new LinkedHashMap<String, Object>();
	}

	public void add(String key, Object value) {
		response.put(key, value);
	}

	public LinkedHashMap<String, Object> getResponse() {
		return response;
	}

	@Override
	public String toString() {
		return "ResponseBean [response=" + response + "]";
	}

}
