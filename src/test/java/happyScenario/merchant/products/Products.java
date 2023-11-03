package happyScenario.merchant.products;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Products {
    WebDriverWait wait;
    public Products(WebDriver driver){
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable( // products in side menu
                By.xpath("//*[@id=\"#kt_app_sidebar_menu\"]/div[1]/a")
        )).click();
        System.out.println(driver.getCurrentUrl().equals("https://merchants.stox-eg.com/products"));
    }
}
