package ru.ibs.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.ibs.framework.utils.Product;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ShoppingCartPage extends BasePage {

  @FindBy(xpath = "//span[contains(text(), 'Оплачивайте')]/../../..//button")
  private List<WebElement> alertList;

  @FindBy(xpath = "//div[contains(text(), 'Корзина')]")
  private WebElement title;

  @FindBy(xpath = "//div[@id='split-Main-0']/div")
  private List<WebElement> itemList;

  @FindBy(xpath = "//section[@data-widget='total']")
  private WebElement checkout;

  public ShoppingCartPage alertClose() {
    waitUntilElementToBeClickable(alertList.get(1)).click();
    return this;
  }

  public ShoppingCartPage cartRevision() {
    checkOpenPage(title);
    for (Product p : productManager.getProductList()) {
      Assertions.assertTrue(
          itemList.stream().map(WebElement::getText).collect(Collectors.joining(""))
              .contains(p.getTitle()));
    }
    return this;
  }


}
