package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.chrome.ChromeDriver;

public class HelperBase {

  protected ChromeDriver wd;


  public HelperBase (ChromeDriver wd){
    this.wd = wd;
  }


  public void type(String locator, String text) {
    wd.findElement(By.name(locator)).click();
    wd.findElement(By.name(locator)).clear();
    wd.findElement(By.name(locator)).sendKeys(text);
  }

  public void click(By locator) {
    wd.findElement(locator).click();
  }

  public boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

}
