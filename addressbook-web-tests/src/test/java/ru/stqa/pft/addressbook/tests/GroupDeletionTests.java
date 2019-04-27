package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;
import java.util.Set;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTO().goToGroupPage();
    if (app.group().all().size() ==0){
      app.group().create(new GroupData().withName("test3"));
    }
  }

  @Test
  public void testGroupDeletion() {
    Groups before = app.group().all();
    GroupData deletedGroup = before.iterator().next();
    app.group().delete(deletedGroup);
    Assert.assertEquals(app.group().count(), before.size() -1);
    Groups after = app.group().all();
    MatcherAssert.assertThat(after, CoreMatchers.equalTo(before.without(deletedGroup)));
  }
}