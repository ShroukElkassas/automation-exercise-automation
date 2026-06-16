package com.automationexercise.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountDeletedPage extends BasePage {

    @FindBy(css = "h2[data-qa='account-deleted']")
    private WebElement accountDeleted;

    @FindBy(css = "a[data-qa='continue-button']")
    private WebElement continueButton;

    public AccountDeletedPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getAccountDeleted() {
        return accountDeleted;
    }

    public HomePage continueButtonClick() {
        continueButton.click();
        return new HomePage(driver);
    }
}