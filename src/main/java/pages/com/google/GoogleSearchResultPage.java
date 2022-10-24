package pages.com.google;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class GoogleSearchResultPage extends GoogleSearchHomePage {
    private final WebDriverWait mWait;

    public GoogleSearchResultPage(WebDriver chromeDriver) {
        super(chromeDriver);
        mWait = new WebDriverWait(chromeDriver, Duration.ofSeconds(30));
    }

    public List<WebElement> getResultTitlesWithLinks() {
        mWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("rso")));
        return chromeDriver.findElements(By.xpath("//*[@id='rso']//h3//ancestor::a"));
    }
}
