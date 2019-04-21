package ru.stqa.pft.addressbook.appmanager;

import static org.testng.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.KontaktData;


import org.openqa.selenium.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


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
      click((By.linkText("home")));

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


   public void initkontaktmodification(int index) {

      // click(By.id("maintable"));
      List<WebElement> elements = WD.findElements(new By.ByXPath("//img[@alt='Edit']"));
      elements.get(index).click();

   }

   public void updatekontakt() {
      click(By.name("update"));
   }

   public void CreateKontact(KontaktData group) {
      initkontcreation();
      fillGroupForm(new KontaktData("bla","blablalbla","lastname", "nick_name", "test3"),true );
      submitKontaktCreation();
      click((By.linkText("home page")));
   }

   public List<KontaktData> getKontaktList() {



      List<KontaktData> kontakts = new ArrayList<KontaktData>();
      List<WebElement> elements = WD.findElements(By.cssSelector("tr"));
      for (int i =1;i < elements.size();i++){
         WebElement element = elements.get(i);
         int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
         List<WebElement> td = element.findElements(By.cssSelector("td"));
         KontaktData kontakt = new KontaktData(id,td.get(2).getText() ,null,td.get(1).getText(),null,null );
         kontakts.add(kontakt);

      }
     return kontakts;
   }
}



