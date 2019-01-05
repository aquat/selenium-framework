package org.aquat.seleniumframework.it;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.aquat.seleniumframework.BaseTest;

public class TestSearchSelenium extends BaseTest{
	
	@Test
	public void verifyTitle() {
		logger.info("Step 1: start browser");
		start();
		
		logger.info("Step 2: Navigate to Home Page");
		GoogleHomePage homepage = navigateTo(GoogleHomePage.class, getData("url"));
		
		logger.info("Step 3: Search for keyword - 'Selenium'");
		homepage.search(getData("keyword"));
				
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		logger.info("Step 4: Assert search result page title");
		Assert.assertTrue(homepage.getTitle().contains(getData("title")));
	}
}
