package ru.stqa.pft.addressbook.tests;


import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.KontaktData;
import ru.stqa.pft.addressbook.model.Kontakts;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class KontactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    if (app.db().kontakts().size() ==0){
      app.getKontactHelper().CreateKontact(new KontaktData().withFirstname("bla").withMiddlename("midddlename").withLastname("lastname").withNickname("NICK").withGroup("test3"));
      app.goTO().goTohomePage();
    }
  }

  @Test
  public void testKontactDeletion() throws Exception {

    //Kontakts before = app.getKontactHelper().all();
    Kontakts before = app.db().kontakts();
    KontaktData deletedKontakt = before.iterator().next();
    app.getKontactHelper().delete(deletedKontakt);
    app.getKontactHelper().acceptNext();
    //Kontakts after = app.getKontactHelper().all();
    Kontakts after = app.db().kontakts();
    Assert.assertEquals(after.size(), before.size() -1);
    MatcherAssert.assertThat(after, CoreMatchers.equalTo(before.without(deletedKontakt)));
  }



}
