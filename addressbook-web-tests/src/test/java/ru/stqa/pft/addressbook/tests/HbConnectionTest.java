package ru.stqa.pft.addressbook.tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.KontaktData;

import java.util.List;

public class HbConnectionTest {

   private SessionFactory sessionFactory;

   @BeforeClass


   protected void setUp() throws Exception {
      // A SessionFactory is set up once for an application!
      final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
              .configure() // configures settings from hibernate.cfg.xml
              .build();
      try {
         sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
      }
      catch (Exception e) {
         e.printStackTrace();
         // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
         // so destroy it manually.
         StandardServiceRegistryBuilder.destroy( registry );
      }
   }



   @Test
   public void testHbConnection() {



     /* Session session1 = sessionFactory.openSession();
      session1.beginTransaction();
      List<GroupData> result1 = session1.createQuery( "from GroupData" ).list();
      for ( GroupData group : result1 ) {
         System.out.println(group);
      }
      session1.getTransaction().commit();
      session1.close();
*/

      Session session = sessionFactory.openSession();
      session.beginTransaction();
      List<KontaktData> result = session.createQuery( "from KontaktData where deprecated = '0000-00-00'" ).list();
      session.getTransaction().commit();
      session.close();

      for ( KontaktData kontakt : result ) {
         System.out.println(kontakt);
         System.out.println(kontakt.getGroups());
      }

   }

}
