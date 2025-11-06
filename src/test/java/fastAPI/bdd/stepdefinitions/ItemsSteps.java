package fastAPI.bdd.stepdefinitions;

import fastAPI.bdd.pages.ItemsPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class ItemsSteps {

    private WebDriver driver;
    private ItemsPage itemsPage;
    private String lastCreatedTitle;


    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("http://localhost:5173/login");

        driver.findElement(By.name("username")).sendKeys("p.bobita@gmail.com");
        driver.findElement(By.name("password")).sendKeys("asdasdasd");
        driver.findElement(By.cssSelector("button[type='submit']")).click();


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//p[text()='Welcome back, nice to see you again!']")
        ));

        driver.get("http://localhost:5173/items?page=1");

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("table")));

        itemsPage = new ItemsPage(driver);
    }

    @Given("the user is on the Items page")
    public void theUserIsOnItemsPage() {
        assertTrue(itemsPage.isItemsTableVisible());
    }

    @When("the user clicks the {string} button")
    public void theUserClicksButton(String buttonLabel) {
        switch (buttonLabel.toLowerCase()) {
            case "add item":
                itemsPage.clickAddItem();
                break;
            case "save":
                itemsPage.submitNewItem();
                break;
            case "cancel":
                itemsPage.cancelNewItem();
                break;
        }
    }

    @When("the user enters {string} as title")
    public void theUserEntersTitle(String title) {
        lastCreatedTitle = title;
        itemsPage.enterTitle(title);
    }

    @When("the user enters {string} as description")
    public void theUserEntersDescription(String description) {
        itemsPage.enterDescription(description);
    }

    @Then("a new item should appear in the list with title {string}")
    public void newItemShouldAppear(String expectedTitle) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean found = wait.until(driver -> itemsPage.isItemTitlePresentAnywhere(expectedTitle));
        assertTrue(found, "Item with title '" + expectedTitle + "' was not found in the table.");
    }

    @Then("the item should have a generated ID")
    public void itemShouldHaveGeneratedId() {
        String id = itemsPage.getIdForTitle(lastCreatedTitle);
        assertNotNull(id, "ID not found for item with title: " + lastCreatedTitle);
        assertFalse(id.isEmpty(), "ID is empty for item with title: " + lastCreatedTitle);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}