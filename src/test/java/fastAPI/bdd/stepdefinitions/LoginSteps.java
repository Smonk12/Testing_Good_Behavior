package fastAPI.bdd.stepdefinitions;

import fastAPI.pages.LoginPage;
import fastAPI.tests.BaseTest;
import fastAPI.utils.TestData;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;

public class LoginSteps extends BaseTest {

    private LoginPage loginPage;

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        loginPage = new LoginPage(driver);
        loginPage.navigateTo("/login");
    }

    @When("I enter a valid email and password")
    public void i_enter_a_valid_email_and_password() {
        loginPage.enterEmail(TestData.Users.VALID_EMAIL);
        loginPage.enterPassword(TestData.Users.VALID_PASSWORD);
    }

    @When("I enter a valid email and incorrect password")
    public void i_enter_a_valid_email_and_incorrect_password() {
        loginPage.enterEmail(TestData.Users.VALID_EMAIL);
        loginPage.enterPassword("WrongPassword123!");
    }

    @When("I enter an unregistered email")
    public void i_enter_an_unregistered_email() {
        loginPage.enterEmail("fake_" + System.currentTimeMillis() + "@example.com");
        loginPage.enterPassword("FakePass123!");
    }

    @When("I leave both login fields empty")
    public void i_leave_both_login_fields_empty() {
        loginPage.enterEmail("");
        loginPage.enterPassword("");
    }

    @When("I enter an invalid email format in login")
    public void i_enter_an_invalid_email_format_in_login() {
        loginPage.enterEmail("notanemail");
        loginPage.enterPassword("Pass123!");
    }

    @When("I click the Log In button")
    public void i_click_the_log_in_button() {
        loginPage.clickLoginButton();
    }

    @Then("I should be redirected to the welcome page")
    public void i_should_be_redirected_to_the_welcome_page() {
        Assertions.assertTrue(loginPage.isLoginSuccessful(),
                "Login should succeed and show the welcome message");
    }

    @Then("I should remain on the login page")
    public void i_should_remain_on_the_login_page() {
        Assertions.assertTrue(driver.getCurrentUrl().contains("/login"),
                "URL should still contain /login");
    }

    @Then("I should see an error message about invalid credentials")
    public void i_should_see_an_error_message_about_invalid_credentials() {
        Assertions.assertTrue(loginPage.hasToastErrorContaining("incorrect"),
                "Toast error should contain 'incorrect'");
    }

    @Then("I should see an error about required fields")
    public void i_should_see_an_error_about_required_fields() {
        Assertions.assertTrue(loginPage.hasFieldErrorContaining("required"),
                "Field error should contain 'required'");
    }

    @Then("I should see an error about invalid email in login")
    public void i_should_see_an_error_about_invalid_email_in_login() {
        boolean hasError = loginPage.hasFieldErrorContaining("valid") ||
                loginPage.hasFieldErrorContaining("email");
        Assertions.assertTrue(hasError,
                "Field error should mention 'valid' or 'email'");
    }
}
