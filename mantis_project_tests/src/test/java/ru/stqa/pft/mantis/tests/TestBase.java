package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;

import java.io.IOException;

public class TestBase {



  protected static final ApplicationManager app =
          new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));


  @BeforeSuite
  public void Start() throws IOException {
    app.init();
  }
}

