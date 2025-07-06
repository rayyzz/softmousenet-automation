package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {

    private WebDriver driver;

    // Locators
    private By usernameField = By.name("username");
    private By passwordField = By.name("password");
    private By loginButton = By.id("secureLogin"); 

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Actions
    public void login(String username, String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement userInput = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        WebElement passInput = driver.findElement(passwordField);

        // Dismiss cookie consent if it exists
        try {
            WebElement cookieBanner = driver.findElement(By.className("cc-window"));
            if (cookieBanner.isDisplayed()) {
                WebElement dismissBtn = driver.findElement(By.className("cc-dismiss"));  // Usually the dismiss/accept button
                dismissBtn.click();
                Thread.sleep(500); // brief pause to let the banner close
            }
        } catch (Exception e) {
            // Cookie banner not present, ignore
        }

        WebElement loginBtn = driver.findElement(loginButton);

        userInput.sendKeys(username);
        passInput.sendKeys(password);
        loginBtn.click();
        }
}
