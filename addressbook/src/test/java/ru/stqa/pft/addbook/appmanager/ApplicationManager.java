package ru.stqa.pft.addbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import sun.plugin2.util.BrowserType;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

  private NavigationHelper navigatorH;
  private GroupHelper groupH;
  private ContactHelper contactH;
  private LoginHelper loginH;
  private WebDriver driver;
  protected String baseUrl;
  private String browser;


  public ApplicationManager(String browser){ this.browser = browser;}

  public void init() {
    if(browser.equals(org.openqa.selenium.remote.BrowserType.FIREFOX)){
      driver = new FirefoxDriver();
    } else if(browser.equals(org.openqa.selenium.remote.BrowserType.IE)){
      driver = new InternetExplorerDriver();
    } else{
      driver = new ChromeDriver();
    }

    baseUrl = "http://localhost/addressbook";

    driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

    navigatorH = new NavigationHelper(this, baseUrl);
    groupH = new GroupHelper(this);
    loginH = new LoginHelper(this);
    contactH = new ContactHelper(this);
  }

  public void stop(){
    driver.quit();
  }


  public NavigationHelper getNavigatorH() {
    return navigatorH;
  }

  public GroupHelper getGroupH() {
    return groupH;
  }

  public LoginHelper getLoginH() {
    return loginH;
  }

  public WebDriver getDriver() {
    return driver;
  }

  public ContactHelper getContactH() {
    return contactH;
  }
}
