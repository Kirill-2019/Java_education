package ru.stqa.pft.addressbook;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class KontactTests {
  private WebDriver WD;


  @BeforeMethod(alwaysRun = true)
  public void setUp() throws Exception {
    WD = new FirefoxDriver();

    WD.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    WD.get("http://localhost/addressbook/");

  }

  @Test
  public void testKontact() throws Exception {



    WD.findElement(By.name("user")).clear();
    WD.findElement(By.name("user")).sendKeys("admin");
    WD.findElement(By.name("pass")).clear();
    WD.findElement(By.name("pass")).sendKeys("secret");
    WD.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Password:'])[1]/following::input[2]")).click();
    WD.findElement(By.linkText("add new")).click();
    WD.findElement(By.name("firstname")).click();
    WD.findElement(By.name("firstname")).clear();
    WD.findElement(By.name("firstname")).sendKeys("test");
    WD.findElement(By.name("middlename")).clear();
    WD.findElement(By.name("middlename")).sendKeys("test");
    WD.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Notes:'])[1]/following::input[1]")).click();
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
  }
}

