package tests;

import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;

public class LoginTest {

    @Test
    public void testLogin() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://softmouse.net");

        LoginPage login = new LoginPage(driver);
        login.login("raiyan.islam", "Ballislife_12");

        // Add assertions here

        driver.quit();
    }
}
