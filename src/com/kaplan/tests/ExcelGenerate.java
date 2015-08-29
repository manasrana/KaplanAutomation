package com.kaplan.tests;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.raybiztech.commonexcelreport.ExcelReportGenerator;

public class ExcelGenerate {

	public static void main(String[] args) throws ParserConfigurationException,
			IOException, SAXException {
		ExcelReportGenerator exe = new ExcelReportGenerator();
		exe.GenerateExcelReport("test.xlsx");

	}
}
