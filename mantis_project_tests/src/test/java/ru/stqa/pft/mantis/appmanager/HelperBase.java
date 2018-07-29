package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.*;

import java.util.Properties;

public class HelperBase {

  protected ApplicationManager manager;
  protected WebDriver driver;
  protected Properties properties;


  public HelperBase(ApplicationManager manager){
    this.manager  = manager;
    this.driver = manager.getDriver();
    this.properties = manager.getProperties();
  }

  public void type(By locator, String text){
    if (text != null){
      WebElement element = driver.findElement(locator);
      if (! text.equals(element.getAttribute("value"))){
        element.clear();
        element.sendKeys(text);
      }
    }
  }

  public void click(By locator){
    driver.findElement(locator).click();
  }

  protected boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  protected boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }
}
