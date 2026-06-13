package com.automationexercise.tests;

import com.automationexercise.pages.HomePage;
import com.automationexercise.pages.ProductDetailPage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

@Epic("Regression Tests")
@Feature("Cart")
public class TestCaseE1 extends TestBasic {

    @Test(description = "Edge Case E1: Add to cart with quantity = 0")
    @Severity(SeverityLevel.NORMAL)
    @Story("Boundary value: zero quantity")
    @Description("""
            1. Launch browser
            2. Navigate to url 'http://automationexercise.com'
            3. Verify that home page is visible successfully
            4. Click 'View Product' for the first product
            5. Set quantity input to 0
            6. Click 'Add to cart'
            7. Click 'View Cart'
            8. Document the quantity displayed in the cart for this boundary value""")
    public void addToCartWithZeroQuantity() {
        TestCase1.verifyThatHomePageIsVisibleSuccessfully();
        verifyQuantityZeroBehavior();
    }

    @Step("Verify quantity behavior when input quantity = 0")
    private void verifyQuantityZeroBehavior() {
        new HomePage(getDriver()).viewProduct1ButtonClick();

        List<String> quantity = new ProductDetailPage(getDriver())
                .increaseQuantity("0")
                .addToCartButtonClick()
                .viewCartButtonClick()
                .getQuantity();

        Assert.assertEquals(quantity.get(0), "0",
                "Document that the app currently accepts quantity = 0 into the cart without validation");
    }
}