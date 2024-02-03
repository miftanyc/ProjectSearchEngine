package com.searchengine.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


	public class ElementUtils {
	
		public WebDriver driver;
		
		public ElementUtils(WebDriver driver) {
			this.driver = driver;
		}

		public WebElement waitForElementToBeClickable(WebElement element,long durationInSeconds) {
			
			WebElement webElement = null;
			try {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(durationInSeconds));
				webElement = wait.until(ExpectedConditions.elementToBeClickable(element));
			}catch(Throwable e) {
				e.printStackTrace();
			}
			return webElement;
			
		}

		public String getTextFunction(WebElement element,long durationInSeconds) {
			WebElement webElement = waitForElementToBeClickable(element,durationInSeconds);
			String getText = webElement.getText();
			return getText;
		}

		public WebElement waitForVisibilityOfElement(WebElement element,long durationInSeconds) {
			
			WebElement webElement = null;
			try {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(durationInSeconds));
				webElement = wait.until(ExpectedConditions.visibilityOf(element));
			}catch(Throwable e) {
				e.printStackTrace();
			}
			
			return webElement;
			
		}

		public void scrollIntoViewElement(WebElement element){
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("arguments[0].scrollIntoView({block: 'center', behavior: 'smooth'});", waitForVisibilityOfElement(element, Utilities.EXPLICIT_WAIT_TIME));
		}

	}
	
	