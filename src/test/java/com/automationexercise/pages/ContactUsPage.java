package com.automationexercise.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ContactUsPage extends BasePage {



    private By getInTouch =
            By.cssSelector("h2.title:nth-child(2)");

    private By submitButton =
            By.name("submit");

    private By alertSuccess =
            By.cssSelector(".status.alert.alert-success");

    public ContactUsPage(WebDriver driver) {
        super(driver);
    }



    public WebElement getGetInTouch() {
        return driver.findElement(getInTouch);
    }

    public ContactUsPage submitButtonClick() {
        driver.findElement(submitButton).click();
        return this;
    }

    public boolean isSuccessAlertDisplayed() {
        try {
            return driver.findElement(alertSuccess).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public ContactUsPage acceptAlertIfPresent() {
        try {
            driver.switchTo().alert().accept();
        } catch (NoAlertPresentException e) {

        }
        return this;
    }
}