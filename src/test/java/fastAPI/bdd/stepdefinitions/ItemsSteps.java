package fastAPI.bdd.stepdefinitions;

import fastAPI.pages.ItemsPage;
import fastAPI.pages.LoginPage;
import fastAPI.tests.BaseTest;
import fastAPI.utils.TestData;
import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ItemsSteps extends BaseTest {

    private ItemsPage itemsPage;
    private String lastCreatedTitle;

    @Given("the user is on the Items page")
    public void i_am_logged_in_and_on_the_items_page() {
        setup();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(TestData.Users.VALID_EMAIL, TestData.Users.VALID_PASSWORD);

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//p[contains(text(),'Welcome back')]")
        ));

        itemsPage = new ItemsPage(driver);
        driver.get(BASE_URL + "/items?page=1");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("table")));
        Assertions.assertTrue(itemsPage.isItemsTableVisible(),
                "Items table should be visible after login.");
    }


    @When("the user clicks the {string} button")
    public void the_user_clicks_the_button(String buttonLabel) {
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
            default:
                throw new IllegalArgumentException("Unknown button label: " + buttonLabel);
        }
    }

    @When("the user enters {string} as title")
    public void the_user_enters_as_title(String title) {
        lastCreatedTitle = title;
        itemsPage.enterTitle(title);
    }

    @When("the user enters {string} as description")
    public void the_user_enters_as_description(String description) {
        itemsPage.enterDescription(description);
    }

    @Then("a new item should appear in the list with title {string}")
    public void a_new_item_should_appear_in_the_list_with_title(String expectedTitle) {
        boolean found = wait.until(driver -> itemsPage.isItemTitlePresentAnywhere(expectedTitle));
        Assertions.assertTrue(found, "Item with title '" + expectedTitle + "' was not found in the table.");
    }

    @Then("the item should have a generated ID")
    public void the_item_should_have_a_generated_id() {
        String id = itemsPage.getIdForTitle(lastCreatedTitle);
        Assertions.assertNotNull(id, "ID not found for item with title: " + lastCreatedTitle);
        Assertions.assertFalse(id.isEmpty(), "ID is empty for item with title: " + lastCreatedTitle);
    }

    @After
    public void tearDown() {
        teardown();
    }
}
