package ru.stqa.pft.mantis.appmanager;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
   private WebDriver WD;


   private Properties properties;
   private String browser;
   private RegistrationHelper registrationHelper;
   private FtpHelper FtpHelper;
   private MailHelper mailHelper;
   private JamesHelper JamesHelper;
   private ChangeHelper changeHelper;
   private SoapHelper SoapHelper;


   public ApplicationManager(String browser) throws IOException {
      this.browser = browser;
      properties = new Properties();

   }

   public void init() throws IOException {

      // String browser = BrowserType.FIREFOX;
      String target = System.getProperty("target", "local");
      properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));


   }


   public void stop() {
      if (WD!=null){WD.quit();}
   }

   public HttpSession newSession(){
      return new HttpSession(this);
   }

   public String getProperty(String key) {
      return properties.getProperty(key);

   }

   public RegistrationHelper registration() {
      if (registrationHelper==null){
         registrationHelper = new RegistrationHelper(this);
      }

      return registrationHelper;
   }

   public FtpHelper ftp(){
      if (FtpHelper == null){
         FtpHelper = new FtpHelper(this);
      }

      return FtpHelper;
   }

   public WebDriver getDriver() {
      if (WD==null){
         if (browser.equals(BrowserType.FIREFOX)) {
            WD = new FirefoxDriver();
         } else if (browser.equals(BrowserType.CHROME)) {
            WD = new ChromeDriver();
         } else {
            WD = new InternetExplorerDriver();
         }

         WD.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
         WD.get(properties.getProperty("web.baseUrl"));
      }

      return WD;

   }

   public MailHelper mail(){
      if (mailHelper ==null){
         mailHelper = new MailHelper(this);
      }

      return mailHelper;
   }
   public JamesHelper james (){
      if (JamesHelper == null){
         JamesHelper = new JamesHelper(this);
      }

      return JamesHelper;
   }


   public ChangeHelper change() {
      if(changeHelper == null){
         changeHelper = new ChangeHelper(this);

      }

      return  changeHelper;
   }

   public SoapHelper soap(){
      if (SoapHelper == null){
         SoapHelper= new SoapHelper(this);
      }
      return SoapHelper;
   }
}



