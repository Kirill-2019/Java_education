package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper {

   private WebDriver wd;

   public SessionHelper(WebDriver wd) {

      this.wd = wd;
   }

   public void Login(String Loginname, String Password) {
      wd.findElement(By.name("user")).clear();
      wd.findElement(By.name("user")).sendKeys(Loginname);
      wd.findElement(By.name("pass")).clear();
      wd.findElement(By.name("pass")).sendKeys(Password);
      // WD.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Password:'])[1]/following::input[2]")).click();
      wd.findElement(By.xpath("//input[@value='Login']")).click();
   }

}
