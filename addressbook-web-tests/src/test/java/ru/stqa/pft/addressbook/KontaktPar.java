package ru.stqa.pft.addressbook;

public class KontaktPar {
   private final String firstname;
   private final String middlename;

   public KontaktPar(String firstname, String middlename) {
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
