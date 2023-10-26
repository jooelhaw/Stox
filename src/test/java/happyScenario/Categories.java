package happyScenario;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Categories {
    Integer lastCategoryID;
    WebDriverWait wait;
    public Categories(WebDriver driver) throws InterruptedException {
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"#kt_app_sidebar_menu\"]/div[3]/a"))).click();
        Thread.sleep(1500);
        lastCategoryID = Integer.valueOf(wait.until(ExpectedConditions.elementToBeClickable( // catch last warehouse id
                By.xpath("/html/body/div[1]/div/div[2]/div[2]/div[1]/div[2]/div/div/div/div[2]/div[3]/div[1]/table/tbody/tr[1]/td[1]/span"))).getText());
        System.out.println("Last warehouse ID:  " + lastCategoryID);
    }
}
