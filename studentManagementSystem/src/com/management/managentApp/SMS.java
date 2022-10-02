package com.management.managentApp;

import java.util.Scanner;

import com.management.bean.Student;
import com.management.dao.StudentDaoImp;
import com.management.exception.StudentExeption;

public class SMS {
	
	public static Scanner sc=new Scanner(System.in);
	
	
	
	////////////////////////////////// Below method is for calling the method////////////////////////////////////////////
	public static void run() {
		//ask for login or register;
		
		System.out.println("1.Register");
		System.out.println("2.Login");
		System.out.println("3.Go back");
		
		int choice = sc.nextInt();
		
		switch(choice) {
		
		case 1: {
				 	register();
					break;
				}
		
		case 2: {
					login();
					break;
				}
		case 3: {
					Main.choice();
				}
		}
		
		
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	public static void login() {
		System.out.println("Enter Email");
		String email=sc.next();
		
		System.out.println("Enter password");
		String password=sc.next();
		
		StudentDaoImp student = new StudentDaoImp();
		
		if(student.login(email,password)) {
			
			System.out.println("You are loged In successfully\n");
			selectCotegory();
			
			
		}else {
			run();
		}
		
	}
	
	
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	public static void register() {
		Student s=new Student();
		
		StudentDaoImp student = new StudentDaoImp();
		try {
			boolean flag=student.register(s);
			
			if(flag) {
				System.out.println("Register successfull Please login");
				run();
			}
			
		} catch (StudentExeption e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	

	public static void selectCotegory() {
		
		StudentDaoImp studentfun = new StudentDaoImp();
		
	
		
		System.out.println("Select functionality:->");
		System.out.println("1.Register in a course: ");
		System.out.println("2.Update your Detail: ");
		System.out.println("3.Display All Course Detail: ");
		System.out.println("4.Exit the function");
		
		int method=sc.nextInt();
		
		
		
		
		
		switch(method) {
		
			case 1: {
					
					
					try {
						String msg = studentfun.registerCourse();
						System.out.println(msg);
						selectCotegory();
						
					} catch (StudentExeption e) {
						System.out.println(e.getMessage());
					}
					
					selectCotegory();
					break;
				}
			
			case 2: {
				     studentfun.updateDetail();
				     selectCotegory();
				     break;
					}
			case 3: {
						studentfun.displayAllCourseAndDetail();
						selectCotegory();
						break;
					}
			case 4: {
						run();
					}
		
		
		
		
		
		
		
		
		}


	}


}
