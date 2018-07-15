package org.aquat.seleniumframework;

import org.testng.Assert;
import org.testng.annotations.Test;

import org.aquat.seleniumframework.annotations.BrowserType;
import org.aquat.seleniumframework.browser.Browser;

public class TestDemo extends BaseTest {
	
	@BrowserType(Browser.CHROME)
	@Test
	public void verifyTitle() {
		logger.info("Step 1: start browser");
		start();
		
		logger.info("Step 2: Navigate to Home Page");
		GoogleHomePage homepage = navigateTo(GoogleHomePage.class, getData("url"));
				
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		logger.info("Step 3: Assert page title");
		Assert.assertEquals(homepage.getTitle(), getData("title"));
	}
}
