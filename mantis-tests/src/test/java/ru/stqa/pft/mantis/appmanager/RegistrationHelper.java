package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;

public class RegistrationHelper {
   private final ApplicationManager app;
   private WebDriver WD;

   public RegistrationHelper(ApplicationManager app) {
      this.app = app;
      WD = app.getDriver();
   }

   public void start(String username, String email) {
      WD.get(app.getProperty("web.baseUrl") + "/signup_page.php");

   }
}
