package com.automationexercise.pages;

import com.automationexercise.utils.SeleniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class CartPage extends BasePage {



    private By productName =
            By.xpath("//td[contains(@class, 'cart_description')]//a");

    private By price =
            By.xpath("//td[contains(@class, 'cart_price')]/p");

    private By quantity =
            By.xpath("//td[contains(@class, 'cart_quantity')]/button");

    private By totalPrice =
            By.xpath("//p[contains(@class, 'cart_total_price')]");

    private By shoppingCart =
            By.cssSelector("li[class='active']");

    private By proceedToCheckoutButton =
            By.cssSelector("a[class='btn btn-default check_out']");

    private By xButton1 =
            By.cssSelector("a[data-product-id='1']");

    private By xButton2 =
            By.cssSelector("a[data-product-id='2']");

    private By emptyCartSpan =
            By.id("empty_cart");

    private By registerLoginButton =
            By.cssSelector("a[href='/login'] u");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    // ===== Methods (UNCHANGED logic) =====

    public List<String> getProductsNames() {
        return driver.findElements(productName)
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<String> getPrices() {
        return driver.findElements(price)
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<String> getQuantity() {
        return driver.findElements(quantity)
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<String> getTotalPrices() {
        return driver.findElements(totalPrice)
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public WebElement getShoppingCart() {
        return driver.findElement(shoppingCart);
    }

    public CheckoutPage proceedToCheckoutLoggedButtonClick() {
        driver.findElement(proceedToCheckoutButton).click();
        return new CheckoutPage(driver);
    }

    public CartPage xButtonClick() {
        driver.findElement(xButton1).click();
        driver.findElement(xButton2).click();
        return this;
    }

    public WebElement getEmptyCartSpan() {
        SeleniumHelper.waitForElementToBeVisible(driver, driver.findElement(emptyCartSpan));
        return driver.findElement(emptyCartSpan);
    }

    public CartPage proceedToCheckoutButtonClick() {
        driver.findElement(proceedToCheckoutButton).click();
        return this;
    }

    public LoginSignupPage registerLoginButtonClick() {
        driver.findElement(registerLoginButton).click();
        return new LoginSignupPage(driver);
    }
}