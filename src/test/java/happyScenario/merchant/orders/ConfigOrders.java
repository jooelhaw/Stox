package happyScenario.merchant.orders;

import happyScenario.constants.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ConfigOrders {
    WebDriver driver;
    AddOrder addOrder;
    @BeforeTest
    public void openBrowser(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(Constants.MER_URL_STAGE);
        driver.findElement(By.name("email")).sendKeys(Constants.USER_MER_STAGING);
        driver.findElement(By.name("password")).sendKeys(Constants.PASS_MER_STAGING);
        driver.findElement(By.cssSelector("button[type=submit]")).click(); // logged in merchant stage

    }

    @Test(priority = 1)
    public void addMandatoryOrders(){addOrder = new AddOrder(driver,"mandatory");}
}
