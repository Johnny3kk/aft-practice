package ru.ibs.framework.tests;

import org.junit.jupiter.api.Test;
import ru.ibs.framework.base.BaseTest;
import ru.ibs.framework.pages.StartPage;

import java.io.IOException;

public class FirstTest extends BaseTest {

  @Test
  public void firstTest() {
    pageManager
        .getPage(StartPage.class)
        .productSearch("iphone")
        .filterSetup("Высокий рейтинг", "")
        .filterSetup("Цена", "150000")
        .filterSetup("Беспроводные интерфейсы", "NFC")
        .fillShoppingCart("even", 8)
        .toTheShoppingCart()
        .alertClose()
        .cartItemRevision()
        .cartSumRevision("Ваша корзина - 8 товаров")
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
        .fillShoppingCart("odd", -1)
        .toTheShoppingCart()
        .alertClose()
        .cartItemRevision()
        .cartSumRevision("Ваша корзина - N товаров")
        .deleteAll()
        .isEmpty()
        .printListOfProduct();
  }
}
