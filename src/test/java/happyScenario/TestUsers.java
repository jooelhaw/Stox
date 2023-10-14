package happyScenario;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestUsers {
    //ff
    WebDriver driver;
    Users users;
    @BeforeTest
    public void openBrowser() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(Constants.ADMIN_URL_STAGE);
        driver.findElement(By.name("email")).sendKeys("admin@admin.com");
        driver.findElement(By.name("password")).sendKeys("password");
        driver.findElement(By.cssSelector("button[type=submit]")).click();
        users = new Users(driver);
    }
    @AfterTest
    public void closeBrowser(){
        driver.quit();
    }

    @Test (priority = 1)
    public void deleteUser() throws InterruptedException {
        users.deleteLastUser(driver);
    }
    @Test (priority = 2)
    public void addUser() throws InterruptedException{
        users.addUser(driver);
    }
    @Test (priority = 3)
    public void editLastUser() throws InterruptedException {
        users.editLastUser(driver);
    }
    @Test (priority = 4)
    public void searchLastUser() throws InterruptedException {
        users.searchUser(driver);
    }



}
