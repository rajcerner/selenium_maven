package com.cerner.pctorion.platform;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cerner.pctorion.utilities.Page;

/**
 * @author jk048034
 *
 */
public class LoginPage extends Page{  


	public LoginPage(WebDriver driver) {
		super(driver);
	}

	//Locate the "Username" field
	@FindBy(id = "j_username")
	WebElement usrNameTxtBx;

	//Locate the "Password" field
	@FindBy(id = "j_password")
	WebElement passwdTxtBx;

	//Locate the "Log In" button
	@FindBy(id = "loginButton")
	WebElement lgnBtn;



	//Method to enter username and password in the corresponding test box
	public LoginPage enterUsernamePassword(String username, String password) {
		(new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOf(usrNameTxtBx));
		
		usrNameTxtBx.sendKeys(username);
		passwdTxtBx.sendKeys(password);
		return this;		
	}

	//Method to click on the "Log In" button
	public LoginPage clickMPlusLoginButton() {
		lgnBtn.click();
		return this;

	}

}
