package org.aquat.seleniumframework.browser;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.aquat.seleniumframework.data.ConfigService;
import org.aquat.seleniumframework.data.ProjectProperty;
import org.aquat.seleniumframework.yamlmodel.ProjectConfig;

/**
 * <p>Implementation of Chrome browsers; used to create Chrome instance</p>
 * 
 */
public class ChromeBrowser implements IBrowser {
	private ChromeOptions options = new ChromeOptions();
	private ProjectConfig config = ConfigService.getInstance().getConfigModel();
	
	public WebDriver startBrowser(boolean mode) {
		if (mode==false) {
			return browserStandalone();
		}
		return browserGrid();
	}

	private WebDriver browserStandalone() {
		logger.info("Starting Chrome browser...");
		System.setProperty(ProjectProperty.CHROME_DRIVER, config.getWebdriver().getChrome().getDriver());	
		addChromeOptions();		
		return new ChromeDriver(options);
	}
	
	private WebDriver browserGrid() {
		WebDriver driver = null;
		String gridServer = config.getSelenium().getGridServer();
		logger.info("Starting Chrome browser in selenium grid mode...");
		DesiredCapabilities capability = DesiredCapabilities.chrome();
		capability.setBrowserName("chrome");
		try {
			driver = new RemoteWebDriver(new URL(gridServer), capability);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return driver;
	}
	
	private void addChromeOptions() {
		ArrayList<String> args = config.getWebdriver().getChrome().getArgs();
		ArrayList<String> extensions = config.getWebdriver().getChrome().getExtensions();
		String downloadDir = config.getWebdriver().getChrome().getDownloadDirectory();
		
		if (!args.isEmpty()) {
			for (String arg : args) {
				if (!arg.trim().isEmpty()) {
					logger.info("Adding Chrome Argument: " + arg.trim());
					options.addArguments(arg.trim());
				}	
			}
		}
		
		if (!extensions.isEmpty()) {
			for (String extension : extensions) {
				if (!extension.trim().isEmpty()) {
					
					String userDir = extension.trim();
					String canonicalDir = null;
					try {
						canonicalDir = new File(userDir).getCanonicalPath();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					logger.info("Adding Chrome Extension: " + canonicalDir);
					options.addExtensions(new File(canonicalDir));
				}	
			}
		}
		
		if (!downloadDir.isEmpty()) {
			HashMap<String, Object> prefs = new HashMap<String, Object>();
			//prefs.put("profile.default_content_settings.popups", 0);
			
			String canonicalDir = null;
			try {
				canonicalDir = new File(downloadDir).getCanonicalPath();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			prefs.put("download.default_directory", canonicalDir);
			options.setExperimentalOption("prefs", prefs);
		}
	}
}
