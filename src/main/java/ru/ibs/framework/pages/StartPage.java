package ru.ibs.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class StartPage extends BasePage {

  public StartPage checkOpenPage() {
    wait.until(ExpectedConditions.visibilityOf(getHeader().getLogo()));
    Assertions.assertTrue(getHeader().getLogo().isDisplayed());
    return this;
  }

}
