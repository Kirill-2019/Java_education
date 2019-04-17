package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupHelper extends HelperBase {

   public GroupHelper(WebDriver WD) {
      super(WD);
   }

   public boolean isThereAGroup() {
      return iselementPresent(By.name("selected[]"));
   }

   public void fillGroupForm(GroupData groupData) {
      type(By.name("group_name"), groupData.getName());
      type(By.name("group_header"), groupData.getHeader());
      type(By.name("group_footer"), groupData.getFooter());
   }

   public void submitGroupCreation() {
      click(By.name("submit"));

   }

   public void returnToGroupPage() {
      click(By.linkText("group page"));
   }

   public void initgroupcreation() {

      click(By.name("new"));
   }

   public void deleteSelectedGroups() {
      click(By.name("delete"));
   }

   public void selectGroup() {
      click(By.name("selected[]"));
   }

   public void initgroupmodification() {

      click(By.name("edit"));

   }

   public void UpdateGroup() {
      click(By.name("update"));
   }

   public void CreateGroup(GroupData group) {
      initgroupcreation();
      fillGroupForm(group);
      submitGroupCreation();
      returnToGroupPage();
   }


}
