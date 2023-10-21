package happyScenario;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
    WebElement confirmButton;
    WebElement aislesNumber;
    WebElement aisleType;
    WebElement racksPerAisle;
    WebElement shelvesPerRack;
    public Warehouses(WebDriver driver) throws InterruptedException {
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable( // open warehouses page
                By.xpath("//a[@href='https://backofficetest.stox-eg.com/warehouses']"))).click();
        Thread.sleep(1500);
        lastWarehouseID = Integer.valueOf(wait.until(ExpectedConditions.elementToBeClickable( // catch last warehouse id
                By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[3]/div[1]/table[1]/tbody[1]/tr[1]/td[1]/span[1]"))).getText());
        System.out.println("Last warehouse ID:  " + lastWarehouseID);
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

            // add arabic name
            nameArField = driver.findElement(
                    By.xpath("//div[@class='card']//div[1]//div[2]//div[1]//input[1]"));
            nameArField.clear();
            nameArField.sendKeys("مخزن " + lastWarehouseID); // add warehouse name ARABIC

            // add prefix field
            prefixField = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[@class='card']//div[1]//div[3]//div[1]//input[1]")));
            prefixField.clear();
            prefixField.sendKeys("W" + lastWarehouseID); // add prefix warehouse

            // add area field
            areaField = wait.until(ExpectedConditions.elementToBeClickable( // add warehouse area
                    By.xpath("/html/body/div[1]/div/div[2]/div[2]/div[1]/div[2]/div/div/div/div[2]/div[2]/div[4]/div/div/div[1]/div[1]/div[2]/input")));
            areaField.clear();
            areaField.click();
            areaField.sendKeys("farshut", Keys.DOWN, Keys.ARROW_DOWN, Keys.ENTER); // not working, select edfu

            // add covered area
            coveredAreaField = wait.until(ExpectedConditions.elementToBeClickable( // add covered area
                    By.xpath("/html/body/div[1]/div/div[2]/div[2]/div[1]/div[2]/div/div/div/div[2]/div[3]/div/div/div/div[1]/div[1]/transition-group/div[2]/input")
            ));
            coveredAreaField.clear();
            coveredAreaField.click();
            coveredAreaField.sendKeys("farshut", Keys.DOWN, Keys.DOWN, Keys.ENTER); // not working, select edfu

            // change to main warehouse
            driver.findElement(By.xpath(
                    "/html/body/div[1]/div/div[2]/div[2]/div[1]/div[2]/div/div/div/div[1]/div/label/input"
            )).click();

            // add warehouse are
            addressField = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("/html/body/div[1]/div/div[2]/div[2]/div[1]/div[2]/div/div/div/div[2]/div[2]/div[1]/div/input")
            ));
            addressField.clear();
            addressField.sendKeys("Automation Address");

            // add warehouse latitude
            latitudeField = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[@class='card-body py-4']//div[2]//div[2]//div[1]//input[1]")
            ));
            latitudeField.clear();
            latitudeField.sendKeys("30.7865"); // latitude

            // add warehouse longitude
            longitudeField = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[@class='card-body py-4']//div[2]//div[3]//div[1]//input[1]")
            ));
            longitudeField.clear();
            longitudeField.sendKeys("31.0004"); // longitude
            // click add button
            driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/div[4]/button")).click(); // click add new warehouse

            // click confirm adding button
            confirmButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("/html/body/div[3]/div/div[6]/button[1]")
            ));
            confirmButton.sendKeys(Keys.ENTER);
        }



    }

    // edit warehouse layout
    public void editWarehouse(WebDriver driver){
        wait.until(ExpectedConditions.elementToBeClickable( // edit warehouse layout button
                By.xpath("/html/body/div[1]/div/div[2]/div[2]/div[1]/div[2]/div/div/div/div[2]/div[3]/div[1]/table/tbody/tr[1]/td[7]/a[1]")
        )).click();

        try { // check if in add shelves page or not
            aislesNumber = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@id=\"kt_app_content_container\"]/div/div/form/div[1]/div[1]/div/input")
            ));
            aislesNumber.sendKeys("3");

            // racks per aisle field
            racksPerAisle = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@id=\"kt_app_content_container\"]/div/div/form/div[2]/div[1]/div/input")
            ));
            racksPerAisle.sendKeys("4");

            // selves per rack field
            shelvesPerRack = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@id=\"kt_app_content_container\"]/div/div/form/div[2]/div[2]/div/input")
            ));
            shelvesPerRack.sendKeys("4");

            // select aisle type two side
            aisleType = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@id=\"kt_app_content_container\"]/div/div/form/div[1]/div[2]/div/select/option[1]")
            ));
            aisleType.click();

            // click save button to add warehouse layout
            driver.findElement(By.xpath("//*[@id=\"kt_app_content_container\"]/div/div/form/div[3]/div/button")).click();
        }catch (Exception e){
            System.out.println("Not found add shelves page continue");
        }finally {

        }
    }


}
