import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Learnings {
//        static Integer userCount = 9;
    public static void main(String[] args) {
        String[] s = "https://merchantstest.stox-eg.com/products/79".split("/");
        System.out.println(s[s.length -1 ]);

    }
}
