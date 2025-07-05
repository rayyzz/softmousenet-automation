package tests;

import java.io.File;
import org.testng.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
//import pages.ExportPage;

import utils.ConfigReader;
import utils.FileUtils;

public class ExportDataTest extends BaseTest {
    
    @Test
    public void exportColonyData() throws InterruptedException {
        // 1. Wait until login lands on homepage
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mice")));

        // 2. Go to animal list
        driver.get("https://softmouse.net/smdb/mouse/list.do");

        // 3. Wait for table to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("resultsDiv")));

        WebElement exportBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("exportMouseMenuButton")));
        exportBtn.click();
        System.out.println("Export button clicked");

        Thread.sleep(20000);

        String downloadDir = ConfigReader.get("downloadPath");
        File downloaded = FileUtils.getLatestFileFromDir(downloadDir, ".xlsx");

        Assert.assertNotNull(downloaded, "No xlsx file was downloaded.");
        Assert.assertTrue(downloaded.exists(), "Downloaded file does not exist.");
    }
}
