package pages;

import org.openqa.selenium.WebDriver;

public class BasePage {
    protected WebDriver chromeDriver;

    protected BasePage(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
    }
}
