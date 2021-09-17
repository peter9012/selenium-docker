package com.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {

  protected WebDriver driver;

  @BeforeTest
  public void setupDriver() throws MalformedURLException {

    String host = "localhost";
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--no-sandbox");
    options.addArguments("--disable-dev-shm-usage");
    DesiredCapabilities dc;

    if(System.getProperty("BROWSER") != null && System.getProperty("BROWSER").equalsIgnoreCase("firefox"))
    {
      dc = DesiredCapabilities.firefox();
    }
    else{
      dc = DesiredCapabilities.chrome();
      dc.setCapability(ChromeOptions.CAPABILITY, options);
    }

    if(System.getProperty("HUB_HOST") != null) {
      host = System.getProperty("HUB_HOST");
    }

    String completeUrl = "http://" + host + ":4444/wd/hub";
    this.driver = new RemoteWebDriver(new URL(completeUrl), dc);
//    System.setProperty("webdriver.chrome.driver", "/Users/peter.lu/Documents/plu_shopstyle/plu_workspace/seleniumdocker/driver/chromedriver");
//    this.driver = new ChromeDriver();
  }

  @AfterTest
  public void quitBrowser() {
    this.driver.quit();
  }

}
