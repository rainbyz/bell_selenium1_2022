import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import pages.com.google.GoogleSearchHomePage;
import pages.com.google.GoogleSearchResultPage;
import pages.org.wikipedia.Wikipedia;
import pages.ru.open.OpenBankHomePage;
import pages.ru.yandex.YandexSearch;

import java.util.ArrayList;
import java.util.List;

public class TestCases extends BaseTest {
    @DisplayName("Задание 1.1")
    @ParameterizedTest
    @CsvSource({"Гладиолус, wikipedia.org"})
    void testCase1(String keyWord, String result) {
        chromeDriver.get("https://www.google.com/");
        GoogleSearchHomePage googleSearchHomePage = new GoogleSearchHomePage(chromeDriver);
        googleSearchHomePage.searchFor(keyWord);
        GoogleSearchResultPage googleSearchResultPage = new GoogleSearchResultPage(chromeDriver);
        boolean flag = false;
        for (WebElement webElement : googleSearchResultPage.getResultTitlesWithLinks()) {
            if (webElement.getText().contains(result)) {
                flag = true;
                break;
            }
        }
        Assertions.assertTrue(flag,
                "В результатах поиска на 1 странице в полученной выборке нет следующей ссылки: " + result);
    }

    @DisplayName("Задание 1.2")
    @ParameterizedTest
    @CsvSource({"Открытие, Банк Открытие: Частным клиентам, USD, EUR"})
    void testCase2(String keyWord, String title, String currency1, String currency2) {
        chromeDriver.get("https://www.google.com/");
        GoogleSearchHomePage googleSearchHomePage = new GoogleSearchHomePage(chromeDriver);
        googleSearchHomePage.searchFor(keyWord);
        GoogleSearchResultPage googleSearchResultPage = new GoogleSearchResultPage(chromeDriver);
        boolean flag1 = false;
        for (WebElement webElement : googleSearchResultPage.getResultTitlesWithLinks()) {
            if (webElement.getText().contains(title)) {
                flag1 = true;
                webElement.click();
                break;
            }
        }
        Assertions.assertTrue(flag1,
                "В результатах поиска на 1 странице в полученной выборке нет следующего заголовка: " + title);

        OpenBankHomePage openBankHomePage = new OpenBankHomePage(chromeDriver);
        WebElement table = openBankHomePage.getCurrencyTable();
        int buyTextIndex = 0, sellTextIndex = 0;
        boolean flag2 = false;
        for (WebElement row : openBankHomePage.getRowsOfCurrencyTable(table)) {
            List<WebElement> columns = openBankHomePage.getColumnsOfCurrencyTable(row);
            for (int i = 0; i < columns.size(); i++) {
                String temp = columns.get(i).getText();
                if (temp.contains(openBankHomePage.getBuyText())) buyTextIndex = i;
                else if (temp.contains(openBankHomePage.getSellText())) sellTextIndex = i;
                else if (temp.equals(currency1) || temp.equals(currency2)) {
                    if (Double.parseDouble(columns.get(buyTextIndex).getText().replaceAll(",", "."))
                            < Double.parseDouble(columns.get(sellTextIndex).getText().replaceAll(",", "."))) {
                        flag2 = true;
                    }
                }
            }
        }
        Assertions.assertTrue(flag2,
                "Курс продажи продажи меньше курса покупки для " + currency1 + " или " + currency2);
    }

    @DisplayName("Задание 1.3")
    @ParameterizedTest
    @CsvSource({"таблица, Таблица — Википедия, Сергей Владимирович, Сергей Адамович"})
    void testCase3(String keyWord, String title, String name1, String name2) {
        chromeDriver.get("https://www.yandex.ru/");
        YandexSearch yandexSearch = PageFactory.initElements(chromeDriver, YandexSearch.class);
        yandexSearch.searchFor(keyWord);
        boolean flag = false;
        for (WebElement webElement : yandexSearch.getResultTitlesWithLinks()) {
            if (webElement.getText().contains(title)) {
                flag = true;
                webElement.click();
                // Смена на новую активную вкладку
                for (String windowHandle : chromeDriver.getWindowHandles()) {
                    if (!chromeDriver.getWindowHandle().contentEquals(windowHandle)) {
                        chromeDriver.switchTo().window(windowHandle);
                        break;
                    }
                }
                break;
            }
        }
        Assertions.assertTrue(flag,
                "В результатах поиска на 1 странице в полученной выборке нет следующего заголовка: " + title);

        Wikipedia wikipedia = PageFactory.initElements(chromeDriver, Wikipedia.class);
        int rowsNumber = wikipedia.getRowsOfTeacherTable().size();
        for (int i = 1; i < rowsNumber; i++) {
            WebElement row = wikipedia.getRowsOfTeacherTable().get(i);
            List<String> namesList = new ArrayList<>();
            for (WebElement column : wikipedia.getColumnsOfTeacherTable(row))
                namesList.add(column.getText());

            if (i == 1) Assertions.assertTrue(String.join(" ", namesList).contains(name1),
                    name1 + " идет не первым в списке");
            else if (i == rowsNumber - 1) Assertions.assertTrue(String.join(" ", namesList).contains(name2),
                    name2 + " идет не последним в списке");
        }
    }
}
