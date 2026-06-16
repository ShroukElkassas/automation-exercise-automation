package com.automationexercise.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountCreatedPage extends BasePage {

    @FindBy(css = "h2[data-qa='account-created']")
    private WebElement accountCreated;

    @FindBy(css = "a[data-qa='continue-button']")
    private WebElement continueButton;

    public AccountCreatedPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getAccountCreated() {
        return accountCreated;
    }

    public LoggedHomePage continueButtonClick() {
        continueButton.click();
        return new LoggedHomePage(driver);
    }
}