package com.automationexercise.pages;

import com.automationexercise.utils.SeleniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class CheckoutPage extends BasePage {



    private By addressDelivery =
            By.xpath("//ul[contains(@id, 'address_delivery')]//li");

    private By addressInvoice =
            By.xpath("//ul[contains(@id, 'address_invoice')]//li");

    private By totalAmount =
            By.xpath("//tr[contains(.,'Total')]/td[last()]/p");

    private By comment =
            By.cssSelector("textarea[name='message']");

    private By placeOrderButton =
            By.cssSelector("a[href='/payment']");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }



    public List<String> getAddressDelivery() {
        return driver.findElements(addressDelivery)
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<String> getAddressInvoice() {
        return driver.findElements(addressInvoice)
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public WebElement getTotalAmount() {
        SeleniumHelper.waitForElementToBeVisible(driver, driver.findElement(totalAmount));
        return driver.findElement(totalAmount);
    }

    public PaymentPage enterComment(String text) {
        driver.findElement(comment).sendKeys(text);
        driver.findElement(placeOrderButton).click();
        return new PaymentPage(driver);
    }
}