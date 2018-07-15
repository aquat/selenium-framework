package org.aquat.seleniumframework.data;

/**
 * <p>Property mapping of all the keys in project.properties</p>
 * 
 */
public interface ProjectProperty {
	static final String SELENIUM_GRID = "selenium.grid";
	static final String SELENIUM_GRID_SERVER = "selenium.grid.server";
	static final String IMPLICIT_WAITS = "selenium.implicitwaits";
	
	static final String BROWSER_WINDOW_SIZE = "browser.window.size";
	static final String BROWSER_TYPE = "browser.type";
	static final String BROWSER_AUTO_CLOSE = "browser.autoclose";
	
	static final String IE_DRIVER = "webdriver.ie.driver";
	
	static final String FIREFOX_BIN = "webdriver.firefox.bin";
	static final String FIREFOX_DRIVER = "webdriver.gecko.driver";
	static final String FIREFOX_PROFILE = "webdriver.firefox.profile";
	
	static final String CHROME_BIN = "webdriver.chrome.bin";
	static final String CHROME_DRIVER = "webdriver.chrome.driver";
	static final String CHROME_ARGS= "webdriver.chrome.args";
	static final String CHROME_EXTS = "webdriver.chrome.extensions";
	static final String CHROME_DOWNLOAD_DIR = "webdriver.chrome.download.directory";
	
	static final String TEST_DATA_SOURCE = "test.data.source";
	static final String TEST_DATA_SET = "test.data.set";
	static final String TEST_URL = "test.url";
	
	static final String TEST_LOGIN_CLASS = "test.login.class";
	static final String TEST_LOGIN_METHOD = "test.login.method";
	static final String TEST_USERNAME = "test.username";
	static final String TEST_PASSWORD = "test.password";	

	static final String LOGGER_SWITCH = "logger.switch";
}
