package ru.ibs.framework.pages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.ibs.framework.utils.Product;

import java.util.List;

public class SearchingResultPage extends BasePage {

  @FindBy(xpath = "//div[contains(text(), 'запрос')]")
  private WebElement title;

  @FindBy(xpath = "//span[contains(text(), 'Все фильтры')]")
  private WebElement filterSetup;

  @FindBy(xpath = "//span[contains(text(), 'Применить')]")
  private WebElement applyBtn;

  @FindBy(xpath = "//div[@data-widget='searchResultsFiltersActive']")
  private WebElement appliedFilters;

  @FindBy(xpath = "//div[contains(text(), 'Дальше')]")
  private WebElement nextPageBtn;

  @FindBy(xpath = "//h3[contains(text(), 'Все фильтры')]/../..//div/div/div")
  private List<WebElement> filtersList;

  @FindBy(xpath = "//a[contains(@class, 'tile-hover-target')]/span")
  private List<WebElement> foundProductList;

  @Step("Устанавливаем вариант фильтра поиска '{option}' с значением '{value}'")
  public SearchingResultPage filterSetup(String option, String value) {
    scrollToElementJs(filterSetup);
    scrollBackJs();
    waitUntilElementToBeClickable(filterSetup).click();
    if (value.equals("")) {
      clickOnToggle(filtersList, option);
    } else if (isInteger(value)) {
      fillTopBorderFilter(filtersList, option, value);
    } else {
      clickOnCheckbox(filtersList, option, value);
    }

    scrollToElementJs(applyBtn);
    waitUntilElementToBeClickable(applyBtn).click();
    waitUntilElementToBeVisible(appliedFilters);
    Assertions.assertTrue(appliedFilters.getText().contains(option));
    return this;
  }

  public SearchingResultPage fillShoppingCart() {
    String string =
        "./../../../..//button/span[not(contains(@class, 'ui-d8')) and not(contains(@class, 'ui-e8'))]";
    for (int i = 0; i < foundProductList.size(); i++) {
      if (i % 2 != 0) {
        if (hasXpath(foundProductList.get(i), string)) {
          productManager.add(
              new Product(
                  foundProductList.get(i).getText(),
                  Integer.parseInt(
                      foundProductList
                          .get(i)
                          .findElement(
                              By.xpath(
                                  "./../../../..//span[@style='color: var(--ozTextPrimary);']"))
                          .getText()
                          .trim()
                          .replaceAll("[^0-9]", ""))));
          foundProductList
              .get(i)
              .findElement(
                  By.xpath(
                      "./../../../..//button/span[not(contains(@class, 'ui-d8')) and not(contains(@class, 'ui-e8'))]"))
              .click();
          if (productManager.getProductList().size() == 8) {
            break;
          }
          System.out.println(foundProductList.get(i).getText());
        }
      }
    }
    if (productManager.getProductList().size() < 8) {
      nextSearchPage();
      fillShoppingCart();
    }
    return this;
  }

  public void nextSearchPage() {
    scrollToElementJs(nextPageBtn.findElement(By.xpath("./../../../../../../..")));
    waitUntilElementToBeClickable(nextPageBtn).click();
    foundProductList =
        driverManager
            .getDriver()
            .findElements(By.xpath("//a[contains(@class, 'tile-hover-target')]/span"));
  }
}
