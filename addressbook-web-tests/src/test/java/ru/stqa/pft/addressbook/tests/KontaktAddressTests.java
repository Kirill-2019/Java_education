package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.KontaktData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class KontaktAddressTests extends TestBase {

   @Test
   public void testKontaktAddress (){

      KontaktData kontakt = app.getKontactHelper().all().iterator().next();
      KontaktData kontaktInfoFromEditForm = app.getKontactHelper().infoFromEditForm(kontakt);
      assertThat(kontakt.getAddress(), equalTo(kontaktInfoFromEditForm.getAddress()));
   }
}

