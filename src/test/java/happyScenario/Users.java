package happyScenario;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Users {
    private static Integer userCount = 0;
    public Users(WebDriver driver){
        // open users page
        driver.findElement(By.xpath("//*[@id=\"#kt_app_sidebar_menu\"]/div[1]/span")).click();
        driver.findElement(By.xpath("//*[@id=\"#kt_app_sidebar_menu\"]/div[1]/div/div/a[1]")).click();
    }
    static void deleteLastUser(WebDriver driver) throws InterruptedException {
        Thread.sleep(3000);
        driver.findElement(By.xpath("//div[@id='app']/div/div[2]/div[3]/div/table/tbody/tr/td[5]/button")).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector(".swal2-confirm")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"kt_app_body\"]/div[3]/div[1]/div[6]/button[1]")).sendKeys(Keys.ENTER);
    }

    void addUser(WebDriver driver) throws InterruptedException {
        try{
            driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/div[2]/div[1]/a[1]")).click();
            driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/div[1]/div[2]/div/select/option[2]")).click();
        }catch (Exception e){
            System.out.println("not Founded add user button");

        }finally {
            driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]")).sendKeys("User " + ++userCount);
            driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/input[1]")).clear(); //clear email field
            driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/input[1]")).sendKeys("automate" + userCount + "@test.com");
            driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/input[1]")).clear(); //clear pass field
            driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/input[1]")).sendKeys("123456");
            driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/div[1]/div[3]/button[1]")).click(); // click add button
            checkUserExist(driver); // check if the name is already used
            Thread.sleep(2000); // wait to confirm on confirmation message after adding a user
            //  driver.findElement(By.xpath("//*[@id=\"kt_app_body\"]/div[3]/div[1]/div[6]/button[1]")).sendKeys(Keys.ENTER);
        }


    }

    private void checkUserExist(WebDriver driver) throws InterruptedException {
        Thread.sleep(2000);
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
            System.out.println("Not Found Error message -> unique name");
            // Thread.sleep(1000);
            driver.findElement(By.xpath("//*[@id=\"kt_app_body\"]/div[3]/div[1]/div[6]/button[1]")).sendKeys(Keys.ENTER);
        }

    }

    static void editLastUser(WebDriver driver) throws InterruptedException {
        Thread.sleep(3000);
//        WebElement userID = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[3]/div[1]/table[1]/tbody[1]/tr[1]/td[1]/span[1]"));
//        driver.get("https://backofficetest.stox-eg.com/users/" + userID.getText() + "/edit");
        driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[2]/div[1]/div[2]/div/div/div/div[2]/div[3]/div[1]/table/tbody/tr[1]/td[5]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]")).clear();
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]")).sendKeys("Automate User");
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/div[1]/div[3]/button[1]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"kt_app_body\"]/div[3]/div[1]/div[6]/button[1]")).sendKeys(Keys.ENTER);
    }
}
