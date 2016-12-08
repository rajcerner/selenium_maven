package com.cerner.pctorion.utilities;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;


public class GetBrowserDriver{
	
	public WebDriver getDriver(String browserName) {
		System.out.println("b "+browserName);
		WebDriver driver = null;
		
		try {
			System.out.println("c ");
			
			if (browserName.equalsIgnoreCase("firefox")) {

				/*DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
				desiredCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);*/
				
				//FirefoxProfile fprofile = new FirefoxProfile();
				
				/*fprofile.setPreference("browser.download.dir", ReportPath.getInstance().getReportPath());
				fprofile.setPreference("browser.download.folderList", 2); 
				
				fprofile.setPreference("browser.helperApps.neverAsk.saveToDisk", 
						"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;"
				 + "application/pdf;" 
				+ "application/vnd.openxmlformats-officedocument.wordprocessingml.document;" 
				+ "text/plain;" 
				+ "text/csv"); 
				fprofile.setPreference( "browser.download.manager.showWhenStarting", false ); 
				fprofile.setPreference( "pdfjs.disabled", true ); */
				
				//driver = new FirefoxDriver(fprofile);	
				driver = new FirefoxDriver();
				System.out.println("mozilla");
			}else{
				System.out.println("fire else");
			}
			if(browserName.equalsIgnoreCase("chrome")){
				System.setProperty("webdriver.chrome.driver", TestUtils.getRelativePath()+"/ExternalLibraries/BrowserSpecificDrivers/chromedriver.exe");
				
				//DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
				//desiredCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				
				//driver=new ChromeDriver(desiredCapabilities);
				driver=new ChromeDriver();
			}
			if(browserName.equalsIgnoreCase("iexplore") || browserName.equalsIgnoreCase("ie")){
				System.setProperty("webdriver.ie.driver",
						TestUtils.getRelativePath()+"/ExternalLibraries/BrowserSpecificDrivers/IEDriverServer.exe");
				//				ProxyServer proxyServer = new ProxyServer(2222);
				//				try {
				//					proxyServer.start();
				//					Proxy seleniumProxy = proxyServer.seleniumProxy();



				/*DesiredCapabilities desiredCapabilities = DesiredCapabilities.internetExplorer();
				desiredCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);
				desiredCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				desiredCapabilities.setCapability("ignoreZoomSetting", true);
				//				desiredCapabilities.setCapability(CapabilityType.PROXY, seleniumProxy);
				 */
				
				//driver=new InternetExplorerDriver(desiredCapabilities);
				driver=new InternetExplorerDriver();
				
				//				} catch (Exception e) {
				//					// 

				//					e.printStackTrace();
				//				}			

			}
			if(browserName.equalsIgnoreCase("safari")){

				SafariOptions safariOptions = new SafariOptions();
				safariOptions.setUseCleanSession(true);
				driver=new SafariDriver(safariOptions);

			}
			
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			
		} catch (Exception exception) {
			System.out.println(exception);
			//report.reportException("getDriver", new RuntimeException(exception.getMessage()));
		} 	
		return driver;
	}
}
