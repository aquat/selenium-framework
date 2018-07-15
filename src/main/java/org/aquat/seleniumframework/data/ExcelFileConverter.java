package org.aquat.seleniumframework.data;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.aquat.seleniumframework.util.ExcelParser;

/**
 * <p>
 * ExcelFileConverter is used to retrieve data from Excel file
 * </p>
 * 
 */
public class ExcelFileConverter {
	private static final String SYSTEM_INFO = "System Info";
	private ExcelParser parser;
	private String fileName;
	private String dataSet;
	
	public ExcelFileConverter init(String fileName, String dataSet) {
		this.fileName = fileName;
		this.dataSet = dataSet;
		return this;
	}

	/**
	 * Retrieve project properties defined in Excel file
	 * @return LinkedHashMap<String, String>
	 */
	public LinkedHashMap<String, String> getPropertiesFromExcel() {
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		
		parser = new ExcelParser();
		parser.load(fileName);
		parser.useSheet(SYSTEM_INFO);
		
		// Get project properties defined in Excel file	
		int firstHeader = parser.getFirstColumnNumber(0);
		int lastHeader = parser.getLastColumnNumber(0);
		ArrayList<String> header = new ArrayList<String>();
		
		for (int i = firstHeader; i<lastHeader; i++) {
			header.add(parser.getCellValue(0, i));
		}
		
		int row = -1;
		int col = header.indexOf(SystemInfoTableHeader.SYSTEM);
		
		int minRow = 1;
		int maxRow = parser.getLastRowNumber();
		for (int i = minRow; i <= maxRow; i++) {
			if (parser.getCellValue(i, col).equals(dataSet)) {	
				row = i;
			}
		}
		
		for (int i = 0; i<header.size(); i++) {
			if (header.get(i).equals(SystemInfoTableHeader.URL)) {
				map.put(ProjectProperty.TEST_URL, parser.getCellValue(row, i));
			}
			else if (header.get(i).equals(SystemInfoTableHeader.USERNAME)) {
				map.put(ProjectProperty.TEST_USERNAME, parser.getCellValue(row, i));
			}
			else if (header.get(i).equals(SystemInfoTableHeader.PASSWORD)) {
				map.put(ProjectProperty.TEST_PASSWORD, parser.getCellValue(row, i));
			}
		}	
		
		return map;
	}	
	
	/**
	 * Retrieve test data from Excel based on case name and key
	 * @param caseName
	 * @param key
	 * @return String
	 */
	public String getTestDataFromExcel(String caseName, String key) {
		String sheet = PropertyService.getInstance().getProperty(ProjectProperty.TEST_DATA_SET);
		parser.useSheet(sheet);

		int firstRow = parser.getFirstRowNumber();
		int lastRow = parser.getLastRowNumber();
		
		for (int i = firstRow; i < lastRow; i++) {
			if (parser.getCellValue(i, 0).equals(caseName)) {
				while (!parser.getCellValue(i+1, 0).equals("")) {
					if (parser.getCellValue(i+1, 0).equals(key)) {
						return parser.getCellValue(i+1, 1);
					}
					i++;
				}
				break;
			}
		}
		
		return null;
	}
	
	/**
	 * The header name in 'System Info' sheet
	 *
	 */
	private class SystemInfoTableHeader {
		public static final String SYSTEM = "SYSTEM";
		public static final String URL = "URL";
		public static final String USERNAME = "USERNAME";
		public static final String PASSWORD = "PASSWORD";
		//public static final String HOSTNAME = "HOSTNAME";
		//public static final String CLIENT = "CLIENT";
	}
}
