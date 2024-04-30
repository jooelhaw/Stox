package happyScenario.merchant.manifests;

import happyScenario.constants.CreateManifest;
import happyScenario.constants.CreateOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AddManifest {
    WebDriverWait wait;
    String lastManifestID;
    // Constructor
    public AddManifest(WebDriver driver) throws InterruptedException {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        fillData(driver); // fill data in all fields

        // click submit order
        driver.findElement(By.xpath(CreateManifest.sendBtn)).click();
        driver.findElement(By.xpath(CreateManifest.confirmBtn)).sendKeys(Keys.ENTER);
    }
    private void sendKeys(String element, String key){
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(element))).clear();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(element))).sendKeys(key);
    }

    private void fillData(WebDriver driver) throws InterruptedException {
        // fill data in the fields
        driver.findElement(By.xpath(CreateManifest.sku1)).clear(); // send sku1
        sendKeys(CreateManifest.sku1, "test-product");

        Thread.sleep(500);

        driver.findElement(By.xpath(CreateManifest.sku1Qty)).clear(); // send sku1Qty
        driver.findElement(By.xpath(CreateManifest.sku1Qty)).click();
//        driver.findElement(By.xpath(CreateManifest.sku1Qty)).sendKeys(Keys.ARROW_UP, Keys.ARROW_UP, Keys.ARROW_UP, Keys.ARROW_UP, Keys.ARROW_UP);
        sendKeys(CreateManifest.sku1Qty, "5");
    }
}
