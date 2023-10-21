package happyScenario;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Warehouses {
    WebDriverWait wait;
    Integer lastWarehouseID;
    WebElement nameEnField;
    WebElement nameArField;
    WebElement prefixField;
    WebElement addressField;
    WebElement latitudeField;
    WebElement longitudeField;
    WebElement areaField;
    WebElement coveredAreaField;
    public Warehouses(WebDriver driver) throws InterruptedException {
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable( // open warehouses page
                By.xpath("//a[@href='https://backofficetest.stox-eg.com/warehouses']"))).click();
        Thread.sleep(1500);
        lastWarehouseID = Integer.valueOf(wait.until(ExpectedConditions.elementToBeClickable( // catch last warehouse id
                By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[3]/div[1]/table[1]/tbody[1]/tr[1]/td[1]/span[1]"))).getText());
        System.out.println(lastWarehouseID);
    }
    public void addWarehouse(WebDriver driver){
        try {
            wait.until(ExpectedConditions.elementToBeClickable( // click add warehouse button
                    By.xpath("//a[normalize-space()='Add Warehouse']"))).click();
        }catch (Exception e){
            System.out.println("I am in add warehouse page");
        }finally {
            nameEnField = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("(//input[@type='text'])[1]")));
            nameEnField.clear(); // clear warehouse name ENGLISH field
            nameEnField.sendKeys("Warehouse " + ++lastWarehouseID); // add warehouse name ENGLISH

            nameArField = driver.findElement(
                    By.xpath("//div[@class='card']//div[1]//div[2]//div[1]//input[1]"));
            nameArField.clear();
            nameArField.sendKeys("مخزن " + lastWarehouseID); // add warehouse name ARABIC

            prefixField = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[@class='card']//div[1]//div[3]//div[1]//input[1]")));
            prefixField.clear();
            prefixField.sendKeys("W" + lastWarehouseID); // add prefix warehouse

//            a = wait.until(ExpectedConditions.elementToBeClickable(
//                    By.xpath("(//input[@type='text'])[1]")));
//            warehouseNameField.clear();
//            warehouseNameField.sendKeys("Warehouse " + lastWarehouseID);
        }



    }
}
