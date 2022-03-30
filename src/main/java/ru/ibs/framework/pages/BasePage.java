package ru.ibs.framework.pages;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
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

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

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

  protected void scrollBackJs() {
    JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driverManager.getDriver();
    javascriptExecutor.executeScript("window.scrollBy(0,-350)", "");
  }

  protected void clickOnCheckbox(List<WebElement> filtersList, String option, String value) {
    for (WebElement e : filtersList) {
      if (e.getText().contains(option)) {
        e.click();
        waitUntilElementToBeClickable(
                e.findElement(By.xpath(".//span[contains(text(), '" + value + "')]")))
            .click();
        break;
      }
    }
  }

  protected void clickOnToggle(List<WebElement> filtersList, String option) {
    for (WebElement e : filtersList) {
      if (e.getText().contains(option)) {
        e.click();
        break;
      }
    }
  }

  protected void fillTopBorderFilter(List<WebElement> filtersList, String option, String value) {
    for (WebElement e : filtersList) {
      if (e.getText().contains(option)) {
        e.click();
        WebElement input = e.findElement(By.xpath(".//p[contains(text(), 'до')]/../input"));
        waitUntilElementToBeClickable(input).click();
        input.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        input.sendKeys(Keys.chord(value, Keys.ENTER));

        boolean checkFlag = wait.until(ExpectedConditions.attributeContains(input, "value", value));
        Assertions.assertTrue(checkFlag, "Поле было заполнено неверно.");
        break;
      }
    }
  }

  protected boolean hasXpath(WebElement element, String xpath) {
    try {
      element.findElement(By.xpath(xpath));
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  protected boolean isInteger(String s) {
    try {
      Integer.parseInt(s);
    } catch (NumberFormatException | NullPointerException e) {
      return false;
    }
    return true;
  }

  @Step("Приложение отчёта о всех добавленных товарах")
  @Attachment(value = "ProductList", type = "text/plain", fileExtension = ".txt")
  public byte[] printListOfProduct() {
    return productManager.report().getBytes(StandardCharsets.UTF_8);
  }
}
