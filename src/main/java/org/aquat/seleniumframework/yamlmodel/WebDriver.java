package org.aquat.seleniumframework.yamlmodel;

public class WebDriver {
	private Chrome chrome;
	private Firefox firefox;
	private IE ie;

	public Chrome getChrome() {
		return chrome;
	}

	public void setChrome(Chrome chrome) {
		this.chrome = chrome;
	}

	public Firefox getFirefox() {
		return firefox;
	}

	public void setFirefox(Firefox firefox) {
		this.firefox = firefox;
	}

	public IE getIe() {
		return ie;
	}

	public void setIe(IE ie) {
		this.ie = ie;
	}

}
