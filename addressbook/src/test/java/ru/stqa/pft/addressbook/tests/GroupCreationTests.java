package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

//import org.openqa.selenium.firefox.FirefoxDriver;


public class GroupCreationTests  extends TestBase {

  @Test
  public void testGroupCreation() {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().initGroupCreation();
    app.getGroupHelper().fillGroupForm(new GroupData("q", "w", "e"));
    app.getGroupHelper().submitGroupCreation();
    app.getGroupHelper().returnGroupPage();
    app.logout();
  }

}
