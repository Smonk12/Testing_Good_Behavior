package fastAPI.tests;

import fastAPI.utils.EnvReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseTest {

    protected WebDriver driver;

    public void setup() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(EnvReader.get("BASE_URL"));
    }

    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}