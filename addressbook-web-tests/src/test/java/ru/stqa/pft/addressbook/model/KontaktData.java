package ru.stqa.pft.addressbook.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;


import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "addressbook")
@XStreamAlias("kontakt")
public class KontaktData {
   @Column(name="firstname")
   private String firstname;

   private String middlename;
   @Column(name = "lastname")
   private String lastname;
   private String nickname;

   @Column(name = "home")
   @Type(type = "text")
   private String homePhone;
   @Column(name = "mobile")
   @Type(type = "text")
   private String mobilePhone;
   @Column(name = "work")
   @Type(type = "text")
   private String workPhone;
   @Transient
   private String allPhones;
   @Column(name = "address")
   @Type(type = "text")
   private String address;
   @Column(name = "email")
   @Type(type = "text")
   private String email;
   @Column(name = "email2")
   @Type(type = "text")
   private String email2;
   @Column(name = "email3")
   @Type(type = "text")
   private String email3;
   @Transient
   private String allmails;
   @Transient
   @Column(name = "photo")
   @Type(type = "text")
   private String photo;

   @XStreamOmitField
   @Id
   @Column(name = "id")
   private Integer id;

   @ManyToMany(fetch = FetchType.EAGER)
   @JoinTable(name="address_in_groups",joinColumns = @JoinColumn (name = "id"),inverseJoinColumns = @JoinColumn(name = "group_id"))
   private Set<GroupData> groups = new HashSet<GroupData>();


   public File getPhoto() {
      if (photo==null) {
         return null;
      }

      return new File(photo);
   }

   public KontaktData withPhoto(File photo) {
      this.photo = photo.getPath();
      return this;
   }

   public KontaktData withId(int id) {
      this.id = id;
      return this;
   }

   public KontaktData withFirstname(String firstname) {
      this.firstname = firstname;
      return this;
   }

   public KontaktData withMiddlename(String middlename) {
      this.middlename = middlename;
      return this;
   }

   public KontaktData withLastname(String lastname) {
      this.lastname = lastname;
      return this;
   }

   public KontaktData withNickname(String nickname) {
      this.nickname = nickname;
      return this;
   }


   public KontaktData withHomePhone(String home) {
      this.homePhone = home;
      return this;
   }

   public KontaktData withMobilePhone(String mobile) {
      this.mobilePhone = mobile;
      return this;
   }
   public KontaktData withWorkPhone(String work) {
      this.workPhone = work;
      return this;
   }

   public KontaktData withallPhones(String allPhones) {
      this.allPhones = allPhones;
      return this;
   }

   public KontaktData withemail(String email) {
      this.email = email;
      return this;
   }
   public KontaktData withemail2(String email2) {
      this.email2 = email2;
      return this;
   }
   public KontaktData withemail3(String email3) {
      this.email3 = email3;
      return this;
   }

   public KontaktData withallmails(String allmails) {
      this.allmails = allmails;
      return this;
   }

   public KontaktData withaddress(String address) {
      this.address = address;
      return this;
   }

   public KontaktData inGroup(GroupData group) {
      groups.add(group);
      return this;
   }

//  publ
//  ic KontaktData(String firstname, String middlename, String lastname, String nickname, String group) {
//      this.firstname = firstname;
//      this.middlename = middlename;
//      this.lastname = lastname;
//      this.nickname = nickname;
//      this.group = group;
//      this.id = Integer.MAX_VALUE;
//   }
//
//   public KontaktData(Integer id, String firstname, String middlename, String lastname, String nickname, String group) {
//      this.firstname = firstname;
//      this.middlename = middlename;
//      this.lastname = lastname;
//      this.nickname = nickname;
//      this.group = group;
//      this.id = id;
//   }
//
//   public KontaktData(Integer id, String firstname, String lastname) {
//      this.firstname = firstname;
//      this.middlename = null;
//      this.lastname = lastname;
//      this.nickname = null;
//      this.group = null;
//      this.id = id;
//   }



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

   public String getallPhones() {
      return allPhones;
   }



   public int getId() {
      return id;
   }

   public String getEmail() {
      return email;
   }

   public String getEmail2() {
      return email2;
   }
   public String getEmail3() {
      return email3;
   }

   public String getAllmails() {
      return allmails;
   }

   public String getAddress() {
      return address;
   }

   public Groups getGroups() {
      return new Groups(groups);
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


   public String getHomePhone() {
      return homePhone;
   }

   public String getMobilePhone() {
      return mobilePhone;
   }

   public String getWorkPhone() {
      return workPhone;
   }

   @Override
   public String toString() {
      return "KontaktData{" +
               "firstname='" + firstname + '\'' +
               "lastname='" + lastname + '\'' +
               "id='" + id + '\'' +
              '}';
   }



}

