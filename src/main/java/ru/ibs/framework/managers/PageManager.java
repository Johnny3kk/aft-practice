package ru.ibs.framework.managers;


import ru.ibs.framework.pages.SearchingResultPage;
import ru.ibs.framework.pages.ShoppingCartPage;
import ru.ibs.framework.pages.StartPage;

public class PageManager {

  private static PageManager INSTANCE = null;

  private StartPage startPage;
  private SearchingResultPage searchPage;
  private ShoppingCartPage cartPage;

  private PageManager() {}

  public static PageManager getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new PageManager();
    }
    return INSTANCE;
  }

  public StartPage getStartPage() {
    if (startPage == null) {
      startPage = new StartPage();
    }
    return startPage;
  }

  public SearchingResultPage getSearchPage() {
    if (searchPage == null) {
      searchPage = new SearchingResultPage();
    }
    return searchPage;
  }

  public ShoppingCartPage getCartPage() {
    if (cartPage == null) {
      cartPage = new ShoppingCartPage();
    }
    return cartPage;
  }

}
