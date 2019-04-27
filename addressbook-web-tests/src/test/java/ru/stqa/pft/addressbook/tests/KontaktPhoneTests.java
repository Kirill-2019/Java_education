package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.KontaktData;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class KontaktPhoneTests extends TestBase {

   @Test
   public void testKontaktPhones(){
     // app.goTO().goTohomePage();
      KontaktData kontakt = app.getKontactHelper().all().iterator().next();
      KontaktData kontaktInfoFromEditForm = app.getKontactHelper().infoFromEditForm(kontakt);

      assertThat(kontakt.getallPhones(), equalTo(mergePhones(kontaktInfoFromEditForm)));
     // assertThat(kontakt.getMobilePhone(), equalTo(cleaned(kontaktInfoFromEditForm.getMobilePhone())));
     // assertThat(kontakt.getWorkPhone(), equalTo(cleaned(kontaktInfoFromEditForm.getWorkPhone())));

   }

   private String mergePhones(KontaktData kontakt) {
      return Arrays.asList(kontakt.getHomePhone(),kontakt.getMobilePhone(),kontakt.getWorkPhone())
              .stream().filter((s)-> !s.equals(""))
              .map(KontaktPhoneTests::cleaned)
              .collect(Collectors.joining("\n"));
   }

   public static String cleaned(String phone){
      return phone.replaceAll("\\s","").replaceAll("[-()]","");
   }
}
