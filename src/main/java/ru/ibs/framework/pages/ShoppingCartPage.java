package ru.ibs.framework.pages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.ibs.framework.utils.Product;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PipedWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ShoppingCartPage extends BasePage {

  @FindBy(xpath = "//span[contains(text(), 'Оплачивайте')]/../../..//button")
  private List<WebElement> alertList;

  @FindBy(xpath = "//div[contains(text(), 'Корзина')]")
  private WebElement title;

  @FindBy(xpath = "//div[@id='split-Main-0']/../../../..")
  private WebElement items;

  @FindBy(xpath = "//section[@data-widget='total']")
  private WebElement checkout;

  @FindBy(xpath = "//div[@data-widget='controls']")
  private WebElement cartControls;

  @FindBy(xpath = "//button/span/span[contains(text(), 'Удалить')]")
  private WebElement deleteBtn;

  @FindBy(xpath = "//h1[contains(text(), 'Корзина пуста')]")
  private WebElement titleWhenEmpty;

  public ShoppingCartPage alertClose() {
    if (hasXpath("//span[contains(text(), 'Оплачивайте')]/../../..//button")) {
      waitUntilElementToBeClickable(alertList.get(1)).click();
    }
    return this;
  }

  public ShoppingCartPage cartItemRevision() {
    checkOpenPage(title);
    List<String> allInCart = new ArrayList<>(Arrays.asList(items.getText().split("\n")));
    for (Product p : productManager.getProductList()) {
      Assertions.assertTrue(
          allInCart.contains(p.getTitle()), "Выбранный при поиске товар не отображён в корзине.");
    }
    return this;
  }

  public ShoppingCartPage cartSumRevision(String string) {
    String check = string;
    if (string.contains("N")) {
      check =
          check
              .replaceAll("N", String.valueOf(productManager.getProductList().size()))
              .replaceAll("товаров", "");
    }
    Assertions.assertTrue(
        checkout.getText().replaceAll("\n", " - ").contains(check),
        "Сумма товаров для заказа не соответствует количеству ранее добавленных.");
    return this;
  }

  public ShoppingCartPage deleteAll() {
    if (!cartControls.findElement(By.xpath(".//input")).isSelected()) {
      cartControls.findElement(By.xpath(".//input")).click();
    }
    cartControls.findElement(By.xpath(".//span")).click();
    waitUntilElementToBeClickable(deleteBtn).click();
    return this;
  }

  public ShoppingCartPage isEmpty() {
    waitUntilElementToBeVisible(titleWhenEmpty);
    Assertions.assertEquals(
        "Корзина пуста",
        titleWhenEmpty.getText().trim(),
        "В корзине остались не удалённые товары.");
    return this;
  }
}
