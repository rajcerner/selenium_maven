package com.cerner.pctorion.utilities;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Datatable class for fetching value from the excel sheet
 * @author jk048034
 */
public class DataTable {
	protected Hashtable<String, String> testData;
	private String testName;
	DataFormatter formatter = new DataFormatter();

	public DataTable(String testName) {
		this.testName=testName;
		loadTestData();

	}

	private void loadTestData() {
		Workbook workbook = getWorkBook(testName);	
		Sheet dataSheet = getSheet(workbook);
		ArrayList<String> columNames= getColumnNames(dataSheet);
		Row testDataRow= getTestDataRowForTestCase(dataSheet);
		if(testDataRow==null){
			throw new RuntimeException("Unable to find testdata row for " + testName);
		}
		testData=prepareTestDataRowHashTable(columNames,testDataRow);
	}


	/**
	 * Get value from the data sheet
	 * @param key column name in the data sheet
	 * @return 
	 */
	public String getValue(String key){
		return testData.get(key);
	}

	private Sheet getSheet(Workbook workbook) {
		TestSettings testSettings= new TestSettings();
		Sheet sheet = workbook.getSheet(testSettings.getTestSheetName());
		return sheet;
	}

	private Workbook getWorkBook(String testName) {
		TestSettings testSetting= new TestSettings();

		FileInputStream fileInputStream=null;
		Workbook workbook = null;
		try {

			if (IsTestDataSheetIn2007Format(testSetting.getTestDataSheetFormat())) {
				String workBookPath=TestUtils.getRelativePath() + File.separator + FrameworkConstants.DATA_FOLDER + File.separator + testSetting.getEnvironment()+ File.separator +FrameworkConstants.TEST_DATA_FILE_NAME+FrameworkConstants.FileXLSX; 
				fileInputStream = new FileInputStream(workBookPath);
				workbook=new XSSFWorkbook(fileInputStream);
			} else {
				String workBookPath=TestUtils.getRelativePath() + File.separator + FrameworkConstants.DATA_FOLDER + File.separator + testSetting.getEnvironment()+ File.separator +FrameworkConstants.TEST_DATA_FILE_NAME+FrameworkConstants.FileXLS; 
				fileInputStream = new FileInputStream(workBookPath);
				workbook= new HSSFWorkbook(fileInputStream);
			}			

			fileInputStream.close();
			return workbook;
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	private boolean IsTestDataSheetIn2007Format(String currentTestDataSheetFormat) {
		if (currentTestDataSheetFormat.equals(FrameworkConstants.FileXLSX)) {
			return true;	
		}
		return false;
	}

	private Hashtable<String, String> prepareTestDataRowHashTable(ArrayList<String> columNames, Row testDataRow) 
	{
		Hashtable<String, String> testDataRowHashTable= new Hashtable<String, String>();		
		for (Cell cell : testDataRow) {
			String columnName=columNames.get(cell.getColumnIndex());
			String columnValue=formatter.formatCellValue(cell);
			testDataRowHashTable.put(columnName, columnValue);
		}
		return testDataRowHashTable;
	}

	private ArrayList<String> getColumnNames(Sheet testDataSheet) {
		ArrayList<String> columnNameList= new ArrayList<String>();
		Row row = testDataSheet.getRow(0);
		for (Cell cell : row) {
			columnNameList.add(cell.getStringCellValue());
		}
		return columnNameList;
	}


	private Row getTestDataRowForTestCase(Sheet testDataSheet) {
		for (Row row : testDataSheet) {
			if (IsRequiredTestCaseRow(row,this.testName)) {
				return row;
			} 
		}		
		return null;
	}

	private boolean IsRequiredTestCaseRow(Row row, String testCaseName) {
		Cell testCaseIdCell= row.getCell(0);
		String testCaseId=testCaseIdCell.getStringCellValue();
		if (testCaseId.equals(testCaseName)) {
			return true;
		}
		return false;
	}
}
