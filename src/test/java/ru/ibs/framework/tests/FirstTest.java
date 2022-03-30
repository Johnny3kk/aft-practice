package ru.ibs.framework.tests;

import org.junit.jupiter.api.Test;
import ru.ibs.framework.base.BaseTest;

public class FirstTest extends BaseTest {

  @Test
  public void testScenario() {
    pageManager
        .getStartPage()
        .productSearch("iphone")
        .filterSetup("Высокий рейтинг", "")
        .filterSetup("Цена", "150000")
    .filterSetup("Беспроводные интерфейсы", "NFC")
    .fillShoppingCart().toTheShoppingCart();
  }
}
