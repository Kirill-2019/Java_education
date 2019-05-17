package ru.stqa.prt.mantis.tests;


import org.testng.annotations.Test;
import ru.stqa.model.Issue;
import ru.stqa.model.Project;

import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class SoapTests extends TestBase {


   @Test
   public void testGetProject() throws IOException, ServiceException {
         // задача 0000001 в mantis имеет статус "решена'
         skipIfNotFixed(0000001);
         Set<Project> projects = app.soap().getProjects();
         System.out.println(projects.size());
         for (Project project : projects) {
            System.out.println(project.getName());
         }

   }

   @Test(enabled = false)
   public void testCreateIssue() throws RemoteException, ServiceException, MalformedURLException {
      Set<Project> projects = app.soap().getProjects();
      Issue issue = new Issue().withSummary("TestIssue").withDescription("Test issue description").withProject(projects.iterator().next());
      Issue created =  app.soap().addIssue(issue);
      assertEquals(issue.getSummary(),created.getSummary());


   }
}
