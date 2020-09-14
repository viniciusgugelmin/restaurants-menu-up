package com.github.viniciusgugelmin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Files {
	
	public static Scanner readFile(String root) throws FileNotFoundException {

		File file = new File(root);
		Scanner inFile = new Scanner(file);
		
		return inFile;
	}
	
	public static PrintWriter printFile(String root) throws IOException {
		
		FileWriter writeFile = new FileWriter(root);
		PrintWriter printFile = new PrintWriter(writeFile);
		
		return printFile;
	}
	
	public static Double printItem(PrintWriter printOrder, Order order, Double bill, String itemType) {

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
			System.out.println(itemType + ":");
			printOrder.println(itemType + ":");
			for (Item item : list) {
				System.out.print(" - ");
				System.out.print(item.getName());
				System.out.print(" - $" + item.getPrice());
				System.out.print(" - " + item.getQuantity());
				if (!item.getNote().contentEquals("..."))
					System.out.print(" - " + item.getNote());
				
				System.out.println("");

				printOrder.print(" - ");
				printOrder.print(item.getName());
				printOrder.print(" - $" + item.getPrice());
				printOrder.print(" - " + item.getQuantity());
				if (!item.getNote().contentEquals("..."))
					printOrder.print(" - " + item.getNote());
				
				printOrder.println("");
				
				bill += (item.getPrice() * item.getQuantity());
			}
		}
		//
		return bill;
	}
}
