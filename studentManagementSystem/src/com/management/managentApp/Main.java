package com.management.managentApp;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc=new Scanner(System.in);
		
		System.out.println("select your cotegory");
		System.out.println("1.As Student:");
		System.out.println("2.As Administrator: ");
		
		int cotegory=sc.nextInt();
		
		if(cotegory==1) System.out.println("welcome student");
		else System.out.println("welcome Administrator ");

	}

}
