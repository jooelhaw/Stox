package happyScenario.backoffice.users;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class Roles {
    private static Integer lastRoleID = 0;
    private static WebElement roleNameField;
    private static WebElement roleDescField;
    private static WebElement editRoleButton;
    private static WebElement errorMessage;
    WebDriverWait wait;

    public Roles(WebDriver driver) throws InterruptedException {
        // open roles page
        driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[1]/div[2]/div/div/div[1]/span")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[1]/div[2]/div/div/div[1]/div/div/a[2]")).click();
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        Thread.sleep(1000); // wait to load table of roles
    }
    public void addRole(WebDriver driver) throws InterruptedException {
        try{
            lastRoleID = Integer.valueOf(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div[2]/div[2]/div[1]/div[2]/div/div/div/div[2]/div[3]/div[1]/table/tbody/tr[1]/td[1]/span"))).getText());
            driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[1]/div[2]/div/a")).click(); // add role button
            driver.findElement(By.xpath("//*[@id=\"kt_roles_select_all\"]")).click(); // select all permissions

        }catch (Exception e){
            System.out.println("not Founded add role button");

        }finally {
            roleNameField = driver.findElement(By.xpath("//div[@class='row']//div[1]//div[1]//input[1]"));
            roleNameField.clear(); // clear role name
            roleNameField.sendKeys("Role " + ++lastRoleID);// add role name
            roleDescField = driver.findElement(By.xpath("//div[@class='row']//div[2]//div[1]//input[1]"));
            roleDescField.clear(); // clear description field
            roleDescField.sendKeys("Automation role full access"); // add role description
            driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/div[3]/button")).click(); //click add button
            if (!checkRoleExist(driver)){// check if the name is already used
                addRole(driver);
            }
        }
    }
    private Boolean checkRoleExist(WebDriver driver) throws InterruptedException {
        try {
            WebElement errorMessage = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app\"]/div/div[2]/div[1]/div[1]/div/div")));
            System.out.println("Found same name " + errorMessage.getText());
            //clear role name field
            roleNameField.clear();
            //clear role description
            roleDescField.clear();
            return false;
        }catch (Exception e){
            System.out.println("Not Found Error message -> unique role name");
//            driver.findElement(By.xpath("//*[@id=\"kt_app_body\"]/div[3]/div[1]/div[6]/button[1]")).sendKeys(Keys.ENTER);
            return true;
        }
    }
    public void editLastRole(WebDriver driver) throws InterruptedException {

        try {
            // click edit button on last added role
            editRoleButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div[2]/div[2]/div[1]/div[2]/div/div/div/div[2]/div[3]/div[1]/table/tbody/tr[1]/td[5]/a")));
            editRoleButton.click();
        }catch (Exception e){
            System.out.println("not Founded edit role button");
        }finally {
            roleNameField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='row']//div[1]//div[1]//input[1]")));
            roleDescField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='row']//div[2]//div[1]//input[1]")));

            roleNameField.clear(); // clear role name field
            roleNameField.sendKeys("Role " + ++lastRoleID);// add role name
            roleDescField.clear(); //clear description field
            roleDescField.sendKeys("Automation role full access"); // add role description
            driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/div[3]/button")).click(); //click edit button
            if (!checkRoleExist(driver)){
                editLastRole(driver);
            }
        }
    }


}
