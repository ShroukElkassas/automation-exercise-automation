package com.automationexercise.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AccountDeletedPage extends BasePage {



    private By accountDeleted =
            By.cssSelector("h2[data-qa='account-deleted']");

    private By continueButton =
            By.cssSelector("a[data-qa='continue-button']");

    public AccountDeletedPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getAccountDeleted() {
        return driver.findElement(accountDeleted);
    }

    public HomePage continueButtonClick() {
        driver.findElement(continueButton).click();
        return new HomePage(driver);
    }
}