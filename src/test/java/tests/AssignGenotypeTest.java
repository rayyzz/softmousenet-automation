package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
//import org.openqa.selenium.JavascriptExecutor;

public class AssignGenotypeTest extends BaseTest {

    @Test
    public void assignGenotypeToMouse() {
        // Step 1: Wait until home screen loads
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mice")));

        // Step 2: Navigate directly to Mice list page
        driver.get("https://softmouse.net/smdb/mouse/list.do");

        // Step 3: Wait for the results table to be visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("resultsDiv")));

        // Step 4: Find and click the checkbox of a specific mouse row
       WebElement targetRowCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tr[td[contains(text(),'M')] and td[contains(text(),'07-06-2023')] and td[contains(text(),'Dummy-Strain')]]//input[@type='checkbox']"
        )));
        // Scroll into view in case it's not visible
        //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", targetRowCheckbox);
        targetRowCheckbox.click();

        // Step 5: Click the Edit Animals button
        WebElement editButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("editMouseMenuButton")));
        editButton.click();

        System.out.println("Successfully navigated to the genotype editing page.");
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("editSave")));
        WebElement genotypeBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("editOpenCloseImage")));
        genotypeBtn.click();

        // Step 6: Wait for Gene dropdown and select value
        WebElement geneDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("geneId")));
        Select geneSelect = new Select(geneDropdown);
        geneSelect.selectByVisibleText("tdTom"); // or "VatCre", "WT"

        // Step 7: Wait for Allele dropdown and select value
        WebElement alleleDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("alleleId")));
        Select alleleSelect = new Select(alleleDropdown);
        alleleSelect.selectByVisibleText("+"); // use .selectByVisibleText("Some Allele") if you know the name

        // Step 8: Click the Edit Animals button
        WebElement addGenotypeBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("geneAlleleAdd")));
        addGenotypeBtn.click();

        // Step 9: Click the save and exit button
        WebElement saveandExitBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("editSave")));
        saveandExitBtn.click();


        

    }
}
