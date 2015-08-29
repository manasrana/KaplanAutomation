package com.kaplan.utils;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Calendar;

public class Utilities {

	public static String getCurrentTimeInMillis() {
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH); // Note: zero based!
		int day = now.get(Calendar.DAY_OF_MONTH);
		int hour = now.get(Calendar.HOUR_OF_DAY);
		int minute = now.get(Calendar.MINUTE);
		int second = now.get(Calendar.SECOND);
		int millis = now.get(Calendar.MILLISECOND);
		String currentTime = "" + year + month + day + hour + minute + second
				+ millis;
		return currentTime;
	}

	public static void showDesktop() {
		try {
			Robot robot;
			robot = new Robot();
			robot.keyPress(KeyEvent.VK_WINDOWS);
			robot.keyPress(KeyEvent.VK_D);
			robot.keyRelease(KeyEvent.VK_WINDOWS);
			robot.keyRelease(KeyEvent.VK_D);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
}
