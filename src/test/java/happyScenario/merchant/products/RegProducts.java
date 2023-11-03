package happyScenario.merchant.products;

import happyScenario.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class RegProducts {
    WebDriver driver;
    Products products;
    @BeforeTest
    public void openBrowser(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(Constants.MER_URL_STAGE);
        driver.findElement(By.name("email")).sendKeys(Constants.USER_MER_PRODUCTION);
        driver.findElement(By.name("password")).sendKeys(Constants.PASS_MER_PRODUCTION);
        driver.findElement(By.cssSelector("button[type=submit]")).click(); // logged in admin stage
        products = new Products(driver);
    }
    @Test(priority = 1)
    public void add(){

    }
    @AfterTest
    public void closeBrowser(){
        driver.quit();
    }
}
