package com.searchengine.qa.testcases;

import com.searchengine.qa.base.Base;
import com.searchengine.qa.pages.GooglePage;
import com.searchengine.qa.pages.YahooPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SearchEngine extends Base {

    public SearchEngine(){
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

//  switch (){
//        case "Chrome":
//
//    }

    public void searchInSearchEngine(String searchWord){

        ;
        switch (prop.getProperty("searchengine")){
            case "google":
                googlePage = new GooglePage(driver);
                googlePage.assertGoogleSearchResult(searchWord);
                break;
            case "yahoo":
                yahooPage = new YahooPage(driver);
                yahooPage.assertYahooSearchResult(searchWord);
                break;
            default:
                throw new IllegalArgumentException("Invalid search engine specified: " + prop.getProperty("searchengine"));
        }

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
