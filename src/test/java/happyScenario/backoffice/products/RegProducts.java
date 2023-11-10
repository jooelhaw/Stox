package happyScenario.backoffice.products;

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
    public void openBrowser() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(Constants.ADMIN_URL_STAGE);
        driver.findElement(By.name("email")).sendKeys(Constants.USER_ADMIN_STAGING);
        driver.findElement(By.name("password")).sendKeys(Constants.PASS_ADMIN_STAGING);
        driver.findElement(By.cssSelector("button[type=submit]")).click(); // logged in merchant stage
        products = new Products(driver);
    }
    @Test(priority = 1)
    public void add() throws InterruptedException {
        products.addProduct(driver);
    }
    @Test(priority = 3)
    public void delete() throws InterruptedException {
        products.deleteFromList(driver);
    }
    @Test(priority = 2)
    public void edit() throws InterruptedException {
        products.editProduct(driver);
    }

    @AfterTest
    public void closeBrowser() throws InterruptedException {
        driver.quit();
    }
}
