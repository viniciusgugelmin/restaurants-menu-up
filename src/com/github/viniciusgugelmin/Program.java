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
			boolean orderExists = false;
			//
			FileWriter writeOrder = new FileWriter("D:\\www\\restaurants-menu-up\\files\\orders\\draft.txt");
			PrintWriter printOrder = new PrintWriter(writeOrder);
			//
			do {
				File fileOrder = new File("D:\\www\\restaurants-menu-up\\files\\orders\\" + orderId + ".txt");
				
				if(fileOrder.exists()) { 
					orderId++;
					orderExists = true;
				} else {
					writeOrder = new FileWriter("D:\\www\\restaurants-menu-up\\files\\orders\\" + orderId + ".txt");
					printOrder = new PrintWriter(writeOrder);
					orderExists = false;
				}
			} while (orderExists);

			/* Writting order */
			//
			System.out.println("May I know your name?");
			String name = in.nextLine();
			//
			Order order = new Order();
			
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
			System.out.println("What do you want do eat? [ANS: 'item's number']");
			//
			int foodId = 0;
			//
			do {
				do {
					foodId = in.nextInt();
					
					if (foodId >= foodList.size() || foodId < 0) 
						System.out.println("ERROR: Invalid number. [MUST BE: greater than 0 or less/equal than " + (foodList.size() - 1));
					
				} while (foodId >= foodList.size() || foodId < 0);
				
				Item foodSelected = foodList.get(foodId);
				Item food = new Item();
				
				System.out.println("How many servings?");
				int servings = 0;
				
				do {
					servings = in.nextInt();
					
					if (servings <= 0) 
						System.out.println("ERROR: Invalid number. [MUST BE: greater than 0]");
					
				} while (servings <= 0);
				
				System.out.println("Any notes?");
				in.nextLine();
				String note = in.nextLine();
				
				food.setName(foodSelected.getName());
				food.setPrice(foodSelected.getPrice());
				food.setQuantity(servings);
				food.setNote(note);
				
				order.setFoods(food);
				
				System.out.println("Anything else to eat? [ANS: yes OR no]");
				moreFood = in.nextLine();
				
				if (!moreFood.equals("yes") && !moreFood.equals("no")) 
					System.out.println("ERROR: Invalid option. [MUST BE: yes OR no]");
				
				if (moreFood.equals("yes"))
						System.out.println("What do you want do eat? [ANS: 'item's number']");
				
			} while (moreFood.equals("yes"));
			
			/* Setters */ 
			//
			order.setId(orderId);
			order.setName(name);
			//
			printOrder.println("Order's ID: " + order.getId());
			printOrder.println("Customer's name: " + order.getName());
			//
			Double bill = 0.0;
			//
			printOrder.println("Foods:");
			for (Item food : order.getFoods()) {
				printOrder.print(" - ");
				printOrder.print(food.getName());
				printOrder.print(" - R$" + food.getPrice());
				printOrder.print(" - " + food.getQuantity());
				printOrder.print(" - " + food.getNote() + "\n");
				
				bill += food.getPrice() * food.getQuantity();
			}
			//
			printOrder.println("Bill: R$" + bill);
			
			writeOrder.close();
			printOrder.close();
			
			System.out.println("Thanks for choosing us!");
			System.out.println("Se you later!");
		} else {
			
		} 
	}
}
