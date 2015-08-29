package com.kaplan.utils;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import javax.imageio.ImageIO;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener2;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

public class CustomTestListener extends TestListenerAdapter implements
		IInvokedMethodListener2 {

	String outputDir = null;

	// Take screen shot only for failed test case
	@Override
	public void onTestFailure(ITestResult result) {
		Reporter.setCurrentTestResult(result);
		captureScreenShot(result);
	}

	private void captureScreenShot(ITestResult result) {
		try {
			String screenshotPath;
			String relativePath;

			String uniqueVal = Utilities.getCurrentTimeInMillis();
			String errorScreenLink = "ErrorScreen";
			String screenshotFileName = "ErrorScreen" + uniqueVal;

			if (!new File(outputDir).exists()) {
				new File(outputDir).mkdir();
			}
			File outDir = new File(outputDir);
			String parentDir = outDir.getParent();
			new File(parentDir + File.separator + "screenshot").mkdir();

			screenshotPath = parentDir + "\\screenshot\\" + screenshotFileName
					+ ".png"; // +
			relativePath = "..\\screenshot";

			String screenshot = "<b><a href=\"" + relativePath + "\\"
					+ screenshotFileName + ".png\" target=\"_blank\">"
					+ errorScreenLink + "</a></b>";

			// Capture the screen shot of the area of the screen defined by the
			// rectangle
			Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
			Rectangle screenBounds = new Rectangle(0, 0, screenDim.width,
					screenDim.height);
			Robot robot = new Robot();
			BufferedImage image = robot.createScreenCapture(screenBounds);
			ImageIO.write(image, "png", new File(screenshotPath));

			String[] screenShotInfo = { "screenshot", screenshot };

			result.setParameters(screenShotInfo);

		} catch (AWTException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void afterInvocation(IInvokedMethod arg0, ITestResult arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeInvocation(IInvokedMethod arg0, ITestResult arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterInvocation(IInvokedMethod arg0, ITestResult arg1,
			ITestContext arg2) {

	}

	@Override
	public void beforeInvocation(IInvokedMethod arg0, ITestResult arg1,
			ITestContext testContext) {
		outputDir = testContext.getOutputDirectory();

	}

	@Override
	public void onTestStart(ITestResult tr) {
		try {
			Thread.sleep(60000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
