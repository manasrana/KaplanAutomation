package com.kaplan.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TopMenu {

	BasePage page;
	public WebDriver driver;
	private String syllabusLink;

	public TopMenu(BasePage basePage) {

		// initialize page details
		page = basePage;
		driver = basePage.driver;
		syllabusLink = page.props.getProperty("home_syllabusLink");
	}

	public void ClickOnSyllabusLink() {
		if (page.WaitForAnElementClickable(driver.findElement(By
				.id(syllabusLink)))) {
			driver.findElement(By.id(syllabusLink)).click();
		}
	}
}
