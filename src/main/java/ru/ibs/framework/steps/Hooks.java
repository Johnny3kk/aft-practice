package ru.ibs.framework.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import ru.ibs.framework.managers.*;
import ru.ibs.framework.utils.PropsConst;

public class Hooks {

    private DriverManager driverManager = DriverManager.getInstance();
    private TestPropManager propManager = TestPropManager.getInstance();
    private ProductManager productManager = ProductManager.getInstance();
    protected PageManager pageManager = PageManager.getInstance();

    @Before
    public void before() {
        InitManager.initFramework();
        driverManager.getDriver().get(propManager.getProperty(PropsConst.BASE_URL));
    }

    @After
    public void after() {
        InitManager.quitFramework();
        productManager.clearProductList();
        pageManager.clearMapPages();
    }
}
