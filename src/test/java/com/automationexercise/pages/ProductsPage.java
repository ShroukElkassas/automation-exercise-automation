package com.automationexercise.pages;

import com.automationexercise.utils.SeleniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class ProductsPage extends BasePage {



    private By titleTextCenter =
            By.cssSelector(".title.text-center");

    private By viewProductOfFirstProductButton =
            By.cssSelector("a[href='/product_details/1']");

    private By searchProductInput =
            By.id("search_product");

    private By submitSearchInput =
            By.id("submit_search");

    private By searchResultsNames =
            By.xpath("//div[contains(@class, 'productinfo text-center')]//p");

    private By addToCartButton1 =
            By.cssSelector("a[data-product-id='1']");

    private By addToCartButton2 =
            By.cssSelector("a[data-product-id='2']");

    private By continueShoppingButton =
            By.cssSelector("button[data-dismiss='modal']");

    private By viewCartButton =
            By.cssSelector("a[href='/view_cart'] u");

    private By menCategory =
            By.cssSelector("a[href='#Men']");

    private By tShirtsCategory =
            By.cssSelector("a[href='/category_products/3']");

    private By brands =
            By.cssSelector("div[class='brands-name']");

    private By poloBrand =
            By.cssSelector("a[href='/brand_products/Polo']");

    private By madameBrand =
            By.cssSelector("a[href='/brand_products/Madame']");

    private final JavascriptExecutor js;

    public ProductsPage(WebDriver driver) {
        super(driver);
        this.js = (JavascriptExecutor) driver;
    }



    private void jsClick(By locator) {
        WebElement element = driver.findElement(locator);

        SeleniumHelper.waitForElementToBeClickable(driver, element);
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        js.executeScript("arguments[0].click();", element);
    }



    public WebElement getTitleTextCenter() {
        return driver.findElement(titleTextCenter);
    }

    public ProductDetailPage viewProductOfFirstProductButtonClick() {
        jsClick(viewProductOfFirstProductButton);
        return new ProductDetailPage(driver);
    }

    public ProductsPage fillSearchProductInput(String searchProduct) {
        driver.findElement(searchProductInput).sendKeys(searchProduct);
        jsClick(submitSearchInput);
        return this;
    }

    public List<String> getProductsSearchNames() {
        return driver.findElements(searchResultsNames)
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public CartPage addProductsToCart() {

        jsClick(addToCartButton1);

        SeleniumHelper.waitForElementToBeClickable(
                driver, driver.findElement(continueShoppingButton));
        driver.findElement(continueShoppingButton).click();

        jsClick(addToCartButton2);

        SeleniumHelper.waitForElementToBeClickable(
                driver, driver.findElement(viewCartButton));
        driver.findElement(viewCartButton).click();

        return new CartPage(driver);
    }

    public ProductsPage menCategoryClick() {
        driver.findElement(menCategory).click();
        return this;
    }

    public ProductsPage tShirtsCategoryClick() {
        driver.findElement(tShirtsCategory).click();
        return this;
    }

    public WebElement getBrands() {
        return driver.findElement(brands);
    }

    public ProductsPage poloBrandClick() {
        driver.findElement(poloBrand).click();
        return this;
    }

    public ProductsPage madameBrandClick() {
        driver.findElement(madameBrand).click();
        return this;
    }
}