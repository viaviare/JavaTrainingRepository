package ru.stqa.pft.addbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addbook.appmanager.ApplicationManager;

public class TestBase {


  protected static final ApplicationManager app =
          new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));


  @BeforeSuite
  public void Start(){
    app.init();
  }

}

