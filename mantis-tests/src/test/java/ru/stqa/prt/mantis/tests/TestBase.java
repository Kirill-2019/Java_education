package ru.stqa.prt.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;

import java.io.File;
import java.io.IOException;


public class TestBase {



   protected static ApplicationManager app = null;


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
