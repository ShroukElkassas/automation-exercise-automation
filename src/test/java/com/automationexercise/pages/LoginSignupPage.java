package com.automationexercise.pages;

import com.automationexercise.utils.JSONReader;
import com.automationexercise.utils.SeleniumHelper;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class LoginSignupPage extends BasePage {



    private By loginToYourAccount =
            By.cssSelector("div[class='login-form'] h2");

    private By loginEmailInput =
            By.cssSelector("input[data-qa='login-email']");

    private By loginPasswordInput =
            By.cssSelector("input[data-qa='login-password']");

    private By loginButton =
            By.cssSelector("button[data-qa='login-button']");

    private By errorLogin =
            By.xpath("/html/body/section/div/div/div[1]/div/form/p");

    private By newUserSignup =
            By.cssSelector("div[class='signup-form'] h2");

    private By signupNameInput =
            By.cssSelector("input[data-qa='signup-name']");

    private By signupEmailInput =
            By.cssSelector("input[data-qa='signup-email']");

    private By signupButton =
            By.cssSelector("button[data-qa='signup-button']");

    private By emailAddressAlreadyExist =
            By.xpath("//section/div/div/div[3]/div/form/p");

    private By loggedInConfirmation =
            By.xpath("//a[contains(.,'Logged in as')]/b");

    public LoginSignupPage(WebDriver driver) {
        super(driver);
    }



    public WebElement getNewUserSignup() {
        return driver.findElement(newUserSignup);
    }

    private void fillSignup(String name, String email) {
        driver.findElement(signupNameInput).sendKeys(name);
        driver.findElement(signupEmailInput).sendKeys(email);
        driver.findElement(signupButton).click();
    }

    public EnterAccountInformationPage fillCorrectSignup(String name, String email) {
        fillSignup(name, email);
        return new EnterAccountInformationPage(driver);
    }

    public LoginSignupPage fillSignupAndStay(String name, String email) {
        driver.findElement(signupNameInput).sendKeys(name);
        driver.findElement(signupEmailInput).sendKeys(email);
        driver.findElement(signupButton).click();
        return this;
    }

    public LoginSignupPage fillIncorrectSignup() throws IOException, ParseException {
        fillSignup(JSONReader.existingUser("name"), JSONReader.existingUser("email"));
        return this;
    }

    public WebElement getLoginToYourAccount() {
        return driver.findElement(loginToYourAccount);
    }

    private void fillLogin(String email, String password) {
        driver.findElement(loginEmailInput).sendKeys(email);
        driver.findElement(loginPasswordInput).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    public LoggedHomePage fillCorrectLogin(String email, String password) {
        fillLogin(email, password);
        SeleniumHelper.waitForElementToBeVisible(driver, driver.findElement(loggedInConfirmation));
        return new LoggedHomePage(driver);
    }

    public LoginSignupPage fillIncorrectLogin(String email, String password) {
        fillLogin(email, password);
        return this;
    }

    public WebElement getErrorLogin() {
        return driver.findElement(errorLogin);
    }

    public WebElement getEmailAddressAlreadyExist() {
        return driver.findElement(emailAddressAlreadyExist);
    }
}