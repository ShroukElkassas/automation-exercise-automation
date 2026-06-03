package com.automationexercise.tests;

import com.automationexercise.pages.ProductsPage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

@Epic("Regression Tests")
@Feature("Search")
public class TestCaseN2 extends TestBasic {

    private static final String NON_EXISTENT_PRODUCT = "xyznonexistentproduct99999";

    @Test(description = "Test Case N2: Search for non-existent product returns no results")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Search with no matching results")
    @Description("""
            1. Launch browser
            2. Navigate to url 'http://automationexercise.com'
            3. Verify that home page is visible successfully
            4. Click on 'Products' button
            5. Enter a non-existent product name in search input
            6. Click search button
            7. Verify 'SEARCHED PRODUCTS' header is visible
            8. Verify that no products are returned""")
    public void searchForNonExistentProductReturnsNoResults() {
        TestCase1.verifyThatHomePageIsVisibleSuccessfully();
        TestCase8.verifyUserIsNavigatedToAllProductsPageSuccessfully();
        verifySearchedProductsHeaderIsVisible();
        verifyNoProductsAreReturned();
    }

    @Step("Verify 'SEARCHED PRODUCTS' header is visible after search")
    private void verifySearchedProductsHeaderIsVisible() {
        String searchedProductsText = new ProductsPage(getDriver())
                .fillSearchProductInput(NON_EXISTENT_PRODUCT)
                .getTitleTextCenter()
                .getText();
        Assert.assertEquals(
                searchedProductsText,
                "SEARCHED PRODUCTS",
                "Verify SEARCHED PRODUCTS header is visible"
        );
    }

    @Step("Verify that no products are returned for non-existent search term")
    private void verifyNoProductsAreReturned() {
        List<String> productsNames = new ProductsPage(getDriver())
                .getProductsSearchNames();
        Assert.assertEquals(
                productsNames.size(),
                0,
                "Verify that search returns 0 products for: " + NON_EXISTENT_PRODUCT
        );
    }
}