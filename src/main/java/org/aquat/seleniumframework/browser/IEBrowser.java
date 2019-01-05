package org.aquat.seleniumframework.browser;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.aquat.seleniumframework.data.ConfigService;
import org.aquat.seleniumframework.data.ProjectProperty;
import org.aquat.seleniumframework.yamlmodel.ProjectConfig;

/**
 * <p>Implementation of IE browsers; used to create IE instance</p>
 * 
 */
public class IEBrowser implements IBrowser {
	private ProjectConfig config = ConfigService.getInstance().getConfigModel();
	
	public WebDriver startBrowser(boolean mode) {
		if (mode==false) {
			return browserStandalone();
		}
		return browserGrid();
	}
	
	private WebDriver browserStandalone() {
		logger.info("Starting Internet Explorer browser...");
		System.setProperty(ProjectProperty.IE_DRIVER, config.getWebdriver().getIe().getDriver());

//		DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
//		capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
//		return new InternetExplorerDriver(capability);
		
		InternetExplorerOptions options = new InternetExplorerOptions();
		options.introduceFlakinessByIgnoringSecurityDomains();
		
		return new InternetExplorerDriver(options);
	}
	
	
	private WebDriver browserGrid() {
		WebDriver driver = null;
		String gridServer = config.getSelenium().getGridServer();
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

