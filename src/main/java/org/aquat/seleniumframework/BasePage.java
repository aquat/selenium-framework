package org.aquat.seleniumframework;

import org.apache.log4j.Logger;
import org.aquat.seleniumframework.data.ProjectProperty;
import org.aquat.seleniumframework.data.PropertyService;
import org.aquat.seleniumframework.util.SystemLogger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

/**
 * <p>All page objects should extend BasePage</p>
 * 
 */
public class BasePage {
	protected WebDriver driver;
	protected static Logger logger = SystemLogger.getLogger(BasePage.class);
	
	/**
	 * 
	 * Constructor
	 * @param driver WebDriver
	 */
	public BasePage(WebDriver driver) {
		this.driver = driver;
		int waits = Integer.parseInt(PropertyService.getInstance().getProperty(ProjectProperty.IMPLICIT_WAITS));
		AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, waits);
		PageFactory.initElements(factory, this);
	}
	
	/**
	 * 
	 * Time to sleep
	 * @param milliseconds
	 */
	public void sleep(long milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Run JavaScript
	 * @param jscript
	 */
	public void runJavaScript(String jscript) {
		((JavascriptExecutor) driver).executeScript(jscript);
	}
	
	/**
	 * Run JavaScript on the given WebElement
	 * @param jscript
	 */
	public void runJavaScript(String jscript, WebElement element) {
		((JavascriptExecutor) driver).executeScript(jscript, element);
	}
	
	/**
	 * Get browser window title
	 * @return
	 */
	public String getTitle() {
		return driver.getTitle();
	}
}
