package ru.stqa.edu.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {

   @Test
   public void testarea() {

      Point p1 = new Point(20, 15);
      Point p2 = new Point (30,50);

      Assert.assertEquals( Math.round(p1.distance(p2)),36);
   }

}
