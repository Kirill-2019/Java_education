package ru.stqa.pft.Generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.GroupData;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

public class GroupDataGenerator {

   @Parameter (names = "-c",description = "GroupCount")
   public int count;

   @Parameter (names = "-f",description = "TargetFile")
   public String file;

   @Parameter (names = "-d",description = "Data Format")
   public String format;

   private void run() throws IOException {
      List<GroupData> groups = GenerateGroups(count);
      if (format.equals("csv")) {
         SaveAsCSV(groups, new File(file));
      } else if(format.equals("xml")) {
         SaveAsXML(groups, new File(file));
      } else if(format.equals("json")) {
         SaveAsjson(groups, new File(file));
      } else{
         System.out.println("Unrecognized format " + format);
      }
   }

   private void SaveAsjson(List<GroupData> groups, File file) throws IOException {
      Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
      String json = gson.toJson(groups);
      try (Writer writer = new FileWriter(file)){
         writer.write(json);
      }
   }

   private void SaveAsXML(List<GroupData> groups, File file) throws IOException {
      XStream xstream = new XStream();
      //xstream.alias("group", GroupData.class);
      xstream.processAnnotations(GroupData.class);
      String xml = xstream.toXML(groups);

      try (Writer writer = new FileWriter(file)){
         writer.write(xml);
      }
   }


   public static void main (String[] args) throws IOException {

      GroupDataGenerator generator = new GroupDataGenerator();
      JCommander jCommander = new JCommander(generator);
      try {
         jCommander.parse(args);
      } catch (ParameterException ex){
         jCommander.usage();
         return;
    }
      generator.run();

   }

   private void SaveAsCSV(List<GroupData> groups, File file) throws IOException {
      //System.out.println(new File(".").getAbsoluteFile());
     try (Writer writer = new FileWriter(file)) {
        for (GroupData group : groups) {
           writer.write(String.format("%s;%s;%s\n", group.getName(), group.getHeader(), group.getFooter()));
        }
     }
   }

   private List<GroupData> GenerateGroups(int count) {
      List<GroupData> groups = new ArrayList<GroupData>();
      for (int i =0; i <count;i++){
         groups.add(new GroupData().withName(String.format("test %s",i)).withHeader(String.format("header %s",i)).withFooter(String.format("footer %s",i)));
      }
      return groups;
   }
}
