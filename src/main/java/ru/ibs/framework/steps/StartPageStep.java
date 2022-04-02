package ru.ibs.framework.steps;

import io.cucumber.java.ru.И;
import ru.ibs.framework.managers.PageManager;
import ru.ibs.framework.pages.StartPage;

public class StartPageStep {

    private PageManager pageManager = PageManager.getInstance();

    @И("^Ищем '(.+)' в каталоге магазина$")
    public void productSearching(String searchRequest) {
        pageManager.getPage(StartPage.class).productSearch(searchRequest);
    }

}
