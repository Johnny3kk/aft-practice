package ru.ibs.framework.pages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class StartPage extends BasePage {

  @Step("Ищем '{productName}' в каталоге магазина")
  public SearchingResultPage productSearch(String productName) {
    checkOpenPage(getHeader().getLogo());
    getHeader().searchProduct(productName);
    return pageManager.getSearchPage();
  }
}
