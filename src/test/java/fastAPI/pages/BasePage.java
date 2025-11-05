package fastAPI.pages;

import fastAPI.utils.EnvReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    private final By fieldErrorLocator = By.cssSelector(".chakra-field__errorText");
    private final By toastErrorLocator = By.cssSelector("[data-type='error'] .chakra-toast__description");

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateTo(String path) {
        driver.get(EnvReader.get("BASE_URL") + path);
    }

    protected boolean hasFieldErrorContaining(String text) {
        try {
            List<WebElement> errors = driver.findElements(fieldErrorLocator);
            for (WebElement e : errors) {
                if (e.getText().toLowerCase().contains(text.toLowerCase())) {
                    return true;
                }
            }
            return false;
        } catch (Exception ex) {
            return false;
        }
    }

    protected boolean hasToastErrorContaining(String text) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(toastErrorLocator));
            List<WebElement> toasts = driver.findElements(toastErrorLocator);
            for (WebElement e : toasts) {
                if (e.getText().toLowerCase().contains(text.toLowerCase())) {
                    return true;
                }
            }
            return false;
        } catch (Exception ex) {
            return false;
        }
    }
}