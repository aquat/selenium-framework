package org.aquat.seleniumframework.data;

import org.aquat.seleniumframework.yamlmodel.TestData;

/**
 * <p>
 * DataService is used to provide access to data in properties or excel file
 * </p>
 * 
 */
public class DataService {
	private static DataService ds;
	
	private DataService() {
		
	}
	
	public static DataService getInstance() {
		if (ds==null) {
			ds = new DataService();
		}
		return ds;
	}
	
	public String getTestParameters(String caseName, String key) {
		TestData data = ConfigService.getInstance().getTestDataModel();
		String value = data.getCases().getTestCaseByName(caseName).getParameter(key);
		if (value == null) {
			value = data.getGeneral().getParameters().get(key);
		}
		return value;
	}
	
}

