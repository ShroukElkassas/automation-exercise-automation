package com.automationexercise.pages;

import com.automationexercise.utils.SeleniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoggedHomePage extends BasePage {



    private By username =
            By.xpath("//a[contains(.,'Logged in as')]/b");

    private By deleteAccountButton =
            By.cssSelector("a[href='/delete_account']");

    private By logoutButton =
            By.cssSelector("a[href='/logout']");

    public LoggedHomePage(WebDriver driver) {
        super(driver);
    }



    public WebElement getUsername() {
        SeleniumHelper.waitForElementToBeVisible(driver, driver.findElement(username));
        return driver.findElement(username);
    }

    public AccountDeletedPage deleteAccountButtonClick() {
        SeleniumHelper.waitForElementToBeClickable(driver, driver.findElement(deleteAccountButton));
        driver.findElement(deleteAccountButton).click();
        return new AccountDeletedPage(driver);
    }

    public LoginSignupPage logoutButtonClick() {
        SeleniumHelper.waitForElementToBeClickable(driver, driver.findElement(logoutButton));
        driver.findElement(logoutButton).click();
        return new LoginSignupPage(driver);
    }
}