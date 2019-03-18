package ru.stqa.pft.addressbook.model;

public class KontaktData {
   private final String firstname;
   private final String middlename;

   public KontaktData(String firstname, String middlename) {
      this.firstname = firstname;
      this.middlename = middlename;
   }

   public String getFirstname() {
      return firstname;
   }

   public String getMiddlename() {
      return middlename;
   }
}
