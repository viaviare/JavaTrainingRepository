package ru.stqa.pft.mantis.appmanager;

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
  private LoginHelper loginH;
  private WebDriver driver;
  protected String baseUrl;
  private String browser;
  private Properties properties;
  private RegistrationHelper registH;
  private FtpHelper ftp;
  private MailHelper mailH;


  public ApplicationManager(String browser) {
    this.browser = browser;
    properties = new Properties();
  }

  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
  }

  public void stop() {
    if (driver != null){
      driver.quit();
    }
  }

  public NavigationHelper getNavigatorH() {
    return navigatorH;
  }

  public LoginHelper getLoginH() {
    return loginH;
  }

  public Properties getProperties() {
    return properties;
  }

  public HttpSession newSession(){
    return new HttpSession(this);
  }

  public String getProperty(String key){
    return properties.getProperty(key);
  }

  public WebDriver getDriver() {
    if (driver == null) {
      if (browser.equals(org.openqa.selenium.remote.BrowserType.FIREFOX)) {
        driver = new FirefoxDriver();
      } else if (browser.equals(org.openqa.selenium.remote.BrowserType.IE)) {
        driver = new InternetExplorerDriver();
      } else {
        driver = new ChromeDriver();
      }
      baseUrl = properties.getProperty("web.baseUrl");
      driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

      navigatorH = new NavigationHelper(this);
      loginH = new LoginHelper(this);
    }
    return driver;
  }

  public RegistrationHelper registration () {
    if(registH == null){
      registH = new RegistrationHelper(this);
    }
    return registH;
  }

  public FtpHelper ftp() {
    if (ftp == null) {
      ftp = new FtpHelper(this);
    }
    return ftp;
  }

  public MailHelper mail() {
    if (mailH == null) {
      mailH = new MailHelper(this);
    }
    return mailH;
  }
}
