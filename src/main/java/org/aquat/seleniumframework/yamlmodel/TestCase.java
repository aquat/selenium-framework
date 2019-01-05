package org.aquat.seleniumframework.yamlmodel;

import java.util.LinkedHashMap;

public class TestCase {
	private String name;
	private LinkedHashMap<String, String> parameters;
	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public LinkedHashMap<String, String> getParameters() {
		return parameters;
	}
	public void setParameters(LinkedHashMap<String, String> parameters) {
		this.parameters = parameters;
	}
	
	public String getParameter(String key) {
		return parameters.get(key);
	}
}
