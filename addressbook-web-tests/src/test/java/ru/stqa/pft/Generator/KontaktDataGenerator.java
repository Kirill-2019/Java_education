package ru.stqa.pft.Generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.KontaktData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class KontaktDataGenerator {

   @Parameter(names = "-c",description = "KontaktCount")
   public int count;

   @Parameter (names = "-f",description = "TargetFile")
   public String file;

   @Parameter (names = "-d",description = "Data Format")
   public String format;

   private void run() throws IOException {
      List<KontaktData> kontakts = GenerateKontakts(count);
      if(format.equals("xml")) {
         SaveAsXML(kontakts, new File(file));
      } else{
         System.out.println("Unrecognized format " + format);
      }
   }


   private void SaveAsXML(List<KontaktData> kontakts, File file) throws IOException {
      XStream xstream = new XStream();
      xstream.processAnnotations(KontaktData.class);
      String xml = xstream.toXML(kontakts);
      Writer writer = new FileWriter(file);
      writer.write(xml);
      writer.close();

   }


   public static void main (String[] args) throws IOException {

      KontaktDataGenerator generator = new KontaktDataGenerator();
      JCommander jCommander = new JCommander(generator);
      try {
         jCommander.parse(args);
      } catch (ParameterException ex){
         jCommander.usage();
         return;
      }
      generator.run();

   }

   private List<KontaktData> GenerateKontakts(int count) {
      List<KontaktData> kontakts = new ArrayList<KontaktData>();
      for (int i =0; i <count;i++){
         kontakts.add(new KontaktData().withFirstname(String.format("firstname %s",i))
                 .withLastname(String.format("lastname %s",i)).withNickname(String.format("nickname %s",i))
                 .withMiddlename(String.format("middlename %s",i)));
      }
      return kontakts;
   }
}
