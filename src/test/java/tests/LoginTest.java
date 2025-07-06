package tests;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.LoginPage;
import utils.ConfigReader;
import java.time.Duration;

public class LoginTest {

    @Test
    public void testLogin() {
        WebDriver driver = new ChromeDriver();
        driver.get(ConfigReader.get("baseURL"));

        LoginPage login = new LoginPage(driver);
        login.login(ConfigReader.get("username"), ConfigReader.get("password"));

        // Assertions

        // Assert that the "mice" tab is visible after login
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement homeTab = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("my_profile")));
        Assert.assertTrue(homeTab.isDisplayed(), "Login failed: 'mice' tab not visible.");

        // Uncomment to test failure behavior
        // Assert.assertEquals("SoftMouse", "WrongTitle", "Intentional failure for demo");


        driver.quit();
    }
}
