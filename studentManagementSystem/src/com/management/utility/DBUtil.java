package com.management.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	
  public static Connection provideConnection() {
	  
	  Connection con = null;
	  
	 try {
		 
		 Class.forName("com.mysql.cj.jdbc.Driver");
		
	} catch (ClassNotFoundException e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	 
	 String url="jdbc:mysql://Localhost:3306/project1";
	 
	 try {
		con=DriverManager.getConnection(url, "root", "Root");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
	  return con;
  }

  
}
