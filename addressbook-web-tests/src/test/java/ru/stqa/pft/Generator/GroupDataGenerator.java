package ru.stqa.pft.Generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
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

   private void run() throws IOException {
      List<GroupData> groups = GenerateGroups(count);
      save(groups,new File (file));
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

   private void save(List<GroupData> groups, File file) throws IOException {
      //System.out.println(new File(".").getAbsoluteFile());
      Writer writer = new FileWriter(file);
      for (GroupData group: groups){
         writer.write(String.format("%s;%s;%s\n",group.getName(),group.getHeader(),group.getFooter()));
      }
      writer.close();
   }

   private List<GroupData> GenerateGroups(int count) {
      List<GroupData> groups = new ArrayList<GroupData>();
      for (int i =0; i <count;i++){
         groups.add(new GroupData().withName(String.format("test %s",i)).withHeader(String.format("header %s",i)).withFooter(String.format("footer %s",i)));
      }
      return groups;
   }
}
