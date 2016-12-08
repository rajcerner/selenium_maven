
package com.cerner.pctorion.platform;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.cerner.pctorion.utilities.Page;



/**
 * @author jk048034
 *
 */
public class LandingPage extends Page {  

	
	public LandingPage(WebDriver driver) {
		super(driver);
	}	


	//Locate the "Login" button
	//@FindBy(linkText = "Login")
	@FindBy(how = How.LINK_TEXT, using ="Login")
	WebElement loginBtn;


	//Method to click on the "Login" button
	public LandingPage initialLoginButton() {
		
		loginBtn.click();
		return this;

	}

}
