package ru.stqa.pft.addressbook;


import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class GroupCreationTests  extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    goToGroupPage();
    initgroupcreation();
    fillGroupForm(new GroupData("test3", "test3", "test3"));
    submitGroupCreation();
    returnToGroupPage();
   // GoToGroupPage("Logout");
  }


}