package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase {

   @Test

   public void testGroupModification(){
      app.getNavigationHelper().goToGroupPage();
      app.getGroupHelper().selectGroup();
      app.getGroupHelper().initgroupmodification();
      app.getGroupHelper().fillGroupForm(new GroupData("test5", "test4", "test3"));
      app.getGroupHelper().UpdateGroup();
      app.getGroupHelper().returnToGroupPage();
   }

}
