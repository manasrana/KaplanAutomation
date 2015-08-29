package com.kaplan.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

	private String userNameTextBox;
	private String passWordTextBox;
	private String loginButton;

	public LoginPage(WebDriver driver) {
		super(driver);
		// Wait for title of the page
		if (!WaitForPageTitle("kaplan Bar Review")) {
			throw new IllegalStateException(
					"This is not Login Page, current page" + "is: "
							+ driver.getCurrentUrl());
		}

		userNameTextBox = props.getProperty("login_userNameTextBox");
		passWordTextBox = props.getProperty("login_passWordTextBox");
		loginButton = props.getProperty("login_loginButton");
	}

	/***
	 * Login to the Application
	 * 
	 * @param uname
	 * @param password
	 */
	public void login(String uname, String password) {
		WebElement element = driver.findElement(By.id(userNameTextBox));

		driver.findElement(By.id(userNameTextBox)).sendKeys(uname);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);",
				element, "color: yellow; border: 2px solid yellow;");
		driver.findElement(By.id(passWordTextBox)).sendKeys(password);
		driver.findElement(By.id(loginButton)).click();
	}

}
