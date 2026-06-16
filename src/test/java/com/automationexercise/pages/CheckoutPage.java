package com.automationexercise.pages;

import com.automationexercise.utils.SeleniumHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class CheckoutPage extends BasePage {

    @FindBy(xpath = "//ul[contains(@id, 'address_delivery')]//li")
    private List<WebElement> addressDelivery;

    @FindBy(xpath = "//ul[contains(@id, 'address_invoice')]//li")
    private List<WebElement> addressInvoice;


    @FindBy(xpath = "//tr[contains(.,'Total')]/td[last()]/p")
    private WebElement totalAmount;

    @FindBy(css = "textarea[name='message']")
    private WebElement comment;

    @FindBy(css = "a[href='/payment']")
    private WebElement placeOrderButton;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getAddressDelivery() {
        return addressDelivery
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<String> getAddressInvoice() {
        return addressInvoice
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public WebElement getTotalAmount() {
        SeleniumHelper.waitForElementToBeVisible(driver, totalAmount);
        return totalAmount;
    }

    public PaymentPage enterComment(String text) {
        comment.sendKeys(text);
        placeOrderButton.click();
        return new PaymentPage(driver);
    }
}