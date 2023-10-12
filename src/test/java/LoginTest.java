import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class LoginTest {
    private static Integer userCount = 0;
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();
        driver.get(Constants.ADMIN_URL_STAGE);
        driver.findElement(By.name("email")).sendKeys("admin@admin.com");
        driver.findElement(By.name("password")).sendKeys("password");
        driver.findElement(By.cssSelector("button[type=submit]")).click();
        // open users page
        driver.findElement(By.xpath("//*[@id=\"#kt_app_sidebar_menu\"]/div[1]/span")).click();
        driver.findElement(By.xpath("//*[@id=\"#kt_app_sidebar_menu\"]/div[1]/div/div/a[1]")).click();



        // add a new user
        addUser(driver);
        addUser(driver);
   //     addUser(driver);

        // edit the last user
        editUser(driver);

        //delete user
        deleteLastUser(driver);



    }

    private static void deleteLastUser(WebDriver driver) throws InterruptedException {
        Thread.sleep(3000);
        driver.findElement(By.xpath("//div[@id='app']/div/div[2]/div[3]/div/table/tbody/tr/td[5]/button")).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector(".swal2-confirm")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"kt_app_body\"]/div[3]/div[1]/div[6]/button[1]")).sendKeys(Keys.ENTER);
    }

    private static void addUser(WebDriver driver) throws InterruptedException {
        try{
            driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/div[2]/div[1]/a[1]")).click();
            driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]")).sendKeys("User " + ++userCount);
            driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/input[1]")).sendKeys("automate" + userCount + "@test.com");
            driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/input[1]")).sendKeys("123456");
            driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/div[1]/div[2]/div/select/option[2]")).click();
            driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/div[1]/div[3]/button[1]")).click(); // click add button
            checkUserExist(driver);
            Thread.sleep(1000);
            driver.findElement(By.xpath("//*[@id=\"kt_app_body\"]/div[3]/div[1]/div[6]/button[1]")).sendKeys(Keys.ENTER);
        }catch (Exception e){
            System.out.println("not Founded add user button");
            driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]")).sendKeys("User " + (++userCount));
            driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/input[1]")).sendKeys("automate" + userCount + "@test.com");
            driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/input[1]")).sendKeys("123456");
         //   driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/div[1]/div[2]/div/select/option[2]")).click();
            driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/div[1]/div[3]/button[1]")).click(); // click add button
            checkUserExist(driver);
            Thread.sleep(1000);
            driver.findElement(By.xpath("//*[@id=\"kt_app_body\"]/div[3]/div[1]/div[6]/button[1]")).sendKeys(Keys.ENTER);
        }


    }

    private static void checkUserExist(WebDriver driver) throws InterruptedException {
        Thread.sleep(1000);
        try {
            WebElement errorMessage = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/small[1]"));
            System.out.println("Found same name");
            //clear name field
            driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]")).clear();
            //clear email field
            driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/input[1]")).clear();
            //clear password field
            driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/input[1]")).clear();
            Thread.sleep(500);
            addUser(driver);
        }catch (Exception e){
            System.out.println("Not Founded");
            Thread.sleep(1000);
            driver.findElement(By.xpath("//*[@id=\"kt_app_body\"]/div[3]/div[1]/div[6]/button[1]")).sendKeys(Keys.ENTER);
        }

           // checkUserExist(driver);

    }

    private static void editUser(WebDriver driver) throws InterruptedException {
        Thread.sleep(3000);
        WebElement userID = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[3]/div[1]/table[1]/tbody[1]/tr[1]/td[1]/span[1]"));
        driver.get("https://backofficetest.stox-eg.com/users/" + userID.getText() + "/edit");
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]")).clear();
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]")).sendKeys("Automate User");
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/div[1]/div[3]/button[1]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"kt_app_body\"]/div[3]/div[1]/div[6]/button[1]")).sendKeys(Keys.ENTER);
    }
}
