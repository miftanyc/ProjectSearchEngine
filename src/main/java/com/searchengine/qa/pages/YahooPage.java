package com.searchengine.qa.pages;

import com.searchengine.qa.utils.ElementUtils;
import com.searchengine.qa.utils.Utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.Properties;

public class YahooPage {
    WebDriver driver;
    private ElementUtils elementUtils;

    public YahooPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        elementUtils = new ElementUtils(driver);
    }

    //Object Binding
    @FindBy(xpath = "//div[@id='main']/descendant::li/descendant::h3/descendant::a")
    private WebElement titleOfSearchResults;



    //Perform Actions
    public String getTextOfFirstElement(){
        String titleText = elementUtils.getTextFunction(titleOfSearchResults, Utilities.EXPLICIT_WAIT_TIME);
        return titleText;
    }

    public void scrollIntoViewFirstElement(){
        elementUtils.scrollIntoViewElement(titleOfSearchResults);
    }

    public void assertYahooSearchResult(String searchWord){

        Properties prop = elementUtils.propLoad();
        driver.get(prop.getProperty("yahooPage")+searchWord);
        String firstElementText = getTextOfFirstElement();
        scrollIntoViewFirstElement();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Given Data: "+searchWord.toLowerCase());
        System.out.println("First Element Text Content: "+firstElementText.toLowerCase());

        Assert.assertTrue(firstElementText.toLowerCase().contains(searchWord.toLowerCase()), "Text content does not contain "+searchWord);
    }

}