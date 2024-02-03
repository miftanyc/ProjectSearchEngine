package com.searchengine.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;


import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import com.searchengine.qa.utils.ExtentReporter;
import com.searchengine.qa.utils.Utilities;

public class MyListeners implements ITestListener{
		
		ExtentReports extentReport;
		ExtentTest extentTest;
		String testName;
		String testData;

		@Override
		public void onStart(ITestContext context) {
			
			extentReport = ExtentReporter.generateExtentReport();
			
		}
		
		@Override
		public void onTestStart(ITestResult result) {
			
			testName = result.getName();
			testData = result.getParameters()[0].toString();
			extentTest = extentReport.createTest(testName);
			extentTest.log(Status.INFO, testName+" started executing");
		
		}

		@Override
		public void onTestSuccess(ITestResult result) {
			
			extentTest.log(Status.PASS, testName + " got Successfully executed");

		}

		@Override
		public void onTestFailure(ITestResult result) {
		
			WebDriver driver = null;
			try {
				driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
			} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
				e.printStackTrace();
			}
			
			//Screenshot Method to call
			String destinationScreenshotPath = Utilities.captureScreenshot(driver, testName+"-"+ testData);
			
			extentTest.addScreenCaptureFromPath(destinationScreenshotPath);
			extentTest.log(Status.INFO, result.getThrowable());
			extentTest.log(Status.FAIL, testName+" got failed");
			
		}

		@Override
		public void onTestSkipped(ITestResult result) {
			
			extentTest.log(Status.INFO, result.getThrowable());
			extentTest.log(Status.SKIP, testName +" got skipped");
		
		}


		@Override
		public void onFinish(ITestContext context) {
			
			extentReport.flush();
			
			//To execute Extent Report to open spontaneously after test suit execution.
			String pathOfExtentReport = System.getProperty("user.dir") + "/test-output/ExtentReports/extentReport.html";
			File extentReport = new File(pathOfExtentReport);
			
			try {
				Desktop.getDesktop().browse(extentReport.toURI());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		
		
}
