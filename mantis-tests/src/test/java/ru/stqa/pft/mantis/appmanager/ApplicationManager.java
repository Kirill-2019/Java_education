package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
   private WebDriver WD;


   private Properties properties;
   private String browser;


   public ApplicationManager(String browser) throws IOException {
      this.browser = browser;
      properties = new Properties();

   }

   public void init() throws IOException {

      // String browser = BrowserType.FIREFOX;
      String target = System.getProperty("target", "local");
      properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

      if (browser.equals(BrowserType.FIREFOX)) {
         WD = new FirefoxDriver();
      } else if (browser.equals(BrowserType.CHROME)) {
         WD = new ChromeDriver();
      } else {
         WD = new InternetExplorerDriver();
      }

      WD.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
      //  WD.get("http://localhost/addressbook/");
      WD.get(properties.getProperty("web.baseUrl"));
   }


   public void stop() {
      WD.quit();
   }
}

   //public boolean isElementPresent(By by) {
   //  try {
   //   WD.findElement(by);
   //    return true;
   //  } catch (NoSuchElementException e) {
   //   return false;
   //  }
   //}



