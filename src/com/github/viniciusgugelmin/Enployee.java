package com.github.viniciusgugelmin;

import java.io.File;
import java.util.Scanner;

public class Enployee {
	public static void getId(Scanner in) {
		
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
}