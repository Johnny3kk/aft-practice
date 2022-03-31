package ru.ibs.framework.tests;

import org.junit.jupiter.api.Test;
import ru.ibs.framework.base.BaseTest;
import ru.ibs.framework.pages.StartPage;

public class FirstTest extends BaseTest {

  @Test
  public void firstTest() {
    pageManager
        .getPage(StartPage.class)
        .productSearch("iphone")
        .filterSetup("Высокий рейтинг", "")
        .filterSetup("Цена", "150000")
        .filterSetup("Беспроводные интерфейсы", "NFC")
        .fillShoppingCart("even", 3)
        .toTheShoppingCart()
        .alertClose()
        .cartItemRevision()
        .cartSumRevision("Ваша корзина - 3 товара")
        .deleteAll()
        .isEmpty()
        .printListOfProduct();
  }

  @Test
  public void secondTest() {
    pageManager
        .getPage(StartPage.class)
        .productSearch("беспроводные наушники")
        .filterSetup("Бренды", "Beats")
        .filterSetup("Бренды", "Samsung")
        .filterSetup("Бренды", "Xiaomi")
        .filterSetup("Цена", "50000")
        .filterSetup("Высокий рейтинг", "")
        .fillShoppingCart("odd", 3)
        .toTheShoppingCart()
        .alertClose()
        .cartItemRevision()
        .cartSumRevision("Ваша корзина - 3 товара")
        .deleteAll()
        .isEmpty()
        .printListOfProduct();
  }
}
