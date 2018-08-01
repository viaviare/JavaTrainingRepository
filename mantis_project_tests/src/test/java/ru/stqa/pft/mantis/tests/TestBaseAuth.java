package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestBaseAuth extends  TestBase {

  @BeforeSuite
  public void startLogin(){
    app.getNavigatorH().openStartPage();
    app.getLoginH().login();
  }

  @AfterSuite (alwaysRun = true)
  public void stop(){
    app.getLoginH().logout();
  }

}
