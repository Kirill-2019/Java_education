package ru.stqa.pft.addressbook.model;

import java.security.acl.Group;
import java.util.Objects;

public class KontaktData {
   private final String firstname;
   private final String middlename;
   private final String lastname;
   private final String nickname;
   private final String group;
   private Integer id;

   public KontaktData(String firstname, String middlename, String lastname, String nickname, String group) {
      this.firstname = firstname;
      this.middlename = middlename;
      this.lastname = lastname;
      this.nickname = nickname;
      this.group = group;
      this.id = Integer.MAX_VALUE;
   }

   public KontaktData(Integer id, String firstname, String middlename, String lastname, String nickname, String group) {
      this.firstname = firstname;
      this.middlename = middlename;
      this.lastname = lastname;
      this.nickname = nickname;
      this.group = group;
      this.id = id;
   }

   public KontaktData(Integer id, String firstname, String lastname) {
      this.firstname = firstname;
      this.middlename = null;
      this.lastname = lastname;
      this.nickname = null;
      this.group = null;
      this.id = id;
   }

   public String getFirstname() {
      return firstname;
   }

   public String getMiddlename() {
      return middlename;
   }

   public String getLastname() {
      return lastname;
   }

   public String getNickname() {
      return nickname;
   }

   public String getGroup() {
      return group;
   }

   public int getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      KontaktData that = (KontaktData) o;
      return Objects.equals(firstname, that.firstname) &&
              Objects.equals(lastname, that.lastname) &&
              Objects.equals(id, that.id);
   }

   @Override
   public int hashCode() {
      return Objects.hash(firstname, lastname, id);
   }
}

