package com.management.managentApp;

import java.util.Scanner;

public class Main {

	public static void choice() {
		Scanner sc = new Scanner(System.in);

		System.out.println("select your cotegory");
		System.out.println("1.As Student:");
		System.out.println("2.As Administrator: ");

		int choice = sc.nextInt();

		if (choice == 1)
			SMS.run();
		else
			AdminService.run();
	}

	public static void main(String[] args) {
		
		choice();

	}

}
