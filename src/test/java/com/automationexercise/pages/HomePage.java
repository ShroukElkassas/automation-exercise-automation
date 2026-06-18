package com.automationexercise.pages;

import com.automationexercise.utils.JSONReader;
import com.automationexercise.utils.SeleniumHelper;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class HomePage extends BasePage {



    private By girlImgResponsive =
            By.cssSelector("div[class='item active'] img[alt='demo website for practice']");

    private By signupLoginButton =
            By.cssSelector("a[href='/login']");

    private By contactUsButton =
            By.cssSelector("a[href='/contact_us']");

    private By testCasesButton =
            By.cssSelector("a[href='/test_cases']");

    private By productsButton =
            By.cssSelector("a[href='/products']");

    private By cartButton =
            By.cssSelector("a[href='/view_cart']");

    private By viewProduct1Button =
            By.cssSelector("a[href='/product_details/1']");

    private By categories =
            By.id("accordian");

    private By womenCategory =
            By.xpath("//*[@id='accordian']/div[1]/div[1]/h4/a/span/i");

    private By dressCategory =
            By.cssSelector("a[href='/category_products/1']");

    // footer
    private By subscription =
            By.cssSelector("div[class='single-widget'] h2");

    private By subscribeEmailInput =
            By.id("susbscribe_email");

    private By subscribeButton =
            By.id("subscribe");

    private By alertSuccessSubscribe =
            By.id("success-subscribe");

    public HomePage(WebDriver driver) {
        super(driver);
    }



    public WebElement homePageIsVisible() {
        return driver.findElement(girlImgResponsive);
    }

    public LoginSignupPage signupLoginClick() {
        driver.findElement(signupLoginButton).click();
        return new LoginSignupPage(driver);
    }

    public ContactUsPage contactUsButtonClick() {
        driver.findElement(contactUsButton).click();
        return new ContactUsPage(driver);
    }

    public ProductsPage productsButtonClick() {
        driver.findElement(productsButton).click();
        return new ProductsPage(driver);
    }

    public CartPage cartButtonClick() {
        driver.findElement(cartButton).click();
        return new CartPage(driver);
    }

    public ProductDetailPage viewProduct1ButtonClick() {
        SeleniumHelper.waitForElementToBeClickable(driver, driver.findElement(viewProduct1Button));
        driver.findElement(viewProduct1Button).click();
        return new ProductDetailPage(driver);
    }

    public WebElement getCategories() {
        return driver.findElement(categories);
    }

    public HomePage womenCategoryClick() {
        SeleniumHelper.waitForElementToBeClickable(driver, driver.findElement(womenCategory));
        driver.findElement(womenCategory).click();
        return this;
    }

    public ProductsPage dressCategoryClick() {
        SeleniumHelper.waitForElementToBeClickable(driver, driver.findElement(dressCategory));
        driver.findElement(dressCategory).click();
        return new ProductsPage(driver);
    }



    public WebElement getSubscription() {
        return driver.findElement(subscription);
    }

    public HomePage fillSubscribe() throws IOException, ParseException {
        driver.findElement(subscribeEmailInput)
                .sendKeys(JSONReader.existingUser("email"));

        SeleniumHelper.waitForElementToBeClickable(driver, driver.findElement(subscribeButton));
        driver.findElement(subscribeButton).click();

        return this;
    }

    public WebElement getAlertSuccessSubscribe() {
        return driver.findElement(alertSuccessSubscribe);
    }

    public HomePage fillSubscribeWithEmail(String email) {
        driver.findElement(subscribeEmailInput).sendKeys(email);

        SeleniumHelper.waitForElementToBeClickable(driver, driver.findElement(subscribeButton));
        driver.findElement(subscribeButton).click();

        return this;
    }

    public boolean isSubscriptionSuccessAlertDisplayed() {
        try {
            return driver.findElement(alertSuccessSubscribe).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}