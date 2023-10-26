package happyScenario;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class RegCategories {
    Categories categories;
    WebDriver driver;
    @BeforeTest
    public void openBrowser() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(Constants.ADMIN_URL_STAGE); // get admin stage link
        driver.findElement(By.name("email")).sendKeys("admin@admin.com");
        driver.findElement(By.name("password")).sendKeys("password");
        driver.findElement(By.cssSelector("button[type=submit]")).click(); // logged in admin stage
        categories = new Categories(driver);
    }
    @AfterTest
    public void closeBrowser(){
        driver.quit();
    }
}
