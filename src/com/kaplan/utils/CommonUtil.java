package com.kaplan.utils;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.Random;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.simpleservicetoolkit.utilities.PropertyManager;

// TODO: Auto-generated Javadoc
/**
 * The Class CommonUtil.
 */
public class CommonUtil {

	/** The Constant log. */
	private static final Log log = LogFactory.getLog(CommonUtil.class);

	/**
	 * Instantiates a new common util.
	 */
	private CommonUtil() {
	}

	/**
	 * Method to get the property file corresponding to action file.
	 * 
	 * @param fileName
	 *            file name of the action class whose corresponding property
	 *            file need to be fetched
	 * @return property file corresponding to action class
	 */
	public static Properties getPropertyByFileName(String fileName) {
		Properties envprop = null;
		InputStream in = null;
		try {
			envprop = new Properties();
			in = CommonUtil.class.getResourceAsStream(fileName);
			envprop.load(in);

		} catch (Exception e) {
			log.error("Error | Util | getPropertyByFileName : ", e);

		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					log.error("Error closing the stream : ", e);
				}
		}
		return envprop;
	}

	/**
	 * Method to get the value of field environment from property file.
	 * 
	 * @return value corresponding to the property field
	 */
	public static String getEnvironment() {

		return PropertyManager.getPropertyManager().getProperty("environment");

	}

	/**
	 * Method to get the URL string to which the xml request has to be sent.
	 * 
	 * @return URL for which the xml request needs to be sent
	 */
	public static String getBaseUrL() {
		PropertyManager props = PropertyManager.getPropertyManager();
		String propUrl = props.getProperty("baseURL");
		String strURL = propUrl + "/instant/2";
		return strURL;
	}

	/**
	 * Method to get the random value.
	 * 
	 * @return random value
	 */
	public static String getRandomValue() {

		Random random = new Random();
		String randomValue = "7,"
				+ String.valueOf(random.nextInt(9999999) + 1000000);
		return randomValue;

	}

	/**
	 * Method return today date
	 * 
	 * @return
	 */
	public static String getTodayDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		return dateFormat.format(cal.getTime()).toString();
	}

	/**
	 * Method to get unique 50 character string.
	 * 
	 * @return String
	 */
	public static String getGUID() {

		final String alphabet = "0123456789ABCDEFGHIJKLMNopqrstuvWxYz";
		final int N = alphabet.length();
		char[] ch = new char[50];
		Random r = new Random();

		for (int i = 0; i < 50; i++) {
			ch[i] = alphabet.charAt(r.nextInt(N));
		}
		return new String(ch);
	}

	public static String convertDoubleStringToDecimalFormat(String text) {

		double value = 0.0;
		if (text != null)
			value = Double.parseDouble(text);
		java.text.DecimalFormat df = new java.text.DecimalFormat();
		df.applyPattern("#"); // without decimal point
		return (df.format(value));

	}
}
