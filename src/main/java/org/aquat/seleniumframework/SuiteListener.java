package org.aquat.seleniumframework;

import org.aquat.seleniumframework.data.DataService;
import org.aquat.seleniumframework.data.PropertyModel;
import org.aquat.seleniumframework.data.PropertyService;
import org.testng.ISuite;
import org.testng.ISuiteListener;

public class SuiteListener implements ISuiteListener{
	public void onStart(ISuite suite) {
		//read all properties defined in project.properties
		DataService ds = DataService.getInstance();
		PropertyService ps = PropertyService.getInstance();
		PropertyModel globalPM = new PropertyModel();
			
		globalPM.setProperties(ds.readProjectPreperties());
		ps.configGlobal(globalPM);
		
		//read parameters defined in suite node in testng.xml
		PropertyModel suitePM = new PropertyModel();	
		suitePM.setProperties(ds.readTestngParameters(suite.getXmlSuite().getParameters()));
		ps.configXmlSuite(suitePM);
	}

	public void onFinish(ISuite suite) {
		
	}
}
