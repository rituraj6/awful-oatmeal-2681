package com.management.managentApp;

import java.util.Scanner;

import com.management.bean.Administrator;
import com.management.bean.Student;
import com.management.dao.AdministratorDaoImp;
import com.management.dao.StudentDaoImp;
import com.management.exception.StudentExeption;

	public class AdminService {
	
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
		
		AdministratorDaoImp administrator = new AdministratorDaoImp();
		
		if(administrator.login(email,password)) {
			
			System.out.println("You are loged In successfully\n");
			selectCotegory();
			
			
		}else {
			run();
		}
		
	}
	
	
	
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	public static void register() {
		Administrator s=new Administrator();
		
		AdministratorDaoImp administrator = new AdministratorDaoImp();
		try {
			boolean flag = administrator.register(s);
			
			if(flag) {
				System.out.println("Register successfull Please login");
				run();
			}
			
		} catch (StudentExeption e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	
	
	
	
////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	public static void selectCotegory() {
		
		AdministratorDaoImp administrator = new AdministratorDaoImp();

		System.out.println("Select functionality:->\n");
		System.out.println("1.Add New course: ");
		System.out.println("2.Update Fees of Course: ");
		System.out.println("3.Delete Course: ");
		System.out.println("4.Search Abour Course: ");
		
		int method=sc.nextInt();
		
		
		
		
		
		switch(method) {
		
			case 1: {
					
					
					try {
						String msg = administrator.addNewCourse();
						System.out.println(msg);
						selectCotegory();
						
					} catch (StudentExeption e) {
						System.out.println(e.getMessage());
					}
					
					selectCotegory();
					break;
				}
			
			case 2: {
					 try {
						String msg=administrator.updateFeesOfCourse();
					
						System.out.println(msg);
					 
					 } catch (StudentExeption e) {
						System.out.println(e.getMessage());
					}
				     selectCotegory();
				     break;
					}
			
			case 3:{
						break;
					}
			
			
			case 4: {
						run();
					}
		
		
		
		
		
		
		
		
		}


	}

	
}
	
	
	
	

