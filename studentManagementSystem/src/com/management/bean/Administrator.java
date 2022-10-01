package com.management.bean;

import java.util.Scanner;

public class Administrator {
	
	
	private int aId;
	private String name;
	private String email;
	private String password;
	
	
	
	public int getaId() {
		return aId;
	}
	public void setaId(int aId) {
		this.aId = aId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	public Administrator(int aId, String name, String email, String password) {
		super();
		this.aId = aId;
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
	
	public Administrator() {
		Scanner sc = new Scanner(System.in);
		
		
		
		System.out.println("Enater your Name: ");
		String name = sc.next();
		
		System.out.println("Enater Your Email: ");
		String email = sc.next();
		
		System.out.println("Enter Your Password: ");
		String pass = sc.next();
		
		this.name=name;
		this.email=email;
		this.password=pass;
		
	}
	
	
	
	
	
	
	

}
