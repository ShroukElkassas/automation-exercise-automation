package com.automationexercise.tests;

import com.automationexercise.pages.HomePage;
import com.automationexercise.pages.ProductDetailPage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

@Epic("Regression Tests")
@Feature("Cart")
public class TestCaseE4 extends TestBasic {

    @Test(description = "Edge Case E4: Add to cart with negative quantity")
    @Severity(SeverityLevel.NORMAL)
    @Story("Boundary value: negative quantity")
    @Description("""
            1. Launch browser
            2. Navigate to url 'http://automationexercise.com'
            3. Verify that home page is visible successfully
            4. Click 'View Product' for the first product
            5. Set quantity input to -1
            6. Click 'Add to cart'
            7. Click 'View Cart'
            8. Verify that quantity = -1 is not accepted""")
    public void addToCartWithNegativeQuantity() {
        TestCase1.verifyThatHomePageIsVisibleSuccessfully();
        verifyQuantityNegativeBehavior();
    }

    @Step("Verify that quantity = -1 is not accepted")
    private void verifyQuantityNegativeBehavior() {

        new HomePage(getDriver()).viewProduct1ButtonClick();

        List<String> quantity = new ProductDetailPage(getDriver())
                .increaseQuantity("-1")
                .addToCartButtonClick()
                .viewCartButtonClick()
                .getQuantity();

        Assert.assertNotEquals(
                quantity.get(0),
                "-1",
                "The application accepted a negative quantity (-1) into the cart without validation."
        );
    }
}
