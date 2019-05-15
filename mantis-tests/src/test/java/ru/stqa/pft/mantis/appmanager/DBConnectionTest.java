package ru.stqa.pft.mantis.appmanager;

import org.testng.annotations.Test;

import java.sql.*;

public class DBConnectionTest {

   @Test
   public void DBConnection(){
      Connection conn = null;

     try {
         conn =
                 DriverManager.getConnection("jdbc:mysql://localhost:3306/bugtracker?user=root&password='';serverTimezone=UTC");
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select group_id,group_name,group_header,group_footer from group_list");



        while (rs.next()){
           System.out.println(rs);

        }

        rs.close();
        st.close();
        conn.close();

         // Do something with the Connection

      } catch (SQLException ex) {
         // handle any errors
         System.out.println("SQLException: " + ex.getMessage());
         System.out.println("SQLState: " + ex.getSQLState());
         System.out.println("VendorError: " + ex.getErrorCode());
      }
   }
}
