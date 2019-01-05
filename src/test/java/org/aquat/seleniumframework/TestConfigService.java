package org.aquat.seleniumframework;

import org.aquat.seleniumframework.data.ConfigService;
import org.aquat.seleniumframework.yamlmodel.ProjectConfig;
import org.aquat.seleniumframework.yamlmodel.TestData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestConfigService {
	
	@Test
	public void shouldConvertYamlModel() {
		ConfigService cs = ConfigService.getInstance();
		ProjectConfig config= cs.getConfigModel();
		TestData data = cs.getTestDataModel();
		
		System.out.println(this.getClass().getName());
		
		Assert.assertEquals(config.getSelenium().isGrid(), false);
		Assert.assertEquals(config.getWebdriver().getChrome().getArgs().get(0), "no-sandbox");
		
		Assert.assertEquals(data.getGeneral().getParameters().get("url"), "https://www.baidu.com");
		//Assert.assertEquals(data.getCases().get("com.sap.gs.Test1").get("url"), "https://www.baidu.com");
		//Assert.assertEquals(data.getCases().getTestCaseByName("com.sap.gs.Test1").getParameter("url"), "https://www.baidu.com");
	}
}
