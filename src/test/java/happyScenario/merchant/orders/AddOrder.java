package happyScenario.merchant.orders;

import happyScenario.constants.CreateOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AddOrder {
    String lastOrderID;
    WebDriverWait wait;
    public AddOrder(WebDriver driver, String mandatory) throws InterruptedException {
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        addMandatoryOrder(driver, wait);

        // click submit order
        driver.findElement(By.xpath(CreateOrder.submitBtn)).click();
        driver.findElement(By.xpath(CreateOrder.confirmBtn)).sendKeys(Keys.ENTER);
    }
    public AddOrder(WebDriver driver, Boolean full){
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        addFullOrder(driver, wait);

        // click submit order
        driver.findElement(By.xpath(CreateOrder.submitBtn)).click();
        driver.findElement(By.xpath(CreateOrder.confirmBtn)).sendKeys(Keys.ENTER);
    }

    // method to find fields and send keys to fields
    public static void sendKeys(WebDriverWait wait, String field, String key){
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(field))).sendKeys(key);
    }

    // fill data in mandatory fields
    public static void addMandatoryOrder(WebDriver driver, WebDriverWait wait) throws InterruptedException {
        // mobile1
        driver.findElement(By.xpath(CreateOrder.mobile1)).clear();
        sendKeys(wait, CreateOrder.mobile1, "01050528862");
        driver.findElement(By.xpath(CreateOrder.mobile1)).sendKeys(Keys.ENTER);
        // receiver name
        driver.findElement(By.xpath(CreateOrder.receiverName)).clear();
        sendKeys(wait, CreateOrder.receiverName, "Yousef El-haw");
        // address
        driver.findElement(By.xpath(CreateOrder.address)).clear();
        sendKeys(wait, CreateOrder.address, "Egypt - Gharbia - Tanta - Mohamed halawa st");
        // area search
        sendKeys(wait, CreateOrder.area, "tanta");
        // area select
        driver.findElement(By.xpath(CreateOrder.areaChild)).click();
        // payment type open dropdown menu
        driver.findElement(By.xpath(CreateOrder.payment)).click();
        // select CC payment
        driver.findElement(By.xpath(CreateOrder.payment)).sendKeys(Keys.DOWN, Keys.ENTER);
        // sku1
        driver.findElement(By.xpath(CreateOrder.sku1)).clear();
        sendKeys(wait, CreateOrder.sku1, "test-product");
        Thread.sleep(500);

        // sku1QTY
        driver.findElement(By.xpath(CreateOrder.sku1Qty)).clear();
        driver.findElement(By.xpath(CreateOrder.sku1Qty)).click();
        driver.findElement(By.xpath(CreateOrder.sku1Qty)).sendKeys(Keys.ARROW_UP, Keys.ARROW_UP);
    }

    public static void addFullOrder(WebDriver driver, WebDriverWait wait){

    }

}
