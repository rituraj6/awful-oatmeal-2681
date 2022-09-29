package com.management.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import com.management.utility.DBUtil;
import com.mysql.cj.xdevapi.PreparableStatement;





public class StudentDaoImp implements StudentDao{

	
	
	@Override
	public String registerCourse(int roll) {
		
		try(Connection con=DBUtil.provideConnection()){
			
			
			
			// This is for selecting the course 
			PreparedStatement ps1=con.prepareStatement("select * from course");
			ResultSet rs = ps1.executeQuery();
			
			while(rs.next()) {
				int id=rs.getInt("cId");
				String cName=rs.getString("cName");
				int seat=rs.getInt("avalSeat");
				System.out.println(id+". "+cName);
				System.out.println("Available seat in this course: "+seat);
				
				System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
			}
			
			System.out.println("Enter The course No.");
			
			Scanner sc=new Scanner(System.in);
			int cId=sc.nextInt();
			
			
			
			
			
			
			
			

			
			PreparedStatement stmt=con.prepareStatement("insert into Emp values(?,?)");  
			
		}catch(SQLException se) {
			
		}
		
		return null;
	}

	
	
	
	
	
	
	@Override
	public String updateDetail(int roll) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> displayAllCourseAndDetail() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public static void main(String[] args) {
		StudentDaoImp s=new StudentDaoImp();
		
		s.registerCourse(1);
	}


}
