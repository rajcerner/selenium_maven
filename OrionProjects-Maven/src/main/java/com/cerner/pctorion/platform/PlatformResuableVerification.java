/**
 * Class for platform verification reusable functions 
 * @author jk048034
 * @date : 02-Dec-2016
 */
package com.cerner.pctorion.platform;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.cerner.pctorion.utilities.Page;
import com.relevantcodes.extentreports.ExtentTest;

/**
 * @author jk048034
 *@date : 02-Dec-2016
 */
public class PlatformResuableVerification extends Page{
	public PlatformResuableVerification(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Author: JK048034
	 * Function: This method verifies patient data in patient search screen
	 */	
	public PlatformResuableVerification verifyPatientDetails(String patientName , String attributeVerify,ExtentTest extentTest,String formatter, String projectFolderPath)
	{
		try{

			List<WebElement> allPatients = driver.findElements(By.cssSelector("div.ion-patient-search-result-detail > h5"));
			ArrayList<String> allValues = new ArrayList<String>();

			for (WebElement patient : allPatients) {
				String actualName = patient.getText();
				allValues.add(actualName);
				continue;
			}
			if(allValues.contains(attributeVerify)==true){
				passTestCase( extentTest, attributeVerify, formatter, projectFolderPath);
			}else{
				System.out.println("step 5");
				failTestCase( extentTest, attributeVerify, formatter, projectFolderPath);
			} 	
		}
		catch(Exception e){
			System.out.println(e.getMessage());

		}
		return this;
	}


	/**
	 * @author jk048034
	 * Method to verify visible text in patient search field
	 * @return String
	 */
	//@SuppressWarnings("unused")
	public void VerifySearchFieldValue(String searchFieldValue,ExtentTest extentTest,String formatter, String projectFolderPath){

		if(searchFieldValue.equals("")){
			passTestCase( extentTest, searchFieldValue, formatter, projectFolderPath);
		}else{
			failTestCase( extentTest, searchFieldValue, formatter, projectFolderPath);	
		}
	}


	/**
	 * @author jk048034
	 * Method to compare two values
	 * @return String
	 */
	public void ValidateTwoStrings(String Actualvalue,String ExpectedValue,ExtentTest extentTest,String formatter, String projectFolderPath){
		if(Actualvalue ==ExpectedValue || Actualvalue.equals(ExpectedValue)){
			passTestCase( extentTest, Actualvalue, formatter, projectFolderPath);
		}else{
			failTestCase( extentTest, Actualvalue, formatter, projectFolderPath);
		}
	}
}
