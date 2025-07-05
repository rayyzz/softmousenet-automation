package tests;

import org.openqa.selenium.By;
//import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;


public class CreateAnimalTest extends BaseTest {

    @Test
    public void createNewMouse() throws Exception {

        // String dob = "19-07-2023";
        // Wait until the Animals > Create New Animals page is loaded
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("appSmallContainer")));

        // Fill form fields
        WebElement malesField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sizeMale")));
        malesField.clear();
        malesField.sendKeys("2");

        WebElement femalesField = driver.findElement(By.id("sizeFemale"));
        femalesField.clear();
        femalesField.sendKeys("3");

        WebElement strainDropdown = driver.findElement(By.id("mouselineId"));
        Select select = new Select(strainDropdown);
        select.selectByVisibleText("Dummy-Strain");

        WebElement dobField = driver.findElement(By.id("birthDatePicker"));
        dobField.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ui-datepicker-div")));
        
        
        // Select year
        WebElement yearDropdown = driver.findElement(By.className("ui-datepicker-year"));
        Select yearSelect = new Select(yearDropdown);
        yearSelect.selectByVisibleText("2024");

        // Select month (0 = January, 6 = July)
        WebElement monthDropdown = driver.findElement(By.className("ui-datepicker-month"));
        Select monthSelect = new Select(monthDropdown);
        monthSelect.selectByVisibleText("Nov");

        // Then select day 19
        WebElement dateToPick = driver.findElement(By.xpath("//a[text()='11']"));
        dateToPick.click();


        // Now click Add
        WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("batchApply")));
        addButton.click();

        //confirming
        WebElement confirmButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("submitBatches")));
        confirmButton.click();

        // After confirming and submitting the new animal
        Thread.sleep(3000); // Optional pause for UI to update

        // Navigate to mouse list page after creation
        driver.get("https://softmouse.net/smdb/mouse/list.do");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("resultsDiv")));

        // Verify the new mouse with matching DOB and strain is listed
        WebElement createdMouseRow = wait.until(ExpectedConditions.visibilityOfElementLocated(
    By.xpath("//td[contains(text(), '11-11-2024')]/following-sibling::td[contains(text(),'tdTomato VatCre')]")
));

        Assert.assertTrue(createdMouseRow.isDisplayed(), "New mouse not found in the list.");

    }


}
