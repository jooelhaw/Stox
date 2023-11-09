package happyScenario.backoffice.warehouses;

import happyScenario.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class RegWarehouses {
    WebDriver driver;
    Warehouses warehouse;
    @BeforeTest
    public void openBrowser() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(Constants.ADMIN_URL_STAGE); // get admin stage link
        driver.findElement(By.name("email")).sendKeys("admin@admin.com");
        driver.findElement(By.name("password")).sendKeys("password");
        driver.findElement(By.cssSelector("button[type=submit]")).click(); // logged in admin stage
        warehouse = new Warehouses(driver);
    }

    @AfterTest
    public void closeBrowser() {
        driver.quit();
    }
    @Test(priority = 1)
    public void addWarehouse(){
        warehouse.addWarehouse(driver);
    }

    @Test(priority = 2)
    public void editLastWarehouseLayout(){
        warehouse.editWarehouse(driver);
    }

}
