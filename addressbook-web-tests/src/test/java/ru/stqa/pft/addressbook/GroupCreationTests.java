package ru.stqa.pft.addressbook;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class GroupCreationTests {
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
    WD.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Password:'])[1]/following::input[2]")).click();
  }

  @Test
  public void testGroupCreation() throws Exception {
    GoToGroupPage("groups");
    initgroupcreation("new");
    initgroupcreation("group_name");
    fillGroupForm("test3", "test3", "test3");
    initgroupcreation("submit");
    GoToGroupPage("group page");
    GoToGroupPage("Logout");
  }

  private void fillGroupForm(String Name, String Header, String Fooder) {
    WD.findElement(By.name("group_name")).clear();
    WD.findElement(By.name("group_name")).sendKeys(Name);
    WD.findElement(By.name("group_header")).clear();
    WD.findElement(By.name("group_header")).sendKeys(Header);
    WD.findElement(By.name("group_footer")).clear();
    WD.findElement(By.name("group_footer")).sendKeys(Fooder);
  }

  private void initgroupcreation(String s) {
    WD.findElement(By.name(s)).click();
  }

  private void GoToGroupPage(String groups) {
    WD.findElement(By.linkText(groups)).click();
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
  }}