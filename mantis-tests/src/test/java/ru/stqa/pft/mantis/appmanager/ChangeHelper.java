package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ChangeHelper extends HelperBase {

   public ChangeHelper(ApplicationManager app) {
      super(app);
   }


   public void login() {
      WD.get(app.getProperty("web.baseUrl"));
      type(By.name("username"),app.getProperty("web.adminLogin"));
      click(By.cssSelector("input[value='Войти']"));
      type(By.name("password"),app.getProperty("web.adminPassword"));
      click(By.cssSelector("input[value='Войти']"));

   }

   public String reset(String user) {


      WD.findElement(By.xpath("//li[6]/a/i")).click();
      WD.findElement(By.xpath("//a[contains(text(),'Управление пользователями')]")).click();

      click(By.linkText(user));
      //click(By.linkText("user1557524245808"));
      String email = WD.findElement(By.xpath("//input[@id='email-field']")).getAttribute("value");
      click(By.xpath("//input[@value='Сбросить пароль']"));

      return email;
   }

   public String getemail() {
      return WD.findElement(By.xpath("//input[@id='email field']")).getAttribute("value");
   }

   public String getuser() {
      WD.findElement(By.xpath("//li[6]/a/i")).click();
      WD.findElement(By.xpath("//a[contains(text(),'Управление пользователями')]")).click();

      List<WebElement> users = WD.findElements(By.tagName("tr"));
      String t = users.get(2).findElements(By.tagName("td")).get(0).getText();
      return t;
   }
}
