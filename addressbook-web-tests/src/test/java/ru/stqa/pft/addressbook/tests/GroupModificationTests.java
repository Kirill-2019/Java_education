package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.assertEquals;

public class GroupModificationTests extends TestBase {

   @BeforeMethod
   public void ensurePreconditions(){
      if (app.db().groups().size() == 0){
         app.goTO().goToGroupPage();
         app.group().create(new GroupData().withName("test3"));
      }


      /*app.goTO().goToGroupPage();
      if (app.group().all().size()==0){
         app.group().create(new GroupData().withName("test3"));
      }
      */
   }
   @Test
   public void testGroupModification(){
     // Groups before = app.group().all();
      Groups before = app.db().groups();
      GroupData modifiedGroup = before.iterator().next();
      GroupData group = new GroupData().withId(modifiedGroup.getId()).withName("test5").withHeader("test4").withFooter("test3");
      app.goTO().goToGroupPage();
      app.group().modify(group);
      assertEquals(app.group().count(), before.size());
     // Groups after = app.group().all();

      Groups after = app.db().groups();
      assertThat(after, equalTo(before.without(modifiedGroup).withadded(group)));
      veryfyGroupListInUI();

   }
}
