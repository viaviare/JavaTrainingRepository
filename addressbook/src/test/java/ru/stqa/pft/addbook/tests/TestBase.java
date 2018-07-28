package ru.stqa.pft.addbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addbook.appmanager.ApplicationManager;
import ru.stqa.pft.addbook.model.ContSet;
import ru.stqa.pft.addbook.model.GroupData;
import ru.stqa.pft.addbook.model.GroupSet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

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


  public void verifyGroupListInUI() {
    if(Boolean.getBoolean("verifyUI")) {
      GroupSet dbGroups = app.getDbH().groups();
      GroupSet uiGroups = app.getGroupH().getGroupSet();
      assertThat(uiGroups, equalTo(dbGroups.stream().map((g) ->
              new GroupData().setId(g.getId()).setName(g.getName()))
              .collect(Collectors.toSet())));
    }
  }

  public void verifyContactListInUI() {
    if(Boolean.getBoolean("verifyUI")) {
      ContSet dbContacts = app.getDbH().contacts();
      ContSet uiContacts = app.getContactH().getContactSetList();
      assertThat(uiContacts, equalTo(dbContacts));
    }
  }
}

