package org.aquat.seleniumframework.data;

import java.util.Iterator;
import java.util.LinkedHashMap;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * <p>
 * PropertyFileConverter is used to parse properties file
 * </p>
 * 
 */
public class PropertyFileConverter {
	private static final String ENCODING = "utf-8";
	private String fileName;
	
	public PropertyFileConverter init(String fileName) {
		this.fileName = fileName;
		return this;
	}

	public LinkedHashMap<String, String> generate() {	
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
	
		PropertiesConfiguration config = new PropertiesConfiguration();
		config.setEncoding(ENCODING);
		try {
			config.load(fileName);
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		
		Iterator<String> it = config.getKeys();	
		while (it.hasNext()) {
			String key = it.next();
			String value = config.getString(key);
			map.put(key, value);
		}
		
		return map;
	}
}