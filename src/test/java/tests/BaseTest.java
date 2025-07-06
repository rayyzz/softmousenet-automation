package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import utils.ConfigReader;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().clearDriverCache().setup();
        WebDriverManager.chromedriver().clearResolutionCache().setup();

        //verify the export download
        String downloadFilepath = System.getProperty("user.dir") + "/downloads";
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", downloadFilepath);

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);

        // initialize driver with options
        driver = new ChromeDriver(options);


        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.manage().window().maximize();
        driver.get(ConfigReader.get("baseURL"));

        // Login
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsernameEmail")));
        emailField.sendKeys(ConfigReader.get("username"));

        WebElement passwordField = driver.findElement(By.id("inputPassword"));
        passwordField.sendKeys(ConfigReader.get("password"));

        WebElement loginButton = driver.findElement(By.id("secureLogin"));
        loginButton.click();

        // Wait for the dashboard root element to ensure login completed
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("root")));

        // Now go directly to the desired page
        driver.get("https://softmouse.net/smdb/mouseline/list.do?reqCode=gotoMouselinelist");

        // Click the "Mice" tab
        WebElement miceTab = wait.until(ExpectedConditions.elementToBeClickable(By.id("mice")));
        miceTab.click();

        WebElement createMouseBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("newMouseMenuButton")));
        createMouseBtn.click();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
