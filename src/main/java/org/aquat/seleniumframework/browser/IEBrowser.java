package org.aquat.seleniumframework.browser;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.aquat.seleniumframework.data.ProjectProperty;
import org.aquat.seleniumframework.data.PropertyService;

/**
 * <p>Implementation of IE browsers; used to create IE instance</p>
 * 
 */
public class IEBrowser implements IBrowser{
	private PropertyService ps = PropertyService.getInstance();
	
	public WebDriver startBrowser(boolean mode) {
		if (mode==false) {
			return browserStandalone();
		}
		return browserGrid();
	}
	
	private WebDriver browserStandalone() {
		logger.info("Starting Internet Explorer browser...");
		System.setProperty(ProjectProperty.IE_DRIVER, ps.getProperty(ProjectProperty.IE_DRIVER));

		DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
		capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		return new InternetExplorerDriver(capability);
	}
	
	
	private WebDriver browserGrid() {
		WebDriver driver = null;
		String gridServer = ps.getProperty(ProjectProperty.SELENIUM_GRID_SERVER);
		logger.info("Starting Internet Explorer browser in Selenium grid mode...");
		DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
		capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		capability.setBrowserName("internet explorer");
		try {
			driver = new RemoteWebDriver(new URL(gridServer), capability);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return driver;
	}
	
}

