package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.KontaktData;

import java.util.Comparator;
import java.util.List;

public class KontaktModification extends TestBase {
   @Test

   public void testKontaktModification() {



      if  (! app.getGroupHelper().isThereAGroup()){
         app.getKontactHelper().CreateKontact(new KontaktData("bla","blablalbla","lastname", "nick_name", "test3"));
         app.getNavigationHelper().goTohomePage();
      }

      List<KontaktData> before = app.getKontactHelper().getKontaktList();

      app.getGroupHelper().selectGroup(before.size()-1);
      app.getKontactHelper().initkontaktmodification(before.size()-1);

      KontaktData kontakt = new KontaktData(before.get(before.size()-1).getId(),"---bla---", "blalbla", "test","TesT",null);

      app.getKontactHelper().fillGroupForm(kontakt,false);
      app.getKontactHelper().updatekontakt();
      app.getNavigationHelper().goTohomePage();



      List<KontaktData> after = app.getKontactHelper().getKontaktList();

      before.remove(before.size()-1);
      before.add(kontakt);

      Comparator<? super KontaktData> byId = (g1, g2 ) -> Integer.compare(g1.getId(),g2.getId());
      before.sort(byId);

      after.sort(byId);

      Assert.assertEquals(after.size(), before.size());


      //Assert.assertEquals(new HashSet<>(before),new HashSet<>(after));
      Assert.assertEquals(before, after);

   }

}


