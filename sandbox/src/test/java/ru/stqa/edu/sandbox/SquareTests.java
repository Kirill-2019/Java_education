package ru.stqa.edu.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SquareTests {

   @Test
   public void testarea() {
      Square s = new Square(5);
      Assert.assertEquals(s.area(),25);
   }
}
