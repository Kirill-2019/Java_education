package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.FormatFlagsConversionMismatchException;
import java.util.List;

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
      returnToGroupPage();
   }
   public void modify(int index, GroupData group) {
     selectGroup(index);
      initgroupmodification();
      fillGroupForm(group);
      UpdateGroup();
      returnToGroupPage();
   }

   public int getGroupCount() {
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

   public void delete(int index) {
      selectGroup(index);
      deleteSelectedGroups();
      returnToGroupPage();
   }

}
