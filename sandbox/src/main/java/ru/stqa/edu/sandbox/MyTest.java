package ru.stqa.edu.sandbox;

import java.sql.SQLOutput;

public class MyTest {

   public static void main(String[] args) {

      hello("world");
      hello("user");
      hello("Alexei");

      double l = 5;
      System.out.println("площадь квадрата со стороной " + l + " = " + area(l));

      float a = 5;
      float b = 7;
      System.out.println("Площадь прямоугольника со сторонами " + a + " и " + b + " = " + area (a,b));

   }

   public static void hello (String text) {

      System.out.println("Hello, " + text + "!");
   }

   public static double area(double l){
      return l*l;
   }

   public static float area (float a, float b ) {
      return a*b;
   }

}

