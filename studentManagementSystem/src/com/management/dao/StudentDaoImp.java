package com.management.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.management.bean.Student;
import com.management.exception.StudentExeption;
import com.management.managentApp.SMS;
import com.management.utility.DBUtil;





public class StudentDaoImp implements StudentDao{
	
	static Student s;
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	public boolean login(String email,String password){
		boolean flag=true;
		
		try(Connection con = DBUtil.provideConnection()) {
			
			PreparedStatement ps = con.prepareStatement("Select * from Student where email=? AND password=?");
			ps.setString(1, email);
			ps.setString(2, password);
			
			ResultSet x = ps.executeQuery();
			
			if(x.next()) {
				flag=true;
				int roll =x.getInt("roll");
				String name = x.getString("name");
				String em = x.getString("email");
				String pass =  x.getString("password");
				s=new Student(roll,name,em,pass);
			}
			else flag=false;
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			
		}
		
		
		return flag;
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	
	
	
	public boolean register(Student s) throws StudentExeption {
		
		boolean flag=true;
		
		try(Connection con=DBUtil.provideConnection()) {
	
			PreparedStatement ps = con.prepareStatement("INSERT INTO student (NAME,EMAIL,PASSWORD) VALUES(?,?,?)");
			ps.setString(1, s.getName());
			ps.setString(2, s.getEmail());
			ps.setString(3, s.getPassword());
			
			int x =ps.executeUpdate();
			
			if(x>0) {
				flag= true;
			}else throw new StudentExeption("Student alrady exist");
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return flag;
	}
	
	
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	
	
	
	
	
	@Override
	public String registerCourse() throws StudentExeption{
		String masg=null;
		
		try(Connection con=DBUtil.provideConnection()){
			
			
			
			// This is for selecting the course 
			PreparedStatement ps1=con.prepareStatement("select * from course");
			ResultSet rs = ps1.executeQuery();
			
			while(rs.next()) {
				int id=rs.getInt("cId");
				String cName=rs.getString("cName");
				int seat=rs.getInt("avalSeat");
				System.out.println(id+". "+cName);
				System.out.println("Available seat in this course: "+seat +" | fees= "+rs.getInt("fees"));
				
				System.out.println("*-=-=-=*=-=-=-=-=*=-=-=-=-*=-=-=-=*");
			}
			
			System.out.println("Enter The course No.");
			
			Scanner sc=new Scanner(System.in);
			int cId=sc.nextInt();
			
			
			//this is for registering in course;
			
			PreparedStatement ps2=con.prepareStatement("insert into student_course values(?,?)");  
			ps2.setInt(1, s.getRoll());
			ps2.setInt(2, cId);
			
			int x=ps2.executeUpdate();
			
//			PreparedStatement ps=con.prepareStatement("UPDATE course SET avalSeat=(select avalSeat from course where cId=?)-1");
			
			
			
			if(x>0) masg="you are registered successfully int course Id "+cId;
			else throw new StudentExeption("Either you already registered in course or check Course ID properly");
			
		}catch(SQLException se) {
			se.printStackTrace();
		}
		
		return masg;
	}

	
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	@Override
	public void updateDetail() {
		
		System.out.println("What do you want to update");
		System.out.println("1. Name: ");
		System.out.println("2. Email: ");
		System.out.println("3. password: ");
		System.out.println("4. Go Back: ");
		
		
		Scanner sc = new Scanner(System.in);
		int option =  sc.nextInt();
		sc.nextLine();
		
		
		try(Connection con = DBUtil.provideConnection()) {
			
			
			switch(option) {
			
			case 1: {
						System.out.println("Enter your new Name: ");
						String name = sc.nextLine();
						PreparedStatement ps =con.prepareStatement("UPDATE student SET Name=? where roll=?");
						
						ps.setString(1, name);
						ps.setInt(2, s.getRoll());
						
						int x=ps.executeUpdate();
						
						if(x>0) System.out.println("Your Name Updated as: "+name);
						updateDetail();
						break;
					}
			
			case 2: {
						System.out.println("Enter your new Email: ");
						String Email = sc.nextLine();
						PreparedStatement ps =con.prepareStatement("UPDATE student SET Email=? where roll=?");
				
						ps.setString(1, Email);
						ps.setInt(2, s.getRoll());
				
						int x=ps.executeUpdate();
						
						if(x>0) System.out.println("Your Name Updated as: "+Email);
						updateDetail();
						break;
					}
			
			case 3: {	
						System.out.println("Enter your new Password: ");
						String password = sc.nextLine();
						PreparedStatement ps =con.prepareStatement("UPDATE student SET Password=? where roll=?");
				
						ps.setString(1, password);
						ps.setInt(2, s.getRoll());
				
						int x=ps.executeUpdate();
						
						if(x>0) System.out.println("Your Name Updated as: "+password);
						updateDetail();
						break;
					}
			
			case 4: {
						SMS.selectCotegory();
						break;
					}
			
			}
			  
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		

	}

	
	
	
	
	
	
	
	@Override
	public void displayAllCourseAndDetail() {
		
		
		try(Connection con = DBUtil.provideConnection()) {
			
			PreparedStatement ps = con.prepareStatement("select * from course");
			ResultSet x = ps.executeQuery();
			
			System.out.println("+-----+-------+----------+------+");
			System.out.println("| cId | cName | avalSeat | Fees |");
			
			while(x.next()) {
				System.out.println("+-----+-------+----------+------+");
				System.out.println("|   "+x.getInt("cId")+" |  "+x.getString("cName")+" |        "+x.getInt("avalSeat")+" | "+x.getInt("fees")+" |");
				System.out.println("+-----+-------+----------+------+");
				
			}
			
			updateDetail();
			
		} catch (SQLException e) {
			
		}
	}

	
	public static void main(String[] args) {
		StudentDaoImp s=new StudentDaoImp();
		
//		s.registerCourse(1);
		
//		s.updateDetail(1);
//		s.login("ROHIT@GMAIL.COM","ROHIT11");
		
//		s.displayAllCourseAndDetail();
	}


}
