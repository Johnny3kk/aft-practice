package ru.ibs.framework.steps;

import io.cucumber.java.ru.И;
import io.qameta.allure.Attachment;
import ru.ibs.framework.managers.PageManager;
import ru.ibs.framework.pages.BasePage;
import ru.ibs.framework.pages.ShoppingCartPage;

public class ShoppingCartPageStep {

  private PageManager pageManager = PageManager.getInstance();

  @И("^Проверяем добавление в корзину всех выбранных продуктов$")
  public void cartItemRevision() {
    pageManager.getPage(ShoppingCartPage.class).alertClose().cartItemRevision();
  }

  @И("^Проверяем отображение текста '(.+)' в корзине$")
  public void cartSumRevision(String text) {
    pageManager.getPage(ShoppingCartPage.class).cartSumRevision(text);
  }

  @И("^Удаляем все товары из корзины$")
  public void deleteAll() {
    pageManager.getPage(ShoppingCartPage.class).deleteAll();
  }

  @И("^Проверяем, что корзина не содержит никаких товаров$")
  public void isEmpty() {
    pageManager.getPage(ShoppingCartPage.class).isEmpty();
  }

  @И("^Приложение отчёта о всех добавленных товарах$")
  public void printListOfProducts() {
    pageManager.getPage(BasePage.class).printListOfProduct();
  }
}
