package org.aquat.seleniumframework.browser;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.aquat.seleniumframework.data.ProjectProperty;
import org.aquat.seleniumframework.data.PropertyService;

/**
 * <p>Implementation of Chrome browsers; used to create Chrome instance</p>
 * 
 */
public class ChromeBrowser implements IBrowser {
	private ChromeOptions options = new ChromeOptions();
	private PropertyService ps = PropertyService.getInstance();
	
	public WebDriver startBrowser(boolean mode) {
		if (mode==false) {
			return browserStandalone();
		}
		return browserGrid();
	}

	private WebDriver browserStandalone() {
		logger.info("Starting Chrome browser...");
		System.setProperty(ProjectProperty.CHROME_DRIVER, ps.getProperty(ProjectProperty.CHROME_DRIVER));	
		addChromeOptions();	
		return new ChromeDriver(options);
	}
	
	private WebDriver browserGrid() {
		WebDriver driver = null;
		String gridServer = ps.getProperty(ProjectProperty.SELENIUM_GRID_SERVER);
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
		if (!ps.getProperty(ProjectProperty.CHROME_ARGS).isEmpty()) {
			String para[] = ps.getProperty(ProjectProperty.CHROME_ARGS).split(";");
			for (String str:para) {
				if (!str.trim().isEmpty()) {
					logger.info("Adding Chrome Argument: " + str.trim());
					options.addArguments(str.trim());
				}	
			}
		}
		
		if (!ps.getProperty(ProjectProperty.CHROME_EXTS).isEmpty()) {
			String para[] = ps.getProperty(ProjectProperty.CHROME_EXTS).split(";");
			for (String str:para) {
				if (!str.trim().isEmpty()) {
					
					String userDir = str.trim();
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
		
		if (!ps.getProperty(ProjectProperty.CHROME_DOWNLOAD_DIR).isEmpty()) {
			HashMap<String, Object> prefs = new HashMap<String, Object>();
			//prefs.put("profile.default_content_settings.popups", 0);
			
			String userDir = ps.getProperty(ProjectProperty.CHROME_DOWNLOAD_DIR);
			String canonicalDir = null;
			try {
				canonicalDir = new File(userDir).getCanonicalPath();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			prefs.put("download.default_directory", canonicalDir);
			options.setExperimentalOption("prefs", prefs);
		}
	}
}
