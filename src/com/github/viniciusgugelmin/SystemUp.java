package com.github.viniciusgugelmin;

import java.util.Scanner;

public class SystemUp {
	
	public static int getSystem(Scanner in) {
		
		/* Enployee's options */
		//
		System.out.println("Options: [1-ADD FOOD; 2-ADD DRINK; 3-ADD WINE; 4-UPDATE FOOD; 5-UPDATE DRINK; 6-UPDATE WINE; "
				+ "7-DELETE FOOD; 8-DELETE DRINK; 9-DELETE WINE; 10-LOG OUT]");
		int addOpt = 0;
		//
		do {
			addOpt = in.nextInt();
			
			if (addOpt < 1 || addOpt > 10)
				System.out.println("ERROR: Invalid option. [MUST BE: greater than 0 and less than 11]");
			
		} while (addOpt < 1 || addOpt > 10);
		return addOpt;
	}
}