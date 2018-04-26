package ru.stqa.pft.addbook.appmanager;

import org.openqa.selenium.By;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class LoginHelper extends HelperBase {

  public LoginHelper(ApplicationManager manager) {super(manager);}


  public void login(){
    type(By.cssSelector("input[name='user']"), "admin");
    type(By.cssSelector("input[name='pass']"), "secret");
    driver.findElement(By.cssSelector("input[type='submit']")).click();
  }


  public void logout(){
    click(By.linkText("Logout"));
  }

}
