package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;


public class TestBase {

   Logger logger = LoggerFactory.getLogger(TestBase.class);

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
   }

   @AfterSuite
   //@AfterMethod(alwaysRun = true)
   public void tearDown() throws Exception {
      app.stop();
   }

   @BeforeMethod
   public void logteststart(Method m,Object[] p){logger.info("start test %m" + m.getName() + "with parametrs " + Arrays.asList(p));}

   @AfterMethod
   public void logtestStop(Method m){logger.info("stop test %m" + m.getName());}

}
