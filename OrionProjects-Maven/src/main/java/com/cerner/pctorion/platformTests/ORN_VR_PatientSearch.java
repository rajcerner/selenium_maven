/**
 * ORN_VR_PatientSearch test case
 * @author jk048034
 * @date : 02Dec2016
 */

package com.cerner.pctorion.platformTests;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cerner.pctorion.platform.LandingPage;
import com.cerner.pctorion.platform.LoginPage;
import com.cerner.pctorion.platform.PatientSearchPage;
import com.cerner.pctorion.platform.PlatformResuableVerification;
import com.cerner.pctorion.utilities.DataTable;
import com.cerner.pctorion.utilities.Settings;
import com.cerner.pctorion.utilities.TestUtils;


public class ORN_VR_PatientSearch extends Settings {

	@Test
	public void VRPatientSearch() throws InterruptedException{
		String[] multipleCellValues = null;
		int numberofvalues=3;
		
		LandingPage landingpage = new LandingPage(browser);
		LoginPage loginpage= new LoginPage(browser);
		PatientSearchPage patientsearchpage= new PatientSearchPage(browser);
		PlatformResuableVerification platformresuableverification = new PlatformResuableVerification(browser);
		TestUtils testutils=new TestUtils();
		
		extentTest = extent.startTest("VRPatientSearch", "Verify Patient search");

		DataTable datatable=new DataTable("ORN_VR_PatientSearch1");		//ORN_VR_PatientSearch

		String username = datatable.getValue("Username");				//jw027642
		System.out.println("username "+username);

		//checking if cell value have delimiter "," with multiple values
		if(username.contains(";")){
			multipleCellValues=testutils.getMultipleCellValues(username, numberofvalues);
			System.out.println("multipleCellValues "+multipleCellValues);
		}else{
			System.out.println(username +" have incorrect value");
			//multipleCellValues[0]=username;
		}

		String password = datatable.getValue("Password");				//asdf
		String patientFullname = datatable.getValue("Fullname");		//"DODDS, BRIAN";
		String mrn=datatable.getValue("MRN");		
		String fin=datatable.getValue("FIN");		
		//String url=datatable.getValue("URL");


		 //Step 1	
		 //Open Test Harness (Patient Search) 
		
		browser.get("https://deveng.test.devcernerpowerchart.com/search/patients");
		(new WebDriverWait(browser, 30)).until(ExpectedConditions.elementToBeClickable(By.linkText("Login")));
		
		
		
		testutils.logInfo(extentTest, "Step 1");
		landingpage.initialLoginButton();
		loginpage.enterUsernamePassword(username, password);//multipleCellValues[0]
		loginpage.clickMPlusLoginButton();

		//Step 2 -- Testing X button
		testutils.logInfo(extentTest, "Step 2");
		patientsearchpage.enterPatientsearchString("abc");
		patientsearchpage.clickXButton();
		String PatientSearchFieldValue=patientsearchpage.getPatientSearchFieldValue();
		System.out.println("PatientSearchFieldValue "+PatientSearchFieldValue);
		platformresuableverification.VerifySearchFieldValue(PatientSearchFieldValue,extentTest, TestUtils.getTimeStamp(), projectFolderPath);
		
		
		//Step 3-- Search by Name
		testutils.logInfo(extentTest, "Step 3");
		patientsearchpage.enterPatientsearchString(patientFullname);
		Thread.sleep(5000);
		platformresuableverification.verifyPatientDetails(patientFullname, patientFullname, extentTest, TestUtils.getTimeStamp(), projectFolderPath);
		patientsearchpage.clickXButton();
		
		//Step 4-- Search by FIN
		testutils.logInfo(extentTest, "Step 4");
		patientsearchpage.enterPatientsearchString(fin);
		Thread.sleep(5000);
		platformresuableverification.verifyPatientDetails(patientFullname, patientFullname, extentTest, TestUtils.getTimeStamp(), projectFolderPath);
		patientsearchpage.clickXButton();
		
		//Step 5-- Search by MRN
		testutils.logInfo(extentTest, "Step 5");
		patientsearchpage.enterPatientsearchString(mrn);
		Thread.sleep(5000);
		platformresuableverification.verifyPatientDetails(patientFullname, patientFullname, extentTest, TestUtils.getTimeStamp(), projectFolderPath);
		patientsearchpage.clickXButton();
		
		//Step 6 -- Select last patient searched for - validate encounter using name in demographics bar 
		testutils.logInfo(extentTest, "Step 6");
		patientsearchpage.enterPatientsearchString(patientFullname);
		patientsearchpage.selectPatient(patientFullname);
		Thread.sleep(5000);
		String patientNameInDemoBanner=patientsearchpage.getPatientNameInDemographicBanner();
		System.out.println("patientNameInDemoBanner "+patientNameInDemoBanner);
		platformresuableverification.ValidateTwoStrings(patientFullname, patientNameInDemoBanner, extentTest, TestUtils.getTimeStamp(),projectFolderPath);
		
		//Step 7 - Navigate back to patient search
		testutils.logInfo(extentTest, "Step 7");
		patientsearchpage.clickbackButtonToPatientSearch();
		
		
		//Step 8 - Search for name to return a lot of results -- testing scrolling not working currently
		
		
		
	}

}



