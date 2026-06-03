package com.automationexercise.pages;

import com.automationexercise.utils.SeleniumHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoggedHomePage {

    // ✅ Fixed: uses text-based XPath instead of fragile li[10] index
    @FindBy(xpath = "//a[contains(.,'Logged in as')]/b")
    private WebElement username;

    // ✅ Fixed: uses href instead of fragile li[5] index
    @FindBy(css = "a[href='/delete_account']")
    private WebElement deleteAccountButton;

    // ✅ Fixed: uses href instead of fragile li[4] index
    @FindBy(css = "a[href='/logout']")
    private WebElement logoutButton;

    private WebDriver driver;

    public LoggedHomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
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