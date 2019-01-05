package org.aquat.seleniumframework.data;

import java.lang.reflect.Method;
import org.aquat.seleniumframework.annotations.BrowserAutoClose;
import org.aquat.seleniumframework.annotations.BrowserType;
import org.aquat.seleniumframework.yamlmodel.ProjectConfig;
import org.testng.ITestNGMethod;

/**
 * <p>
 * AnnotationHandler is used to retrieve customized annotation value for each test
 * </p>
 * 
 */
public class AnnotationHandler {
	private ITestNGMethod testNGMethod;
	private ProjectConfig config = ConfigService.getInstance().getConfigModel();
	
	public AnnotationHandler(ITestNGMethod testNGMethod) {
		this.testNGMethod = testNGMethod;
	}
	
	public void overwriteConfig() {	
		Method testMethod = testNGMethod.getConstructorOrMethod().getMethod();

		BrowserType browserType = testMethod.getAnnotation(BrowserType.class);
		if (browserType != null) {
			String type = browserType.value().toString();
			//System.out.println("[Test Property] browser.type=" + type);
			config.getBrowser().setType(type);		
		}

		BrowserAutoClose bac = testMethod.getAnnotation(BrowserAutoClose.class);
		if (bac != null) {
			boolean flag = bac.value();
			//System.out.println("[Test Property] browser.autoclose=" + flag);
			config.getBrowser().setAutoClose(flag);
		}
		
		//TODO
		//should support all project configuration overwriting and test data injection

	}
}
