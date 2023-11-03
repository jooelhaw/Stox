package happyScenario.backoffice.categories;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Categories {
    WebElement confirmButton;
    WebElement lastCategory;
    Integer lastCategoryID;

    WebElement nameEnField;
    WebElement nameArField;
    WebElement editLastCategoryBtn;
    WebElement deleteLastCategoryBtn;
    WebDriverWait wait;
    public Categories(WebDriver driver) throws InterruptedException {
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"#kt_app_sidebar_menu\"]/div[3]/a"))).click();
        Thread.sleep(1500);
        lastCategoryID = Integer.valueOf(wait.until(ExpectedConditions.elementToBeClickable( // catch last category id
                By.xpath("/html/body/div[1]/div/div[2]/div[2]/div[1]/div[2]/div/div/div/div[2]/div[3]/div[1]/table/tbody/tr[1]/td[1]/span"))).getText());
        System.out.println("Last warehouse ID:  " + lastCategoryID);
    }
    public void addCategory(WebDriver driver, String type){
        if (type == null){
            type = "Category ";
        }
        try {
            driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[1]/div[2]/div/a")).click(); // click add category button
        }catch (Exception e){
            System.out.println("Not found add category button , you are in add category page");
        }finally {
            nameEnField = wait.until(ExpectedConditions.elementToBeClickable( // category name English
                    By.xpath("/html/body/div[1]/div/div[2]/div[2]/div[1]/div[2]/div/div/div/div/div[1]/div[1]/div/input")));
            nameEnField.clear();
            nameEnField.sendKeys(type + ++lastCategoryID);

            nameArField = wait.until(ExpectedConditions.elementToBeClickable( // category name Arabic
                    By.xpath("/html/body/div[1]/div/div[2]/div[2]/div[1]/div[2]/div/div/div/div/div[1]/div[2]/div/input")
            ));
            nameArField.clear();
            nameArField.sendKeys("فئه " + lastCategoryID);

            // click save button to add a new parent category
            driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div[2]/button")).click();

            // click confirm button
            confirmButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("/html/body/div[3]/div/div[6]/button[1]")
            ));
            confirmButton.click();
        }

    }
    public void addSubCategories(WebDriver driver){
        lastCategory = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div[1]/div/div[2]/div[2]/div[1]/div[2]/div/div/div/div[2]/div[3]/div[1]/table/tbody/tr[1]/td[2]/span/a")
        ));
        lastCategory.click();
        try {
            lastCategoryID = Integer.valueOf(wait.until(ExpectedConditions.elementToBeClickable( // catch last category id
                    By.xpath("/html/body/div[1]/div/div[2]/div[2]/div[1]/div[2]/div/div/div/div[2]/div[3]/div[1]/table/tbody/tr[1]/td[1]/span"))).getText());
        }catch (Exception e){
            System.out.println("Not found any sub categories");
        }finally {
            addCategory(driver, "Sub Category ");
        }
    }
    public void checkMaxLevel(WebDriver driver) throws InterruptedException {
        // back to main categories page
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"#kt_app_sidebar_menu\"]/div[3]/a"))).click();
        Thread.sleep(1500);

        addCategory(driver, "Category");
        addSubCategories(driver);
        addSubCategories(driver);
        addSubCategories(driver); // 3 level only
    }
    public void editLastCategory(WebDriver driver){
        // click edit button on last category
        editLastCategoryBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div[1]/div/div[2]/div[2]/div[1]/div[2]/div/div/div/div[2]/div[3]/div[1]/table/tbody/tr[1]/td[4]/a")
        ));
        editLastCategoryBtn.click();

        addCategory(driver,null);

    }
    public void deleteLastCategory(WebDriver driver){
        // click delete button on last category
        deleteLastCategoryBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div[1]/div/div[2]/div[2]/div[1]/div[2]/div/div/div/div[2]/div[3]/div[1]/table/tbody/tr[1]/td[4]/button")
        ));
        deleteLastCategoryBtn.click();

        // click confirm button
        confirmButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div[3]/div/div[6]/button[1]")
        ));
        confirmButton.click();
    }
}
