package pages.org.wikipedia;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class Wikipedia {
    private final By rowsOfTeacherTableLocator = By.xpath("./tr");
    private final By columnsOfTeacherTableLocator = By.xpath("./td");
    @FindBy(how = How.XPATH,
            using = "//table[caption[contains(text(),'Преподаватели кафедры программирования')]]/tbody")
    private WebElement teacherTable;

    public List<WebElement> getRowsOfTeacherTable() {
        return teacherTable.findElements(rowsOfTeacherTableLocator);
    }

    public List<WebElement> getColumnsOfTeacherTable(WebElement row) {
        return row.findElements(columnsOfTeacherTableLocator);
    }
}
