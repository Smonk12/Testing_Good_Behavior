package fastAPI.bdd.stepdefinitions;

import fastAPI.pages.LoginPage;
import fastAPI.pages.SettingsPage;
import fastAPI.pages.SignUpPage;
import fastAPI.tests.BaseTest;
import fastAPI.utils.TestData;
import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;

public class SettingsSteps extends BaseTest {

    private SettingsPage settingsPage;
    private String tempEmail;
    private boolean passwordChanged = false;

    @Given("I am logged in and on the user settings page")
    public void i_am_logged_in_and_on_settings() {
        new LoginPage(driver).loginValid();
        settingsPage = new SettingsPage(driver);
        settingsPage.navigateTo("/settings");
    }

    @Then("I should see full name and email displayed")
    public void i_should_see_full_name_and_email() {
        Assertions.assertTrue(settingsPage.isFullNameVisible(), "Full name should be visible");
        Assertions.assertTrue(settingsPage.isEmailVisible(), "Email should be visible");
    }

    // ---------------- Password Change ----------------

    @When("I navigate to the Password tab")
    public void i_navigate_to_password_tab() {
        settingsPage.goToPasswordTab();
    }

    @When("I enter current password, new password, and confirm new password")
    public void i_enter_valid_passwords() {
        settingsPage.enterCurrentPassword(TestData.Users.VALID_PASSWORD);
        settingsPage.enterNewPassword("NewPass123!");
        settingsPage.enterConfirmPassword("NewPass123!");
        passwordChanged = true; // mark for reset later
    }

    @When("I enter wrong current password and valid new passwords")
    public void i_enter_wrong_current_password() {
        settingsPage.enterCurrentPassword("WrongPass123!");
        settingsPage.enterNewPassword("NewPass123!");
        settingsPage.enterConfirmPassword("NewPass123!");
    }

    @When("I enter valid current password but mismatched new passwords")
    public void i_enter_mismatched_new_passwords() {
        settingsPage.enterCurrentPassword(TestData.Users.VALID_PASSWORD);
        settingsPage.enterNewPassword("NewPass123!");
        settingsPage.enterConfirmPassword("Different123!");
    }

    @When("I click Save in password form")
    public void i_click_save_password() {
        settingsPage.clickSavePassword();
    }

    @Then("I should see a success message")
    public void i_should_see_success() {
        Assertions.assertTrue(settingsPage.hasToastSuccess(), "Success toast expected");
    }

    @Then("I should see an error about incorrect current password")
    public void i_should_see_incorrect_password_error() {
        Assertions.assertTrue(settingsPage.hasErrorIncorrectPassword(), "Incorrect password error expected");
    }

    @Then("I should see an error about passwords not matching")
    public void i_should_see_password_mismatch_error() {
        Assertions.assertTrue(settingsPage.hasErrorPasswordMismatch(), "Password mismatch error expected");
    }

    // ---------------- Appearance ----------------

    @When("I navigate to the Appearance tab")
    public void i_navigate_to_appearance_tab() {
        settingsPage.goToAppearanceTab();
    }

    @When("I select Dark Mode")
    public void i_select_dark_mode() {
        settingsPage.selectDarkMode();
    }

    @Then("the page should switch to dark theme")
    public void page_should_be_dark() {
        Assertions.assertTrue(settingsPage.isDarkModeApplied(), "Dark mode should be applied");
    }

    // ---------------- Account Deletion ----------------

    @When("I log out and create a temporary user")
    public void i_create_temp_user() {
        settingsPage = new SettingsPage(driver);
        settingsPage.logUserOut();

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.navigateTo("/signup");

        // create unique temp email
        tempEmail = "delete_me_" + System.currentTimeMillis() + "@example.com";
        signUpPage.enterFullName("Temp User");
        signUpPage.enterEmail(tempEmail);
        signUpPage.enterPassword("TempPass123!");
        signUpPage.enterConfirmPassword("TempPass123!");
        signUpPage.clickSignUpButton();

        Assertions.assertTrue(signUpPage.isSignUpSuccessful(), "Temp user should be created.");
    }

    @When("I log in as that temporary user")
    public void i_log_in_as_temp_user() {
        new LoginPage(driver).login(tempEmail, "TempPass123!");
        settingsPage = new SettingsPage(driver);
        settingsPage.navigateTo("/settings");
    }

    @When("I navigate to the Danger zone tab")
    public void i_navigate_to_danger_zone() {
        settingsPage.goToDangerZoneTab();
    }

    @When("I click the Delete button")
    public void i_click_delete_button() {
        settingsPage.clickDeleteAccount();
    }

    @When("I confirm account deletion")
    public void i_confirm_account_deletion() {
        settingsPage.confirmAccountDeletion();
    }

    @Then("I should be redirected to the login page after deletion")
    public void i_should_be_redirected_to_the_login_page_after_deletion() {
        Assertions.assertTrue(driver.getCurrentUrl().contains("/login"),
                "User should be redirected to login page after deletion.");
    }

    // ---------------- Hooks for cleanup ----------------

    @After
    public void resetPasswordIfChanged() {
        if (passwordChanged) {
            SettingsPage settingsPage = new SettingsPage(driver);

            settingsPage.logUserOut();

            LoginPage loginPage = new LoginPage(driver);
            loginPage.login(TestData.Users.VALID_EMAIL, "NewPass123!");

            settingsPage.navigateTo("/settings");
            settingsPage.resetToValidPassword("NewPass123!");

            passwordChanged = false;
        }
    }
}
