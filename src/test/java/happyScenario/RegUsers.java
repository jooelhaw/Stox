package happyScenario;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class RegUsers {
    //ff
    WebDriver driver;
    Roles roles;
    Users users;
    @BeforeTest
    public void openBrowser() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(Constants.ADMIN_URL_STAGE); // get admin stage link
        driver.findElement(By.name("email")).sendKeys("admin@admin.com");
        driver.findElement(By.name("password")).sendKeys("password");
        driver.findElement(By.cssSelector("button[type=submit]")).click(); // logged in admin stage
//        users = new Users(driver); // open users page
        roles = new Roles(driver); // open roles page
    }
    @AfterTest
    public void closeBrowser(){
        driver.quit();
    }

    @Test (priority = 1)
    public void add() throws InterruptedException{
//        users.addUser(driver);
        System.out.print("done");

    }
    @Test (priority = 2)
    public void checkCreationDate() throws InterruptedException {
//        users.checkCreationDate(driver);
    }
    @Test (priority = 3)
    public void editLast() throws InterruptedException {
//        users.editLastUser(driver);
    }
    @Test (priority = 4)
    public void searchLast() throws InterruptedException {
//        users.searchUser(driver);
    }
    @Test (priority = 5)
    public void delete() throws InterruptedException {
//        users.deleteLastUser(driver);
    }



}
