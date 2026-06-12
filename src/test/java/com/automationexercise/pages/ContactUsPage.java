package com.automationexercise.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactUsPage {

    @FindBy(css = "h2.title:nth-child(2)")
    private WebElement getInTouch;











    @FindBy(name = "submit")
    private WebElement submitButton;

    @FindBy(css = ".status.alert.alert-success")
    private WebElement alertSuccess;


    private WebDriver driver;

    public ContactUsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public WebElement getGetInTouch() {
        return getInTouch;
    }



    public ContactUsPage submitButtonClick() {
        submitButton.click();
        return this;
    }







    // New: safe check — doesn't throw if the success alert never appears
    public boolean isSuccessAlertDisplayed() {
        try {
            return alertSuccess.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    // New: handles the JS confirm() dialog if it appears, does nothing if it doesn't
    public ContactUsPage acceptAlertIfPresent() {
        try {
            driver.switchTo().alert().accept();
        } catch (org.openqa.selenium.NoAlertPresentException e) {
            // no JS confirm shown — likely blocked before that point
        }
        return this;
    }
}
