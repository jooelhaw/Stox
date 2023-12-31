package happyScenario.backoffice.users;

import org.openqa.selenium.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class Users {
    private static Integer userCount = 0;
    public Users(WebDriver driver) throws InterruptedException {
        // open users page
        driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[1]/div[2]/div/div/div[1]/span")).click();
        driver.findElement(By.xpath("//*[@id=\"#kt_app_sidebar_menu\"]/div[1]/div/div/a[1]")).click();
    }
    public void deleteLastUser(WebDriver driver) throws InterruptedException {
        Thread.sleep(3000);
        driver.findElement(By.xpath("//div[@id='app']/div/div[2]/div[3]/div/table/tbody/tr/td[5]/button")).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector(".swal2-confirm")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"kt_app_body\"]/div[3]/div[1]/div[6]/button[1]")).sendKeys(Keys.ENTER);
    }

    public void addUser(WebDriver driver) throws InterruptedException {
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
            if (!checkUserExist(driver)){// check if the name is already used
                addUser(driver);
            }
            Thread.sleep(2000); // wait to confirm on confirmation message after adding a user
        }
    }

    private Boolean checkUserExist(WebDriver driver) throws InterruptedException {
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

    public void editLastUser(WebDriver driver) throws InterruptedException {
        Thread.sleep(1000);
        try {
            driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[2]/div[1]/div[2]/div/div/div/div[2]/div[3]/div[1]/table/tbody/tr[1]/td[5]/a")).click();
            addNewRole(driver);
            Thread.sleep(1000);
        }catch (Exception e){
            System.out.println("not Founded edit user button");
        }finally {
            driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]")).clear();
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]")).sendKeys("User " + ++userCount);
            driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/input[1]")).clear(); //clear email field
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/input[1]")).sendKeys("automate" + userCount + "@test.com");
            driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/div[1]/div[3]/button[1]")).click();
            if (!checkUserExist(driver)){
                editLastUser(driver);
            }
            Thread.sleep(1000);
        }
    }
    public void searchUser(WebDriver driver) throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/div[1]/div[1]/input[1]")).sendKeys("User " + userCount);
        Thread.sleep(3000);
        String resultCount = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[3]/div[2]/div[1]/div[2]/span[5]")).getText();
        System.out.println("Count of results: " + resultCount);
        if (resultCount.equals("1")){
            System.out.println("Result passed : 1 result " + resultCount);
        }
    }
    void addNewRole(WebDriver driver) throws InterruptedException {
        try {
            // Find and click the "New" button to add a new role
            WebElement newButton = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/label[1]/a[1]"));
            newButton.click();

            Thread.sleep(500);

            // Select all permissions
            WebElement selectAll = driver.findElement(By.xpath("//*[@id=\"kt_roles_select_all\"]"));
            selectAll.click();

            // Deactivate the role
            WebElement deactivateRole = driver.findElement(By.xpath("//*[@id=\"kt_modal_1\"]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/label[1]/input[1]"));
            deactivateRole.click();
        } catch (Exception e) {
            System.out.println("Error: Not Found New? role button");
//            addNewRole(driver);

        }finally {
            // Add role name
            WebElement roleNameInput = driver.findElement(By.xpath("//*[@id=\"kt_modal_1\"]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/input[1]"));
            roleNameInput.clear();
            roleNameInput.sendKeys("Role " + ++userCount);

            // Clear description field
            WebElement descriptionInput = driver.findElement(By.xpath("//*[@id=\"kt_modal_1\"]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/input[1]"));
            descriptionInput.clear();

            // Add role description
            descriptionInput.sendKeys("Automation Description");

            // Click the "Add Role" button
            WebElement addRoleButton = driver.findElement(By.xpath("//*[@id=\"kt_modal_1\"]/div[1]/div[1]/div[2]/div[1]/div[2]/div[3]/button[1]"));
            addRoleButton.click();
            Thread.sleep(2000);
            // Check for error message (unique role name)
            try {
                WebElement errorMessage = driver.findElement(By.xpath("//*[@id=\"kt_modal_1\"]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]"));
                System.out.println("Error message -> found " + errorMessage.getText());
                Thread.sleep(500);
                addNewRole(driver);
            } catch (NoSuchElementException r) {
                // Error message not found, continue
                System.out.println("Error message not found, continue");
            }
        }
    }

    public void checkCreationDate(WebDriver driver) throws InterruptedException {
        Thread.sleep(1000);
        WebElement creationDate = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[3]/div[1]/table[1]/tbody[1]/tr[1]/td[4]/span[1]"));
//        creationDate.getText();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        if (creationDate.getText().equals(dtf.format(now))){
            System.out.println("True; The creation date of last user is " + creationDate.getText());
        }
    }
}
