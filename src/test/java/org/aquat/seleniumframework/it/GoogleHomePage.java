package org.aquat.seleniumframework.it;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import org.aquat.seleniumframework.BasePage;

public class GoogleHomePage extends BasePage{
	
	public GoogleHomePage(WebDriver driver) {
		super(driver);
	}
	
	public GoogleResultPage search(String keywords) {
//		driver.findElement(By.name("q")).sendKeys(keywords);
//		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		
		//replace google with baidu
		driver.findElement(By.id("kw")).sendKeys(keywords);
		driver.findElement(By.id("kw")).sendKeys(Keys.ENTER);
		
		return new GoogleResultPage(driver);
	}
}
