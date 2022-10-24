package pages.ru.open;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;

import java.time.Duration;
import java.util.List;

public class OpenBankHomePage extends BasePage {
    private final WebDriverWait mWait;
    private final String buyText = "покупает";
    private final String sellText = "продает";
    private final By currencyTableLocator = By.xpath("//table[contains(@class,'main-page-exchange')]//tbody");

    public OpenBankHomePage(WebDriver chromeDriver) {
        super(chromeDriver);
        mWait = new WebDriverWait(chromeDriver, Duration.ofSeconds(60));
    }

    public WebElement getCurrencyTable() {
        mWait.until(ExpectedConditions.visibilityOfElementLocated(currencyTableLocator));
        return chromeDriver.findElement(currencyTableLocator);
    }

    public List<WebElement> getRowsOfCurrencyTable(WebElement table) {
        return table.findElements(By.xpath("./tr"));
    }

    public List<WebElement> getColumnsOfCurrencyTable(WebElement row) {
        return row.findElements(By.xpath("./td"));
    }

    public String getBuyText() {
        return buyText;
    }

    public String getSellText() {
        return sellText;
    }
}
