package com.automationexercise.tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Regression Tests")
@Feature("User")
public class TestCaseN1 extends TestBasic {

    @Test(description = "Test Case N1: Login with empty email and password")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Login with empty credentials")
    @Description("""
            1. Launch browser
            2. Navigate to url 'http://automationexercise.com'
            3. Verify that home page is visible successfully
            4. Click on 'Signup / Login' button
            5. Verify 'Login to your account' is visible
            6. Leave email and password fields empty
            7. Click 'Login' button
            8. Verify that user stays on login page (HTML5 validation blocks submission)""")
    public void loginWithEmptyEmailAndPassword() {
        TestCase1.verifyThatHomePageIsVisibleSuccessfully();
        TestCase2.verifyLoginToYourAccountIsVisible();
        verifyThatEmptyLoginIsRejected();
    }

    @Step("Verify that empty login credentials are rejected")
    private void verifyThatEmptyLoginIsRejected() {
        // Submit with empty fields
        new com.automationexercise.pages.LoginSignupPage(getDriver())
                .fillIncorrectLogin("", "");

        // HTML5 'required' blocks submission — user stays on /login page
        String currentUrl = getDriver().getCurrentUrl();
        Assert.assertTrue(
                currentUrl.contains("/login"),
                "Verify that empty login keeps user on login page. URL: " + currentUrl
        );
    }
}