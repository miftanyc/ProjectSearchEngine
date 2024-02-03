package com.searchengine.qa.base;

import com.searchengine.qa.utils.Utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class Base {

    WebDriver driver;
    public Properties prop;
    public Properties dataProp;

    public Base(){

        //Load the Config Properties File
        prop = new Properties();
        File propFile = new File(System.getProperty("user.dir")+"/src/main/java/com/searchengine/qa/config/config.properties");

        try {
            FileInputStream propfis = new FileInputStream(propFile);
            prop.load(propfis);
        } catch (IOException e) {
            e.printStackTrace();
        }


        //Load the Test Data Properties File
        dataProp = new Properties();
        File dataFile = new File(System.getProperty("user.dir")+"/src/main/java/com/searchengine/qa/testdata/testdata.properties");
        FileInputStream datafis;

        try {
            datafis = new FileInputStream(dataFile);
            dataProp.load(datafis);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    //Initialize Browser

    public WebDriver initializeBrowser(String browserName){

        String headlessValue = prop.getProperty("headless");
        boolean isHeadless = Boolean.parseBoolean(headlessValue);

        switch (browserName){
            case "chrome":
                if(isHeadless){
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--headless");
                    driver = new ChromeDriver(chromeOptions);
                    break;
                }else{
                    driver = new ChromeDriver();
                    break;
                }

            case "firefox":
                if(isHeadless){
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addArguments("--headless");
                    driver = new FirefoxDriver(firefoxOptions);
                    break;
                }else{
                    driver = new FirefoxDriver();
                    break;
                }

            case "edge":
                if(isHeadless){
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.addArguments("--headless");
                    driver = new EdgeDriver(edgeOptions);
                    break;
                }else{
                    driver = new EdgeDriver();
                    break;
                }

            default:
                throw new IllegalArgumentException("Invalid Browser Name specified: " + browserName);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));
        driver.manage().window().maximize();

        return driver;
    }
}
