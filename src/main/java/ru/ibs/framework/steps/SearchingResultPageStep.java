package ru.ibs.framework.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.И;
import ru.ibs.framework.managers.PageManager;
import ru.ibs.framework.pages.SearchingResultPage;

public class SearchingResultPageStep {

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
  }

  @И("^Наполняем корзину найденными товарами с параметром '(.+)' в задонном количестве '(.*)' с обычной доставкой$")
  public void fillShoppingCart(String option, int numberOfPickingProducts) {
      pageManager.getPage(SearchingResultPage.class).fillShoppingCart(option, numberOfPickingProducts);
  }

  @И("^Переходим в корзину$")
  public void toTheShoppingCart() {
    pageManager.getPage(SearchingResultPage.class).toTheShoppingCart();
  }
}
