package com.automationexercise.tests;

import com.automationexercise.pages.LoginSignupPage;
import com.automationexercise.utils.Util;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Regression Tests")
@Feature("User")
public class TestCaseN4 extends TestBasic {

    @Test(description = "Test Case N4: Register with invalid email format")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Register with invalid email format")
    @Description("""
            1. Launch browser
            2. Navigate to url 'http://automationexercise.com'
            3. Verify that home page is visible successfully
            4. Click on 'Signup / Login' button
            5. Verify 'New User Signup!' is visible
            6. Enter valid name but invalid email format (no @ symbol)
            7. Click 'Signup' button
            8. Verify that form is not submitted — user stays on login page""")
    public void registerWithInvalidEmailFormat() {
        TestCase1.verifyThatHomePageIsVisibleSuccessfully();
        TestCase1.verifyNewUserSignupIsVisible();
        verifyThatInvalidEmailFormatIsRejected();
    }

    @Step("Verify that invalid email format is rejected")
    private void verifyThatInvalidEmailFormatIsRejected() {
        String uniqueName = "TestUser" + Util.generateCurrentDateAndTime();

        new LoginSignupPage(getDriver())
                .fillSignupAndStay(uniqueName, "invalidemail_no_at_symbol");

        String currentUrl = getDriver().getCurrentUrl();
        Assert.assertTrue(
                currentUrl.contains("/login"),
                "Verify that invalid email keeps user on login page. URL: " + currentUrl
        );
    }
}