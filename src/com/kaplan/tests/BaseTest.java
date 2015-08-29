package com.kaplan.tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.simpleservicetoolkit.utilities.PropertyManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.kaplan.page.LoginPage;
import com.kaplan.utils.SeleniumUtilities;

public class BaseTest {

	protected WebDriver driver;
	protected PropertyManager props;
	private SeleniumUtilities seleniumUtilities;

	@BeforeMethod
	public void setUp() throws IOException, IllegalAccessException {
		props = PropertyManager.getPropertyManager();
		driver = new FirefoxDriver();
		// driver=new HtmlUnitDriver(true);
		driver.get("http://kbr.qa.kaplan.com/Student/Home");
		// seleniumUtilities = new SeleniumUtilities();
		driver.manage().window().maximize();

	}

	/**
	 * Open the Application
	 * 
	 * @return
	 */
	public LoginPage openApplication() {
		// driver = seleniumUtilities.driverSetUp();
		return new LoginPage(driver);
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}

	}
}
