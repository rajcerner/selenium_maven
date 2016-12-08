package com.cerner.pctorion.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/**
 * @author jk048034
 *
 */
public class TestUtils {

	/**
	 * Get Current time stamp
	 * @return
	 */
	public static String getTimeStamp() {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy_MMM_dd_HH_mm_ss");
		return format.format(date);
	}

	/**
	 * Get relative path of the framework
	 * @return
	 */
	public static String getRelativePath() {
		String relativePath = new File(System.getProperty("user.dir"))
				.getAbsolutePath();
		if (relativePath.endsWith("bin")) {
			relativePath = new File(System.getProperty("user.dir")).getParent();
		}
		return relativePath;
	}


	/**
	 * Get relative path of the framework
	 * @param cellvalue, numberofvalues
	 * @return multipleCellValues
	 */
	public String[] getMultipleCellValues(String cellValue, int numberofvalues){
		System.out.println("cellValue "+cellValue);

		String[] multipleCellValues = cellValue.split("\\;");

		if(multipleCellValues.length!=numberofvalues){

			System.out.println(cellValue +" have incorrect value");
			
		}else{
			for(int i=0; i<numberofvalues; i++){

				System.out.println(multipleCellValues[i]);
			}
		}
		return multipleCellValues;
	}
	
	
	

	public void logInfo(ExtentTest extentTest,String infomessage){
		extentTest.log(LogStatus.INFO, infomessage);
	}
}
