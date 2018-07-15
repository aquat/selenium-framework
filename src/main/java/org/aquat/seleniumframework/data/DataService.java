package org.aquat.seleniumframework.data;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * DataService is used to provide access to data in properties or excel file
 * </p>
 * 
 */
public class DataService {
	private static DataService ds;
	private static final String USER_DIR = "user.dir";
	private static final String PROJECT_PROPERTIES = "project.properties";
	private static final String excelExtension = "xlsx";
	private ExcelFileConverter excelConverter;
	
	private DataService() {
		
	}
	
	public static DataService getInstance() {
		if (ds==null) {
			ds = new DataService();
		}
		return ds;
	}
	
	public LinkedHashMap<String, String> readProjectPreperties() {
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		String projectPropertyFileName = System.getProperty(USER_DIR) + File.separator + PROJECT_PROPERTIES;
		PropertyFileConverter propConverter = new PropertyFileConverter();
		map = propConverter.init(projectPropertyFileName).generate();	
		
		//read properties from excel if using Excel as data source
		if (map.get(ProjectProperty.TEST_DATA_SOURCE).contains(excelExtension)) {
			String dataSet = map.get(ProjectProperty.TEST_DATA_SET);
			String excelFileName = System.getProperty(USER_DIR) + File.separator + map.get(ProjectProperty.TEST_DATA_SOURCE);
			excelConverter = new ExcelFileConverter();
			LinkedHashMap<String, String> map2 = excelConverter.init(excelFileName, dataSet).getPropertiesFromExcel();
			
			map.putAll(map2);
		}
		
		return map;
	}
	
	public LinkedHashMap<String, String> readTestngParameters(Map<String, String> parameters) {
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		if (parameters.isEmpty()) {
			return map;
		}

		//determine if the testng parameters are related to project properties
		PropertyService ps = PropertyService.getInstance();
		Set<String> keys = ps.getKeys();		
		parameters.forEach((k, v) -> {
			if (keys.contains(k)) {
				map.put(k, v);
			}
		});
		
		//read properties from excel if user defines data source or data set in testng parameters
		if (map.keySet().contains(ProjectProperty.TEST_DATA_SET) || map.keySet().contains(ProjectProperty.TEST_DATA_SOURCE)) {	
			String dataSet = map.get(ProjectProperty.TEST_DATA_SET);
			String excelFileName = null;
			
			if (map.keySet().contains(ProjectProperty.TEST_DATA_SOURCE)) {
				excelFileName = System.getProperty(USER_DIR) + File.separator + map.get(ProjectProperty.TEST_DATA_SOURCE);
			} else {
				excelFileName = System.getProperty(USER_DIR) + File.separator + ps.getProperty(ProjectProperty.TEST_DATA_SOURCE);
			}
 
			excelConverter = new ExcelFileConverter();
			LinkedHashMap<String, String> map2 = excelConverter.init(excelFileName, dataSet).getPropertiesFromExcel();

			map.putAll(map2);
		}
		
		return map;
	}
	
	public String getTestData(String caseName, String key) {
		PropertyService ps = PropertyService.getInstance();
		String dataSource = ps.getProperty(ProjectProperty.TEST_DATA_SOURCE);		
		
		if (dataSource.contains(excelExtension)) {			
			return excelConverter.getTestDataFromExcel(caseName, key);
		}
		if (dataSource.equals("properties")) {
			return getTestDataFromProperties(caseName, key);
		}
		
		return null;
	}
	
	private String getTestDataFromProperties(String caseName, String key) {
		LinkedHashMap<String, String> testData = new LinkedHashMap<String, String>();
		String propertiesFileName = null;
		try {
			String simpleName = Class.forName(caseName).getSimpleName();
			propertiesFileName = Class.forName(caseName).getResource(simpleName + ".properties").getFile();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		PropertyFileConverter propConverter = new PropertyFileConverter();
		testData = propConverter.init(propertiesFileName).generate();
		
		return testData.get(key);
	}
}
