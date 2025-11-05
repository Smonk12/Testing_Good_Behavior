package fastAPI.tests;

import fastAPI.pages.LoginPage;
import fastAPI.utils.TestData;
import org.junit.jupiter.api.*;

public class LoginTests extends BaseTest {

    private LoginPage loginPage;

    @BeforeEach
    void setUpTest() {
        setup();
        loginPage = new LoginPage(driver);
        loginPage.navigateTo("/login");
    }

    @AfterEach
    void cleanUp() {
        teardown();
    }

    @Test
    void testValidLogin() {
        loginPage.enterEmail(TestData.Users.VALID_EMAIL);
        loginPage.enterPassword(TestData.Users.VALID_PASSWORD);
        loginPage.clickLoginButton();

        Assertions.assertTrue(loginPage.isLoginSuccessful(),
                "Login should succeed and show welcome message");
    }

    @Test
    void testInvalidPassword() {
        loginPage.enterEmail(TestData.Users.VALID_EMAIL);
        loginPage.enterPassword("WrongPassword123!");
        loginPage.clickLoginButton();

        Assertions.assertTrue(loginPage.hasToastErrorContaining("incorrect"),
                "Should show toast error about incorrect password");
    }

    @Test
    void testNonexistentUser() {
        String fakeEmail = "fakeuser_" + System.currentTimeMillis() + "@example.com";
        loginPage.enterEmail(fakeEmail);
        loginPage.enterPassword("FakePassword!");
        loginPage.clickLoginButton();

        Assertions.assertTrue(loginPage.hasToastErrorContaining("incorrect"),
                "Should show toast error for non-existent user");
    }

    @Test
    void testEmptyFields() {
        loginPage.enterEmail("");
        loginPage.enterPassword("");
        loginPage.clickLoginButton();

        Assertions.assertTrue(loginPage.hasFieldErrorContaining("required"),
                "Should show field error for required fields");
    }

    @Test
    void testInvalidEmailFormat() {
        loginPage.enterEmail("invalidemail");
        loginPage.enterPassword("SomePassword!");
        loginPage.clickLoginButton();

        boolean hasError = loginPage.hasFieldErrorContaining("valid") ||
                loginPage.hasFieldErrorContaining("email");
        Assertions.assertTrue(hasError,
                "Should show field error for invalid email format");
    }
}