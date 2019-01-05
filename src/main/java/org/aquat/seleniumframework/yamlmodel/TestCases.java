package org.aquat.seleniumframework.yamlmodel;

import java.util.LinkedHashMap;

public class TestCases extends LinkedHashMap<String, LinkedHashMap<String, String>> {

	private static final long serialVersionUID = 2184296418418531478L;
	
	public TestCase getTestCaseByName(String name) {
		TestCase testcase = new TestCase();
		testcase.setName(name);
		testcase.setParameters(this.get(name));
		
		return testcase;
	}
}
