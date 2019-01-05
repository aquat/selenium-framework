package org.aquat.seleniumframework.browser;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.aquat.seleniumframework.data.ConfigService;
import org.aquat.seleniumframework.data.ProjectProperty;
import org.aquat.seleniumframework.yamlmodel.ProjectConfig;

/**
 * <p>Implementation of Firefox browsers; used to create Firefox instance</p>
 * 
 */
public class FirefoxBrowser implements IBrowser {
	private ProjectConfig config = ConfigService.getInstance().getConfigModel();
	
	public WebDriver startBrowser(boolean mode) {
		if (mode==false) {
			return browserStandalone();
		}
		return browserGrid();
	}
	
	private WebDriver browserStandalone() {
		logger.info("Starting Firefox browser...");
		System.setProperty(ProjectProperty.FIREFOX_DRIVER, config.getWebdriver().getFirefox().getDriver());
		
		FirefoxProfile ffprofile = null;
		String firefoxProfile = config.getWebdriver().getFirefox().getProfile();
		String firefoxBin = config.getWebdriver().getFirefox().getBin();
		if (firefoxBin.isEmpty()) {
			System.clearProperty(ProjectProperty.FIREFOX_BIN);
		}
		else {
			System.setProperty(ProjectProperty.FIREFOX_BIN, firefoxBin);
		}
		
		if (firefoxProfile.isEmpty()) {
			ProfilesIni profiles = new ProfilesIni();
			ffprofile = profiles.getProfile("default"); 
		}
		else {
			File dir = new File(firefoxProfile);
			ffprofile = new FirefoxProfile(dir);
		}
		
		FirefoxOptions options = new FirefoxOptions();
		options.setProfile(ffprofile);
		return new FirefoxDriver(options); 
	}

	private WebDriver browserGrid() {
		WebDriver driver = null;
		String gridServer = config.getSelenium().getGridServer();
		logger.info("Starting Firefox browser in Selenium grid mode...");
		DesiredCapabilities capability = DesiredCapabilities.firefox();
		capability.setBrowserName("firefox");
		try {
			driver = new RemoteWebDriver(new URL(gridServer), capability);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return driver;
	}
}

