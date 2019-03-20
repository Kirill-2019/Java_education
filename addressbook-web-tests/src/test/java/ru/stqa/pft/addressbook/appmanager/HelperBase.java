package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

public class HelperBase {
   protected WebDriver WD;

   public HelperBase(WebDriver WD) {
      this.WD = WD;
   }

   protected void type(By Locator, String text) {
      click(Locator);
      if (text != null) {
         String existingText = WD.findElement(Locator).getAttribute("value");
         if (text.equals(existingText)) {
            WD.findElement(Locator).clear();
            WD.findElement(Locator).sendKeys(text);
         }
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

}
