package ru.stqa.pft.addbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

  private NavigationHelper navigatorH;
  private GroupHelper groupH;
  private ContactHelper contactH;
  private LoginHelper loginH;
  private WebDriver driver;
  protected String baseUrl;
  private String browser;
  private Properties properties;


  public ApplicationManager(String browser){
    this.browser = browser;
    properties = new Properties();
  }

  public void init() throws IOException {
    if(browser.equals(org.openqa.selenium.remote.BrowserType.FIREFOX)){
      driver = new FirefoxDriver();
    } else if(browser.equals(org.openqa.selenium.remote.BrowserType.IE)){
      driver = new InternetExplorerDriver();
    } else{
      driver = new ChromeDriver();
    }

    String target = System.getProperty("target","local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

    baseUrl = properties.getProperty("web.baseUrl");


    driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

    navigatorH = new NavigationHelper(this);
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

  public Properties getProperties() {return properties;}
}
