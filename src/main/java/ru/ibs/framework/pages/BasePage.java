package ru.ibs.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.ibs.framework.managers.DriverManager;
import ru.ibs.framework.managers.PageManager;
import ru.ibs.framework.managers.ProductManager;
import ru.ibs.framework.pages.blocks.Header;

import java.util.Arrays;

public class BasePage {

    protected DriverManager driverManager = DriverManager.getInstance();
    protected PageManager pageManager = PageManager.getInstance();
    protected ProductManager productManager = ProductManager.getInstance();
    protected WebDriverWait wait = new WebDriverWait(driverManager.getDriver(), 17, 1500);
    protected Actions act = new Actions(driverManager.getDriver());
    private Header header = new Header();

    public BasePage() {
        PageFactory.initElements(driverManager.getDriver(), this);
    }

    public Header getHeader() {
        return header;
    }

    public void checkOpenPage(WebElement element) {
        waitUntilElementToBeVisible(element);
        Assertions.assertTrue(element.isDisplayed());
    }

    protected WebElement waitUntilElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected WebElement waitUntilElementToBeVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void scrollToElementJs(WebElement element) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driverManager.getDriver();
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    protected void fillInputField(WebElement element, String value) {
        scrollToElementJs(element);
        waitUntilElementToBeVisible(element);
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        element.sendKeys(value);

        boolean checkFlag = wait.until(ExpectedConditions.attributeContains(element, "value", value));
        Assertions.assertTrue(checkFlag, "Поле было заполнено неверно.");
    }

    protected boolean hasXpath(WebElement element, String xpath) {
        try{
            element.findElement(By.xpath(xpath));
            return true;
        }catch (NoSuchElementException e) {
            return false;
        }
    }

    protected String getXpath(WebElement element) {
        String[] arr = element.toString().split("xpath: ");
        char[] result = arr[1].toCharArray();
        return String.copyValueOf(result, 0, (result.length - 1));
    }
}
