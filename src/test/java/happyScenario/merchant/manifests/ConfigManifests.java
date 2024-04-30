package happyScenario.merchant.manifests;

import happyScenario.constants.Constants;
import happyScenario.constants.CreateManifest;
import happyScenario.constants.CreateOrder;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Objects;

public class ConfigManifests {
    WebDriver driver;
    WebDriverWait wait;
    String lastManifestID;
    AddManifest addManifest;
    String lastManifestDate;

    @BeforeTest
    public void openBrowser() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(Constants.MER_URL_STAGE);
        driver.findElement(By.name("email")).sendKeys(Constants.USER_MER_STAGING);
        driver.findElement(By.name("password")).sendKeys(Constants.PASS_MER_STAGING);
        driver.findElement(By.cssSelector("button[type=submit]")).click(); // logged in merchant stag
        Thread.sleep(2600);
        driver.navigate().to("https://merchantstest.stox-eg.com/manifests"); // open manifest page
        lastManifestID = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CreateManifest.lastManifestID))).getText();
        System.out.println("before adding : " + lastManifestID);
        driver.navigate().to("https://merchantstest.stox-eg.com/manifests/create"); // navigate to create manifest page
    }
    @Test(priority = 1)
    public void addManifest() throws InterruptedException {
        addManifest = new AddManifest(driver);
        Thread.sleep(500);
        driver.navigate().refresh();
        // get manifest id from the manifest url
        String[] lastManifestUrl = driver.getCurrentUrl().split("/");
        addManifest.lastManifestID = lastManifestUrl[lastManifestUrl.length -1]; // last manifest id
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Thread.sleep(2800);
        System.out.println("after adding the manifest : " + addManifest.lastManifestID);
        assert (Integer.parseInt(addManifest.lastManifestID) > Integer.parseInt(lastManifestID));
    }

}
