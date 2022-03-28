package ru.ibs.framework.base;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import ru.ibs.framework.managers.DriverManager;
import ru.ibs.framework.managers.InitManager;
import ru.ibs.framework.managers.PageManager;
import ru.ibs.framework.managers.TestPropManager;
import ru.ibs.framework.utils.PropsConst;

public class BaseTest {

  private DriverManager driverManager = DriverManager.getInstance();
  private TestPropManager propManager = TestPropManager.getInstance();
  protected PageManager pageManager = PageManager.getInstance();

  @BeforeAll
  public static void before() {
    InitManager.initFramework();
  }

  @BeforeEach
  public void beforeEach() {
    driverManager.getDriver().get(propManager.getProperty(PropsConst.BASE_URL));
  }

  @AfterAll
  public static void after() {
    InitManager.quitFramework();
  }

}
