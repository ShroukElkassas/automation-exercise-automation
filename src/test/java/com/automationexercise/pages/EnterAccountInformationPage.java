package com.automationexercise.pages;

import com.automationexercise.utils.JSONReader;
import com.automationexercise.utils.Util;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;

public class EnterAccountInformationPage extends BasePage {



    private By enterAccountInformation =
            By.xpath("//b[contains(.,'Enter Account Information')]");

    private By titleMrCheckbox =
            By.id("id_gender1");

    private By passwordInput =
            By.id("password");

    private By daysSelect =
            By.id("days");

    private By monthsSelect =
            By.id("months");

    private By yearsSelect =
            By.id("years");

    private By newsletterCheckbox =
            By.id("newsletter");

    private By specialOffersCheckbox =
            By.id("optin");

    private By firstNameInput =
            By.id("first_name");

    private By lastNameInput =
            By.id("last_name");

    private By companyInput =
            By.id("company");

    private By address1Input =
            By.id("address1");

    private By address2Input =
            By.id("address2");

    private By countrySelect =
            By.id("country");

    private By stateInput =
            By.id("state");

    private By cityInput =
            By.id("city");

    private By zipcodeInput =
            By.id("zipcode");

    private By mobileNumberInput =
            By.id("mobile_number");

    private By createAccountButton =
            By.cssSelector("button[data-qa='create-account']");

    public EnterAccountInformationPage(WebDriver driver) {
        super(driver);
    }



    public org.openqa.selenium.WebElement getEnterAccountInformation() {
        return driver.findElement(enterAccountInformation);
    }

    public AccountCreatedPage fillAccountDetails() throws IOException, ParseException {

        String password = "pass" + Util.generateCurrentDateAndTime();

        driver.findElement(titleMrCheckbox).click();
        driver.findElement(passwordInput).sendKeys(password);

        Select days = new Select(driver.findElement(daysSelect));
        days.selectByValue(JSONReader.accountDetails("day"));

        Select months = new Select(driver.findElement(monthsSelect));
        months.selectByValue(JSONReader.accountDetails("month"));

        Select years = new Select(driver.findElement(yearsSelect));
        years.selectByValue(JSONReader.accountDetails("year"));

        driver.findElement(newsletterCheckbox).click();
        driver.findElement(specialOffersCheckbox).click();

        driver.findElement(firstNameInput).sendKeys(JSONReader.accountDetails("firstName"));
        driver.findElement(lastNameInput).sendKeys(JSONReader.accountDetails("lastName"));
        driver.findElement(companyInput).sendKeys(JSONReader.accountDetails("company"));
        driver.findElement(address1Input).sendKeys(JSONReader.accountDetails("address1"));
        driver.findElement(address2Input).sendKeys(JSONReader.accountDetails("address2"));

        Select countrySelector = new Select(driver.findElement(countrySelect));
        countrySelector.selectByValue(JSONReader.accountDetails("country"));

        driver.findElement(stateInput).sendKeys(JSONReader.accountDetails("state"));
        driver.findElement(cityInput).sendKeys(JSONReader.accountDetails("city"));
        driver.findElement(zipcodeInput).sendKeys(JSONReader.accountDetails("zipcode"));
        driver.findElement(mobileNumberInput).sendKeys(JSONReader.accountDetails("mobileNumber"));

        driver.findElement(createAccountButton).click();

        return new AccountCreatedPage(driver);
    }
}