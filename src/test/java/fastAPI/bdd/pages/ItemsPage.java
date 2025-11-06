package fastAPI.bdd.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.NoSuchElementException;

public class ItemsPage {
    private WebDriver driver;

    @FindBy(css = "button[value='add-item']")
    private WebElement addItemButton;

    @FindBy(css = "input[name='title']")
    private WebElement titleInput;

    @FindBy(css = "input[name='description']")
    private WebElement descriptionInput;

    @FindBy(css = "button[type='submit']")
    private WebElement saveButton;

    @FindBy(xpath = "//button[text()='Cancel']")
    private WebElement cancelButton;

    public ItemsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickAddItem() {
        addItemButton.click();
    }

    public boolean isItemsTableVisible() {
        try {
            WebElement table = driver.findElement(By.cssSelector("table"));
            return table.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void enterTitle(String title) {
        titleInput.clear();
        titleInput.sendKeys(title);
    }

    public void enterDescription(String description) {
        descriptionInput.clear();
        descriptionInput.sendKeys(description);
    }

    public void submitNewItem() {
        saveButton.click();
    }

    public void cancelNewItem() {
        cancelButton.click();
    }

    public void clickMenuButtonForTitle(String title) {
        WebElement menuButton = driver.findElement(By.xpath(
                "//td[text()='" + title + "']/..//button"
        ));
        menuButton.click();
    }

    public boolean isItemTitlePresentAnywhere(String title) {
        try {
            WebElement itemCell = driver.findElement(By.xpath("//table//td[text()='" + title + "']"));
            return itemCell.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public String getIdForTitle(String title) {
        try {
            WebElement row = driver.findElement(By.xpath("//td[text()='" + title + "']/parent::tr"));
            WebElement idCell = row.findElement(By.xpath("./td[1]"));
            return idCell.getText();
        } catch (NoSuchElementException e) {
            return null;
        }
    }
}