package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class HelperBase {
   protected ApplicationManager app;
   protected WebDriver WD;

   public HelperBase(ApplicationManager app) {
      this.app = app;
      this.WD = app.getDriver();

   }

   protected void type(By Locator, String text) {
      click(Locator);
      if (text != null) {
         String existingText = WD.findElement(Locator).getAttribute("value");
         if (! text.equals(existingText)) {
            WD.findElement(Locator).clear();
            WD.findElement(Locator).sendKeys(text);
         }

      }


   }

   protected void attach(By Locator, File file) {

      if (file != null) {
         WD.findElement(Locator).sendKeys(file.getAbsolutePath());
      }
  }

   protected void click(By locator) {
      WD.findElement(locator).click();
   }

   private boolean isAlertPresent() {
      try {
         WD.switchTo().alert();
         return true;
      } catch (NoAlertPresentException e) {
         return false;
      }
   }

   public boolean iselementPresent(By locator) {
      try {
         WD.findElement(locator);
         return true;
      } catch (NoSuchElementException ex) {
         return false;

      }
      }
}
