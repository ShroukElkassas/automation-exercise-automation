package com.automationexercise.tests;

import com.automationexercise.pages.LoginSignupPage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Regression Tests")
@Feature("User")
public class TestCaseN3 extends TestBasic {

    @Test(description = "Test Case N3: Login with valid email but empty password")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Login with empty password")
    @Description("""
            1. Launch browser
            2. Navigate to url 'http://automationexercise.com'
            3. Verify that home page is visible successfully
            4. Click on 'Signup / Login' button
            5. Verify 'Login to your account' is visible
            6. Enter valid email but leave password empty
            7. Click 'Login' button
            8. Verify that user stays on login page (HTML5 validation blocks submission)""")
    public void loginWithValidEmailButEmptyPassword() {
        TestCase1.verifyThatHomePageIsVisibleSuccessfully();
        TestCase2.verifyLoginToYourAccountIsVisible();
        verifyThatLoginWithEmptyPasswordIsRejected();
    }

    @Step("Verify that login with empty password is rejected")
    private void verifyThatLoginWithEmptyPasswordIsRejected() {
        new LoginSignupPage(getDriver())
                .fillIncorrectLogin("test@test.com", "");

        String currentUrl = getDriver().getCurrentUrl();
        Assert.assertTrue(
                currentUrl.contains("/login"),
                "Verify that empty password keeps user on login page. URL: " + currentUrl
        );
    }
}