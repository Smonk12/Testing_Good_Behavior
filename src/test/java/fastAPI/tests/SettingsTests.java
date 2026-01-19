package fastAPI.tests;

import fastAPI.pages.LoginPage;
import fastAPI.pages.SettingsPage;
import fastAPI.pages.SignUpPage;
import fastAPI.utils.TestData;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;

public class SettingsTests extends BaseTest {

    private SettingsPage settingsPage;

    @BeforeEach
    void setupTest() {
        setup();
        new LoginPage(driver).loginValid();
        settingsPage = new SettingsPage(driver);
        settingsPage.navigateTo("/settings");
    }

    @AfterEach
    void cleanup() {
        teardown();
    }

    @Test
    void testViewProfile() {
        Assertions.assertTrue(settingsPage.isFullNameVisible());
        Assertions.assertTrue(settingsPage.isEmailVisible());
    }

    @Test
    void testChangePasswordSuccess() {
        settingsPage.goToPasswordTab();
        settingsPage.changePassword(TestData.Users.VALID_PASSWORD, "NewPass123!");
        Assertions.assertTrue(settingsPage.hasToastSuccess(), "Password change should succeed.");

        settingsPage.resetToValidPassword("NewPass123!");
    }

    @Test
    void testWrongCurrentPassword() {
        settingsPage.goToPasswordTab();
        settingsPage.enterCurrentPassword("Wrong123!");
        settingsPage.enterNewPassword("NewPass123!");
        settingsPage.enterConfirmPassword("NewPass123!");
        settingsPage.clickSavePassword();
        Assertions.assertTrue(settingsPage.hasErrorIncorrectPassword());
    }

    @Test
    void testPasswordMismatch() {
        settingsPage.goToPasswordTab();
        settingsPage.enterCurrentPassword(TestData.Users.VALID_PASSWORD);
        settingsPage.enterNewPassword("NewPass123!");
        settingsPage.enterConfirmPassword("Different123!");
        settingsPage.clickSavePassword();
        Assertions.assertTrue(settingsPage.hasErrorPasswordMismatch());
    }

    @Test
    void testDarkMode() {
        settingsPage.goToAppearanceTab();
        settingsPage.selectDarkMode();
        Assertions.assertTrue(settingsPage.isDarkModeApplied(),"Expected page to switch to dark mode.");
    }

    @Test
    void testDeleteDialog() {
        settingsPage = new SettingsPage(driver);
        settingsPage.logUserOut();

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.navigateTo("/signup");

        String tempEmail = "delete_me_" + System.currentTimeMillis() + "@example.com";
        signUpPage.enterFullName("Temp User");
        signUpPage.enterEmail(tempEmail);
        signUpPage.enterPassword("TempPass123!");
        signUpPage.enterConfirmPassword("TempPass123!");
        signUpPage.clickSignUpButton();

        new LoginPage(driver).login(tempEmail, "TempPass123!");
        settingsPage = new SettingsPage(driver);
        settingsPage.navigateTo("/settings");

        settingsPage.goToDangerZoneTab();
        settingsPage.clickDeleteAccount();
        settingsPage.confirmAccountDeletion();

        Assertions.assertTrue(driver.getCurrentUrl().contains("/login"),
                "User should be redirected to login page after deletion.");
    }

}