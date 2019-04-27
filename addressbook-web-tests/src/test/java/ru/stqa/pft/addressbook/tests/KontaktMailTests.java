package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.KontaktData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class KontaktMailTests extends TestBase {

   @Test
   public void testsKontaktMail(){
      KontaktData kontakt = app.getKontactHelper().all().iterator().next();
      KontaktData kontaktInfoFromEditForm = app.getKontactHelper().infoFromEditForm(kontakt);

      assertThat(kontakt.getAllmails(), equalTo(mergeMails(kontaktInfoFromEditForm)));
   }

   private String mergeMails(KontaktData kontakt) {
      return Arrays.asList(kontakt.getEmail(),kontakt.getEmail2(),kontakt.getEmail3())
              .stream().filter((s)-> !s.equals(""))
              .collect(Collectors.joining("\n"));

   }

}
