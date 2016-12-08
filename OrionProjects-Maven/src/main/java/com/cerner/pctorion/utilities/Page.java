package com.cerner.pctorion.utilities;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;



/**
 * @author jk048034
 *
 */
public abstract class Page {
	
	 public WebDriver driver;
	 
		public Page(WebDriver driver)
		{
			this.driver = driver;
			PageFactory.initElements(this.driver, this);
		}
		
		public void passTestCase(ExtentTest extentTest,String visibleText,String formatter, String projectFolderPath){
			String path=projectFolderPath+"/Results/";
			extentTest.log(LogStatus.PASS, visibleText+" text found in webpage");
			extentTest.log(LogStatus.INFO, extentTest.addScreenCapture(ExtentManger.CaptureScreen(driver,path+"Results"+formatter)));
			extentTest.addScreenCapture(path);
		}
		
		public void failTestCase(ExtentTest extentTest,String visibleText,String formatter, String projectFolderPath){
			String path=projectFolderPath+"/Results/";
			extentTest.log(LogStatus.FAIL, visibleText+" text found in webpage");
			extentTest.log(LogStatus.INFO, extentTest.addScreenCapture(ExtentManger.CaptureScreen(driver, path+"Results"+formatter)));
			extentTest.addScreenCapture(path);
		}
		
		public void logWarning(ExtentTest extentTest,String Warningmessage,String formatter, String projectFolderPath){
			String path=projectFolderPath+"/Results/";
			
			extentTest.log(LogStatus.WARNING, Warningmessage) ;
			extentTest.log(LogStatus.INFO, extentTest.addScreenCapture(ExtentManger.CaptureScreen(driver, path+"Results"+formatter)));
			extentTest.addScreenCapture(path);
		}
}
