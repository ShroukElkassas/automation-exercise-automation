package com.automationexercise.tests;

import com.automationexercise.pages.ContactUsPage;
import com.automationexercise.pages.HomePage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Regression Tests")
@Feature("Contact Us")
public class TestCaseN5 extends TestBasic {

    @Test(description = "Test Case N5: Submit Contact Us form with empty fields")
    @Severity(SeverityLevel.NORMAL)
    @Story("Submit Contact Us form with empty required fields")
    @Description("""
            1. Launch browser
            2. Navigate to url 'http://automationexercise.com'
            3. Verify that home page is visible successfully
            4. Click on 'Contact Us' button
            5. Verify 'GET IN TOUCH' is visible
            6. Click 'Submit' button without filling any fields
            7. Verify that success message is NOT displayed""")
    public void submitContactUsFormWithEmptyFields() {
        TestCase1.verifyThatHomePageIsVisibleSuccessfully();
        verifyGetInTouchIsVisible();
        verifyThatEmptyContactUsFormIsRejected();
    }

    @Step("Verify 'GET IN TOUCH' is visible")
    private void verifyGetInTouchIsVisible() {
        String getInTouchText = new HomePage(getDriver())
                .contactUsButtonClick()
                .getGetInTouch()
                .getText();
        Assert.assertEquals(getInTouchText, "GET IN TOUCH", "Verify 'GET IN TOUCH' is visible");
    }

    @Step("Verify that empty Contact Us form submission is rejected")
    private void verifyThatEmptyContactUsFormIsRejected() {
        boolean alertDisplayed = new ContactUsPage(getDriver())
                .submitButtonClick()
                .acceptAlertIfPresent()
                .isSuccessAlertDisplayed();
        Assert.assertFalse(alertDisplayed, "Verify that success message is not shown for empty Contact Us form");
    }
}