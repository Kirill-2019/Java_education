package ru.stqa.prt.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;
import ru.stqa.pft.mantis.appmanager.SoapHelper;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;


public class TestBase {



   protected static ApplicationManager app = null;

   public void skipIfNotFixed(int issueId) throws MalformedURLException, ServiceException, RemoteException {
      if (isIssueOpen(issueId)) {
         throw new SkipException("Ignored because of issue " + issueId);
      }
   }

   public boolean isIssueOpen(int issueId) throws MalformedURLException, ServiceException, RemoteException {

      MantisConnectPortType mc = SoapHelper.getMantisConnect();
      IssueData issue = mc.mc_issue_get("administrator","root", BigInteger.valueOf(issueId));
      if(!issue.getStatus().getId().equals(80)){
         return false;
      }


      return true ;
   }


   static {
      try {
         app = new ApplicationManager(BrowserType.FIREFOX);
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   //protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser",BrowserType.CHROME));

   @BeforeSuite
  // @BeforeMethod(alwaysRun = true)
   public void setUp() throws Exception {
      app.init();
      app.ftp().upload(new File("src/test/resources/config_inc.php"),"config_inc.php","config_inc.php.bak");

   }

   @AfterSuite
   //@AfterMethod(alwaysRun = true)
   public void tearDown() throws Exception {
      app.ftp().restore("config_inc.php.bak","config_inc.php");
      app.stop();
   }

}
