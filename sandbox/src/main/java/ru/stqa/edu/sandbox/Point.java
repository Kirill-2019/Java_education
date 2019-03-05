package ru.stqa.edu.sandbox;

public class Point {
//Класс для представления точек на двумерной плоскости.
   public int x;
   public int y;

   public Point ( int x, int y) {
      this.x = x;
      this.y = y;
   }

   public double distance( int x, int y) {
      return (Math.sqrt( (x - this.x)^2 + (y - this.y)^2));
   }

}
