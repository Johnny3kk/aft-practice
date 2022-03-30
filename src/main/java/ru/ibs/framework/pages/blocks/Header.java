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

  @FindBy(xpath = "//div[@data-widget='searchBarDesktop']//input[@name]")
  private WebElement search;

  @FindBy(xpath = "//div[@data-widget='searchBarDesktop']//button")
  private WebElement searchBtn;

  @FindBy(xpath = "//span[@class='tsCaptionBold mc']")
  private WebElement cartItemCount;

  @FindBy(xpath = "//span[contains(text(), 'Корзина')]")
  private WebElement shoppingCart;

  public SearchingResultPage searchProduct(String productName) {
    wait.until(ExpectedConditions.elementToBeClickable(search));
    search.sendKeys(productName);
    wait.until(ExpectedConditions.elementToBeClickable(searchBtn)).click();
    return pageManager.getSearchPage();
  }

  public WebElement getLogo() {
    return logo;
  }

  public WebElement getCartItemCount() {
    return cartItemCount;
  }

  public WebElement getShoppingCart() {
    return shoppingCart;
  }
}
