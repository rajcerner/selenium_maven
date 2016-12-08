package com.cerner.pctorion.platform;


import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.cerner.pctorion.utilities.Page;


public class PatientSearchPage  extends Page{  


	public PatientSearchPage(WebDriver driver) {
		super(driver);
	}
	
	PlatformResuableVerification platformResuableVerification = new PlatformResuableVerification(driver);

	@FindBy(id="ion-patient-search-text-box")
	public static WebElement patSrcTxtBx;

	@FindBy(xpath=".//*[@id='ion-patient-search-form']/fieldset/button")
	public static WebElement xBtn;

	@FindBy(xpath=".//*[@data-patient-id='1660009']/a/div[2]/h5/strong")
	public WebElement fullNameLbl;

	@FindBy(xpath=".//*[@data-patient-id='1660009']/a/div[2]/h6[1]")
	public static WebElement agdLbl;

	@FindBy(xpath=".//*[@data-patient-id='1660009']/a/div[2]/h6[2]")
	public static WebElement ssnLbl;

	@FindBy(xpath=".//*[@data-patient-id='1660009']/a/div[2]/div[1]/h6")
	public static WebElement pcpLbl;

	@FindBy(xpath=".//*[@data-patient-id='1660009']/a/div[1]/div[2]/h6/small")
	public static WebElement mbNoLbl;

	@FindBy(xpath=".//*[@data-patient-id='1660009']/a/div[1]/div[1]/h6/small")
	WebElement hmNoLbl;

	@FindBy(css=".demographics-row>h1")
	WebElement patientNameInDemoBanner;

	@FindBy(css =".btn.pull-left.ion-encounters-back-btn-link.icon-arrow-left")
	WebElement backbutton;

	public PatientSearchPage enterPatientsearchString(String patientsearchstring) throws InterruptedException {
		patSrcTxtBx.sendKeys(patientsearchstring);
		return this;
	}

	public void clearTestArea() 
	{
		patSrcTxtBx.clear();
	}	

	public void clickXButton() 
	{
		try{
			xBtn.click();
		}
		catch(ElementNotVisibleException e) {
			//platformResuableVerification.logWarning(null, null, null, null);
			//test.log(LogStatus.WARNING, "X button does not exist or no text entered into search area") ;
		} 
	}

	/**
	 * This method selects patient
	 * @author RV042687
	 */
	public void selectPatient(String patientFullname) 
	{
		/*
		 * cssSelector: Points to all the PatientName WebElements
		 */
		List<WebElement> allPatientElements = driver.findElements(By.cssSelector(".ion-patient-search-result-detail > h5"));
		WebElement myPatientElement = null;
		for (WebElement element: allPatientElements) { 
			if (element.getText().equals(patientFullname))
			{
				element.click();
				myPatientElement = element; //element.click();   ->this also works
				//myPatientElement.click();
				break;
			}
		}
	}

	/**
	 * @author jk048034
	 * Method to retrieve patient search field value
	 * @return String
	 */
	public String getPatientSearchFieldValue(){

		String PatientSearchFieldValue=patSrcTxtBx.getText();
		return PatientSearchFieldValue;
	}


	/**
	 * @author jk048034
	 * Method to retrieve patient name in  demographic banner
	 * @return String
	 */
	public String getPatientNameInDemographicBanner(){

		System.out.println("inisde demo");
		String PatientNameInDemographicBanner=patientNameInDemoBanner.getText();
		return PatientNameInDemographicBanner;
	}


	/**
	 * @author jk048034
	 * Method to retrieve patient name in  demographic banner
	 * @return String
	 */
	public void clickbackButtonToPatientSearch(){
		backbutton.click();
	}

}