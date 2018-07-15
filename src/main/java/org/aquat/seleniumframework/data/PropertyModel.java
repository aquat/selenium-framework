package org.aquat.seleniumframework.data;

import java.util.LinkedHashMap;

/**
 * <p>
 * PropertyModel
 * </p>
 * 
 */
public class PropertyModel {
	private LinkedHashMap<String, String> properties = new LinkedHashMap<String, String>();

	public LinkedHashMap<String, String> getProperties() {
		return properties;
	}

	public void setProperties(LinkedHashMap<String, String> properties) {
		this.properties.putAll(properties);
	}
	
	public void setProperty(String key, String value) {
		this.properties.put(key, value);
	}
	
	public String getProperty(String key){
		return properties.get(key);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[").append(System.lineSeparator());
		properties.forEach((k, v) -> sb.append("  ").append(k).append("=").append(v).append(System.lineSeparator()));
		sb.append("]");
		
		return sb.toString();
	}
	
	public boolean isEmpty() {
		if (properties.isEmpty()) {
			return true;
		}
		return false;
	}
	
	public PropertyModel duplicate() {
		PropertyModel pm = new PropertyModel();
		pm.setProperties(properties);
		return pm;
	}

	public void merge(PropertyModel pm) {
		properties.putAll(pm.getProperties());	
	}
	
}
