package ru.stqa.pft.addressbook;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class TestBase {
   private WebDriver WD;

   @BeforeMethod(alwaysRun = true)
   public void setUp() throws Exception {
     WD = new FirefoxDriver();
     WD.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
     WD.get("http://localhost/addressbook/");
     Login("admin", "secret");


   }

   private void Login(String Loginname, String Password) {
     WD.findElement(By.name("user")).clear();
     WD.findElement(By.name("user")).sendKeys(Loginname);
     WD.findElement(By.name("pass")).clear();
     WD.findElement(By.name("pass")).sendKeys(Password);
    // WD.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Password:'])[1]/following::input[2]")).click();
     WD.findElement(By.xpath("//input[@value='Login']")).click();
   }

   protected void fillGroupForm(GroupData groupData) {
     WD.findElement(By.name("group_name")).click();
     WD.findElement(By.name("group_name")).clear();
     WD.findElement(By.name("group_name")).sendKeys(groupData.getName());
     WD.findElement(By.name("group_header")).clear();
     WD.findElement(By.name("group_header")).sendKeys(groupData.getHeader());
     WD.findElement(By.name("group_footer")).clear();
     WD.findElement(By.name("group_footer")).sendKeys(groupData.getFooter());
   }

   protected void submitGroupCreation() {
     WD.findElement(By.name("submit")).click();

   }

   protected void returnToGroupPage() {
     WD.findElement(By.linkText("group page")).click();
   }

   protected void initgroupcreation() {

     WD.findElement(By.name("new")).click();
   }

   protected void goToGroupPage()
   {
     WD.findElement(By.linkText("groups")).click();
   }

   @AfterMethod(alwaysRun = true)
   public void tearDown() throws Exception {
     WD.quit();
   }

   private boolean isElementPresent(By by) {
     try {
       WD.findElement(by);
       return true;
     } catch (NoSuchElementException e) {
       return false;
     }
   }

   private boolean isAlertPresent() {
     try {
       WD.switchTo().alert();
       return true;
     } catch (NoAlertPresentException e) {
       return false;
     }
   }

   protected void deleteSelectedGroups() {
     WD.findElement(By.name("delete")).click();
   }

   protected void selectGroup() {
     WD.findElement(By.name("selected[]")).click();
   }
}
