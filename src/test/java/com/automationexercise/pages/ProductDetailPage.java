package com.automationexercise.pages;

import com.automationexercise.utils.JSONReader;
import com.automationexercise.utils.SeleniumHelper;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class ProductDetailPage extends BasePage {



    private By productName =
            By.cssSelector("div[class='product-information'] h2");

    private By productCategory =
            By.xpath("//div[@class='product-information']//p[contains(.,'Category')]");

    private By productPrice =
            By.cssSelector("div[class='product-information'] span span");

    private By productAvailability =
            By.xpath("//div[@class='product-information']//p[contains(.,'Availability')]");

    private By productCondition =
            By.xpath("//div[@class='product-information']//p[contains(.,'Condition')]");

    private By productBrand =
            By.xpath("//div[@class='product-information']//p[contains(.,'Brand')]");

    private By quantityInput =
            By.id("quantity");

    private By addToCartButton =
            By.cssSelector("button[class='btn btn-default cart']");

    private By viewCartButton =
            By.cssSelector("a[href='/view_cart'] u");

    private By writeYourReview =
            By.cssSelector("a[href='#reviews']");

    private By yourNameInput =
            By.id("name");

    private By emailAddress =
            By.id("email");

    private By addReviewHere =
            By.id("review");

    private By submitButton =
            By.id("button-review");

    private By successMessage =
            By.cssSelector("div[class='alert-success alert'] span");

    public ProductDetailPage(WebDriver driver) {
        super(driver);
    }



    public WebElement getProductName() {
        return driver.findElement(productName);
    }

    public WebElement getProductCategory() {
        return driver.findElement(productCategory);
    }

    public WebElement getProductPrice() {
        return driver.findElement(productPrice);
    }

    public WebElement getProductAvailability() {
        return driver.findElement(productAvailability);
    }

    public WebElement getProductCondition() {
        return driver.findElement(productCondition);
    }

    public WebElement getProductBrand() {
        return driver.findElement(productBrand);
    }



    public ProductDetailPage increaseQuantity(String value) {
        driver.findElement(quantityInput).clear();
        driver.findElement(quantityInput).sendKeys(value);
        return this;
    }

    public ProductDetailPage addToCartButtonClick() {
        SeleniumHelper.waitForElementToBeClickable(driver,
                driver.findElement(addToCartButton));

        driver.findElement(addToCartButton).click();
        return this;
    }

    public CartPage viewCartButtonClick() {
        SeleniumHelper.waitForElementToBeClickable(driver,
                driver.findElement(viewCartButton));

        driver.findElement(viewCartButton).click();
        return new CartPage(driver);
    }

    public WebElement getWriteYourReview() {
        return driver.findElement(writeYourReview);
    }

    public ProductDetailPage fillReview() throws IOException, ParseException {

        driver.findElement(yourNameInput)
                .sendKeys(JSONReader.existingUser("name"));

        driver.findElement(emailAddress)
                .sendKeys(JSONReader.existingUser("email"));

        driver.findElement(addReviewHere)
                .sendKeys("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");

        driver.findElement(submitButton).click();

        return this;
    }

    public WebElement getSuccessMessage() {
        SeleniumHelper.waitForElementToBeVisible(driver,
                driver.findElement(successMessage));

        return driver.findElement(successMessage);
    }
}