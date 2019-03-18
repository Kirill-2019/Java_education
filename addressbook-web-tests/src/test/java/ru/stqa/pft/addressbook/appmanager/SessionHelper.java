package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper extends HelperBase {

   public SessionHelper(WebDriver wd) {

      super(wd);
   }

   public void Login(String Loginname, String Password) {
      type(By.name("user"), Loginname);
      type(By.name("pass"), Password);
      click(By.xpath("//input[@value='Login']"));
   }
}
