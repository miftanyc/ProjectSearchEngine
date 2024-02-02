package com.searchengine.qa.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;

public class Utilities {

    public static final int IMPLICIT_WAIT_TIME = 15;
    public static final int PAGE_LOAD_TIME = 15;
    public static final int EXPLICIT_WAIT_TIME=30;



    public static String captureScreenshot(WebDriver driver, String testName) {

        File srcScreenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String destinationScreenshotPath = System.getProperty("user.dir") +"/screenshots/"+testName+".png";
        try {
            FileHandler.copy(srcScreenshot, new File (destinationScreenshotPath));
        } catch (IOException e) {

            e.printStackTrace();
        }
        return destinationScreenshotPath;

    }



}
