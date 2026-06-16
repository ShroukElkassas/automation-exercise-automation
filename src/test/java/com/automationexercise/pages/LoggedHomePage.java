package com.automationexercise.pages;

import com.automationexercise.utils.SeleniumHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoggedHomePage extends BasePage {


    @FindBy(xpath = "//a[contains(.,'Logged in as')]/b")
    private WebElement username;


    @FindBy(css = "a[href='/delete_account']")
    private WebElement deleteAccountButton;


    @FindBy(css = "a[href='/logout']")
    private WebElement logoutButton;

    public LoggedHomePage(WebDriver driver) {
        super(driver);
    }

    public WebElement getUsername() {
        SeleniumHelper.waitForElementToBeVisible(driver, username);
        return username;
    }

    public AccountDeletedPage deleteAccountButtonClick() {
        SeleniumHelper.waitForElementToBeClickable(driver, deleteAccountButton);
        deleteAccountButton.click();
        return new AccountDeletedPage(driver);
    }

    public LoginSignupPage logoutButtonClick() {
        SeleniumHelper.waitForElementToBeClickable(driver, logoutButton);
        logoutButton.click();
        return new LoginSignupPage(driver);
    }
}