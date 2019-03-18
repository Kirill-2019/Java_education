package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class KontactDeletionTests extends TestBase {

  @Test
  public void testKontactDeletion() throws Exception {

    app.getGroupHelper().selectGroup();
    app.getKontactHelper().deleteSelectedKontact();
    app.getKontactHelper().acceptNext();
  }

}
