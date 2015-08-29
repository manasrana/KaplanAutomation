package com.kaplan.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.simpleservicetoolkit.utilities.PropertyManager;

public class SeleniumUtilities {

	String DEFAULT_TIMEOUT;
	String BASE_URL;
	PropertyManager props;
	String browser;
	String downloadFolder;

	public SeleniumUtilities() {
		props = PropertyManager.getPropertyManager();
		BASE_URL = props.getProperty("baseURL");
		DEFAULT_TIMEOUT = props.getProperty("defaultTimeout");
		browser = props.getProperty("browser");
		downloadFolder = props.getProperty("downloadFolder");
	}

	public WebDriver driverSetUp() {
		WebDriver driver = null;

		if (browser.contains("firefox")) {
			driver = new FirefoxDriver(getProfile());
		}
		driver.switchTo();
		((JavascriptExecutor) driver).executeScript("window.focus();");
		driver.manage().window().maximize();

		try {
			driver.get(BASE_URL);
			sleepTime(2);

		} catch (Exception ex) {

		}
		return driver;
	}

	private FirefoxProfile getProfile() {
		String workingdirectory = System.getProperty("user.dir");
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference(
				"browser.helperApps.neverAsk.openFile",
				"text/csv, application/pdf, application/x-msexcel,application/excel,application/x-excel,application/excel,application/x-excel,application/excel,application/vnd.ms-excel,application/x-excel,application/x-msexcel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml,application/excel");
		profile.setPreference(
				"browser.helperApps.neverAsk.saveToDisk",
				"text/csv, application/pdf, application/x-msexcel,application/excel,application/x-excel,application/excel,application/x-excel,application/excel, application/vnd.ms- excel,application/x-excel,application/x-msexcel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml,application/excel,text/x-c");
		profile.setPreference("browser.helperApps.alwaysAsk.force", false);
		profile.setPreference("browser.download.manager.showWhenStarting",
				false);
		profile.setPreference("browser.download.folderList", 2);
		profile.setPreference("browser.download.dir", workingdirectory
				+ downloadFolder);
		return profile;
	}

	public void sleepTime(int timeInSeconds) {
		try {
			Thread.sleep(timeInSeconds * 1000);
		} catch (InterruptedException e) {
			System.out.println("awakened prematurely");
		}
	}
}
