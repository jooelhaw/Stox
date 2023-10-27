package happyScenario.categories;

import happyScenario.Constants;
import happyScenario.categories.Categories;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

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
    @Test(priority = 1)
    public void add(){
        categories.addCategory(driver,null);
    }
    @Test(priority = 2)
    public void addSub(){
        categories.addSubCategories(driver);
    }
    @Test(priority = 3)
    public void checkSubCategoriesLevel() throws InterruptedException {
        categories.checkMaxLevel(driver);
    }
    @Test(priority = 4)
    public void edit(){
        categories.editLastCategory(driver);
    }
    @Test(priority = 5)
    public void delete(){
        categories.deleteLastCategory(driver);
    }

}
