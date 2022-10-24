package pages.com.google;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.BasePage;
import pages.SearchableFor;

public class GoogleSearchHomePage extends BasePage implements SearchableFor {
    private final WebElement searchField;

    public GoogleSearchHomePage(WebDriver chromeDriver) {
        super(chromeDriver);
        this.searchField = chromeDriver.findElement(By.name("q"));
    }

    @Override
    public void searchFor(String line) {
        searchField.sendKeys(line);
        searchField.submit();
    }
}
