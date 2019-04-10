package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;


import java.util.concurrent.TimeUnit;

public class ApplicationManager {
   private WebDriver WD;


   private SessionHelper SessionHelper;
   private NavigationHelper navigationHelper;
   private GroupHelper groupHelper;
   private KontactHelper kontactHelper;
   private String browser;


   public ApplicationManager(String browser) {
      this.browser = browser;

   }

   public void init() {

     // String browser = BrowserType.FIREFOX;

      if (browser.equals(BrowserType.FIREFOX)){
         WD = new FirefoxDriver();
      } else if (browser.equals(BrowserType.CHROME)){
         WD = new ChromeDriver();
      } else {
         WD = new InternetExplorerDriver();
      }

      WD.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
      WD.get("http://localhost/addressbook/");
      groupHelper = new GroupHelper(WD);
      kontactHelper = new KontactHelper(WD);
      navigationHelper = new NavigationHelper(WD);
      SessionHelper = new SessionHelper(WD);
      SessionHelper.Login("admin", "secret");
   }


   public void stop() {
      WD.quit();
   }

   private boolean isElementPresent(By by) {
     try {
       WD.findElement(by);
       return true;
     } catch (NoSuchElementException e) {
       return false;
     }
   }


   public KontactHelper getKontactHelper() {
      return kontactHelper;
   }

   public GroupHelper getGroupHelper() {
      return groupHelper;
   }

   public NavigationHelper getNavigationHelper() {
      return navigationHelper;
   }
}
