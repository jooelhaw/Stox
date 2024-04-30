package happyScenario.merchant.orders;

import happyScenario.constants.Constants;
import happyScenario.constants.CreateOrder;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Objects;

public class ConfigOrders {
    WebDriverWait wait;
    String lastOrderID;
    WebDriver driver;
    AddOrder addOrder;
    @BeforeTest
    public void openBrowser() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(Constants.MER_URL_STAGE);
        driver.findElement(By.name("email")).sendKeys(Constants.USER_MER_STAGING);
        driver.findElement(By.name("password")).sendKeys(Constants.PASS_MER_STAGING);
        driver.findElement(By.cssSelector("button[type=submit]")).click(); // logged in merchant stage
        driver.navigate().to("https://merchantstest.stox-eg.com/orders"); // navigate to orders page
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Thread.sleep(2800);
        lastOrderID = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CreateOrder.lastOrderID))).getText();
        System.out.println("before adding" + lastOrderID);
        driver.navigate().to("https://merchantstest.stox-eg.com/orders/create"); // navigate to create order page

    }

    @Test(priority = 1)
    public void addMandatoryOrders() throws InterruptedException {
        addOrder = new AddOrder(driver,"mandatory");
        Thread.sleep(500);
        driver.navigate().refresh();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Thread.sleep(2800);
        addOrder.lastOrderID = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CreateOrder.lastOrderID))).getText();
        System.out.println("after adding" + addOrder.lastOrderID);
        assert (Integer.parseInt(addOrder.lastOrderID) > Integer.parseInt(lastOrderID));

    }

}
