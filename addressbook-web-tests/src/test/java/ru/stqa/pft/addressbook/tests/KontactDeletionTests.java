package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.NavigationHelper;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.KontaktData;

import static org.testng.Assert.assertTrue;

public class KontactDeletionTests extends TestBase {

  @Test
  public void testKontactDeletion() throws Exception {

    if  (! app.getGroupHelper().isThereAGroup()){
      app.getKontactHelper().CreateKontact(new KontaktData("bla","blablalbla","lastname", "nick_name", "test3"));
      app.getNavigationHelper().goTohomePage();
    }

    app.getGroupHelper().selectGroup();
    app.getKontactHelper().deleteSelectedKontact();
    app.getKontactHelper().acceptNext();
  }

}
