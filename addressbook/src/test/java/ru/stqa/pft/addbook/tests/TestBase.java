package ru.stqa.pft.addbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addbook.appmanager.ApplicationManager;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class TestBase {

  Logger logger = LoggerFactory.getLogger(TestBase.class);

  protected static final ApplicationManager app =
          new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));


  @BeforeSuite
  public void Start() throws IOException {
    app.init();
  }

  @BeforeMethod
  public void logTestStart(Method m, Object[] params){
    logger.info("start " + m  + " parameters " + Arrays.asList(params));
  }

  @AfterMethod (alwaysRun = true)
  public void logTestStop(Method m){
    logger.info("stop " + m);
  }
}

