package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.KontaktData;
import ru.stqa.pft.addressbook.model.Kontakts;

import java.util.Set;

public class dellGroupContactTest extends TestBase {

   @BeforeTest
   public void prepare(){
      ensurePreconditions();

      Kontakts kontakts = app.db().kontakts();
      Groups groups = app.db().groups();
      Groups before = new Groups();
      int sum = 0;

      for (KontaktData modifiedKontakt : kontakts) {
         if (modifiedKontakt.getGroups().size() != 0) {
            sum = sum + modifiedKontakt.getGroups().size();
         }
      }

      if (sum ==0){
         app.getKontactHelper().goHome();
         app.getKontactHelper().selectKontaktByID(kontakts.iterator().next().getId());
         app.getKontactHelper().selectGroupByid( groups.iterator().next().getId());
         app.getKontactHelper().addContactInGroup();
         app.getKontactHelper().goHome();
      }
   }

   @Test
   public void testDellGroupContact() throws InterruptedException {
      int idGroup = 0;
      KontaktData dellKontakt = new KontaktData();
      Groups groups = app.db().groups();


      Set<KontaktData> before = new Kontakts();
      for (GroupData group : groups) {
         before = group.getKontakts();
         if (before.size() != 0) {

            app.getKontactHelper().goHome();
            idGroup = group.getId();
            app.getKontactHelper().selectGroupByidUP(idGroup);
            //удалим 1 контакт
            dellKontakt = group.getKontakts().iterator().next();
            app.getKontactHelper().selectKontaktByID(dellKontakt.getId());
            app.getKontactHelper().removeKontakt();
            app.getKontactHelper().goHome();

            break;

         }

      }

      groups = app.db().groups();
      Set<KontaktData> after = null;
      for (GroupData group : groups) {

         if (group.getId() == idGroup) {
            after = group.getKontakts();
            break;
         }
      }


      MatcherAssert.assertThat(((Kontakts) after).withadded(dellKontakt), CoreMatchers.equalTo(before));


   }



}
