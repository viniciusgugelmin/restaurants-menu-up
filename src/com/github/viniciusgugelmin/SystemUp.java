package com.github.viniciusgugelmin;

import java.util.Scanner;

public class SystemUp {
	
	public static int getSystem(Scanner in) {
		
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