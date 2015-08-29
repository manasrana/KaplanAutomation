package com.kaplan.page;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.simpleservicetoolkit.utilities.PropertyManager;

public class BasePage {

	public WebDriver driver;
	public TopMenu topMenu;
	protected PropertyManager props;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		props = PropertyManager.getPropertyManager();
		topMenu = new TopMenu(this);
	}

	/***
	 * Method to Get Driver
	 * 
	 * @return driver object
	 */
	public WebDriver getDriver() {
		return driver;
	}

	/***
	 * Wait for page title
	 * 
	 * @param titleOfPage
	 * @return
	 */
	public boolean WaitForPageTitle(String titleOfPage) {
		try {
			(new WebDriverWait(driver, 10))
					.until(new ExpectedCondition<Boolean>() {
						public Boolean apply(WebDriver d) {
							return d.getTitle().toLowerCase()
									.startsWith(titleOfPage.toLowerCase());
						}
					});
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	/***
	 * Wait for an Element Present
	 * 
	 * @param elementLocator
	 * @return
	 */
	public boolean WaitForAnElementLocated(By elementLocator) {
		try {
			(new WebDriverWait(driver, 10)).until(ExpectedConditions
					.presenceOfElementLocated(elementLocator));
			return true;
		} catch (TimeoutException time_out_exception) {
			return false;
		}
	}

	/***
	 * Wait for an element to be clickable
	 * 
	 * @param element
	 * @return
	 */
	public boolean WaitForAnElementClickable(WebElement element) {
		try {
			(new WebDriverWait(driver, 10)).until(ExpectedConditions
					.elementToBeClickable(element));
			return true;
		} catch (TimeoutException time_out_exception) {
			return false;
		}
	}

	/***
	 * Verify for text present
	 * 
	 * @param element
	 * @param toBeVerifyText
	 * @return
	 */
	public boolean VerifyForTextPresent(WebElement element,
			String toBeVerifyText) {
		try {
			(new WebDriverWait(driver, 10)).until(ExpectedConditions
					.textToBePresentInElement(element, toBeVerifyText));

			return true;
		} catch (TimeoutException time_out_exception) {
			return false;
		}
	}
}
