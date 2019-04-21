package ru.stqa.pft.addressbook.tests;


import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.NavigationHelper;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.KontaktData;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class KontactDeletionTests extends TestBase {

  @Test
  public void testKontactDeletion() throws Exception {



    if  (! app.getGroupHelper().isThereAGroup()){
      app.getKontactHelper().CreateKontact(new KontaktData("bla","blablalbla","lastname", "nick_name", "test3"));
      app.getNavigationHelper().goTohomePage();
    }
    List<KontaktData> before = app.getKontactHelper().getKontaktList();
    app.getGroupHelper().selectGroup(before.size() -1);
    app.getKontactHelper().deleteSelectedKontact();
    app.getKontactHelper().acceptNext();


    List<KontaktData> after = app.getKontactHelper().getKontaktList();

    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() -1);
    Assert.assertEquals(before,after);
  }



}
