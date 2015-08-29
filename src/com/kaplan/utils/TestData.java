package com.kaplan.utils;

import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.Map;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

public class TestData {

	int testCaseId;

	@Setter
	@Getter(AccessLevel.PACKAGE)
	public String SYLLABUSNAME;


	public TestData(int testCaseId) throws IllegalAccessException {
		this.testCaseId = testCaseId;
		initData();
	}

	/**
	 * Read data from excel and initialize to the variable
	 * 
	 * @throws IllegalAccessException
	 */
	public void initData() throws IllegalAccessException {

		Map<Integer, Map<String, String>> result = null;
		try {
			result = ExcelReader.getDataForTestCase(testCaseId);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Map<String, String> resultMap = result.get(testCaseId);
		Field[] fields = TestData.class.getDeclaredFields();
		for (Field field : fields) {
			setData(field, resultMap);
		}

	}

	/**
	 * Set data to variable
	 * 
	 * @param field
	 * @param resultMap
	 * @throws IllegalAccessException
	 */
	private void setData(Field field, Map<String, String> resultMap)
			throws IllegalAccessException {

		if (resultMap.containsKey(field.getName())
				&& !resultMap.get(field.getName()).isEmpty())
			try {
				field.set(this, resultMap.get(field.getName()));
			} catch (IllegalArgumentException e) {
				throw new IllegalArgumentException(
						"Provided Arguement is Invalid");
			} catch (IllegalAccessException e1) {
				throw new IllegalAccessException(
						"Provided Arguement is not accessible");
			}
	}

}

