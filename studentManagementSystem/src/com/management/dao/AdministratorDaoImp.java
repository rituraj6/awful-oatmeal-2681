package com.management.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.management.bean.Administrator;
import com.management.bean.StudentBatchDTO;
import com.management.exception.StudentExeption;
import com.management.utility.DBUtil;

public class AdministratorDaoImp implements AdministratorDao {

	static Administrator a;
	static Scanner sc = new Scanner(System.in);

/////////////////////////////Login///////////////////////////////////////////////////////////////

	public boolean login(String email, String password) {
		boolean flag = true;

		try (Connection con = DBUtil.provideConnection()) {

			PreparedStatement ps = con.prepareStatement("Select * from Administrator where email=? AND password=?");
			ps.setString(1, email);
			ps.setString(2, password);

			ResultSet x = ps.executeQuery();

			if (x.next()) {
				int roll = x.getInt("AID");
				String name = x.getString("name");
				String em = x.getString("email");
				String pass = x.getString("password");
				a = new Administrator(roll, name, em, pass);
			} else
				flag = false;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return flag;
	}

///////////////////////////////////////////////////////////////////////////////////////////////	

	public boolean register(Administrator a) throws StudentExeption {

		boolean flag = true;

		try (Connection con = DBUtil.provideConnection()) {

			PreparedStatement ps = con
					.prepareStatement("INSERT INTO Administrator (NAME,EMAIL,PASSWORD) VALUES(?,?,?)");
			ps.setString(1, a.getName());
			ps.setString(2, a.getEmail());
			ps.setString(3, a.getPassword());

			int x = ps.executeUpdate();

			if (x > 0) {
				flag = true;
			} else
				throw new StudentExeption("Administrator alrady exist");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return flag;
	}

/////////////////////////////////////////////////////////////////////////////////////////////////	

	@Override
	public String addNewCourse() throws StudentExeption {

		String msg = null;

		System.out.println("Enter Course Name: ");
		String courseName = sc.next();

		System.out.println("Enter course Fees: ");
		int price = sc.nextInt();

		try (Connection con = DBUtil.provideConnection()) {

			PreparedStatement ps = con.prepareStatement("INSERT INTO course (cName,Fees) values(?,?)");
			ps.setString(1, courseName);
			ps.setInt(2, price);

			int x = ps.executeUpdate();

			if (x > 0) {
				msg = "Course added successfully:";
			} else
				throw new StudentExeption("Course Already Exist");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return msg;

	}

///////////////////////////////////////////////////////////////////////////////////////////////	

	@Override
	public String updateFeesOfCourse() throws StudentExeption {
		String msg = null;

		System.out.println("Enter course Id:");
		int id = sc.nextInt();

		System.out.println("Enter new Fees: ");
		int fees = sc.nextInt();

		try (Connection con = DBUtil.provideConnection()) {

			PreparedStatement ps = con.prepareStatement("UPDATE course SET fees=? where cId=?");
			ps.setInt(1, fees);
			ps.setInt(2, id);

			int x = ps.executeUpdate();
			if (x > 0)
				msg = "Fees Updated sucessfully:";
			else
				throw new StudentExeption("Course is Not Exist:");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return msg;
	}

/////////////////////////////////////////////////////////////////////////////////////////////////	

	@Override
	public String deleteCoursefrombatch() throws StudentExeption {
		String msg = null;

		System.out.println("Enter course Id: ");
		int cid = sc.nextInt();

		try (Connection con = DBUtil.provideConnection()) {

			PreparedStatement ps = con.prepareStatement("DELETE FROM batch WHERE Bid=?");
			ps.setInt(1, cid);

			int x = ps.executeUpdate();

			if (x > 0)
				msg = "course deleted FROM Batch: ";
			else
				throw new StudentExeption("Batch Not Found");

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

		try (Connection con = DBUtil.provideConnection()) {

			PreparedStatement ps = con.prepareStatement("SELECT * FROM course WHERE cName=?");
			ps.setString(1, cname);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				System.out.println("cId: " + rs.getInt("cId"));
				System.out.println("Course Name: " + rs.getString("cName"));
				System.out.println("Course Fees: " + rs.getInt("fees"));
				System.out.println("---------------------");
			} else
				throw new StudentExeption("Course Not Exist: ");

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}

	}

/////////////////////////////////////////////////////////////////////////////////////////////////	

	@Override
	public void createBatch() {

		System.out.println("ENTER batch name: ");
		String bname = sc.next();

		System.out.println("Enter course Name: ");
		String cname = sc.next();

		System.out.println("Enter Total seat: ");
		int seat = sc.nextInt();

		try (Connection con = DBUtil.provideConnection()) {
//			PreparedStatement ps1 = con.prepareStatement("se")

			PreparedStatement ps = con
					.prepareStatement("insert into batch values((select cid from course where cName=?),?,?)");
			ps.setString(1, cname);
			ps.setString(2, bname);
			ps.setInt(3, seat);

			int x = ps.executeUpdate();

			if (x > 0)
				System.out.println("batch created: ");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

//////////////////////////////////////////////////////////////////////////////////////////////////	

	@Override
	public void alocateBatchForStudent() throws StudentExeption {

		String batch = "java";

		List<StudentBatchDTO> studentsDtos = new ArrayList<>();

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement(
					"select * from student_course where cid = (select cid from course where cname = ?)");

			ps.setString(1, batch);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				StudentBatchDTO student = new StudentBatchDTO();

				student.setbId(rs.getInt("cid"));
				student.setRoll(rs.getInt("roll"));
				student.setcName(batch);

				studentsDtos.add(student);
			}

//			System.out.println(studentsDtos);

			if (studentsDtos.size() == 0)
				throw new StudentExeption("No students enrolled in batch : " + batch);

			int bid = studentsDtos.get(0).getbId();

			int seats = this.getSeats(bid);
			int x=0;
				
			for (int i = 0; i < studentsDtos.size(); i++) {

				if (seats != 0) {
					seats--;

					int roll = studentsDtos.get(i).getRoll();
					int cid = studentsDtos.get(i).getbId();
					String name = studentsDtos.get(i).getcName();

					try {

						PreparedStatement ps2 = conn.prepareStatement("insert into student_batch values (?,?,?)");

						ps2.setInt(1, roll);
						ps2.setInt(2, cid);
						ps2.setString(3, name);

						 x += ps2.executeUpdate();


					} catch (SQLException e) {
//						System.out.println(e.getMessage());
						e.printStackTrace();
					}
				} else {
					break;
				}
			}
			
			
			
				System.out.println(x+" Students added to batch: "+studentsDtos.get(0).getcName());

			try {
				PreparedStatement ps3 = conn.prepareStatement("update batch set avalSeat = ? where bId = ?");

				ps3.setInt(1, seats);
				ps3.setInt(2, bid);

				int y = ps3.executeUpdate();

				if (y > 0)
					System.out.println("Batch updated sucess...!!!");

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public static int getSeats(int bid) {
		int seat = 0;

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("select avalSeat from batch  where bid = ?");
			ps.setInt(1, bid);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				seat = rs.getInt("avalSeat");
			}

		} catch (SQLException e) {
//			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return seat;
	}

//	public static void main(String[] args) {
//		try {
//			new AdministratorDaoImp().alocateBatchForStudent();
//		} catch (StudentExeption e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

/////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void updateSeatUnderBatch() {

		System.out.println("Enter the Batch Name: ");
		String bname = sc.next();

		System.out.println("How much seat You want to Increase: ");
		int seat = sc.nextInt();

		int avalSeat = avalSeat(bname) + seat;

		try (Connection con = DBUtil.provideConnection()) {

			PreparedStatement ps = con.prepareStatement("UPDATE Batch SET avalSeat=? where bName=?");
			ps.setInt(1, avalSeat);
			;
			ps.setString(2, bname);

			int x = ps.executeUpdate();
			if (x > 0)
				System.out.println("Total Seat Number Updated");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

////////////////////////////////////////////////////////////////////////////////////////////////	

	public int avalSeat(String bname) {
		int seat = 0;

		try (Connection con = DBUtil.provideConnection()) {

			PreparedStatement ps = con.prepareStatement("SELECT avalSeat from Batch where bName=?");
			ps.setInt(1, seat);

			ResultSet x = ps.executeQuery();

			if (x.next())
				seat = x.getInt("avalSeat");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return seat;
	}

//////////////////////////////////////////////////////////////////////////////////////////////////	

	@Override
	public void viewAllStudentOfEveryBatch() {

		try(Connection con =  DBUtil.provideConnection()) {
			
			PreparedStatement ps = con.prepareStatement("select s.roll,s.name,s.email,b.batch");
			
		} catch (SQLException e) {
			
		}

	}

}
