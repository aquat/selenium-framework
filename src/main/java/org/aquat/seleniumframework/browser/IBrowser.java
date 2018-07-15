package org.aquat.seleniumframework.browser;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import org.aquat.seleniumframework.util.SystemLogger;

/**
 * <p>interface to all browsers</p>
 * 
 */
public interface IBrowser {
	Logger logger = SystemLogger.getLogger(IBrowser.class);
	WebDriver startBrowser(boolean mode);
}
