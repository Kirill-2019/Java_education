package ru.stqa.edu.sandbox;

public class MyTest {

   public static void main(String[] args) {
      //Задание №2
      //точки на плоскости
      Point p1 = new Point(20, 15);
      Point p2 = new Point (30,50);

      //функция
      System.out.println( "Решение задания № 2 пункт 3:   " + distance(p1,p2));

      //метод
      System.out.println( "Решение задания № 2 пункт 4:   " + p1.distance(p2));


      //работа по лекции
      System.out.println();
      System.out.println();
      System.out.println("Практика:");

      hello("world");
      hello("user");
      hello("Alexei");

      Square s = new Square(5);

       //  double l = 5;
      System.out.println("площадь квадрата со стороной " + s.l + " = " + s.area());

      Rectangle r = new Rectangle(5,7);

      //r.a = 5;
      //r.b = 7;
      System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " +r.area());

   }

   //Функция вычисления расстояния между точками
   public static double distance( Point p1, Point p2) {
       return (Math.sqrt( Math.pow((p2.x - p1.x),2) + Math.pow((p2.y - p1.y),2)));
   }

   public static void hello (String text) {

      System.out.println("Hello, " + text + "!");
   }
}

