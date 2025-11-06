package fastAPI.bdd.stepdefinitions;

import fastAPI.pages.SignUpPage;
import fastAPI.tests.BaseTest;
import fastAPI.utils.TestData;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;

public class SignUpSteps extends BaseTest {

    private SignUpPage signUpPage;

    @Given("I am on the signup page")
    public void i_am_on_the_signup_page() {
        signUpPage = new SignUpPage(driver);
        signUpPage.navigateTo("/signup");
    }

    @When("I enter a valid name, email, and password")
    public void i_enter_a_valid_name_email_and_password() {
        signUpPage.enterFullName(TestData.Users.DUMMY_NAME);
        signUpPage.enterEmail(TestData.Users.DUMMY_EMAIL);
        signUpPage.enterPassword(TestData.Users.DUMMY_PASSWORD);
        signUpPage.enterConfirmPassword(TestData.Users.DUMMY_PASSWORD);
    }

    @When("I leave all signup fields empty")
    public void i_leave_all_signup_fields_empty() {
        signUpPage.enterFullName("");
        signUpPage.enterEmail("");
        signUpPage.enterPassword("");
        signUpPage.enterConfirmPassword("");
    }

    @When("I enter an existing email and password")
    public void i_enter_an_existing_email_and_password() {
        signUpPage.enterFullName(TestData.Users.DUMMY_NAME);
        signUpPage.enterEmail(TestData.Users.VALID_EMAIL);
        signUpPage.enterPassword(TestData.Users.VALID_PASSWORD);
        signUpPage.enterConfirmPassword(TestData.Users.VALID_PASSWORD);
    }

    @When("I enter mismatched passwords")
    public void i_enter_mismatched_passwords() {
        signUpPage.enterFullName(TestData.Users.DUMMY_NAME);
        signUpPage.enterEmail(TestData.Users.DUMMY_EMAIL);
        signUpPage.enterPassword("Password123!");
        signUpPage.enterConfirmPassword("Different321!");
    }

    @When("I enter an invalid email format in signup")
    public void i_enter_an_invalid_email_format_in_signup() {
        signUpPage.enterFullName(TestData.Users.DUMMY_NAME);
        signUpPage.enterEmail("bademail");
        signUpPage.enterPassword(TestData.Users.DUMMY_PASSWORD);
        signUpPage.enterConfirmPassword(TestData.Users.DUMMY_PASSWORD);
    }

    @When("I click the Sign Up button")
    public void i_click_the_sign_up_button() {
        signUpPage.clickSignUpButton();
    }

    @Then("I should be redirected to the login page after signup")
    public void i_should_be_redirected_to_the_login_page_after_signup() {
        Assertions.assertTrue(signUpPage.isSignUpSuccessful(),
                "Sign-up should succeed and redirect to login");
    }

    @Then("I should remain on the signup page")
    public void i_should_remain_on_the_signup_page() {
        Assertions.assertTrue(driver.getCurrentUrl().contains("/signup"),
                "URL should still contain /signup");
    }

    @Then("I should see error messages")
    public void i_should_see_error_messages() {
        Assertions.assertTrue(signUpPage.hasFieldErrorContaining("required"),
                "Field error should contain 'required'");
    }

    @Then("I should see an error about the existing account")
    public void i_should_see_an_error_about_the_existing_account() {
        Assertions.assertTrue(signUpPage.hasToastErrorContaining("already exists"),
                "Toast error should contain 'already exists'");
    }

    @Then("I should see an error about password mismatch")
    public void i_should_see_an_error_about_password_mismatch() {
        boolean hasError = signUpPage.hasFieldErrorContaining("match") ||
                signUpPage.hasFieldErrorContaining("same");
        Assertions.assertTrue(hasError,
                "Field error should mention 'match' or 'same'");
    }

    @Then("I should see an error about invalid email in signup")
    public void i_should_see_an_error_about_invalid_email_in_signup() {
        boolean hasError = signUpPage.hasFieldErrorContaining("valid") ||
                signUpPage.hasFieldErrorContaining("email");
        Assertions.assertTrue(hasError,
                "Field error should mention 'valid' or 'email'");
    }
}
