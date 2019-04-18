package ru.stqa.pft.sandbox;

import java.util.ArrayList;
import java.util.List;

public class Collections {

   public static void main(String[] args){
      // String[] langs = new String[4];
      // langs[0] = "java";
      // langs[1] = "c#";
      //langs[2] = "Python";
      //langs[3] = "PHP";

      String[] langs = {"java","c#","Python","PHP"};

      for (int i=0; i < langs.length; i++){
         System.out.println("Я хочу выучить " + langs[i]);
      }
      System.out.println();

      for (String l: langs){
         System.out.println("Я хочу выучить " + l);
      }

      List<String> languages = new ArrayList<String>();
      languages.add("java");
      languages.add("c#");
      languages.add("Python");
      languages.add("PHP");

      System.out.println();

      for (String l: languages){
         System.out.println("Я хочу выучить " + l);
      }

      System.out.println();

      for (int i=0; i < languages.size(); i++){
         System.out.println("Я хочу выучить " + languages.get(i));
      }


   }

}
