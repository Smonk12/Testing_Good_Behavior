package fastAPI.tests;

import fastAPI.pages.SignUpPage;
import fastAPI.utils.TestData;
import org.junit.jupiter.api.*;

public class SignUpTests extends BaseTest {

    private SignUpPage signUpPage;

    @BeforeEach
    void setUpTest() {
        setup();
        signUpPage = new SignUpPage(driver);
        signUpPage.navigateTo("/signup");
    }

    @AfterEach
    void cleanUp() {
        teardown();
    }

    @Test
    void testValidSignUp() {
        signUpPage.enterFullName(TestData.Users.DUMMY_NAME);
        signUpPage.enterEmail(TestData.Users.DUMMY_EMAIL);
        signUpPage.enterPassword(TestData.Users.DUMMY_PASSWORD);
        signUpPage.enterConfirmPassword(TestData.Users.DUMMY_PASSWORD);
        signUpPage.clickSignUpButton();

        Assertions.assertTrue(signUpPage.isSignUpSuccessful(),
                "Sign-up should succeed and redirect to login");
    }

    @Test
    void testEmptyFields() {
        signUpPage.enterFullName("");
        signUpPage.enterEmail("");
        signUpPage.enterPassword("");
        signUpPage.enterConfirmPassword("");
        signUpPage.clickSignUpButton();

        Assertions.assertTrue(signUpPage.hasFieldErrorContaining("required"),
                "Should show field error for required fields");
    }

    @Test
    void testExistingUserSignUp() {
        signUpPage.enterFullName(TestData.Users.DUMMY_NAME);
        signUpPage.enterEmail(TestData.Users.VALID_EMAIL);
        signUpPage.enterPassword(TestData.Users.VALID_PASSWORD);
        signUpPage.enterConfirmPassword(TestData.Users.VALID_PASSWORD);
        signUpPage.clickSignUpButton();

        Assertions.assertTrue(signUpPage.hasToastErrorContaining("already exists"),
                "Should show toast error for existing email");
    }

    @Test
    void testPasswordMismatch() {
        signUpPage.enterFullName(TestData.Users.DUMMY_NAME);
        signUpPage.enterEmail(TestData.Users.DUMMY_EMAIL);
        signUpPage.enterPassword("Password123!");
        signUpPage.enterConfirmPassword("Password321!");
        signUpPage.clickSignUpButton();

        boolean hasError = signUpPage.hasFieldErrorContaining("match") ||
                signUpPage.hasFieldErrorContaining("same");
        Assertions.assertTrue(hasError,
                "Should show field error for password mismatch");
    }

    @Test
    void testInvalidEmailFormat() {
        signUpPage.enterFullName(TestData.Users.DUMMY_NAME);
        signUpPage.enterEmail("notanemail");
        signUpPage.enterPassword(TestData.Users.DUMMY_PASSWORD);
        signUpPage.enterConfirmPassword(TestData.Users.DUMMY_PASSWORD);
        signUpPage.clickSignUpButton();

        boolean hasError = signUpPage.hasFieldErrorContaining("valid") ||
                signUpPage.hasFieldErrorContaining("email");
        Assertions.assertTrue(hasError,
                "Should show field error for invalid email");
    }
}