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

		/* To do */
		//
		if (toDoOpt.equals("order")) {

			/* Order ID generator */
			//
			long orderId = 1;

			boolean orderExists = false;
			//
			PrintWriter printOrder = Files.toPrint(Files.getDraftFile(), true);
			//
			do {
				String file = (Files.getOrderRoot() + orderId + ".txt");
				File fileOrder = new File(file);

				if (fileOrder.exists()) {
					orderId++;
					orderExists = true;
				} else {
					printOrder = Files.toPrint(file, true);
					orderExists = false;
				}
			} while (orderExists);
		

			/* Writting order */
			//
			Order order = new Order();
			order.setId(orderId);
			//
			System.out.println("May I know your name?");
			String name = in.nextLine();
			order.setName(name);

			/* Foods */
			//
			List<Item> foodList = Menu.showGet("food");
			//
			Order.addToList(in, order, foodList, "food");

			/* Drinks */
			//
			List<Item> drinkList = Menu.showGet("drink");
			//
			Order.addToList(in, order, drinkList, "drink");

			/* Wines */
			//
			List<Item> wineList = Menu.showGet("wine");
			//
			Order.addToList(in, order, wineList, "wine");

			/* Setters */
			//
			double bill = Order.addOrderFile(order, printOrder);
			Order.showGet(order, bill);
			printOrder.close();

			System.out.println("Thanks for choosing us!");
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
				List<Item> foodList = Menu.getFileList("food");
				List<Item> drinkList = Menu.getFileList("drink");
				List<Item> wineList = Menu.getFileList("wine");
				//
				switch (addOpt) {
				case 1:
					// Add food
					Menu.showAdd(in, foodList, "food");
					break;
				case 2:
					// Add drink
					Menu.showAdd(in, drinkList, "drink");
					break;
				case 3:
					// Add wine
					Menu.showAdd(in, wineList, "wine");
					break;
				case 4:
					// Update food
					Menu.showUpdate(in, foodList, "food");
					break;
				case 5:
					// Update drink
					Menu.showUpdate(in, drinkList, "drink");
					break;
				case 6:
					// Update wine
					Menu.showUpdate(in, wineList, "wine");
					break;
				case 7:
					// Delete food
					Menu.showRemove(in, foodList, "food");
					break;
				case 8:
					// Delete drink
					Menu.showRemove(in, drinkList, "drink");
					break;
				case 9:
					// Delete wine
					Menu.showRemove(in, wineList, "wine");
					break;
				case 10:
					logOut = true;
					System.out.println("Bye!");
					break;
				}
			} while (logOut == false);
		}

		in.close();
	}
}
