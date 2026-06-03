package com.automationexercise.pages;

import com.automationexercise.utils.JSONReader;
import com.automationexercise.utils.SeleniumHelper;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class ProductDetailPage {

    @FindBy(css = "div[class='product-information'] h2")
    private WebElement productName;

    // ✅ Fixed: text-based instead of fragile index XPath
    @FindBy(xpath = "//div[@class='product-information']//p[contains(.,'Category')]")
    private WebElement productCategory;

    @FindBy(css = "div[class='product-information'] span span")
    private WebElement productPrice;

    // ✅ Fixed: text-based instead of fragile index XPath
    @FindBy(xpath = "//div[@class='product-information']//p[contains(.,'Availability')]")
    private WebElement productAvailability;

    // ✅ Fixed: text-based instead of fragile index XPath
    @FindBy(xpath = "//div[@class='product-information']//p[contains(.,'Condition')]")
    private WebElement productCondition;

    // ✅ Fixed: text-based instead of fragile index XPath
    @FindBy(xpath = "//div[@class='product-information']//p[contains(.,'Brand')]")
    private WebElement productBrand;

    @FindBy(id = "quantity")
    private WebElement quantityInput;

    @FindBy(css = "button[class='btn btn-default cart']")
    private WebElement addToCartButton;

    @FindBy(css = "a[href='/view_cart'] u")
    private WebElement viewCartButton;

    @FindBy(css = "a[href='#reviews']")
    private WebElement writeYourReview;

    @FindBy(id = "name")
    private WebElement yourNameInput;

    @FindBy(id = "email")
    private WebElement emailAddress;

    @FindBy(id = "review")
    private WebElement addReviewHere;

    @FindBy(id = "button-review")
    private WebElement submitButton;

    @FindBy(css = "div[class='alert-success alert'] span")
    private WebElement successMessage;

    private WebDriver driver;

    public ProductDetailPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public WebElement getProductName() {
        return productName;
    }

    public WebElement getProductCategory() {
        return productCategory;
    }

    public WebElement getProductPrice() {
        return productPrice;
    }

    public WebElement getProductAvailability() {
        return productAvailability;
    }

    public WebElement getProductCondition() {
        return productCondition;
    }

    public WebElement getProductBrand() {
        return productBrand;
    }

    public ProductDetailPage increaseQuantity(String value) {
        quantityInput.clear();
        quantityInput.sendKeys(value);
        return this;
    }

    public ProductDetailPage addToCartButtonClick() {
        SeleniumHelper.waitForElementToBeClickable(driver, addToCartButton);
        addToCartButton.click();
        return this;
    }

    public CartPage viewCartButtonClick() {
        SeleniumHelper.waitForElementToBeClickable(driver, viewCartButton);
        viewCartButton.click();
        return new CartPage(driver);
    }

    public WebElement getWriteYourReview() {
        return writeYourReview;
    }

    public ProductDetailPage fillReview() throws IOException, ParseException {
        yourNameInput.sendKeys(JSONReader.existingUser("name"));
        emailAddress.sendKeys(JSONReader.existingUser("email"));
        addReviewHere.sendKeys("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        submitButton.click();
        return this;
    }

    public WebElement getSuccessMessage() {
        SeleniumHelper.waitForElementToBeVisible(driver, successMessage);
        return successMessage;
    }
}