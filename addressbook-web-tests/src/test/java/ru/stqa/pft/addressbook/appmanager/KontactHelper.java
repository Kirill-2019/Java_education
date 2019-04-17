package ru.stqa.pft.addressbook.appmanager;

import static org.testng.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.KontaktData;


import org.openqa.selenium.*;


public class KontactHelper extends HelperBase {
   private boolean acceptNextAlert = true;
   public KontactHelper(WebDriver WD) {
      super(WD);
   }

   public void fillGroupForm(KontaktData kontaktData, boolean creation) {
      type(By.name("firstname"), kontaktData.getFirstname());
      type(By.name("middlename"), kontaktData.getMiddlename());
      type(By.name("lastname"), kontaktData.getLastname());
      type(By.name("nickname"), kontaktData.getNickname());

      if (creation) {
         new Select(WD.findElement(By.name("new_group"))).selectByVisibleText(kontaktData.getGroup());
      } else {
         Assert.assertFalse(iselementPresent(By.name("new_group")));
      }

   }

   public void submitKontaktCreation() {
      click(By.name("submit"));

   }


   public void acceptNext() {
      acceptNextAlert = true;
      assertTrue(closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
   }

   private String closeAlertAndGetItsText() {
      try {
         Alert alert = WD.switchTo().alert();
         String alertText = alert.getText();
         if (acceptNextAlert) {
            alert.accept();
         } else {
            alert.dismiss();
         }
         return alertText;
      } finally {
         acceptNextAlert = true;
      }
   }

   public void initkontcreation() {

      click(By.linkText("add new"));
   }

   public void deleteSelectedKontact() {
     // click(By.name("delete"));
     // click(By.xpath(".//*[normalize-space(text()) and normalize-space(.)='Select all'])[1]/following::input[2])"));
      click(By.xpath("//input[@value='Delete']"));
   }
   public void KontactClic() {  click(By.name("container"));}


   public void initkontaktmodification() {

     // click(By.id("maintable"));

      click(By.xpath("//img[@alt='Edit']"));
   }

   public void updatekontakt() {
      click(By.name("update"));
   }

   public void CreateKontact(KontaktData group) {
      initkontcreation();
      fillGroupForm(new KontaktData("bla","blablalbla","lastname", "nick_name", "test3"),true );
      submitKontaktCreation();
   }

}



