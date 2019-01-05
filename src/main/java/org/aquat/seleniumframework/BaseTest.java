package org.aquat.seleniumframework;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.aquat.seleniumframework.browser.BrowserFactory;
import org.aquat.seleniumframework.data.DataService;
import org.aquat.seleniumframework.util.SystemLogger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;

/**
 * <p>All test case classes should extend BaseTest</p>
 * 
 */
@Listeners({TestNGTestListener.class,
			TestNGSuiteListener.class})
public class BaseTest {
	public static Logger logger = SystemLogger.getLogger(BaseTest.class);
	
	private WebDriver driver;

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Launch browser and maximize window
	 */
	public void start() {
		BrowserFactory factory = new BrowserFactory();
		driver = factory.getInstance();
	}

	/**
	 * Open URL in parameter. Construct page object with given page object class.
	 * @param page
	 * @param url
	 * @return
	 */
	public <T extends BasePage> T navigateTo(Class<? extends BasePage> page, String url) {
		driver.get(url);
		return pageConstuctor(page);
	}

	/**
	 * Create page object by invoke its constructor. Class constructor should have WebDriver as parameter
	 * @param page
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private <T extends BasePage> T pageConstuctor(Class<? extends BasePage> page) {
		T t = null;
		Constructor<? extends BasePage> cons = null;

		try {
			cons = page.getConstructor(WebDriver.class);
		} catch (NoSuchMethodException e1) {
			e1.printStackTrace();
		} catch (SecurityException e1) {
			e1.printStackTrace();
		}

		try {
			t = (T) cons.newInstance(driver);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		return t;
	}


	/**
	 * 
	 * Read test data from yaml file
	 * 
	 * @param key 
	 * @return String
	 */
	public String getData(String key) {
		String name = this.getClass().getName();
		
		String value = DataService.getInstance().getTestParameters(name, key);
		logger.info("[Test Parameter] " + key + " = " + value);
		
		if (value == null) {
			throw new RuntimeException("Test Parameter: " + key + " not defined.");
		}
		
		return value;
	}

	/**
	 * get current time stamp based on format. format example: "yyyyMMddHHmmss"
	 * 
	 * @param dateFormat
	 * @return
	 */
	public String getTimeStamp(String dateFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(new Date());
	}
}
