package fastAPI.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    private final By emailField          = By.name("username");
    private final By passwordField       = By.name("password");
    private final By loginButton         = By.xpath("//button[contains(text(),'Log In')]");
    private final By welcomeMessage     = By.xpath("//p[contains(text(),'Welcome back, nice to see you again!')]");

    public LoginPage(WebDriver driver) {
        super(driver);
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

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public boolean isLoginSuccessful() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(welcomeMessage));
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