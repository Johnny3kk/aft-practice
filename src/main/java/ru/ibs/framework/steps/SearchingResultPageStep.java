package ru.ibs.framework.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.И;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.ibs.framework.managers.PageManager;
import ru.ibs.framework.pages.SearchingResultPage;

public class SearchingResultPageStep {

  private static final Logger logger = LoggerFactory.getLogger(SearchingResultPageStep.class);

  private PageManager pageManager = PageManager.getInstance();

  @И("^Устанавливаем вариант фильтра поиска:$")
  public void fillSetup(DataTable mapOptionAndValue) {
    mapOptionAndValue
        .asMap(String.class, String.class)
        .forEach(
            (key, value) ->
                pageManager
                    .getPage(SearchingResultPage.class)
                    .filterSetup((String) value, (String) key));
    logger.info("Установлены заданные фильтры поиска");
  }

  @И("^Наполняем корзину найденными товарами с параметром '(.+)' в задонном количестве '(.*)' с обычной доставкой$")
  public void fillShoppingCart(String option, int numberOfPickingProducts) {
    logger.info("Начинаем заполнять корзину");
      pageManager.getPage(SearchingResultPage.class).fillShoppingCart(option, numberOfPickingProducts);
      logger.info("Корзина заполнена");
  }

  @И("^Переходим в корзину$")
  public void toTheShoppingCart() {
    pageManager.getPage(SearchingResultPage.class).toTheShoppingCart();
    logger.info("Перешли на страницу корзины");
  }
}
