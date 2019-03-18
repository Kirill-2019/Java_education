package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupHelper {
   private WebDriver WD;

   public GroupHelper(WebDriver WD) {
      this.WD = WD;
   }

   public void fillGroupForm(GroupData groupData) {
     WD.findElement(By.name("group_name")).click();
     WD.findElement(By.name("group_name")).clear();
     WD.findElement(By.name("group_name")).sendKeys(groupData.getName());
     WD.findElement(By.name("group_header")).clear();
     WD.findElement(By.name("group_header")).sendKeys(groupData.getHeader());
     WD.findElement(By.name("group_footer")).clear();
     WD.findElement(By.name("group_footer")).sendKeys(groupData.getFooter());
   }

   public void submitGroupCreation() {
     WD.findElement(By.name("submit")).click();

   }

   public void returnToGroupPage() {
     WD.findElement(By.linkText("group page")).click();
   }

   public void initgroupcreation() {

     WD.findElement(By.name("new")).click();
   }

   public void deleteSelectedGroups() {
     WD.findElement(By.name("delete")).click();
   }

   public void selectGroup() {
     WD.findElement(By.name("selected[]")).click();
   }
}
