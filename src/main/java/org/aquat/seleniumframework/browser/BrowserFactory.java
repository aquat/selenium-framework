package org.aquat.seleniumframework.browser;

import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

import org.aquat.seleniumframework.data.ProjectProperty;
import org.aquat.seleniumframework.data.PropertyService;
import org.aquat.seleniumframework.util.SystemLogger;

/**
 * <p>Provide abilities to set browser properties and create instances</p>
 * 
 */
public class BrowserFactory {
	private static Logger logger = SystemLogger.getLogger(BrowserFactory.class);
	private WebDriver driver; 
	
	public WebDriver getInstance() {			
		PropertyService ps = PropertyService.getInstance();
		String type = ps.getProperty(ProjectProperty.BROWSER_TYPE);
		boolean mode = Boolean.parseBoolean(ps.getProperty(ProjectProperty.SELENIUM_GRID));
		
		if (type.equalsIgnoreCase("chrome")) {
			driver =  new ChromeBrowser().startBrowser(mode);
		}
		else if (type.equalsIgnoreCase("firefox")) {
			driver =  new FirefoxBrowser().startBrowser(mode);
		}
		else if (type.equalsIgnoreCase("ie")) {
			driver =  new IEBrowser().startBrowser(mode);
		}
		else {
			logger.info("Property 'browser.type' not defined or wrong. Use Chrome as default");
			driver =  new ChromeBrowser().startBrowser(mode);	
		}
		
		setSize();
		
		return driver;
	}
	
	private void setSize() {
		PropertyService ps = PropertyService.getInstance();
		String size = ps.getProperty(ProjectProperty.BROWSER_WINDOW_SIZE).trim();
		
		if (size.equalsIgnoreCase("maximize")) {
			driver.manage().window().maximize();
			return;
		}
		else if (size.contains("*")) {
			System.out.println(size);
			String dims[] = size.split("\\*");
			driver.manage().window().setSize(new Dimension(Integer.parseInt(dims[0]), Integer.parseInt(dims[1])));
			return;
		}
		else {
			logger.info("Property 'browser.window.size' not defined or wrong. Use default window size");
			return;
		}	
	}
}
