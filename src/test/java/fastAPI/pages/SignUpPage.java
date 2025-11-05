package fastAPI.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SignUpPage extends BasePage {

    private final By fullNameField        = By.name("full_name");
    private final By emailField           = By.name("email");
    private final By passwordField        = By.name("password");
    private final By confirmPasswordField = By.name("confirm_password");
    private final By signUpButton         = By.xpath("//button[contains(text(),'Sign Up')]");
    private final By loginLinkIndicator   = By.xpath("//*[contains(text(),'Log In')]");

    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    public void enterFullName(String name) {
        WebElement element = driver.findElement(fullNameField);
        element.clear();
        element.sendKeys(name);
    }

    public void enterEmail(String email) {
        WebElement element = driver.findElement(emailField);
        element.clear();
        element.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement element = driver.findElement(passwordField);
        element.clear();
        element.sendKeys(password);
    }

    public void enterConfirmPassword(String password) {
        WebElement element = driver.findElement(confirmPasswordField);
        element.clear();
        element.sendKeys(password);
    }

    public void clickSignUpButton() {
        driver.findElement(signUpButton).click();
    }

    public boolean isSignUpSuccessful() {
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.urlContains("/login"),
                    ExpectedConditions.presenceOfElementLocated(loginLinkIndicator)
            ));
            return true;
        } catch (Exception e) {
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