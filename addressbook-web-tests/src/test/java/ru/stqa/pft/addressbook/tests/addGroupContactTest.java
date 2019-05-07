package ru.stqa.pft.addressbook.tests;

import com.google.common.collect.ForwardingSet;
import jdk.nashorn.internal.ir.WhileNode;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.KontaktData;
import ru.stqa.pft.addressbook.model.Kontakts;

import java.util.ArrayDeque;
import java.util.List;

public class addGroupContactTest extends TestBase {

   GroupData addedGroup = new GroupData();



   @BeforeMethod
   public void ensurePreconditions(){
      Kontakts kontakts = app.db().kontakts();
      Groups groups = app.db().groups();
      if (kontakts.size() ==0){
         app.getKontactHelper().CreateKontact(new KontaktData().withFirstname("bla").withMiddlename("midddlename").withLastname("lastname").withNickname("NICK"));
         app.goTO().goTohomePage();
      }

      if (groups.size()==0){
         app.goTO().goToGroupPage();
         app.group().create(new GroupData().withName("test1"));
         app.getKontactHelper().goHome();
      }
   }

   @Test
   public void testAddGroupContact () throws InterruptedException {

      Kontakts kontakts = app.db().kontakts();
      Groups   groups   = app.db().groups();
      Groups before = new Groups();
      Groups after = new Groups();
      int id_mod = 0;

      Boolean createGroup = true;
      for (KontaktData modifiedKontakt : kontakts ){
         before = modifiedKontakt.getGroups();
         if (modifiedKontakt.getGroups().size() == 0) {
            //у выбранного контакта нет привязки, а в списке групп есть минимум 1. Привязываем к любой группе
            app.getKontactHelper().selectKontaktByID(modifiedKontakt.getId());
            addedGroup = groups.iterator().next();
            app.getKontactHelper().selectGroupByid(addedGroup.getId());
            app.getKontactHelper().addContactInGroup();
            app.getKontactHelper().goHome();
            id_mod = modifiedKontakt.getId();
            createGroup = false;
            break;

         }else if (modifiedKontakt.getGroups().size() < groups.size()) {
            //у выбранного контакта есть привязки к группам, но мы можем его привязать к другой из списка.
            app.getKontactHelper().selectKontaktByID(modifiedKontakt.getId());
            Groups lastGroup = groups;
            for (GroupData g : modifiedKontakt.getGroups()){
                lastGroup = lastGroup.without(g);
            }
            addedGroup = lastGroup.iterator().next();
            app.getKontactHelper().selectGroupByid(addedGroup.getId());
            app.getKontactHelper().addContactInGroup();
            app.getKontactHelper().goHome();
            id_mod = modifiedKontakt.getId();
            createGroup = false;
            break;
         }
         //выбранный контакт привязан ко всем группам, берем следующий.
      }

      //кончились контакты выбираем любой и добавляем группу
      if (createGroup) {
         KontaktData modifiedKontakt = kontakts.iterator().next();
         before = modifiedKontakt.getGroups();

         app.goTO().goToGroupPage();
         addedGroup = new GroupData().withName("test1");
         app.group().create(addedGroup);
         app.getKontactHelper().goHome();
         int idNewGroup = app.getKontactHelper().getIdNewGroup();
         app.getKontactHelper().selectGroupByid(idNewGroup);
         addedGroup.withId(idNewGroup);
         app.getKontactHelper().selectKontaktByID(modifiedKontakt.getId());
         app.getKontactHelper().addContactInGroup();
         app.getKontactHelper().goHome();
         id_mod = modifiedKontakt.getId();


      }

      kontakts = app.db().kontakts();
      for (KontaktData modifiedKontakt : kontakts ) {
         if(modifiedKontakt.getId() == id_mod){
            after = modifiedKontakt.getGroups();
         }
      }
      before = before.withadded(addedGroup);
      MatcherAssert.assertThat(after, CoreMatchers.equalTo(before));
      int a=1;
  }



}
