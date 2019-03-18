package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.KontaktData;

public class KontactCreationTests extends TestBase{

  @Test
  public void testKontact() throws Exception {
    app.getKontactHelper().initkontcreation();
    app.getKontactHelper().fillGroupForm(new KontaktData("bla","blablalbla"));
    app.getKontactHelper().submitKontaktCreation();

  }

}

