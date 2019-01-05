package org.aquat.seleniumframework.it;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.aquat.seleniumframework.BaseTest;
import org.aquat.seleniumframework.annotations.BrowserAutoClose;
import org.aquat.seleniumframework.annotations.BrowserType;
import org.aquat.seleniumframework.browser.Browser;

public class TestSearchHello extends BaseTest{
	
	@BrowserType(Browser.CHROME)
	@BrowserAutoClose(true)
	@Test
	public void verifyTitle() {
		logger.info("Step 1: start browser");
		start();
		
		logger.info("Step 2: Navigate to Home Page");
		GoogleHomePage homepage = navigateTo(GoogleHomePage.class, getData("url"));
				
		logger.info("Step 3: Search for keyword - 'hello'");
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
