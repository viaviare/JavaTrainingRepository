package ru.stqa.pft.addbook.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class TestBaseAuth extends  TestBase {

  @BeforeSuite
  public void startLogin(){
    app.getNavigatorH().openStartPage();
    app.getLoginH().login();
  }

  @AfterSuite
  public void stop(){
    app.getLoginH().logout();
    app.stop();
  }

}
