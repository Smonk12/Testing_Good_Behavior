package fastAPI.tests;

import fastAPI.utils.EnvReader;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

public class BaseTest {

    protected static WebDriver driver;
    protected static WebDriverWait wait;
    protected final String BASE_URL = EnvReader.get("BASE_URL", "http://localhost:5173");

    protected void setup() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        }
    }

    protected void teardown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    protected void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected void type(By locator, String text) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        el.clear();
        el.sendKeys(text);
    }

    protected boolean isDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean hasToastContaining(String text) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(@class,'toast')]//p[contains(text(),'" + text + "')]"))).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean hasFieldErrorContaining(String text) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//p[contains(@class,'error') and contains(text(),'" + text + "')]"))).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
