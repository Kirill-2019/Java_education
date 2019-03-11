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
  }

  @Test
  public void testGroupCreation() throws Exception {
    WD.get("http://localhost/addressbook/");
    WD.findElement(By.name("user")).clear();
    WD.findElement(By.name("user")).sendKeys("admin");
    WD.findElement(By.name("pass")).clear();
    WD.findElement(By.name("pass")).sendKeys("secret");
    WD.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Password:'])[1]/following::input[2]")).click();
    WD.findElement(By.linkText("groups")).click();
    WD.findElement(By.name("new")).click();
    WD.findElement(By.name("group_name")).click();
    WD.findElement(By.name("group_name")).clear();
    WD.findElement(By.name("group_name")).sendKeys("test3");
    WD.findElement(By.name("group_header")).clear();
    WD.findElement(By.name("group_header")).sendKeys("test3");
    WD.findElement(By.name("group_footer")).clear();
    WD.findElement(By.name("group_footer")).sendKeys("test3");
    WD.findElement(By.name("submit")).click();
    WD.findElement(By.linkText("group page")).click();
    WD.findElement(By.linkText("Logout")).click();
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