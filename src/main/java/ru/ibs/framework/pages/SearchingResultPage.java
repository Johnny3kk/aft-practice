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

  @FindBy(
      xpath =
          "//h3[contains(text(), 'Все фильтры')]/../../div/div[not(@data-widget='searchResultsFiltersActive')]/div")
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
      if (!fillTopBorderFilter(filtersList, option, value)) {
        pushAcceptFilterBtn();
        return this;
      }
    } else {
      clickOnCheckbox(filtersList, option, value);
    }

    pushAcceptFilterBtn();
    Assertions.assertTrue(appliedFilters.getText().contains(option));
    return this;
  }

  @Step("Наполняем корзину 8 чётными найденными товарами с обычной доставкой")
  public SearchingResultPage fillShoppingCart(String option, int numberOfPickingProducts) {
    int inCart = 0;
    boolean pickOption;
    String btnXpath =
        "./../../../..//button/span[not(contains(@class, 'ui-d8')) and not(contains(@class, 'ui-e8'))]";
    for (int i = 0; i < foundProductList.size(); i++) {
      if (option.equals("even")) {
        pickOption = i % 2 != 0;
      } else {
        pickOption = i % 2 == 0;
      }
      if (pickOption) {
        if (hasXpath(foundProductList.get(i), btnXpath)) {
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
          foundProductList.get(i).findElement(By.xpath(btnXpath)).click();
          wait.until(
              ExpectedConditions.textToBePresentInElement(
                  getHeader().getCartItemCount(), String.valueOf(inCart + 1)));
          Assertions.assertEquals(
              String.valueOf(inCart + 1), getHeader().getCartItemCount().getText());
          inCart++;
          if (productManager.getProductList().size() == numberOfPickingProducts) {
            break;
          }
        }
      }
    }
    if (productManager.getProductList().size() < numberOfPickingProducts) {
      nextSearchPage();
      fillShoppingCart(option, numberOfPickingProducts);
    }
    return this;
  }

  public ShoppingCartPage toTheShoppingCart() {
    waitUntilElementToBeClickable(getHeader().getShoppingCart()).click();
    return pageManager.getPage(ShoppingCartPage.class);
  }

  public void nextSearchPage() {
    scrollToElementJs(nextPageBtn);
    scrollBackJs();
    waitUntilElementToBeClickable(nextPageBtn).click();
    foundProductList =
        driverManager
            .getDriver()
            .findElements(By.xpath("//a[contains(@class, 'tile-hover-target')]/span"));
  }

  public void pushAcceptFilterBtn() {
    scrollToElementJs(applyBtn);
    waitUntilElementToBeClickable(applyBtn).click();
    waitUntilElementToBeVisible(appliedFilters);
  }
}
