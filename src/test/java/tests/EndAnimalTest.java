package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
// import org.testng.Assert;

public class EndAnimalTest extends BaseTest {

    @Test
    public void endMouseAsDeceased() {
        // 1. Wait until login lands on homepage
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mice")));

        // 2. Go to animal list
        driver.get("https://softmouse.net/smdb/mouse/list.do");

        // 3. Wait for table to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("resultsDiv")));

        // 4. Locate and select mouse with known attributes
        WebElement targetRowCheckbox = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//tr[td[contains(text(),'M')] and td[contains(text(),'07-19-2023')] and td[contains(text(),'102w')] and td[contains(text(),'Stock')] and td[contains(text(),'Dummy-Strain')]]//input[@type='checkbox']")));
        targetRowCheckbox.click();


        // 5. Click "End Animals" button
        WebElement endButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("endMouseMenuButton")));
        endButton.click();

        // 6. Fill out end animal form
        WebElement endDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("endDatePicker")));
        //endDate.clear();
        endDate.sendKeys("07-02-2025"); // Use any date

        Select endTypeDropdown = new Select(wait.until(ExpectedConditions.elementToBeClickable(By.id("endTypeId"))));
        endTypeDropdown.selectByVisibleText("Found Dead");

        // Optional: enter comment
        WebElement commentBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("comment")));
        commentBox.sendKeys("Recorded mouse death - Automation");

        // Click body to dismiss any open overlays (like calendar or dropdowns)
        driver.findElement(By.tagName("body")).click();

        // Scroll the button into view
        WebElement submitBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("boxApplySubmit")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitBtn);

        // Click using JavaScript to avoid interception
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitBtn);

        System.out.println("Mouse successfully marked as deceased.");
        // ---------- Assertion Block: Filter and Verify Mouse is Listed as Dead ----------

        // 7. Refresh the animal list page
        driver.get("https://softmouse.net/smdb/mouse/list.do");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("resultsDiv")));

        // Assert the mouse is no longer visible in the list
        try {
            driver.findElement(By.xpath("//tr[td[contains(text(),'M')] and td[contains(text(),'07-19-2023')] and td[contains(text(),'102w')] and td[contains(text(),'Stock')] and td[contains(text(),'Dummy-Strain')] and td[contains(text(),'tdTom(+)')]]//input[@type='checkbox']"));
            // If found, fail the test
            throw new AssertionError("Mouse still appears in the active list after being marked as deceased.");
        } catch (org.openqa.selenium.NoSuchElementException e) {
            // Expected case — mouse not found
            System.out.println("Mouse correctly does not appear in the active list.");
        }


    }
}
