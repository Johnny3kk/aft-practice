package ru.ibs.framework.pages.blocks;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.ibs.framework.managers.DriverManager;
import ru.ibs.framework.managers.PageManager;
import ru.ibs.framework.pages.SearchingResultPage;

public class Header {

  private DriverManager driverManager = DriverManager.getInstance();
  private PageManager pageManager = PageManager.getInstance();
  private WebDriverWait wait = new WebDriverWait(driverManager.getDriver(), 10, 1000);
  private Actions act = new Actions(driverManager.getDriver());

  public Header() {
    PageFactory.initElements(driverManager.getDriver(), this);
  }

  @FindBy(xpath = "//img[@alt='Ozon']")
  private WebElement logo;

  @FindBy(xpath = "//input[@class='w9s']")
  private WebElement search;

  @FindBy(xpath = "//button[@class='sx0']")
  private WebElement searchBtn;

  public SearchingResultPage searchProduct(String productName) {
    wait.until(ExpectedConditions.elementToBeClickable(search));
    search.sendKeys(productName);
    wait.until(ExpectedConditions.elementToBeClickable(searchBtn)).click();
    return pageManager.getSearchPage();
  }

  public WebElement getLogo() {
    return logo;
  }
}
