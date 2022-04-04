package ru.ibs.framework.steps;

import io.cucumber.java.ru.И;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.ibs.framework.managers.PageManager;
import ru.ibs.framework.pages.StartPage;

public class StartPageStep {

    private static final Logger logger = LoggerFactory.getLogger(StartPageStep.class);

    private PageManager pageManager = PageManager.getInstance();

    @И("^Ищем '(.+)' в каталоге магазина$")
    public void productSearching(String searchRequest) {
        logger.info("Открытие стартовой страницы");
        pageManager.getPage(StartPage.class).productSearch(searchRequest);
        logger.info("Выполнен поисковый запрос '" + searchRequest + "'");
    }

}
