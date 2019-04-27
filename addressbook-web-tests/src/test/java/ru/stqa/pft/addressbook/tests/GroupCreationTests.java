package ru.stqa.pft.addressbook.tests;


import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

//import java.util.Comparator;
//import java.util.FormatFlagsConversionMismatchException;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;


public class GroupCreationTests  extends TestBase {

  @Test
  public void testGroupCreation() {

    app.goTO().goToGroupPage();

    Groups before = app.group().all();
    GroupData group = new GroupData().withName("test2");
    app.group().create(group);
    assertThat(app.group().count(),equalTo(before.size()+1));
    Groups after = app.group().all();
    assertThat(after, equalTo(before.withadded(group.withId(after.stream().mapToInt((g)-> g.getId()).max().getAsInt()))));

  }

  @Test
  public void testGroupBadCreation(){

    app.goTO().goToGroupPage();

    Groups before = app.group().all();
    GroupData group = new GroupData().withName("test2'");
    app.group().create(group);
    assertThat(app.group().count(),equalTo(before.size()));
    Groups after = app.group().all();
    assertThat(after, equalTo(before));

  }



}