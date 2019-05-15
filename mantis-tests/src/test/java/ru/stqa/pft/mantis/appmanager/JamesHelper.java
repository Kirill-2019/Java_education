package ru.stqa.pft.mantis.appmanager;

import org.apache.commons.net.telnet.TelnetClient;
import org.omg.CORBA.TIMEOUT;
import ru.stqa.model.MailMessage;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.SocketException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JamesHelper {

   private Session mailSession;
   private ApplicationManager app;
   private TelnetClient telnet;
   private String mailserver;
   private InputStream in;
   private PrintStream out;
   private long timeout;
   private Store store;


   public JamesHelper(ApplicationManager app){
      this.app = app;
      telnet = new TelnetClient();
      mailSession = Session.getDefaultInstance(System.getProperties());

   }

   public boolean doesUserExist(String name) {
      initTelnetSession();
      write("verify " + name);
      String result = readUntil("exist");
      closeTelnetSession();
      return result.trim().equals("User " + name + " exist");
   }

   public void createUser(String name, String password){
      initTelnetSession();
      write("adduser " + name + " " + password);
      String result = readUntil("User " + name + " added");
      closeTelnetSession();
   }

   private void closeTelnetSession() { write ("quit");
   }


   private void initTelnetSession() {
      mailserver = app.getProperty("mailserver.host");
      int port = Integer.parseInt(app.getProperty("mailserver.port"));
      String login = app.getProperty("mailserver.adminlogin");
      String password = app.getProperty("mailserver.adminpassword");

      try{
         telnet.connect(mailserver, port);
         in = telnet.getInputStream();
         out = new PrintStream( telnet.getOutputStream());

      } catch (SocketException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
      readUntil("Login id:");
      write("");
      readUntil("Password:");
      write("");

      readUntil("Login id:");
      write(login);
      readUntil("Password:");
      write(password);

      readUntil("Welcome " + login + ". HELP for a list of commands");




   }

   private void write(String value) {
      try {
         out.println(value);
         out.flush();
         System.out.println(value);
      } catch (Exception e){
         e.printStackTrace();
      }

   }

   private String readUntil(String pattern) {
      try{
         char lastChar = pattern.charAt(pattern.length() - 1);
         StringBuffer sb = new StringBuffer();

         while (true){
            char ch = (char) in.read();
            System.out.println(ch);
            sb.append(ch);
            if (ch == lastChar){
               if (sb.toString().endsWith(pattern)){
                  return sb.toString();
               }
            }
         }
      } catch (Exception e){
         e.printStackTrace();
      }

      return null;

   }


   public List<MailMessage> waitForMail(String username, String password, int timeout) throws MessagingException {
      long now = System.currentTimeMillis();
      while (System.currentTimeMillis() < now + timeout) {
         List <MailMessage> allMail = getAllMail(username, password);
         if (allMail.size() > 0){
            return allMail;
         }
         try{
            Thread.sleep(1000);
         } catch (InterruptedException e) {
            e.printStackTrace();
         }

      }

      throw new Error ("No mail :-(");
   }
   public List<MailMessage> getAllMail(String username, String password) throws MessagingException{
      Folder inbox = openInbox(username, password);
      List<MailMessage> messages = Arrays.asList(inbox.getMessages()).stream().map((m) -> toModelMail(m)).collect(Collectors.toList());
      closeFolder(inbox);
      return messages;
   }

   private void closeFolder(Folder folder) throws MessagingException {
      folder.close(true);
      store.close();
   }

   private Folder openInbox(String username, String password) throws MessagingException {
      store =mailSession.getStore("pop3");
      store.connect(mailserver, username, password);
      Folder folder = store.getDefaultFolder().getFolder("INBOX");
      folder.open(Folder.READ_WRITE);
      return folder;

   }

   public static MailMessage toModelMail(Message m) {
      try{
          return new MailMessage(m.getAllRecipients()[0].toString(), (String) m.getContent());
      } catch (MessagingException e) {
         e.printStackTrace();
         return null;
      } catch (IOException e) {
         e.printStackTrace();
         return null;
      }
   }
}
