package ru.stqa.pft.addbook.appmanager;

import org.openqa.selenium.By;

public class NavigationHelper extends HelperBase {

  private String baseUrl;

  public NavigationHelper(ApplicationManager manager){
    super (manager);
    this.baseUrl = manager.baseUrl;
  };

  public void openStartPage() {
    driver.navigate().to(baseUrl);
  }


  public void gotoHomePage() {
    if(isElementPresent(By.id("maintable"))){
      return;
    }
    click(By.linkText("home"));
  }

  public void gotoGroupPage() {
    if (driver.getCurrentUrl().equals(baseUrl + properties.getProperty("web.groupPage")) &&
            isElementPresent(By.cssSelector("input[name='new']"))){
      return;
    }
    click(By.linkText("groups"));
  }


}
