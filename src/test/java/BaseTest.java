import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class BaseTest {
    protected WebDriver chromeDriver;

    @BeforeEach
    public void setup() {
        System.setProperty("webdriver.chrome.driver", System.getenv("CHROME_DRIVER"));
        // System.setProperty("webdriver.chrome.driver", System.getProperty("user.home") + "/Downloads/chromedriver");
        chromeDriver = new ChromeDriver();
        chromeDriver.manage().window().maximize();
        chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        chromeDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        chromeDriver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
    }

    @AfterEach
    public void shutDown() {
        chromeDriver.quit();
    }
}
