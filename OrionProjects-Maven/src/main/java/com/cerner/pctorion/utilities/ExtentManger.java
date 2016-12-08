package com.cerner.pctorion.utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;

/**
 * @author jk048034
 *
 */
public class ExtentManger extends Settings 
{

	//@SuppressWarnings("deprecation")
	public static ExtentReports Instance(ExtentReports extent)
	{
		extent =new ExtentReports(projectFolderPath+"/Results/ExtentReport.html", true);
		extent.loadConfig(new File(projectFolderPath+"/src/com/cerner/pctorion/utilities/extent-config.xml"));
		return extent;
	}
	
	public static String CaptureScreen(WebDriver driver, String ImagesPath)
	{
		TakesScreenshot oScn = (TakesScreenshot) driver;
		File oScnShot = oScn.getScreenshotAs(OutputType.FILE);
		File oDest = new File(ImagesPath+".jpg");
		
		try 
		{
			FileUtils.copyFile(oScnShot, oDest);
		} catch (IOException e) 
		{
			System.out.println(e.getMessage());
		}

		return ImagesPath+".jpg";
	}
}
