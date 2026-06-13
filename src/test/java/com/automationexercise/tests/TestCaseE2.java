package com.automationexercise.tests;

import com.automationexercise.pages.CartPage;
import com.automationexercise.pages.HomePage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Regression Tests")
@Feature("Cart")
public class TestCaseE2 extends TestBasic {

    @Test(description = "Edge Case E2: Proceed to Checkout without being logged in")
    @Severity(SeverityLevel.NORMAL)
    @Story("Checkout requires authentication")
    @Description("""
            1. Launch browser
            2. Navigate to url 'http://automationexercise.com'
            3. Verify that home page is visible successfully
            4. Click 'Products' button and add products to cart
            5. Click 'Proceed To Checkout' while NOT logged in
            6. Click 'Register / Login' in the resulting prompt
            7. Verify that user is navigated to the login page""")
    public void proceedToCheckoutWithoutLogin() {
        TestCase1.verifyThatHomePageIsVisibleSuccessfully();
        verifyGuestIsPromptedToLoginBeforeCheckout();
    }

    @Step("Verify that guest user is prompted to register/login before checkout")
    private void verifyGuestIsPromptedToLoginBeforeCheckout() {
        new HomePage(getDriver())
                .productsButtonClick()
                .addProductsToCart();

        String loginToYourAccountText = new CartPage(getDriver())
                .proceedToCheckoutButtonClick()
                .registerLoginButtonClick()
                .getLoginToYourAccount()
                .getText();

        Assert.assertEquals(loginToYourAccountText, "Login to your account",
                "Verify guest user is prompted to register/login before checkout");
    }
}