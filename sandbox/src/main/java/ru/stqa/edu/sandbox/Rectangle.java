package ru.stqa.edu.sandbox;

public class Rectangle {

   public float a;
   public float b;

   public Rectangle (float a, float b) {
     this.a = a;
     this.b = b;

   }

   public float area() {
      return this.a * this.b;
   }

}
