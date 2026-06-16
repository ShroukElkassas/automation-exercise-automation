package com.automationexercise.pages;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContactUsPage extends BasePage {

    @FindBy(css = "h2.title:nth-child(2)")
    private WebElement getInTouch;

    @FindBy(name = "submit")
    private WebElement submitButton;

    @FindBy(css = ".status.alert.alert-success")
    private WebElement alertSuccess;

    public ContactUsPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getGetInTouch() {
        return getInTouch;
    }

    public ContactUsPage submitButtonClick() {
        submitButton.click();
        return this;
    }


    public boolean isSuccessAlertDisplayed() {
        try {
            return alertSuccess.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }


    public ContactUsPage acceptAlertIfPresent() {
        try {
            driver.switchTo().alert().accept();
        } catch (NoAlertPresentException e) {
            // no JS confirm shown — likely blocked before that point
        }
        return this;
    }
}