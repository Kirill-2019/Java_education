package ru.stqa.pft.addressbook.appmanager;

import static org.testng.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.KontaktData;


import org.openqa.selenium.*;
import ru.stqa.pft.addressbook.model.Kontakts;

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

   public void KontactClic() {
      click(By.name("container"));
   }


   public void initkontaktmodification(int id) {
      WebElement element = getStingInTable(id);
      element.findElement(By.xpath("//img[@alt='Edit']")).click();
   }

   public void initkontaktmodificationById(int id) {
    // WD.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a",id))).click();
      WD.findElement(By.xpath(String.format("//tr[.//input[@value='%s']]/td[8]/a",id))).click();
   }

   public void updatekontakt() {
      click(By.name("update"));
   }

   public void CreateKontact(KontaktData kontakt) {
      initkontcreation();
      fillGroupForm(kontakt, true);
      submitKontaktCreation();
      click((By.linkText("home page")));

   }

   public void modify(KontaktData kontakt) {
      selectKontaktByID(kontakt.getId());
      initkontaktmodification(kontakt.getId());
      fillGroupForm(kontakt,false);
      UpdateKontakt();
      click((By.linkText("home page")));

   }

   private void UpdateKontakt() {
      click(By.name("update"));
   }

   /*public List<KontaktData> getKontaktList() {
      List<KontaktData> kontakts = new ArrayList<KontaktData>();
      List<WebElement> elements = WD.findElements(By.cssSelector("tr"));
      for (int i =1;i < elements.size();i++){
         WebElement element = elements.get(i);
         int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
         List<WebElement> td = element.findElements(By.cssSelector("td"));
        // KontaktData kontakt = new KontaktData(id,td.get(2).getText() ,null,td.get(1).getText(),null,null );
         KontaktData kontakt = new KontaktData(id,td.get(2).getText() ,td.get(1).getText());
         kontakts.add(kontakt);

      }
     return kontakts;
   }
*/
   public Kontakts all() {
      Kontakts kontakts = new Kontakts();
      List<WebElement> elements = WD.findElements(By.cssSelector("tr"));
      for (int i = 1; i < elements.size(); i++) {
         WebElement element = elements.get(i);
         int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
         List<WebElement> td = element.findElements(By.cssSelector("td"));
         String allPhones = td.get(5).getText();
         kontakts.add(new KontaktData().withId(id).withFirstname(td.get(2).getText()).withLastname(td.get(1).getText())
                 .withaddress(td.get(3).getText()).withallmails(td.get(4).getText()).withallPhones(allPhones));


//         String[] phones = td.get(5).getText().split("\n");
//         kontakts.add(new KontaktData().withId(id).withFirstname(td.get(2).getText()).withLastname(td.get(1).getText())
//                 .withHomePhone(phones[0]).withMobilePhone(phones[1]).withWorkPhone(phones[2]));
      }
      return kontakts;

   }


   public void delete(KontaktData Kontakt) {

      selectKontaktByID(Kontakt.getId());
      deleteSelectedKontact();
   }

   private void selectKontaktByID(int id) {
     WebElement element = getStingInTable(id);
     element.findElement(By.tagName("input")).click();

   }

   private WebElement getStingInTable(int id) {
      List<WebElement> elements = WD.findElements(By.cssSelector("tr"));

      for (int i = 1; i < elements.size(); i++) {
         WebElement element = elements.get(i);
         int ident = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
         if (id == ident) {
            return element;
         }
      }

      return null;
   }

   public KontaktData infoFromEditForm(KontaktData kontakt) {
      initkontaktmodificationById(kontakt.getId());

      String firstname     = WD.findElement(By.name("firstname")).getAttribute("value");
      String lastname      = WD.findElement(By.name("lastname")).getAttribute("value");
      String home          = WD.findElement(By.name("home")).getAttribute("value");
      String mobile        = WD.findElement(By.name("mobile")).getAttribute("value");
      String work          = WD.findElement(By.name("work")).getAttribute("value");
      String address       = WD.findElement(By.name("address")).getAttribute("value");
      String email         = WD.findElement(By.name("email")).getAttribute("value");
      String email2        = WD.findElement(By.name("email2")).getAttribute("value");
      String email3        = WD.findElement(By.name("email3")).getAttribute("value");

      WD.navigate().back();

      return new KontaktData().withId(kontakt.getId()).withFirstname(firstname).withLastname(lastname)
              .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withaddress(address).withemail(email)
              .withemail2(email2).withemail3(email3);

   }
}



