package ru.stqa.pft.addbook.appmanager;

import org.openqa.selenium.*;

public class HelperBase {

  protected ApplicationManager manager;
  protected WebDriver driver;

  public HelperBase(ApplicationManager manager){
    this.manager  = manager;
    this.driver = manager.getDriver();
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
