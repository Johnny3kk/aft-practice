package ru.ibs.framework.pages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class StartPage extends BasePage {

  public SearchingResultPage productSearch(String productName) {
    checkOpenPage(getHeader().getLogo());
    getHeader().searchProduct(productName);
    return pageManager.getPage(SearchingResultPage.class);
  }
}
