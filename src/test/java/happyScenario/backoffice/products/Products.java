package happyScenario.backoffice.products;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class Products {
    WebDriverWait wait;
    WebElement saveButton;
    WebElement nameField;
    WebElement skuField;
    WebElement insertButton;
    String[] cuUrl;
    WebElement brandField;
    WebElement categoryField;
    WebElement lengthField;
    WebElement heightField;
    WebElement widthField;
    WebElement weightField;
    WebElement descriptionField;
    WebElement deleteButton;
    WebElement editButton;
    WebElement searchBar;
    WebElement imagesField;
    Integer lastProductID;
    public Products(WebDriver driver) throws InterruptedException {
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable( // products in side menu
                By.xpath("//*[@id=\"#kt_app_sidebar_menu\"]/div[5]/a")
        )).click();
        System.out.println(driver.getCurrentUrl().equals("https://merchants.stox-eg.com/products"));
        Thread.sleep(1500);
        try {
            lastProductID = Integer.valueOf(wait.until(ExpectedConditions.elementToBeClickable( // get last product id
                    By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[3]/div[1]/table[1]/tbody[1]/tr[1]/td[1]/span[1]"
                    ))).getText());
        }catch (Exception e){
            lastProductID = 1;
        }
    }

    public void locateAddingElements(WebDriver driver){
        // set sku field
        skuField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/input[1]"
        )));

        // set name field
        nameField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "//div[@class='col-md']//input[@type='text']"
        )));

        // click save button
        saveButton = driver.findElement(By.xpath("//button[@type='button']"));
    }

    public void locateProductsElements(WebDriver driver){

        // find search bar
        searchBar = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]")));

        // find first delete button
        deleteButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div[1]/div/div[2]/div[2]/div[1]/div[2]/div/div/div/div[2]/div[3]/div[1]/table/tbody/tr[1]/td[11]/button")
        ));

        // find first edit button
        editButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div[1]/div/div[2]/div[2]/div[1]/div[2]/div/div/div/div[2]/div[3]/div[1]/table/tbody/tr[1]/td[11]/a")
        ));

    }
    public void addProduct(WebDriver driver) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable( // click add product button
                    By.xpath("//a[normalize-space()='Add Product']")
            )).click();


        }catch (Exception e){
            System.out.println("You are not in Products page");
        }finally {
            locateAddingElements(driver);

            nameField.sendKeys("Product " + ++lastProductID);

            skuField.sendKeys("pro" + lastProductID);

            saveButton.click();

            // click confirm button
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("/html/body/div[3]/div/div[6]/button[1]")
            )).click();

            WebElement productName = driver.findElement(By.xpath( // product name after added
                    "/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/h3[1]"));

            WebElement productSKU = driver.findElement(By.xpath( // product sku after added
                    "/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[1]"
            ));
            Assert.assertEquals(productName.getText(),"Product " + lastProductID); // assert name added successfully
            Assert.assertEquals(productSKU.getText(), "pro" + lastProductID); // assert sku added successfully
        }
    }

//    private void checkProductAdded(WebDriver driver) throws InterruptedException {
//        Integer skuID;
//        // sku id
//        if (cuUrl != null) {
//            skuID = Integer.valueOf(cuUrl[cuUrl.length - 1]);
//        }else {
//            skuID = 73;
//        }
//        driver.navigate().refresh();
//
//        locateProductsElements(driver);
//
//        searchBar.clear();
//        Thread.sleep(700);
//        searchBar.sendKeys("pro" + lastProductID); // send search key SKU pro75
//        Thread.sleep(2000);
//
//        // Assert id exist
////        lastProductID = Integer.valueOf(wait.until(ExpectedConditions.elementToBeClickable( // get last product id
////                By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[3]/div[1]/table[1]/tbody[1]/tr[1]/td[1]/span[1]"
////                ))).getText());
//        Assert.assertEquals(lastProductID,skuID);
//
//    }


    public void deleteFromList(WebDriver driver) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable( // products in side menu
                By.xpath("//*[@id=\"#kt_app_sidebar_menu\"]/div[5]/a")
        )).click();

        locateProductsElements(driver);

        searchBar.click();
        searchBar.clear();
        Thread.sleep(700);
        searchBar.sendKeys("pro" + lastProductID); // send search key SKU pro75

        // click first delete button to remove from the list
        deleteButton.click();

        // click yes delete button
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div[3]/div/div[6]/button[1]")
        )).click();

        // click confirm button
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div[3]/div/div[6]/button[1]")
        )).click();

        searchBar.clear();

    }
}