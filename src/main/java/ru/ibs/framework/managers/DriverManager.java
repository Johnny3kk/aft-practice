package ru.ibs.framework.managers;

import org.apache.commons.exec.OS;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import ru.ibs.framework.utils.PropsConst;

import java.net.MalformedURLException;
import java.net.URI;

public class DriverManager {

  private WebDriver driver;
  private TestPropManager propManager = TestPropManager.getInstance();

  private static DriverManager INSTANCE = null;

  private DriverManager() {}

  public static DriverManager getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new DriverManager();
    }
    return INSTANCE;
  }

  public WebDriver getDriver() {
    if (driver == null) {
      initDriver();
    }
    return driver;
  }

  private void initDriver() {
    if (OS.isFamilyWindows()) {
      initDriverWindowsOsFamily();
    } else if (OS.isFamilyMac()) {
      initDriverMacOsFamily();
    } else if (OS.isFamilyUnix()) {
      initDriverUnixOsFamily();
    }
  }

  public void quitDriver() {
    if (driver != null) {
      driver.quit();
      driver = null;
    }
  }

  private void initDriverWindowsOsFamily() {
    initDriverAnyOsFamily(
        propManager.getProperty(PropsConst.PATH_GECKO_DRIVER_WINDOWS),
        propManager.getProperty(PropsConst.PATH_CHROME_DRIVER_WINDOWS));
  }

  private void initDriverMacOsFamily() {
    initDriverAnyOsFamily(
        propManager.getProperty(PropsConst.PATH_GECKO_DRIVER_MAC),
        propManager.getProperty(PropsConst.PATH_CHROME_DRIVER_MAC));
  }

  private void initDriverUnixOsFamily() {
    initDriverAnyOsFamily(
        propManager.getProperty(PropsConst.PATH_GECKO_DRIVER_UNIX),
        propManager.getProperty(PropsConst.PATH_CHROME_DRIVER_UNIX));
  }

  private void initDriverAnyOsFamily(String gecko, String chrome) {
    if (propManager.getProperty(PropsConst.TYPE_BROWSER).equals("firefox")) {
      System.setProperty("webdriver.gecko.driver", gecko);
      driver = new FirefoxDriver();
    } else if (propManager.getProperty(PropsConst.TYPE_BROWSER).equals("chrome")) {
      System.setProperty("webdriver.chrome.driver", chrome);
      driver = new ChromeDriver();
    } else if (propManager.getProperty(PropsConst.TYPE_BROWSER).equals("remoteChrome")) {
      DesiredCapabilities capabilities = new DesiredCapabilities();
      capabilities.setBrowserName("chrome");
      capabilities.setVersion("84.0");
      capabilities.setCapability("enableVNC", true);
      capabilities.setCapability("enableVideo", false);

      try {
        driver =
            new RemoteWebDriver(
                URI.create("http://51.250.100.60:4444/wd/hub").toURL(), capabilities);
      } catch (MalformedURLException e) {
        e.printStackTrace();
      }
    } else if (propManager.getProperty(PropsConst.TYPE_BROWSER).equals("remoteFirefox")) {
      DesiredCapabilities capabilities = new DesiredCapabilities();
      capabilities.setBrowserName("firefox");
      capabilities.setVersion("78.0");
      capabilities.setCapability("enableVNC", true);
      capabilities.setCapability("enableVideo", false);

      try {
        driver =
            new RemoteWebDriver(
                URI.create("http://51.250.100.60:4444/wd/hub").toURL(), capabilities);
      } catch (MalformedURLException e) {
        e.printStackTrace();
      }
    }
  }
}
