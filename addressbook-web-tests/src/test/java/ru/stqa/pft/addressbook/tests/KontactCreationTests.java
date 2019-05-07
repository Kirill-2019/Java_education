package ru.stqa.pft.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.KontaktData;
import ru.stqa.pft.addressbook.model.Kontakts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class KontactCreationTests extends TestBase{

  @DataProvider
  public Iterator<Object[]> validKontaktsFromXML() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/kontakts.xml")));
    String xml = "";
    String line = reader.readLine();
    while (line !=null){
      xml += line;
      line = reader.readLine();
    }
    XStream xstream = new XStream();
    xstream.processAnnotations(KontaktData.class);
    List<KontaktData> kontakts = (List<KontaktData>) xstream.fromXML(xml);
    return kontakts.stream().map((g)-> new Object[]{g}).collect(Collectors.toList()).iterator();
  }


  @Test (enabled = false)
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

  @Test (dataProvider = "validKontaktsFromXML")
  public void testKontactCreation(KontaktData kontakt) throws Exception {

    Groups groups = app.db().groups();
    if (groups.size()==0){
      GroupData group = new GroupData().withName("test2'");
      app.group().create(group);
    }

    //Kontakts before = app.getKontactHelper().all();
    Kontakts before = app.db().kontakts();
    File photo = new File("src/test/resources/1515055240299396095.jpg");
    app.getKontactHelper().CreateKontact(kontakt.withPhoto(photo).inGroup(groups.iterator().next()));
    //Kontakts after = app.getKontactHelper().all();
    Kontakts after = app.db().kontakts();
    assertThat(after.size(),equalTo(before.size()+1));
    assertThat(after, equalTo(before.withadded(kontakt.withId(after.stream().mapToInt((g)-> g.getId()).max().getAsInt()))));
    veryfyKontaktListInUI();

  }

}

