package org.aquat.seleniumframework.yamlmodel;

import java.util.ArrayList;

public class Chrome {
	private String bin;
	private String driver;
	private ArrayList<String> args;
	private ArrayList<String> extensions;
	private String downloadDirectory;

	public String getBin() {
		return bin;
	}

	public void setBin(String bin) {
		this.bin = bin;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public ArrayList<String> getArgs() {
		return args;
	}

	public void setArgs(ArrayList<String> args) {
		this.args = args;
	}

	public ArrayList<String> getExtensions() {
		return extensions;
	}

	public void setExtensions(ArrayList<String> extensions) {
		this.extensions = extensions;
	}

	public String getDownloadDirectory() {
		return downloadDirectory;
	}

	public void setDownloadDirectory(String downloadDirectory) {
		this.downloadDirectory = downloadDirectory;
	}

}
