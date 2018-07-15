package org.aquat.seleniumframework.data;

import java.lang.reflect.Field;
import java.util.Set;

/**
 * <p>
 * PropertyService is used to access properties through tests
 * </p>
 * 
 */
public class PropertyService {
	private static PropertyService ps;
	private PropertyModel global;
	private PropertyModel xmlSuite;
	private PropertyModel xmlTest;
	private PropertyModel testMethod;
	
	/**
	 * Constructor </br></br>
	 * Initialize properties defined in ProjectPropery.java and set default value to empty String; </br>
	 * This way, users don't have to define every entry in Project.properties file
	 */
	private PropertyService() {
		global = new PropertyModel();
		
		Field[] fields = ProjectProperty.class.getFields();
		for (Field field : fields) {
			try {				
				global.setProperty((String)field.get(ProjectProperty.class), "");
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	public static PropertyService getInstance() {
		if (ps==null) {
			ps = new PropertyService();
		}
		return ps;
	}
	
	public void configGlobal(PropertyModel pm) {
		global.merge(pm);
		xmlSuite = global.duplicate();
		xmlTest = global.duplicate();
		testMethod = global.duplicate();
		System.out.println("******** Start of Project Properties ********");
		System.out.println(global);
		System.out.println("********  End of Project Properties  ********");
		System.out.println();
	}
	
	public void configXmlSuite(PropertyModel pm) {
		xmlSuite = global.duplicate();
		if (pm.isEmpty()) {
			return;
		}
		xmlSuite.merge(pm);		
		
		System.out.println("******** Start of XML Suite Parameters ********");
		System.out.println(pm);
		System.out.println("********  End of XML Suite Parameters  ********");
		System.out.println();
	}
	
	public void configXmlTest(PropertyModel pm) {
		xmlTest = xmlSuite.duplicate();
		if (pm.isEmpty()) {
			return;
		}
		xmlTest.merge(pm);	
		
		System.out.println("******** Start of XML Test Parameters ********");
		System.out.println(pm);
		System.out.println("********  End of XML Test Parameters  ********");
		System.out.println();
	}
	
	public void configTestMethod(PropertyModel pm) {
		testMethod = xmlTest.duplicate();
		if (pm.isEmpty()) {
			return;
		}
		testMethod.merge(pm);	
		
		System.out.println("******** Start of Test Method Annotations ********");
		System.out.println(pm);
		System.out.println("********  End of Test Method Annotations  ********");
		System.out.println();
	}
	
	public String getProperty(String key) {
		return testMethod.getProperty(key);
	}
	
	public Set<String> getKeys() {
		return global.getProperties().keySet();
	}
}
