package com.github.viniciusgugelmin;

import java.util.Scanner;

public class Program {
	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		String toDoOpt = "";
		
		/* Greetings */
		System.out.println("Welcome to 'Positivo restaurant'...");
		System.out.println("Wanna take a sit? Or are you going to work today? [ANS: sit OR work]");
		
		do {
			toDoOpt = in.nextLine().toLowerCase();
			
			if (!toDoOpt.equals("sit") && !toDoOpt.equals("work")) 
				System.out.println("ERROR: Invalid option. [MUST BE: sit OR work]");
			
		} while (!toDoOpt.equals("sit") && !toDoOpt.equals("work"));
		
		if (toDoOpt.equals("sit")) {
			
		} else {
			
		} 
	}
}
