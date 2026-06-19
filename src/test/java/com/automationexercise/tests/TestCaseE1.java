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
            8. Verify that quantity = 0 is not accepted""")
    public void addToCartWithZeroQuantity() {
        TestCase1.verifyThatHomePageIsVisibleSuccessfully();
        verifyQuantityZeroBehavior();
    }

    @Step("Verify that quantity = 0 is not accepted")
    private void verifyQuantityZeroBehavior() {

        new HomePage(getDriver()).viewProduct1ButtonClick();

        List<String> quantity = new ProductDetailPage(getDriver())
                .increaseQuantity("0")
                .addToCartButtonClick()
                .viewCartButtonClick()
                .getQuantity();

        Assert.assertNotEquals(
                quantity.get(0),
                "0",
                "The application accepted quantity = 0 into the cart without validation."
        );
    }
}
