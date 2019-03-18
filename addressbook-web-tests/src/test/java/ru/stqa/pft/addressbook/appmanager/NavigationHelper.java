package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper {


   public NavigationHelper(WebDriver wd) {
      this.WD =  wd;
   }

   public void goToGroupPage()
   {
     WD.findElement(By.linkText("groups")).click();
   }
}
