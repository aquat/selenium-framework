package org.aquat.seleniumframework.yamlmodel;

public class ProjectConfig {
	private Selenium selenium;
	private Browser browser;
	private WebDriver webdriver;
	private Log log;

	public Selenium getSelenium() {
		return selenium;
	}

	public void setSelenium(Selenium selenium) {
		this.selenium = selenium;
	}

	public Browser getBrowser() {
		return browser;
	}

	public void setBrowser(Browser browser) {
		this.browser = browser;
	}

	public WebDriver getWebdriver() {
		return webdriver;
	}

	public void setWebdriver(WebDriver webdriver) {
		this.webdriver = webdriver;
	}

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}

}
