package tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class CreateCageTest extends BaseTest {

    @Test
    public void createCageWithMouse() {
        // Step 1: Go to Animals tab (Mice tab)
        WebElement miceTab = wait.until(ExpectedConditions.elementToBeClickable(By.id("mice")));
        miceTab.click();

        // Step 2: Go to Mouse List page (Move Animals is usually here)
        driver.get("https://softmouse.net/smdb/mouse/list.do");  // You can also navigate via UI if needed

        // Step 3: Wait for table of animals to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("resultsDiv")));


        //Debug
        List<WebElement> rows = driver.findElements(By.xpath("//table//tr"));
        for (WebElement row : rows) {
            System.out.println("ROW TEXT: " + row.getText());
        }

        // Step 4: Find and select the row with matching Sex, DOB, Strain
        // You may need to refine this depending on how the table is structured
        WebElement targetRowCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tr[td[contains(text(),'M')] and td[contains(text(),'07-06-2023')] and td[contains(text(),'Dummy-Strain')]]//input[@type='checkbox']"
        )));
        targetRowCheckbox.click();

        // Step 5: Click Move button
        WebElement moveMouseBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("moveMouseMenuButton")));
        moveMouseBtn.click();

        // Optional: add more steps here to handle the Move dialog / new cage creation
        System.out.println("Clicked Move button for matching mouse.");

        // create/update cage
        WebElement createCageBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("moveApply")));
        createCageBtn.click();
        
    }
}
