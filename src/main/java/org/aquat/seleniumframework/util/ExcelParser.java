package org.aquat.seleniumframework.util;

import java.io.File;
import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelParser {
	private XSSFSheet currentSheet;
	private XSSFWorkbook excelFile;
	
	public ExcelParser load(String fileName) {	
		try {
			excelFile = new XSSFWorkbook(new File(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		}
		
		return this;
	}
	
	public ExcelParser useSheet(String sheet) {
		currentSheet = excelFile.getSheet(sheet);
		return this;
	}
	
	/**
	 * Get cell value
	 * @param row
	 * @param column
	 * @return
	 */
	public String getCellValue(int row, int column) {
		//workaround for row that is not defined; return empty string instead.
		XSSFRow rowx = currentSheet.getRow(row);
		if (rowx == null) {
			return "";
		}
		
		XSSFCell cell = currentSheet.getRow(row).getCell(column);
		
		if (cell.getCellType()==Cell.CELL_TYPE_BLANK) {
			return "";
		}
		if (cell.getCellType()==Cell.CELL_TYPE_NUMERIC) {
			double d = cell.getNumericCellValue();
			if (d % 1.0 == 0) {
				return Integer.toString((int) d);
			}
			return Double.toString(d);
		}
		if (cell.getCellType()==Cell.CELL_TYPE_STRING) {
			return cell.getStringCellValue();
		}
		
		return "";
	}
	
	public int getFirstRowNumber() {
		return currentSheet.getFirstRowNum();
	}
	
	public int getLastRowNumber() {
		return currentSheet.getLastRowNum();
	}
	
	public int getFirstColumnNumber(int row) {
		return currentSheet.getRow(row).getFirstCellNum();
	}
	
	public int getLastColumnNumber(int row) {
		return currentSheet.getRow(row).getLastCellNum();
	}
}
