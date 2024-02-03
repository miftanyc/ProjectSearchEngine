package com.searchengine.qa.testcases;

import com.searchengine.qa.base.Base;
import com.searchengine.qa.pages.GooglePage;
import com.searchengine.qa.pages.YahooPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SearchEngineTest extends Base {

    public SearchEngineTest(){
        super();
    }

    public WebDriver driver;
    GooglePage googlePage;
    YahooPage yahooPage;

    @BeforeMethod
    public void setup(){
        driver = initializeBrowser(prop.getProperty("browserName"));

    }


    @Test(dataProvider = "Search Word Provider")

    public void searchInSearchEngine(String searchWord){

        StringBuilder sb = null;
        switch (prop.getProperty("searchEngine")){
            case "google":
                googlePage = new GooglePage(driver);
                driver.get(prop.getProperty("googlePage")+searchWord);
                sb = new StringBuilder(googlePage.getTextOfFirstElement());
                googlePage.scrollIntoViewFirstElement();
                break;

            case "yahoo":
                yahooPage = new YahooPage(driver);
                driver.get(prop.getProperty("yahooPage")+searchWord);
                sb = new StringBuilder(yahooPage.getTextOfFirstElement());
                yahooPage.scrollIntoViewFirstElement();
                break;

            default:
                throw new IllegalArgumentException("Invalid search engine specified: " + prop.getProperty("searchEngine"));
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Given Data: "+searchWord.toLowerCase());
        System.out.println("First Element Text Content: "+sb.toString().toLowerCase());
        Assert.assertTrue(sb.toString().toLowerCase().contains(searchWord.toLowerCase()), "Text content does not contain "+searchWord);

    }


    @DataProvider(name = "Search Word Provider")
    public Object[] searchWordDataProvider(){
        return new Object[]{
                dataProp.getProperty("searchWord1"),
                dataProp.getProperty("searchWord2"),
                dataProp.getProperty("searchWord3")

        };

    }


    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

}
