package com.automationexercise.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AccountCreatedPage extends BasePage {



    private By accountCreated = By.cssSelector("h2[data-qa='account-created']");

    private By continueButton = By.cssSelector("a[data-qa='continue-button']");

    public AccountCreatedPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getAccountCreated() {
        return driver.findElement(accountCreated);
    }

    public LoggedHomePage continueButtonClick() {
        driver.findElement(continueButton).click();
        return new LoggedHomePage(driver);
    }
}