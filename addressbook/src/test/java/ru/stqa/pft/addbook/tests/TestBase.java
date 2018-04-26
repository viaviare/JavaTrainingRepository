package ru.stqa.pft.addbook.tests;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addbook.appmanager.ApplicationManager;

public class TestBase {


  protected static final ApplicationManager app = new ApplicationManager();

  @BeforeSuite
  public void Start(){
    app.init();
  }

}
