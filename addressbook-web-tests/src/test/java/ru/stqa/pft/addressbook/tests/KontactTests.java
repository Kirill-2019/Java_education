package ru.stqa.pft.addressbook.tests;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class KontactTests {
  private WebDriver WD;


  @BeforeMethod(alwaysRun = true)
  public void setUp() throws Exception {
    WD = new FirefoxDriver();

    WD.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    WD.get("http://localhost/addressbook/");
    Login();
  }

  @Test
  public void testKontact() throws Exception {


    goToKontaktPage();
    fillKontakt(new KontaktPar("test", "test"));
    returnToHome();
    //WD.findElement(By.linkText("Logout")).click();
  }

  private void returnToHome() {
    WD.findElement(By.linkText("home page")).click();
  }

  private void fillKontakt(KontaktPar kontaktPar) {
    WD.findElement(By.name("firstname")).click();
    WD.findElement(By.name("firstname")).clear();
    WD.findElement(By.name("firstname")).sendKeys(kontaktPar.getFirstname());
    WD.findElement(By.name("middlename")).clear();
    WD.findElement(By.name("middlename")).sendKeys(kontaktPar.getMiddlename());
    //WD.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Notes:'])[1]/following::input[1]")).click();
    WD.findElement(By.xpath("//input[@value='Enter']")).click();
  }

  private void goToKontaktPage() {
    WD.findElement(By.linkText("add new")).click();
  }

  private void Login() {
    WD.findElement(By.name("user")).clear();
    WD.findElement(By.name("user")).sendKeys("admin");
    WD.findElement(By.name("pass")).clear();
    WD.findElement(By.name("pass")).sendKeys("secret");
    //WD.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Password:'])[1]/following::input[2]")).click();
    WD.findElement(By.xpath("//input[@value='Login']")).click();
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

