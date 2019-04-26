package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.KontaktData;
import ru.stqa.pft.addressbook.model.Kontakts;

import java.util.Comparator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class KontaktModification extends TestBase {

   @BeforeMethod
   public void ensurePreconditions(){
      if (app.getKontactHelper().all().size() ==0){
         app.getKontactHelper().CreateKontact(new KontaktData().withFirstname("bla").withMiddlename("midddlename").withLastname("lastname").withNickname("NICK").withGroup("test3"));
         app.goTO().goTohomePage();
      }
   }

   @Test
   public void testKontaktModification() {

      Kontakts before = app.getKontactHelper().all();
      KontaktData modifiedKontakt = before.iterator().next();
      KontaktData kontakt = new KontaktData().withId(modifiedKontakt.getId()).withFirstname("---bla---").withMiddlename("blalbla").withLastname("test").withNickname("TesT");
      app.getKontactHelper().modify(kontakt);
      Kontakts after = app.getKontactHelper().all();
      assertEquals(after.size(), before.size());
     assertThat(after, equalTo(before.without(modifiedKontakt).withadded(kontakt)));


      /*app.group().selectGroupByID(modifiedKontakt.getId());
      app.getKontactHelper().initkontaktmodification(before.size()-1);

      KontaktData kontakt = new KontaktData(before.get(before.size()-1).getId(),"---bla---", "blalbla", "test","TesT",null);

      app.getKontactHelper().fillGroupForm(kontakt,false);
      app.getKontactHelper().updatekontakt();
      app.goTO().goTohomePage();



      List<KontaktData> after = app.getKontactHelper().getKontaktList();

      before.remove(before.size()-1);
      before.add(kontakt);

      Comparator<? super KontaktData> byId = (g1, g2 ) -> Integer.compare(g1.getId(),g2.getId());
      before.sort(byId);

      after.sort(byId);

      Assert.assertEquals(after.size(), before.size());


      //Assert.assertEquals(new HashSet<>(before),new HashSet<>(after));
      Assert.assertEquals(before, after);*/

   }

}


