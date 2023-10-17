package happyScenario;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class Roles {
    private static String lastRoleID = "000";
    static private WebElement roleNameField;

    public Roles(WebDriver driver) throws InterruptedException {
        // open roles page
        driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[1]/div[2]/div/div/div[1]/span")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[1]/div[2]/div/div/div[1]/div/div/a[2]")).click();
        Thread.sleep(1000); // wait to load table of roles
    }
    void addRole(WebDriver driver) throws InterruptedException {
        try{
            lastRoleID = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[2]/div[1]/div[2]/div/div/div/div[2]/div[3]/div[1]/table/tbody/tr[1]/td[1]/span")).getText();
            driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[1]/div[2]/div/a")).click(); // add role button
            driver.findElement(By.xpath("//*[@id=\"kt_roles_select_all\"]")).click(); // select all permissions

        }catch (Exception e){
            System.out.println("not Founded add role button");

        }finally {
            roleNameField = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/div[1]/div[1]/div/input"));
            roleNameField.clear(); // clear role name
            roleNameField.sendKeys("Role " + lastRoleID);// add role name
            driver.findElement(By.xpath()).clear(); //clear email field
            driver.findElement(By.xpath()).sendKeys("role" + lastRoleID + "@automation.com");

            driver.findElement(By.xpath()).clear(); //clear pass field
            driver.findElement(By.xpath()).sendKeys("123456");
            driver.findElement(By.xpath()).click(); // click add button
            if (!checkRoleExist(driver)){// check if the name is already used
                addRole(driver);
            }
            Thread.sleep(2000); // wait to confirm on confirmation message after adding a user
        }
    }
    private Boolean checkRoleExist(WebDriver driver) throws InterruptedException {
        Thread.sleep(2000);
        try {
            WebElement errorMessage = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/small[1]"));
            System.out.println("Found same name " + errorMessage.getText());
            //clear name field
            driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]")).clear();
            //clear email field
            driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/input[1]")).clear();
            //clear password field
            driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/input[1]")).clear();
            Thread.sleep(500);
            return false;
        }catch (Exception e){
            System.out.println("Not Found Error message -> unique name");
            driver.findElement(By.xpath("//*[@id=\"kt_app_body\"]/div[3]/div[1]/div[6]/button[1]")).sendKeys(Keys.ENTER);
            return true;
        }
    }
}
