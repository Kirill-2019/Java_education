package ru.stqa.prt.mantis.tests;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.model.MailMessage;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import sun.plugin2.main.client.WDonatePrivilege;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTest extends TestBase {

   @BeforeMethod
   public void startMailServer() {
      app.mail().start();
   }

   @Test
   public void testChangePassword () throws IOException, MessagingException {


         app.change().login();
         String user    = app.change().getuser();
         String email   = app.change().reset(user);
         String pass    = String.format("P%s", System.currentTimeMillis());


         List<MailMessage> mailMessages = app.mail().waitForMail(1, 60000);
         String confirmationLink = findConfirmationLink(mailMessages, email);
         app.registration().finish(confirmationLink, pass);

         HttpSession session = (HttpSession) app.newSession();
         assertTrue(((HttpSession) session).login(user,pass));
         assertTrue(session.isLoggedInAs(user));




   }


   @AfterMethod(alwaysRun = true)
   public void stopMailServer(){
      app.mail().stop();
   }


   private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
      MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
      VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
      return regex.getText(mailMessage.text);
   }

}

