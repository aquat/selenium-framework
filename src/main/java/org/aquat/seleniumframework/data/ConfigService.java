package org.aquat.seleniumframework.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.FileInputStream;

import org.aquat.seleniumframework.yamlmodel.ProjectConfig;
import org.aquat.seleniumframework.yamlmodel.TestData;
import org.yaml.snakeyaml.Yaml;

public class ConfigService {
	private static ConfigService cs;
	private static String fileConfigYaml = "config.yml";
	private static String fileTestData = "testdata.yml";
	private ProjectConfig projectConfig;
	private TestData testData;
	
	private ConfigService() {
		//initialize yaml model for config.yml and testdata.yml in working directory
		
		InputStream is = null;
		Yaml yaml = new Yaml();
		
		try {
			is = new FileInputStream(System.getProperty("user.dir") + File.separator + fileConfigYaml);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
		projectConfig = yaml.loadAs(is, org.aquat.seleniumframework.yamlmodel.ProjectConfig.class);	
		
		try {
			is = new FileInputStream(System.getProperty("user.dir") + File.separator + fileTestData);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
		testData = yaml.loadAs(is, org.aquat.seleniumframework.yamlmodel.TestData.class);		
	}
	
	public static ConfigService getInstance() {
		if (cs == null) {
			cs = new ConfigService();
		}
		return cs;
	}

	public ProjectConfig getConfigModel() {
		return projectConfig;
	}
	
	public TestData getTestDataModel() {
		return testData;
	}
}
