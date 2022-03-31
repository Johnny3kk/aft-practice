package ru.ibs.framework.base;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import ru.ibs.framework.managers.*;
import ru.ibs.framework.utils.PropsConst;

public class BaseTest {

  private DriverManager driverManager = DriverManager.getInstance();
  private TestPropManager propManager = TestPropManager.getInstance();
  private ProductManager productManager = ProductManager.getInstance();
  protected PageManager pageManager = PageManager.getInstance();


  @BeforeAll
  public static void before() {
    InitManager.initFramework();
  }

  @BeforeEach
  public void beforeEach() {
    driverManager.getDriver().get(propManager.getProperty(PropsConst.BASE_URL));
  }

  @AfterEach
  public void afterEach() {
    productManager.clearProductList();
    pageManager.clearMapPages();
  }

  @AfterAll
  public static void after() {
    InitManager.quitFramework();
  }

}
