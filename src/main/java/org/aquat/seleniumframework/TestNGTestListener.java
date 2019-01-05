package org.aquat.seleniumframework;

import java.util.Arrays;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import org.aquat.seleniumframework.data.AnnotationHandler;
import org.aquat.seleniumframework.data.ConfigService;
import org.aquat.seleniumframework.yamlmodel.ProjectConfig;

public class TestNGTestListener implements ITestListener {
	private ProjectConfig config = ConfigService.getInstance().getConfigModel();
	
	public void onStart(ITestContext context) {	

	}

	public void onFinish(ITestContext context) {
		
	}

	public void onTestStart(ITestResult result) {
		String testName = "TEST CASE: " + result.getInstanceName() + "." + result.getMethod().getMethodName();		
		char[] chars = new char[testName.length() + 12];
		Arrays.fill(chars, '*');
		String str = new String(chars);
		System.out.println(str);
		System.out.println("***** " + testName + " *****");
		System.out.println(str);
		System.out.println();
		
		AnnotationHandler anno = new AnnotationHandler(result.getMethod());
		anno.overwriteConfig();
		
		// TODO 
		// create test report entry
	}

	public void onTestSuccess(ITestResult result) {
		cleanUp(result.getMethod());
	}

	public void onTestFailure(ITestResult result) {
		cleanUp(result.getMethod());
	}

	public void onTestSkipped(ITestResult result) {
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	private void cleanUp(ITestNGMethod testNGMethod) {
		// Close browser automatically
		if(config.getBrowser().isAutoClose()) {
			BaseTest test = (BaseTest) testNGMethod.getInstance();
			if (test.getDriver() != null) {
				test.getDriver().quit();
			}
		}
	}
}
