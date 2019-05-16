package ru.stqa.prt.mantis.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.model.Issue;
import ru.stqa.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class SoapTests extends TestBase {
   @Test
   public void testGetProject() throws MalformedURLException, ServiceException, RemoteException {
     // задача 0000002 в mantis имеет статус "новая'
      if(!isIssueOpen(0000002)) {
        Set<Project> projects = app.soap().getProjects();
        System.out.println(projects.size());
        for (Project project : projects) {
           System.out.println(project.getName());
        }
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
