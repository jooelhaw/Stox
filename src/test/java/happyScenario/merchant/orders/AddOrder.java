package happyScenario.merchant.orders;

import happyScenario.constants.CreateOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddOrder {
    WebDriverWait wait;
    public AddOrder(WebDriver driver, String mandatory){
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        // open create a new order page
        driver.navigate().to("https://merchantstest.stox-eg.com/orders/create");
        addMandatoryOrder(driver, wait);
    }
    public AddOrder(WebDriver driver, Boolean full){
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        // open create a new order page
        driver.navigate().to("https://merchantstest.stox-eg.com/orders/create");
        addFullOrder(driver, wait);
    }

    // method to find fields and send keys to fields
    public static void sendKeys(WebDriverWait wait, String field, String key){
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(field))).sendKeys(key);
    }

    // fill data in mandatory fields
    public static void addMandatoryOrder(WebDriver driver, WebDriverWait wait){
        // mobile1
        sendKeys(wait, CreateOrder.mobile1, "01033166502");
        // receiver name
        sendKeys(wait, CreateOrder.receiverName, "Yousef El-haw");
        // address
        sendKeys(wait, CreateOrder.address, "Egypt - Gharbia - Tanta - Mohamed halawa st");
    }

    public static void addFullOrder(WebDriver driver, WebDriverWait wait){

    }

}
