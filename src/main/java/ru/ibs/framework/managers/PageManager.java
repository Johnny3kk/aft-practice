package ru.ibs.framework.managers;


import ru.ibs.framework.pages.BasePage;
import ru.ibs.framework.pages.SearchingResultPage;
import ru.ibs.framework.pages.ShoppingCartPage;
import ru.ibs.framework.pages.StartPage;

import java.util.HashMap;
import java.util.Map;

public class PageManager {

  private static PageManager INSTANCE = null;
  private Map<String, BasePage> mapPages = new HashMap<>();

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

  public <T extends BasePage> T getPage(Class<T> tClass) {
    if (mapPages.isEmpty() || mapPages.get(tClass.getName()) == null) {
      try{
      mapPages.put(tClass.getName(), tClass.newInstance());
      } catch (InstantiationException | IllegalAccessException e) {
        e.printStackTrace();
      }
    }
    return (T) mapPages.get(tClass.getName());
  }

  public void clearMapPages() {
    mapPages.clear();
  }

//  public StartPage getStartPage() {
//    if (startPage == null) {
//      startPage = new StartPage();
//    }
//    return startPage;
//  }
//
//  public SearchingResultPage getSearchPage() {
//    if (searchPage == null) {
//      searchPage = new SearchingResultPage();
//    }
//    return searchPage;
//  }
//
//  public ShoppingCartPage getCartPage() {
//    if (cartPage == null) {
//      cartPage = new ShoppingCartPage();
//    }
//    return cartPage;
//  }

}
