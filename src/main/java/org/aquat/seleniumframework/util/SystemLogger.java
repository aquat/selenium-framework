package org.aquat.seleniumframework.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * 
 * <p>SystemLogger class controls log output behavior. 
 * Use configuration file of &ltuser.dir&gt/log4j.properties
 * </p>
 * 
 */
public final class SystemLogger {
	private static final String LOG4J_FILE = "log4j.properties";
	private static String log4jPropertyPath = System.getProperty("user.dir") + File.separator + LOG4J_FILE;
	private static Logger logger;

	static {	
		Properties props = new Properties();
		try {
			InputStream is = new FileInputStream(log4jPropertyPath);
			props.load(is);
		} catch (IOException e) {
			System.err.println("Fail to load log4j.properties");
		}
		PropertyConfigurator.configure(props);
	}

	/**
	 * Create a singleton Logger object and return.
	 * @param clazz name of the class.
	 * @return Logger object.
	 */
	public static Logger getLogger(final Class<?> clazz) {
		logger = Logger.getLogger(clazz);
		return logger;
	}
}

