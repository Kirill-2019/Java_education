package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.KontaktData;
import ru.stqa.pft.addressbook.model.Kontakts;

import java.util.Comparator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class KontactCreationTests extends TestBase{

  @Test
  public void testKontact() throws Exception {

    Kontakts before = app.getKontactHelper().all();
    KontaktData kontakt = new KontaktData().withFirstname("bla").withLastname("lastname").withMiddlename("blablabla").withNickname("nick_name").withGroup("test3");
    app.getKontactHelper().CreateKontact(kontakt);
    Kontakts after = app.getKontactHelper().all();
    assertThat(after.size(),equalTo(before.size()+1));
    assertThat(after, equalTo(before.withadded(kontakt.withId(after.stream().mapToInt((g)-> g.getId()).max().getAsInt()))));

    }
}

