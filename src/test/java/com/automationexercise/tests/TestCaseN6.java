package com.automationexercise.tests;

import com.automationexercise.pages.HomePage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Regression Tests")
@Feature("Subscription")
public class TestCaseN6 extends TestBasic {

    private static final String INVALID_EMAIL = "notanemail";

    @Test(description = "Test Case N6: Subscribe to newsletter with invalid email format")
    @Severity(SeverityLevel.NORMAL)
    @Story("Subscribe with invalid email format")
    @Description("""
            1. Launch browser
            2. Navigate to url 'http://automationexercise.com'
            3. Verify that home page is visible successfully
            4. Scroll down to footer
            5. Verify text 'SUBSCRIPTION'
            6. Enter an invalid email format (no '@' symbol) and click arrow button
            7. Verify that success subscription message is NOT displayed""")
    public void subscribeWithInvalidEmailFormat() {
        TestCase1.verifyThatHomePageIsVisibleSuccessfully();
        TestCase10.verifyTextSubscription();
        verifyThatInvalidEmailSubscriptionIsRejected();
    }

    @Step("Verify that subscription with invalid email format is rejected")
    private void verifyThatInvalidEmailSubscriptionIsRejected() {
        boolean alertDisplayed = new HomePage(getDriver())
                .fillSubscribeWithEmail(INVALID_EMAIL)
                .isSubscriptionSuccessAlertDisplayed();
        Assert.assertFalse(alertDisplayed, "Verify that success subscription message is not shown for invalid email: " + INVALID_EMAIL);
    }
}