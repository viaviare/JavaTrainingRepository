package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginHelper extends HelperBase{


  public LoginHelper(ChromeDriver wd){
    super(wd);
  }

  public void login(String username, String password) {
    wd.get("http://localhost/addressbook/");
    type("user", username);
    type("pass", password);
    wd.findElement(By.xpath("//form[@id='LoginForm']/input[3]")).click();
  }
}
