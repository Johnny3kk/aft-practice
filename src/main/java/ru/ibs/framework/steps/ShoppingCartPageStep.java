package ru.ibs.framework.steps;

import io.cucumber.java.ru.И;
import io.qameta.allure.Attachment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.ibs.framework.managers.PageManager;
import ru.ibs.framework.pages.BasePage;
import ru.ibs.framework.pages.ShoppingCartPage;

public class ShoppingCartPageStep {

  private static final Logger logger = LoggerFactory.getLogger(ShoppingCartPageStep.class);

  private PageManager pageManager = PageManager.getInstance();

  @И("^Проверяем добавление в корзину всех выбранных продуктов$")
  public void cartItemRevision() {
    pageManager.getPage(ShoppingCartPage.class).alertClose().cartItemRevision();
    logger.info("Продукты в корзине проверены.");
  }

  @И("^Проверяем отображение текста '(.+)' в корзине$")
  public void cartSumRevision(String text) {
    pageManager.getPage(ShoppingCartPage.class).cartSumRevision(text);
    logger.info("Сумма товаров для заказа проверена.");
  }

  @И("^Удаляем все товары из корзины$")
  public void deleteAll() {
    pageManager.getPage(ShoppingCartPage.class).deleteAll();
    logger.info("Все товары удалены из корзины.");
  }

  @И("^Проверяем, что корзина не содержит никаких товаров$")
  public void isEmpty() {
    pageManager.getPage(ShoppingCartPage.class).isEmpty();
    logger.info("Корзина не содержит никаких товаров.");
  }

  @И("^Приложение отчёта о всех добавленных товарах$")
  public void printListOfProducts() {
    pageManager.getPage(BasePage.class).printListOfProduct();
    logger.info("Приложен отчёт с ранее добавленными в корзину товарами.");
  }
}
