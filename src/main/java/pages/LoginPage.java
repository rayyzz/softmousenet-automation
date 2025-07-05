package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

    private WebDriver driver;

    // Locators
    private By usernameField = By.name("j_username");
    private By passwordField = By.name("j_password");
    private By loginButton = By.id("loginButton"); // Adjust if different

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Actions
    public void login(String username, String password) {
        WebElement userInput = driver.findElement(usernameField);
        WebElement passInput = driver.findElement(passwordField);
        WebElement loginBtn = driver.findElement(loginButton);

        userInput.sendKeys(username);
        passInput.sendKeys(password);
        loginBtn.click();
    }
}
