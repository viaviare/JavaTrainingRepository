package ru.stqa.pft.addbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addbook.model.GroupData;

import java.sql.*;
import java.util.*;

public class DbConnectionTest {

  @Test
  public void dbConnection(){
    Connection conn = null;
    try {
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?serverTimezone=UTC&user=root&password=");
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery("Select group_id, group_name from group_list");
      List<GroupData> groups = new ArrayList<GroupData>();
      while(rs.next()){
        groups.add(new GroupData().setId(rs.getInt("group_id")).setName(rs.getString("group_name")));
        System.out.println(rs.getInt("group_id") + rs.getString("group_name"));
      }
      rs.close();
      st.close();
      conn.close();


    } catch (SQLException ex) {
      // handle any errors
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }
  }

}
