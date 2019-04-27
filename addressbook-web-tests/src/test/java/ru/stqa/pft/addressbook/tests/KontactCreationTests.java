package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.KontaktData;
import ru.stqa.pft.addressbook.model.Kontakts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class KontactCreationTests extends TestBase{

  @Test
  public void testKontact() throws Exception {

    Kontakts before = app.getKontactHelper().all();
    File photo = new File("src/test/resources/1515055240299396095.jpg");
    KontaktData kontakt = new KontaktData().withFirstname("bla").withLastname("lastname").withMiddlename("blablabla")
            .withNickname("nick_name").withPhoto(photo);
    app.getKontactHelper().CreateKontact(kontakt);
    Kontakts after = app.getKontactHelper().all();
    assertThat(after.size(),equalTo(before.size()+1));
    assertThat(after, equalTo(before.withadded(kontakt.withId(after.stream().mapToInt((g)-> g.getId()).max().getAsInt()))));

    }

    @Test(enabled = false)
    public void TestCurrentDir (){
      File currentDir = new File(".");
      System.out.println(currentDir.getAbsolutePath());
      File photo = new File("src/test/resources/1515055240299396095.jpg");
      System.out.println(photo.getAbsolutePath());
      System.out.println(photo.exists());

  }

}

