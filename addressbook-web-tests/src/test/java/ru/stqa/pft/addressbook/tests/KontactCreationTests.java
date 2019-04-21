package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.KontaktData;

import java.util.Comparator;
import java.util.List;

public class KontactCreationTests extends TestBase{

  @Test
  public void testKontact() throws Exception {

    List<KontaktData> before = app.getKontactHelper().getKontaktList();

    KontaktData kontakt = new KontaktData("bla","blablalbla","lastname", "nick_name", "test3");
    app.getKontactHelper().CreateKontact(kontakt);
    List<KontaktData> after = app.getKontactHelper().getKontaktList();





    int max = after.stream().max((o1, o2) -> Integer.compare(o1.getId(),o2.getId())).get().getId();
    kontakt.setId(max);
    before.add(kontakt);
    //Assert.assertEquals(new HashSet<>(before),new HashSet<>(after));

    Comparator<? super KontaktData> byId = (g1, g2 ) -> Integer.compare(g1.getId(),g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before,after);


  }

}

