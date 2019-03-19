package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class equationTest {

   @Test
   public void test0() {
      equation e = new equation(1,1,1);
      Assert.assertEquals(e.rootNumber(),0);

   }

   @Test
   public void test1() {
      equation e = new equation(1,2,1);
      Assert.assertEquals(e.rootNumber(),1);

   }

   @Test
   public void test2() {
      equation e = new equation(1,5,6);
      Assert.assertEquals(e.rootNumber(),2);

   }

}
