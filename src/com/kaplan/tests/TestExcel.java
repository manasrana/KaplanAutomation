package com.kaplan.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.kaplan.utils.TestData;

public class TestExcel {

	String syllabusName;
	TestData testData;

	@Test
	public void Test1() throws IllegalAccessException {
		int TEST_CASE_ID = 1;
		readTestDataFromExcel(TEST_CASE_ID);
		System.out.println(syllabusName);

	}

	private void readTestDataFromExcel(int TEST_CASE_ID)
			throws IllegalAccessException {

		testData = new TestData(TEST_CASE_ID);
		// Get the test data from test data sheet for TC
		syllabusName = testData.SYLLABUSNAME.toString();
		Assert.assertEquals(true, true);
	}
}
