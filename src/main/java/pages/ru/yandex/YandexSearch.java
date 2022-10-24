package pages.ru.yandex;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.SearchableFor;

import java.time.Duration;
import java.util.List;

public class YandexSearch implements SearchableFor {
    private final WebDriverWait mWait;
    @FindBy(how = How.XPATH, using = "//input[@id='text']")
    private WebElement searchField;
    @FindBy(how = How.XPATH, using = "//button/span[contains(text(),'Найти')]//ancestor::button")
    private WebElement searchButton;
    @FindBy(how = How.XPATH, using = "//li[contains(@class,'serp-item')]//h2//ancestor::a")
    private List<WebElement> results;

    public YandexSearch(WebDriver chromeDriver) {
        mWait = new WebDriverWait(chromeDriver, Duration.ofSeconds(30));
    }

    public List<WebElement> getResultTitlesWithLinks() {
        mWait.until(ExpectedConditions.visibilityOfAllElements(results));
        return results;
    }

    @Override
    public void searchFor(String line) {
        searchField.sendKeys(line);
        searchButton.click();
    }
}
