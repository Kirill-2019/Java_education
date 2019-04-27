package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.*;

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


   public void selectGroup(int index ) {

      WD.findElements(By.name("selected[]")).get(index).click();
   }

   public void selectGroupByID(int id ) {

      WD.findElement(By.cssSelector("input[value ='" + id + "']")).click();
   }

   public void initgroupmodification() {

      click(By.name("edit"));

   }

   public void UpdateGroup() {
      click(By.name("update"));
   }

   public void create(GroupData group) {
      initgroupcreation();
      fillGroupForm(group);
      submitGroupCreation();
      GroupCash=null;
      returnToGroupPage();
   }
    public void modify(GroupData group) {
      selectGroupByID(group.getId());
      initgroupmodification();
      fillGroupForm(group);
      UpdateGroup();
       GroupCash=null;
      returnToGroupPage();
   }

   public int count() {
      return WD.findElements(By.name("selected[]")).size();
   }

   public List<GroupData> list() {
      List<GroupData> groups = new ArrayList<GroupData>();
      List<WebElement> elements = WD.findElements(By.cssSelector("span.group"));
      for (WebElement element :elements ){
        String name = element.getText();
        int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
        groups.add(new GroupData().withId(id).withName(name));
      }
      return groups;

   }


   private Groups GroupCash = null;
   public Groups all() {
      if (GroupCash != null){
         return new Groups(GroupCash);
      }

      GroupCash = new Groups();
      List<WebElement> elements = WD.findElements(By.cssSelector("span.group"));
      for (WebElement element :elements ){
         String name = element.getText();
         int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
         GroupCash.add(new GroupData().withId(id).withName(name));
      }
      return  new Groups(GroupCash);

   }
 public void delete(GroupData Group) {
      selectGroupByID(Group.getId());
      deleteSelectedGroups();
      GroupCash=null;
      returnToGroupPage();


   }
}
