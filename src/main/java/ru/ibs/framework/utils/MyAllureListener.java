package ru.ibs.framework.utils;

import io.qameta.allure.Attachment;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import ru.ibs.framework.managers.DriverManager;

public class MyAllureListener implements AfterTestExecutionCallback {

  @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
  public byte[] getScreenshot() {
    return ((TakesScreenshot) DriverManager.getInstance().getDriver())
        .getScreenshotAs(OutputType.BYTES);
  }

  @Override
  public void afterTestExecution(ExtensionContext extensionContext) {
    if (extensionContext.getExecutionException().isPresent()) {
      getScreenshot();
    }
  }
}
