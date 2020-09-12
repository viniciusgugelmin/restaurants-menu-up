package com.github.viniciusgugelmin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program {
	
	public static void main(String[] args) throws IOException {
		
		/* Greetings */
		//
		System.out.println("Welcome to 'Positivo restaurant'...");
		System.out.println("Wanna take a sit? Or are you going to work today? [ANS: sit OR work]");
		//
		Scanner in = new Scanner(System.in);
		String toDoOpt = "";
		//
		do {
			toDoOpt = in.nextLine().toLowerCase();
			
			if (!toDoOpt.equals("sit") && !toDoOpt.equals("work")) 
				System.out.println("ERROR: Invalid option. [MUST BE: sit OR work]");
			
		} while (!toDoOpt.equals("sit") && !toDoOpt.equals("work"));
		//
		System.out.println("");
		
		/* Files reader */
		//
		File fileFoods = new File("D:\\www\\restaurants-menu-up\\files\\menu\\foods.txt");
		Scanner inFoods = new Scanner(fileFoods);
		//
		File fileDrinks = new File("D:\\www\\restaurants-menu-up\\files\\menu\\drinks.txt");
		Scanner inDrinks = new Scanner(fileDrinks);
		//
		File fileWines = new File("D:\\www\\restaurants-menu-up\\files\\menu\\wines.txt");
		Scanner inWines = new Scanner(fileWines);
		
		/* To do */
		//
		if (toDoOpt.equals("sit")) {
			
			/* Order ID generator */
			//
			long orderId = 1;
			boolean orderExists = false;;
			//
			do {
				File fileOrder = new File("D:\\www\\restaurants-menu-up\\files\\orders\\" + orderId + ".txt");
				
				if(fileOrder.exists()) { 
					orderId++;
					orderExists = true;
				} else {
					FileWriter writeOrder = new FileWriter("D:\\www\\restaurants-menu-up\\files\\orders\\" + orderId + ".txt");
					PrintWriter printOrder = new PrintWriter(writeOrder);
					orderExists = false;
				}
			} while (orderExists);

			/* Writting order */
			//
			System.out.println("May I know your name?");
			String name = in.nextLine();
			//
			Order order = new Order();
			order.setName(name);
			
			/* Showing foods */
			//
			List<Item> foodList = new ArrayList<>();
			//
			System.out.println("");
			System.out.println("Here it is our menu...");
			inFoods.nextLine();
			//
			int cont = 0;
			//
			while (inFoods.hasNext()) {
				String line = inFoods.nextLine();
				String[] i = line.split(";");
				
				Item food = new Item();
				food.setName(i[0]);
				food.setPrice(Double.parseDouble(i[1].replaceAll(",", ".")));
				
				foodList.add(food);
				System.out.println(cont + ";" + line);
				
				cont++;
			}
			//
			System.out.println("");
			
			/* Requesting food*/
			//
			String moreFood = "yes";
			System.out.println(order.getName() + ", what do you want do eat? [ANS: 'item's number']");
			//
			do {
				int id = 0;
				
				do {
					id = in.nextInt();
					
					if (id >= foodList.size() || id < 0) 
						System.out.println("ERROR: Invalid number. [MUST BE: greater than 0 or less/equal than " + (foodList.size() - 1));
					
				} while (id >= foodList.size() || id < 0);
				
				foodList.get(id).getName();
				
				System.out.println("Anything else to eat? [ANS: yes OR no]");
				in.nextLine();
				moreFood = in.nextLine();
				
				if (!moreFood.equals("yes") && !moreFood.equals("no")) 
					System.out.println("ERROR: Invalid option. [MUST BE: yes OR no]");
				
				if (moreFood.equals("yes"))
						System.out.println(order.getName() + ", what do you want do eat? [ANS: 'item's number']");
				
			} while (moreFood.equals("yes"));
			
		} else {
			
		} 
	}
}
