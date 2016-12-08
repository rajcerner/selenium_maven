package com.cerner.pctorion.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Read Test settings from framework.propeties files
 *
 */
public class TestSettings {
	Properties properties;

	public TestSettings(){
		loadProperties();
	}

	/**
	 * Get property value 
	 * @param key property key
	 * @return value of the property
	 */
	public String getSettings(String key){
		return properties.getProperty(key);
	}


	/**
	 * Get the browser name in which tests needs to be run
	 */
	public String getBrowserConfigurations(){		
		return properties.getProperty("browser");		
	}

	/**
	 *	Get the URL of the application under test 
	 */
	public String getApplicationURL(){
		return getSettings("url");
	}



	public String getEnvironment() {
		return getSettings("Domain");
	}

	/**
	 *	Get the TestSuite tab name under test 
	 */
	public String getTestSheetName() {
		return getSettings("Sheet");
	}
	
	
	/**
	 * Load properties from framework.properties file
	 */
	private void loadProperties() {
		properties= new Properties();
		try {
			String relativePath=TestUtils.getRelativePath();
			properties.load(new FileInputStream(relativePath + File.separator + "framework.properties"));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public String getTestDataSheetFormat() {
		return properties.getProperty("TestDataSheetFormat");
	}

}
