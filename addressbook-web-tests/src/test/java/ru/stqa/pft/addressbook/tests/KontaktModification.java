package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.KontaktData;

import java.util.List;

public class KontaktModification extends TestBase {
   @Test

   public void testKontaktModification() {



      if  (! app.getGroupHelper().isThereAGroup()){
         app.getKontactHelper().CreateKontact(new KontaktData("bla","blablalbla","lastname", "nick_name", "test3"));
         app.getNavigationHelper().goTohomePage();
      }

      List<GroupData> before = app.getGroupHelper().getGroupList();
      app.getGroupHelper().selectGroup(before.size() -1);
      app.getKontactHelper().initkontaktmodification();
      app.getKontactHelper().fillGroupForm(new KontaktData("---bla---", "blalbla", "test","TesT",null),false);
      app.getKontactHelper().updatekontakt();
      app.getNavigationHelper().goTohomePage();

      List<GroupData> after = app.getGroupHelper().getGroupList();

      Assert.assertEquals(after.size(), before.size());

   }

}


