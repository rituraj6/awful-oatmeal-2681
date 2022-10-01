package com.management.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.management.bean.Administrator;
import com.management.exception.StudentExeption;
import com.management.utility.DBUtil;

public class AdministratorDaoImp implements AdministratorDao{
	
	static Administrator a;
	static Scanner sc = new Scanner(System.in);

	
	
/////////////////////////////Login///////////////////////////////////////////////////////////////
	
	
	public boolean login(String email,String password) {
		boolean flag=true;
		
		
		try(Connection con = DBUtil.provideConnection()) {
			
			PreparedStatement ps = con.prepareStatement("Select * from Administrator where email=? AND password=?");
			ps.setString(1, email);
			ps.setString(2, password);
			
			ResultSet x = ps.executeQuery();
			
			if(x.next()) {
				flag=true;
				int roll =x.getInt("roll");
				String name = x.getString("name");
				String em = x.getString("email");
				String pass =  x.getString("password");
				a=new Administrator(roll,name,em,pass);
			}
			else flag=false;
			
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		

		return flag;
	}
	
	
	
///////////////////////////////////////////////////////////////////////////////////////////////	
	
	
	
	public boolean register(Administrator a) throws StudentExeption {
		
			boolean flag=true;
		
			try(Connection con=DBUtil.provideConnection()) {
	
				PreparedStatement ps = con.prepareStatement("INSERT INTO Administrator (NAME,EMAIL,PASSWORD) VALUES(?,?,?)");
				ps.setString(1, a.getName());
				ps.setString(2, a.getEmail());
				ps.setString(3, a.getPassword());
			
				int x =ps.executeUpdate();
			
				if(x>0) {
					flag= true;
				}else throw new StudentExeption("Administrator alrady exist");
			
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		
			return flag;
		}
	
/////////////////////////////////////////////////////////////////////////////////////////////////	
	
	
	
	

	@Override
	public String addNewCourse() throws StudentExeption {
		
		String msg=null;
		
		System.out.println("Enter Course Name: ");
		String courseName = sc.next();
		
		System.out.println("Enter course Fees: ");
		int price = sc.nextInt();
		
		try(Connection con = DBUtil.provideConnection()) {
			
			PreparedStatement ps = con.prepareStatement("INSERT INTO course (cName,Fees) values(?,?)");
			ps.setString(1, courseName);
			ps.setInt(2, price);
			
			int x = ps.executeUpdate();
			
			if(x>0) {
				msg="Course added successfully:";
			}else throw new StudentExeption("Course Already Exist");
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return msg;
		
	}
	
	
	
	
	
	
///////////////////////////////////////////////////////////////////////////////////////////////	
	
	
	


	@Override
	public String updateFeesOfCourse() throws StudentExeption {
		String msg=null;
		
		
		
		System.out.println("Enter course Id:");
		int id = sc.nextInt();
		
		System.out.println("Enter new Fees: ");
		int fees = sc.nextInt();
		
		
		try(Connection con  = DBUtil.provideConnection()) {
			
			PreparedStatement ps = con.prepareStatement("UPDATE course SET fees=? where cId=?");
			ps.setInt(1, fees);
			ps.setInt(2, id);
			
			int x=ps.executeUpdate();
			if(x>0)msg="Fees Updated sucessfully:";
			else throw new StudentExeption("Course is Not Exist:");
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return msg;
	}
	
	
	
/////////////////////////////////////////////////////////////////////////////////////////////////	


	
	@Override
	public String deleteCoursefrombatch() throws StudentExeption {
		String msg=null;
		
		System.out.println("Enter course Id: ");
		int cid = sc.nextInt();
		
		try(Connection con = DBUtil.provideConnection()) {
			
			PreparedStatement ps = con.prepareStatement("DELETE FROM batch WHERE cId=?");
			ps.setInt(1, cid);
			
			int x = ps.executeUpdate();
			
			if(x>0)msg="course deleted: ";
			else throw new StudentExeption("Batch Not Found");
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return msg;
	}
	
	
	
/////////////////////////////////////////////////////////////////////////////////////////////////
	

	@Override
	public void searchAboutCourse() throws StudentExeption {
		System.out.println("Enter course Name");
		String cname = sc.next();
		
		try(Connection con = DBUtil.provideConnection()) {
			
			PreparedStatement ps = con.prepareStatement("SELECT * FROM course WHERE cName=?");
			ps.setString(1, cname);
			
			ResultSet rs= ps.executeQuery();
			
			if(rs.next()) {
				System.out.println("cId: "+rs.getInt("cId"));
				System.out.println("Course Name: "+rs.getString("cName"));
				System.out.println("Course Fees: "+rs.getInt("fees"));
				System.out.println("---------------------");
			}else throw new StudentExeption("Course Not Exist: ");
			
		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
			
		}
		
		
		
	}
	
	
/////////////////////////////////////////////////////////////////////////////////////////////////	

	@Override
	public void createBatch() {
		
		

		
	}

	@Override
	public void alocateBatchForStudent() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateSeatUnderBatch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void viewAllStudentOfEveryBatch() {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
