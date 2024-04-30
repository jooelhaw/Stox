package happyScenario.merchant.manifests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AddManifest {
    WebDriverWait wait;
    public AddManifest(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        addManifest();
    }
    private void sendKeys(String element, String key){
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(element))).clear();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(element))).sendKeys(key);
    }

    private void addManifest() {

    }
}
