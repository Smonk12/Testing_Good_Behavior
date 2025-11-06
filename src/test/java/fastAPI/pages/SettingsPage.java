package fastAPI.pages;

import fastAPI.tests.BaseTest;
import fastAPI.utils.TestData;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SettingsPage extends BaseTest {

    // Tabs
    private final By myProfileTab = By.cssSelector("button[data-value='my-profile']");
    private final By passwordTab = By.cssSelector("button[data-value='password']");
    private final By appearanceTab = By.cssSelector("button[data-value='appearance']");
    private final By dangerZoneTab = By.cssSelector("button[data-value='danger-zone']");

    // Profile info
    private final By fullNameField = By.cssSelector("p.css-byswsa");
    private final By emailField = By.cssSelector("p.css-1h3nhjf");

    // Password fields
    private final By currentPasswordInput = By.name("current_password");
    private final By newPasswordInput = By.name("new_password");
    private final By confirmPasswordInput = By.name("confirm_password");
    private final By savePasswordButton = By.cssSelector("button.css-1brcduv");
    private final By passMismatchError = By.xpath("//*[contains(@class, 'chakra-field__errorText') and contains(text(), 'passwords do not match')]");


    // Appearance
    private final By darkModeLabel = By.xpath("//label[contains(.,'Dark Mode')]");
    private final By bodyTag = By.tagName("body");

    // Danger Zone
    private final By deleteButton = By.cssSelector("button.css-12uh7mz");

    // Other
    private final By userButton = By.cssSelector("button[data-testid='user-menu']");
    private final By logOutButton = By.cssSelector("div[data-value='logout']");



    public SettingsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateTo(String path) {
        driver.get(BASE_URL + path);
        wait.until(ExpectedConditions.visibilityOfElementLocated(myProfileTab));
    }

    // Tabs
    public void goToPasswordTab() {
        click(passwordTab);
        wait.until(ExpectedConditions.visibilityOfElementLocated(currentPasswordInput));
    }

    public void goToAppearanceTab() {
        click(appearanceTab);
    }

    public void goToDangerZoneTab() {
        click(dangerZoneTab);
        wait.until(ExpectedConditions.visibilityOfElementLocated(deleteButton));
    }

    // Profile
    public boolean isFullNameVisible() {
        return isDisplayed(fullNameField);
    }

    public boolean isEmailVisible() {
        return isDisplayed(emailField);
    }

    // Password
    public void enterCurrentPassword(String pass) {
        type(currentPasswordInput, pass);
    }

    public void enterNewPassword(String pass) {
        type(newPasswordInput, pass);
    }

    public void enterConfirmPassword(String pass) {
        type(confirmPasswordInput, pass);
    }

    public void clickSavePassword() {
        click(savePasswordButton);
    }

    public void confirmAccountDeletion() {
        By confirmDelete = By.xpath("//*[@id=\"dialog:_r_a_:content\"]/form/div[3]/div/button[2]");

        click(confirmDelete);

        wait.until(ExpectedConditions.urlContains("/login"));
    }


    public void logUserOut() {
        By toast = By.cssSelector("div[data-scope='toast']");
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(toast));
        } catch (TimeoutException ignored) {
        }

        WebElement userMenu = wait.until(ExpectedConditions.elementToBeClickable(userButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", userMenu);

        WebElement logout = wait.until(ExpectedConditions.elementToBeClickable(logOutButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", logout);

        wait.until(ExpectedConditions.urlContains("/login"));
    }


    public boolean hasErrorPasswordMismatch() {
        try {
            WebElement error = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(passMismatchError));
            return error.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }


    public boolean hasToast(String type, String messageContains) {
        try {
            By toast = By.cssSelector("div[data-scope='toast'][data-type='" + type + "']");
            WebElement toastElement = wait.until(ExpectedConditions.visibilityOfElementLocated(toast));

            String text = toastElement.getText().toLowerCase();
            return text.contains(messageContains.toLowerCase());
        } catch (TimeoutException e) {
            return false;
        }
    }


    public boolean hasToastSuccess() {
        return hasToast("success", "password updated");
    }

    public boolean hasErrorIncorrectPassword() {
        return hasToast("error", "incorrect") || hasFieldErrorContaining("current");
    }


    public void changePassword(String current, String newPassword) {
        goToPasswordTab();
        enterCurrentPassword(current);
        enterNewPassword(newPassword);
        enterConfirmPassword(newPassword);
        clickSavePassword();

        // Wait for the success toast to appear
        By toast = By.cssSelector("div[data-scope='toast'][data-type='success']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(toast));
    }


    public void resetToValidPassword(String currentPassword) {
        changePassword(currentPassword, TestData.Users.VALID_PASSWORD);
        hasToastSuccess();
    }

    // Appearance
    public void selectDarkMode() {
        WebElement label = wait.until(ExpectedConditions.elementToBeClickable(darkModeLabel));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", label);
    }

    public boolean isDarkModeApplied() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("html")));
        WebElement html = driver.findElement(By.tagName("html"));
        String classAttr = html.getAttribute("class");
        String colorScheme = html.getAttribute("style");

        boolean isDarkClass = classAttr != null && classAttr.contains("dark");
        boolean isDarkScheme = colorScheme != null && colorScheme.contains("dark");

        return isDarkClass || isDarkScheme;
    }

    public boolean isLightModeApplied() {
        WebElement html = driver.findElement(By.tagName("html"));
        String classAttr = html.getAttribute("class");
        String colorScheme = html.getAttribute("style");

        return (classAttr != null && classAttr.contains("light")) ||
                (colorScheme != null && colorScheme.contains("light"));
    }

    // Danger Zone
    public void clickDeleteAccount() {
        click(deleteButton);
    }

    public boolean isDeleteDialogOpen() {
        try {
            By dialog = By.cssSelector("div[role='dialog']");
            return wait.until(ExpectedConditions.visibilityOfElementLocated(dialog)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
