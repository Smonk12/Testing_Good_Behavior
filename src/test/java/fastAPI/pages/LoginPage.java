package fastAPI.pages;

import fastAPI.utils.TestData;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    private final By emailField = By.name("username"); // confirmed correct
    private final By passwordField = By.name("password");
    private final By loginButton = By.xpath("//button[contains(.,'Log In') or contains(.,'Login')]");
    private final By userButton = By.cssSelector("button[data-testid='user-menu']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToLoginPage() {
        navigateTo("/login");
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
    }

    public void loginValid() {
        navigateToLoginPage();
        enterEmail(TestData.Users.VALID_EMAIL);
        enterPassword(TestData.Users.VALID_PASSWORD);
        clickLoginButton();
        wait.until(ExpectedConditions.visibilityOfElementLocated(userButton));
    }

    public void login(String email, String password) {
        navigateToLoginPage();
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();

        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(userButton),
                ExpectedConditions.presenceOfElementLocated(By.cssSelector("[role='status']"))
        ));
    }

    public void enterEmail(String email) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        element.clear();
        element.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        element.clear();
        element.sendKeys(password);
    }

    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public boolean isLoginSuccessful() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(userButton));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean hasFieldErrorContaining(String text) {
        return super.hasFieldErrorContaining(text);
    }

    public boolean hasToastErrorContaining(String text) {
        return super.hasToastErrorContaining(text);
    }
}
