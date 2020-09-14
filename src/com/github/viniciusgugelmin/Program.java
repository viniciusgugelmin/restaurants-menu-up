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
		Scanner in = getIn();
		String toDoOpt = getToDoOpt(in);
		
		/* File reader */
		//
		Scanner inFoods = Files.readFile("D:\\www\\restaurants-menu-up\\files\\menu\\foods.txt");
		Scanner inDrinks = Files.readFile("D:\\www\\restaurants-menu-up\\files\\menu\\drinks.txt");
		Scanner inWines = Files.readFile("D:\\www\\restaurants-menu-up\\files\\menu\\wines.txt");
		
		/* To do */
		//
		if (toDoOpt.equals("sit")) {
			
			/* Order ID generator */
			//
			long orderId = 1;
			boolean orderExists = false;
			//
			PrintWriter printOrder = Files.printFile("D:\\www\\restaurants-menu-up\\files\\draft\\draft.txt");
			//
			do {
				File fileOrder = new File("D:\\www\\restaurants-menu-up\\files\\orders\\" + orderId + ".txt");
				
				if(fileOrder.exists()) { 
					orderId++;
					orderExists = true;
				} else {
					printOrder = Files.printFile("D:\\www\\restaurants-menu-up\\files\\orders\\" + orderId + ".txt");
					orderExists = false;
				}
			} while (orderExists);

			/* Writting order */
			//
			Order order = new Order();
			//
			System.out.println("May I know your name?");
			String name = in.nextLine();
			
			/* Foods */
			//
			List<Item> foodList = Menu.getItems(inFoods, ";");
			//
			Menu.getOrder(in, order, foodList, "eat", false);
			
			/* Drinks */
			//
			List<Item> drinkList = Menu.getItems(inDrinks, "\t");
			//
			Menu.getOrder(in, order, drinkList, "drink", false);
			
			/* Wines */
			//
			List<Item> wineList = Menu.getItems(inWines, "\t");
			//
			Menu.getOrder(in, order, wineList, "drink", true);
			
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
			bill = printItem(printOrder, order, bill, "Foods");
			bill = printItem(printOrder, order, bill, "Drinks");
			bill = printItem(printOrder, order, bill, "Wines");
			//
			printOrder.println("Bill: $" + bill);
			//
			printOrder.close();
			
			System.out.println("Thanks for choosing us!");
			System.out.println("Your bill: $" + bill);
			System.out.println("See you later!");
		} else {
			
			/* Request enployee ID */
			//
			getEmployeeId(in);
			
			/* Enployee's options */
			//
			System.out.println("Options: [1-ADD FOOD; 2-ADD DRINK; 3-ADD WINE]");
			int addOpt = 0;
			//
			do {
				addOpt = in.nextInt();
				
				if (addOpt < 1 || addOpt > 3)
					System.out.println("ERROR: Invalid option. [MUST BE: 1, 2 or 3]");
				
			} while (addOpt < 1 || addOpt > 3);

			/* Add Options */
			//
			Scanner inFile = Files.readFile("D:\\www\\restaurants-menu-up\\files\\draft\\draft.txt");
			//
			switch (addOpt) {
			case 1:
				inFile = Files.readFile("D:\\www\\restaurants-menu-up\\files\\menu\\foods.txt");
				
				List<Item> itemList = Menu.getItems(inFile, ";");
				
				System.out.print("Number of foods to add: ");
				int num = in.nextInt();
				
				for (int count = 1; count <= num; count++) {
					Item item = new Item();
					
					System.out.println("New food");
					
					System.out.println("Name: ");
					in.nextLine();
					item.setName(in.nextLine());
					
					System.out.println("Price: ");
					item.setPrice(in.nextDouble());
	
					itemList.add(item);
				}
				
				break;
			case 2:
				break;
			case 3:
				break;
			}
			
			inFile.close();
		} 
		
		System.out.println("fim");
		in.close();
		inFoods.close();
		inDrinks.close();
		inWines.close();
	}

	private static Scanner getIn() {

		/* Greetings */
		//
		System.out.println("Welcome to 'Positivo restaurant'...");
		System.out.println("Wanna take a sit? Or are you going to work today? [ANS: sit OR work]");
		//
		Scanner in = new Scanner(System.in);
		//
		return in;
	}
	
	private static String getToDoOpt(Scanner in) {
		
		/* Validate choice/option */
		//
		String toDoOpt = "";
		//
		do {
			toDoOpt = in.nextLine().toLowerCase();
			
			if (!toDoOpt.equals("sit") && !toDoOpt.equals("work")) 
				System.out.println("ERROR: Invalid option. [MUST BE: sit OR work]");
			
		} while (!toDoOpt.equals("sit") && !toDoOpt.equals("work"));
		//
		System.out.println("");
		//
		return toDoOpt;
	}
	
	private static Double printItem(PrintWriter printOrder, Order order, Double bill, String itemType) {

		/* Print items and bill */
		//
		List<Item> list = new ArrayList<>();
		//
		if (itemType.equals("Foods")) {
			list = order.getFoods();
		} else if (itemType.equals("Drinks")) {
			list = order.getDrinks();
		} else if (itemType.equals("Wines")) {
			list = order.getWines();
		}
		//
		if (list.size() > 0) {
			printOrder.println(itemType + ":");
			for (Item item : list) {
				printOrder.print(" - ");
				printOrder.print(item.getName());
				printOrder.print(" - $" + item.getPrice());
				printOrder.print(" - " + item.getQuantity());
				printOrder.print(" - " + item.getNote() + "\n");
				
				bill += (item.getPrice() * item.getQuantity());
			}
		}
		//
		return bill;
	}
	
	private static void getEmployeeId(Scanner in) {
		
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
