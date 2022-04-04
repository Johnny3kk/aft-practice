package ru.ibs.framework.pages;

import io.qameta.allure.Allure;
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
import java.util.List;

public class BasePage {

  protected DriverManager driverManager = DriverManager.getInstance();
  protected PageManager pageManager = PageManager.getInstance();
  protected ProductManager productManager = ProductManager.getInstance();
  protected WebDriverWait wait = new WebDriverWait(driverManager.getDriver(), 10, 1000);
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
    Assertions.assertTrue(element.isDisplayed(), "Страница не загрузилась");
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
      waitUntilElementToBeClickable(e);
      if (e.getText().contains(option)) {
        e.click();
        if (hasXpath(e, ".//span[contains(text(), 'Посмотреть все')]")) {
          e.findElement(By.xpath(".//span[contains(text(), 'Посмотреть все')]")).click();
          WebElement input = e.findElement(By.xpath(".//input[@type='text']"));
          waitUntilElementToBeClickable(input).click();
          input.sendKeys(value);
        }
        WebElement filter = e.findElement(By.xpath(".//span[contains(text(), '" + value + "')]"));
        waitUntilElementToBeVisible(filter);
        waitUntilElementToBeClickable(filter).click();
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

  protected boolean fillTopBorderFilter(List<WebElement> filtersList, String option, String value) {
    for (WebElement e : filtersList) {
      if (e.getText().contains(option)) {
        e.click();
        WebElement input = e.findElement(By.xpath(".//p[contains(text(), 'до')]/../input"));
        if (Integer.parseInt(input.getAttribute("value")) < Integer.parseInt(value)) {
          return false;
        }
        waitUntilElementToBeClickable(input).click();
        input.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        input.sendKeys(Keys.chord(value, Keys.ENTER));

        boolean checkFlag = wait.until(ExpectedConditions.attributeContains(input, "value", value));
        Assertions.assertTrue(checkFlag, "Поле было заполнено неверно.");
        break;
      }
    }
    return true;
  }

  protected boolean hasXpath(WebElement element, String xpath) {
    try {
      element.findElement(By.xpath(xpath));
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  protected boolean hasXpath(String xpath) {
    try {
      driverManager.getDriver().findElement(By.xpath(xpath));
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

  public void printListOfProduct() {
    Allure.getLifecycle().addAttachment("ProductList", "text/plain", ".txt", productManager.report().getBytes(StandardCharsets.UTF_8));
  }
}
