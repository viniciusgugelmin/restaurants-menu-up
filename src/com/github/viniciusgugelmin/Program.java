package com.github.viniciusgugelmin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) throws IOException {

		/* Greetings */
		//
		System.out.println("Welcome to 'Positivo restaurant'...");
		System.out.println("Wanna make an order? Or are you going to log into the system? [ANS: order OR system]");
		//
		Scanner in = new Scanner(System.in);
		//
		String toDoOpt = "";
		//
		do {
			toDoOpt = in.nextLine().toLowerCase();

			if (!toDoOpt.equals("order") && !toDoOpt.equals("system"))
				System.out.println("ERROR: Invalid option. [MUST BE: order OR system]");

		} while (!toDoOpt.equals("order") && !toDoOpt.equals("system"));
		//
		System.out.println("");

		/* File reader */
		//
		Scanner inFoods = Files.toRead("D:\\www\\restaurants-menu-up\\files\\menu\\foods.txt");
		Scanner inDrinks = Files.toRead("D:\\www\\restaurants-menu-up\\files\\menu\\drinks.txt");
		Scanner inWines = Files.toRead("D:\\www\\restaurants-menu-up\\files\\menu\\wines.txt");

		/* To do */
		//
		if (toDoOpt.equals("order")) {

			/* Order ID generator */
			//
			long orderId = 1;
			boolean orderExists = false;
			//
			PrintWriter printOrder = Files.toPrint("D:\\www\\restaurants-menu-up\\files\\draft\\draft.txt");
			//
			do {
				File fileOrder = new File("D:\\www\\restaurants-menu-up\\files\\orders\\" + orderId + ".txt");

				if (fileOrder.exists()) {
					orderId++;
					orderExists = true;
				} else {
					printOrder = Files.toPrint("D:\\www\\restaurants-menu-up\\files\\orders\\" + orderId + ".txt");
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
			List<Item> foodList = Menu.getAll(inFoods, ";");
			//
			Order.add(in, order, foodList, "eat", false);

			/* Drinks */
			//
			List<Item> drinkList = Menu.getAll(inDrinks, "\t");
			//
			Order.add(in, order, drinkList, "drink", false);

			/* Wines */
			//
			List<Item> wineList = Menu.getAll(inWines, "\t");
			//
			Order.add(in, order, wineList, "drink", true);

			/* Setters */
			//
			order.setId(orderId);
			order.setName(name);
			//
			System.out.println("\n-----------");
			System.out.println("Order's ID: " + order.getId());
			System.out.println("Customer's name: " + order.getName());
			//
			printOrder.println("Order's ID: " + order.getId());
			printOrder.println("Customer's name: " + order.getName());
			//
			Double bill = 0.0;
			//
			bill = Item.getAll(printOrder, order, bill, "Foods");
			bill = Item.getAll(printOrder, order, bill, "Drinks");
			bill = Item.getAll(printOrder, order, bill, "Wines");
			//
			System.out.println("Bill: $" + bill);
			System.out.println("-----------\n");
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
			Enployee.getId(in);
			//
			boolean logOut = false;

			do {
				/* Enployee's options */
				//
				int addOpt = SystemUp.getSystem(in);

				/* Add Options */
				//
				switch (addOpt) {
				case 1:
					List<Item> foodList = Menu.getAll(inFoods, ";");

					Menu.add(in, foodList, "food", "D:\\www\\restaurants-menu-up\\files\\menu\\foods.txt");
					break;
				case 2:
					List<Item> drinkList = Menu.getAll(inDrinks, "\t");

					Menu.add(in, drinkList, "drink", "D:\\www\\restaurants-menu-up\\files\\menu\\drinks.txt");
					break;
				case 3:
					List<Item> wineList = Menu.getAll(inWines, "\t");

					Menu.add(in, wineList, "wine", "D:\\www\\restaurants-menu-up\\files\\menu\\wines.txt");
					break;
				case 4:
					logOut = true;
					System.out.println("Bye!");
					break;
				}
			} while (logOut == false);
		}

		in.close();
		inFoods.close();
		inDrinks.close();
		inWines.close();
	}
}
