package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
   private WebDriver WD;


   private SessionHelper SessionHelper;
   private NavigationHelper navigationHelper;
   private GroupHelper groupHelper;
   private KontactHelper kontactHelper;
   private Properties properties;
   private String browser;
   private DbHelper dbHelper;


   public ApplicationManager(String browser) throws IOException {
      this.browser = browser;
      properties = new Properties();

   }

   public void init() throws IOException {

     // String browser = BrowserType.FIREFOX;
      String target = System.getProperty("target","local");
      properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties",target))));

      dbHelper = new DbHelper();

      if (browser.equals(BrowserType.FIREFOX)){
         WD = new FirefoxDriver();
      } else if (browser.equals(BrowserType.CHROME)){
         WD = new ChromeDriver();
      } else {
         WD = new InternetExplorerDriver();
      }

      WD.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    //  WD.get("http://localhost/addressbook/");
      WD.get(properties.getProperty("web.baseUrl"));
      groupHelper = new GroupHelper(WD);
      kontactHelper = new KontactHelper(WD);
      navigationHelper = new NavigationHelper(WD);
      SessionHelper = new SessionHelper(WD);
      SessionHelper.Login(properties.getProperty("web.adminLogin"),properties.getProperty("web.adminPassword"));
      //SessionHelper.Login("admin", "secret");


   }


   public void stop() {
      WD.quit();
   }

   //public boolean isElementPresent(By by) {
   //  try {
   //   WD.findElement(by);
   //    return true;
   //  } catch (NoSuchElementException e) {
   //   return false;
   //  }
   //}


   public KontactHelper getKontactHelper() {
      return kontactHelper;
   }

   public GroupHelper group() {
      return groupHelper;
   }

   public NavigationHelper goTO() {
      return navigationHelper;
   }

   public DbHelper db() {return dbHelper;}

}
