package com.github.viniciusgugelmin;

import java.io.File;
import java.util.Scanner;

public class Enployee {
	
	public static void getEmployeeId(Scanner in) {
		
		/* Request enployee ID */
		//
		System.out.print("Type your ID as enployee to login: ");
		int id = 0;
		boolean enployeeExists = false;
		//
		do {
			id = in.nextInt();
			
			File enployee = new File("D:\\www\\restaurants-menu-up\\files\\enployees\\" + id + ".txt");
			enployeeExists = enployee.exists() ? true : false;
			
			System.out.println(enployee.exists() ? "Welcome to Positivo's System" : "ERROR: Invalid ID");
			
		} while (!enployeeExists);
	}
	
	public static int getEnployeesOptions(Scanner in) {
		
		/* Enployee's options */
		//
		System.out.println("Options: [1-ADD FOOD; 2-ADD DRINK; 3-ADD WINE; 4-LOG OUT]");
		int addOpt = 0;
		//
		do {
			addOpt = in.nextInt();
			
			if (addOpt < 1 || addOpt > 4)
				System.out.println("ERROR: Invalid option. [MUST BE: 1, 2, 3 OR 4]");
			
		} while (addOpt < 1 || addOpt > 4);
		return addOpt;
	}
}
