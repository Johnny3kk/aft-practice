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

  @FindBy(xpath = "//div[contains(text(), 'Цена')]/..//p[contains(text(), 'до')]/../input")
  private WebElement priceTopBorder;

  @FindBy(xpath = "//div[@value='Высокий рейтинг']")
  private WebElement highRateCheck;

  @FindBy(xpath = "//span[contains(text(), 'NFC')]/../..")
  private WebElement nfcCheck;

  @FindBy(xpath = "//a[contains(@class, 'tile-hover-target')]/span")
  private List<WebElement> foundProductList;

  @Step("Устанавливаем верхнюю границу цены '{price}'")
  public SearchingResultPage limitPrice(String price) {
    fillInputField(priceTopBorder, price);
    return this;
  }

  @Step("Включаем опцию 'Высокий рейтинг'")
  public SearchingResultPage clickOnHighRateCheck() {
    scrollToElementJs(highRateCheck.findElement(By.xpath("./../../..")));
    waitUntilElementToBeVisible(highRateCheck);
    waitUntilElementToBeClickable(highRateCheck).click();
//    waitUntilElementToBeClickable(getHeader().getLogo());
    return this;
  }

  public SearchingResultPage assertOnHighRateCheck() {
    //    checkOpenPage(title);
    //    scrollToElementJs(priceTopBorder);
    //    waitUntilElementToBeVisible(highRateCheck);
    //    waitUntilElementToBeClickable(highRateCheck);
    Assertions.assertTrue(highRateCheck.findElement(By.xpath("./..//input")).isSelected());
    return this;
  }

  @Step("Включаем опцию 'NFC'")
  public SearchingResultPage clickOnNfcCheck() {
    scrollToElementJs(nfcCheck.findElement(By.xpath("./../../../..")));
    waitUntilElementToBeVisible(nfcCheck);
    waitUntilElementToBeClickable(nfcCheck).click();
    pageManager.getSearchPage();
    return this;
  }

  public SearchingResultPage assertOnNfc() {
    checkOpenPage(title);
    scrollToElementJs(nfcCheck.findElement(By.xpath("./../../../..")));
    waitUntilElementToBeVisible(nfcCheck);
    waitUntilElementToBeClickable(nfcCheck);
    Assertions.assertTrue(nfcCheck.findElement(By.xpath(".//input")).isSelected());
    return this;
  }

  public SearchingResultPage fillShoppingCart() {
        String string = "./../../../..//button/span[not(contains(@class, 'ui-d8')) and not(contains(@class, 'ui-e8'))]";
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
                          .getText().trim().replaceAll("[^0-9]", ""))));
          foundProductList
                  .get(i)
                  .findElement(
                          By.xpath("./../../../..//button/span[not(contains(@class, 'ui-d8')) and not(contains(@class, 'ui-e8'))]")).click();
          if (productManager.getProductList().size() == 8) {
            break;
          }
          System.out.println(foundProductList.get(i).getText());
        }
      }
    }
    return this;
  }
}
