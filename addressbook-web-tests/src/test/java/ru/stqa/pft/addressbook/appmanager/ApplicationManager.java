package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
   WebDriver WD;


   private SessionHelper SessionHelper;
   private NavigationHelper navigationHelper;
   private GroupHelper groupHelper;
   private KontactHelper kontactHelper;

   public void init() {
      WD = new FirefoxDriver();
      WD.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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
