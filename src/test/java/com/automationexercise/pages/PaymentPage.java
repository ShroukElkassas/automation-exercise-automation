package com.automationexercise.pages;

import com.automationexercise.utils.JSONReader;
import com.automationexercise.utils.SeleniumHelper;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class PaymentPage extends BasePage {



    private By nameOnCardInput =
            By.cssSelector("input[data-qa='name-on-card']");

    private By cardNumberInput =
            By.cssSelector("input[data-qa='card-number']");

    private By cvcInput =
            By.cssSelector("input[data-qa='cvc']");

    private By expirationMonthInput =
            By.cssSelector("input[data-qa='expiry-month']");

    private By expirationYearInput =
            By.cssSelector("input[data-qa='expiry-year']");

    private By payAndConfirmOrderButton =
            By.cssSelector("button[data-qa='pay-button']");

    private By successMessage =
            By.cssSelector("div[class='col-sm-9 col-sm-offset-1'] p");

    public PaymentPage(WebDriver driver) {
        super(driver);
    }



    public PaymentPage fillPaymentDetails() throws IOException, ParseException {

        driver.findElement(nameOnCardInput)
                .sendKeys(JSONReader.paymentDetails("nameOnCard"));

        driver.findElement(cardNumberInput)
                .sendKeys(JSONReader.paymentDetails("cardNumber"));

        driver.findElement(cvcInput)
                .sendKeys(JSONReader.paymentDetails("cvc"));

        driver.findElement(expirationMonthInput)
                .sendKeys(JSONReader.paymentDetails("expirationMonth"));

        driver.findElement(expirationYearInput)
                .sendKeys(JSONReader.paymentDetails("expirationYear"));

        SeleniumHelper.waitForElementToBeClickable(driver,
                driver.findElement(payAndConfirmOrderButton));

        driver.findElement(payAndConfirmOrderButton).click();

        return this;
    }

    public WebElement getSuccessMessage() {
        SeleniumHelper.waitForElementToBeVisible(driver,
                driver.findElement(successMessage));

        return driver.findElement(successMessage);
    }
}