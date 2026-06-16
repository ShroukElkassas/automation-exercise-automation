package com.automationexercise.pages;

import com.automationexercise.utils.SeleniumHelper;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class ProductsPage extends BasePage {

    @FindBy(css = ".title.text-center")
    private WebElement titleTextCenter;

    @FindBy(css = "a[href='/product_details/1']")
    private WebElement viewProductOfFirstProductButton;

    @FindBy(id = "search_product")
    private WebElement searchProductInput;

    @FindBy(id = "submit_search")
    private WebElement submitSearchInput;

    @FindBy(xpath = "//div[contains(@class, 'productinfo text-center')]//p")
    private List<WebElement> searchResultsNames;

    @FindBy(css = "a[data-product-id='1']")
    private WebElement addToCartButton1;

    @FindBy(css = "a[data-product-id='2']")
    private WebElement addToCartButton2;

    @FindBy(css = "button[data-dismiss='modal']")
    private WebElement continueShoppingButton;

    @FindBy(css = "a[href='/view_cart'] u")
    private WebElement viewCartButton;

    @FindBy(css = "a[href='#Men']")
    private WebElement menCategory;

    @FindBy(css = "a[href='/category_products/3']")
    private WebElement tShirtsCategory;

    @FindBy(css = "div[class='brands-name']")
    private WebElement brands;

    @FindBy(css = "a[href='/brand_products/Polo']")
    private WebElement poloBrand;

    @FindBy(css = "a[href='/brand_products/Madame']")
    private WebElement madameBrand;

    private final JavascriptExecutor js;

    public ProductsPage(WebDriver driver) {
        super(driver);
        this.js = (JavascriptExecutor) driver;
    }


    private void jsClick(WebElement element) {
        SeleniumHelper.waitForElementToBeClickable(driver, element);
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        js.executeScript("arguments[0].click();", element);
    }

    public WebElement getTitleTextCenter() {
        return titleTextCenter;
    }

    public ProductDetailPage viewProductOfFirstProductButtonClick() {
        jsClick(viewProductOfFirstProductButton);
        return new ProductDetailPage(driver);
    }

    public ProductsPage fillSearchProductInput(String searchProduct) {
        searchProductInput.sendKeys(searchProduct);
        jsClick(submitSearchInput);
        return this;
    }

    public List<String> getProductsSearchNames() {
        return searchResultsNames
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public CartPage addProductsToCart() {

        jsClick(addToCartButton1);
        SeleniumHelper.waitForElementToBeClickable(driver, continueShoppingButton);
        continueShoppingButton.click();
        jsClick(addToCartButton2);
        SeleniumHelper.waitForElementToBeClickable(driver, viewCartButton);
        viewCartButton.click();
        return new CartPage(driver);
    }

    public ProductsPage menCategoryClick() {
        menCategory.click();
        return this;
    }

    public ProductsPage tShirtsCategoryClick() {
        tShirtsCategory.click();
        return this;
    }

    public WebElement getBrands() {
        return brands;
    }

    public ProductsPage poloBrandClick() {
        poloBrand.click();
        return this;
    }

    public ProductsPage madameBrandClick() {
        madameBrand.click();
        return this;
    }
}