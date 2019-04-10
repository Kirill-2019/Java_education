package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.KontaktData;

public class KontaktModification extends TestBase {
   @Test

   public void testKontaktModification() {


      app.getGroupHelper().selectGroup();
      app.getKontactHelper().initkontaktmodification();
      app.getKontactHelper().fillGroupForm(new KontaktData("---bla---", "blalbla", "test","TesT",null),false);
      app.getKontactHelper().updatekontakt();
      app.getNavigationHelper().goTohomePage();
   }

}


