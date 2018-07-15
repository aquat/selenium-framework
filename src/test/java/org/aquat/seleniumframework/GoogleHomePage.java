package org.aquat.seleniumframework;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import org.aquat.seleniumframework.data.PropertyService;

public class GoogleHomePage extends BasePage {
	
	public GoogleHomePage(WebDriver driver) {
		super(driver);
		System.out.println(PropertyService.getInstance().getProperty("webdriver.chrome.download.directory"));
		
	}
	
	public void search(String keywords) {
		driver.findElement(By.id("lst-ib")).sendKeys(keywords);
		driver.findElement(By.id("lst-ib")).sendKeys(Keys.ENTER);
		//driver.findElement(By.name("btnK")).click();
	}
	

}
