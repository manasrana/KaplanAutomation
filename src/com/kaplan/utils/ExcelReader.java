package com.kaplan.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	private static Logger log = Logger.getLogger(ExcelReader.class);

	/**
	 * @param testCaseIds
	 *            - Test case ids
	 * @return data map
	 * @throws FileNotFoundException
	 */
	public static Map<Integer, Map<String, String>> getDataForTestCase(
			int... testCaseIds) throws FileNotFoundException {
		InputStream inputStream = null;
		XSSFWorkbook workBook = null;

		String xlsPath = new File(
				"src\\testresources\\testdata\\TestData_Sheet.xlsx")
				.getAbsolutePath();
		System.out.println(xlsPath);
		try {
			inputStream = new FileInputStream(xlsPath);
			workBook = new XSSFWorkbook(inputStream);
		} catch (FileNotFoundException e) {
			log.error("File not found in the specified path.");
			throw new FileNotFoundException();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Map<Integer, Map<String, String>> testCaseDataMap = new HashMap<Integer, Map<String, String>>();

		for (int i = 0; i < testCaseIds.length; i++) {
			int key = testCaseIds[i];
			Map<String, String> dataMap = readFromExcel(workBook, inputStream,
					key);
			testCaseDataMap.put(key, dataMap);
		}
		return testCaseDataMap;
	}

	/**
	 * @param workBook
	 * @param inputStream
	 * @param testCaseId
	 * @return data map
	 */
	private static Map<String, String> readFromExcel(XSSFWorkbook workBook,
			InputStream inputStream, double testCaseId) {
		Map<String, String> data = new HashMap<String, String>();

		int sheetNumber = getSheetNumber((int) testCaseId);

		double rowNumericvalue;
		double rowTestCaseId;

		String rowNameAttribute;
		String rowValueAttribute;

		XSSFSheet sheet = workBook.getSheetAt(sheetNumber);
		Iterator<Row> rows = sheet.rowIterator();

		nextRow: while (rows.hasNext()) {
			XSSFRow row = (XSSFRow) rows.next();

			// display row number in the console.
			int rowNum = row.getRowNum();

			log.debug("Row No.: " + rowNum);

			rowNumericvalue = -1;

			rowTestCaseId = -1;

			rowNameAttribute = null;

			rowValueAttribute = null;

			// Don't read the header
			if (rowNum <= 0) {
				continue;
			}

			// once get a row its time to iterate through cells.
			Iterator<Cell> cells = row.cellIterator();

			while (cells.hasNext()) {

				XSSFCell cell = (XSSFCell) cells.next();
				/*
				 * Now we will get the cell type and display the values
				 * accordingly.
				 */
				int columnIndex = cell.getColumnIndex();
				boolean istestCaseId = (columnIndex == ExcelReaderConstant.TC_ID_COLUMN_INDEX);

				switch (cell.getCellType()) {
				case XSSFCell.CELL_TYPE_NUMERIC: {
					// cell type numeric.

					rowNumericvalue = cell.getNumericCellValue();
					log.debug("columnIndex ::" + columnIndex
							+ " Numeric value: " + rowNumericvalue);

					if (istestCaseId)
						rowTestCaseId = rowNumericvalue;

					// search for test case id existence in the excel
					if (istestCaseId && (testCaseId != rowNumericvalue)) {

						continue nextRow;
					} else if (columnIndex == ExcelReaderConstant.VALUE_COLUMN_INDEX) {
						rowValueAttribute = Double.toString(rowNumericvalue);

						log.debug("rowNameAttribute ::" + rowNameAttribute
								+ " rowValueAttribute ::" + rowValueAttribute);

					}

					break;
				}

				case XSSFCell.CELL_TYPE_STRING: {

					log.debug("columnIndex ::" + columnIndex);
					// cell type string.
					XSSFRichTextString richTextString = cell
							.getRichStringCellValue();

					boolean rowTestCaseIdExist = (rowTestCaseId != -1);

					log.debug("rowTestCaseIdExist ::" + rowTestCaseIdExist);
					// check for name and value column index
					if (rowTestCaseIdExist
							&& columnIndex == ExcelReaderConstant.NAME_COLUMN_INDEX) {
						rowNameAttribute = richTextString.toString();
					} else if (rowTestCaseIdExist
							&& columnIndex == ExcelReaderConstant.VALUE_COLUMN_INDEX) {
						rowValueAttribute = richTextString.toString();
					}

					log.debug("String value: " + richTextString.getString()
							+ " rowNameAttribute ::" + rowNameAttribute
							+ " rowValueAttribute ::" + rowValueAttribute
							+ " rowTestCaseId ::" + rowTestCaseId);

					break;
				}

				default: {

					// types other than String and Numeric.
					log.debug("Other Type ::" + XSSFCell.CELL_TYPE_STRING);

					break;
				}
				}
			}

			if (rowNameAttribute != null && rowValueAttribute != null)
				data.put(rowNameAttribute, rowValueAttribute);
		}

		return data;
	}

	/**
	 * @param testCaseId
	 * @return sheet number
	 * @throws IllegalStateException
	 */
	private static int getSheetNumber(int testCaseId)
			throws IllegalStateException {
		return testCaseId / 100;
	}

}
