package com.kaplan.tests;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.kaplan.page.LoginPage;
import com.kaplan.utils.TestData;

public class TestScenario1_3 extends BaseTest {
	private static final Log log = LogFactory.getLog(TestScenario1_3.class);
	String username;
	String password;
	String syllabusName;
	TestData testData;

	@Override
	@BeforeMethod(groups = { "P1" })
	public void setUp() throws IOException, IllegalAccessException {
		super.setUp();
		username = props.getProperty("student_username");
		password = props.getProperty("student_password");
	}

	@Test
	public void TestScenario1() throws IllegalAccessException {
		log.info("Test for Scenario1");
		// int TEST_CASE_ID = 1;
		// readTestDataFromExcel(TEST_CASE_ID);
		// LoginPage loginPage = openApplication();
		// loginPage.login(username, password);
		// loginPage.topMenu.ClickOnSyllabusLink();//(groups = { "functest"

	}

	@Test
	public void TestScenario2() throws IllegalAccessException {
		log.info("Test for Scenario2");
		// openApplication();
	}

	private void readTestDataFromExcel(int TEST_CASE_ID)
			throws IllegalAccessException {

		testData = new TestData(TEST_CASE_ID);
		// Get the test data from test data sheet for TC
		syllabusName = testData.SYLLABUSNAME.toString();

	}
}
