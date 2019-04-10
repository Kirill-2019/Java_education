package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {


   public NavigationHelper(WebDriver wd) {
      super(wd);
   }

   public void goToGroupPage() {
      //if iselementPresent(By.name("groups"))

      click(By.linkText("groups"));
   }



   // public void goToKontaktPage() {click((By.linkText("add new")));
  public void goTohomePage() {
     click((By.linkText("home page")));
  }

   }

