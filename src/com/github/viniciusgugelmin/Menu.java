package com.github.viniciusgugelmin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
	
	private static List<Item> foods;
	private static List<Item> drinks;
	private static List<Item> wines;
	
	static {
		foods = new ArrayList<Item>();
		drinks = new ArrayList<Item>();
		wines = new ArrayList<Item>();
	}
	
	public static List<Item> getAll(Scanner inItems, String split) {
		
		/* Showing items of menu */
		//
		List<Item> list = new ArrayList<>();
		//
		System.out.println("");
		System.out.println("Here it is our menu...");
		inItems.nextLine();
		//
		int cont = 0;
		//
		while (inItems.hasNext()) {
			String line = inItems.nextLine();
			String[] i = line.split(split);
			
			Item item = new Item();
			
			if (split.equals(";")) {
				item.setName(i[0]);
				item.setPrice(Double.parseDouble(i[1].replaceAll(",", ".")));
			} else {
				item.setPrice(Double.parseDouble(i[0].replaceAll(",", ".")));
				item.setName(i[1]);
			}
			
			list.add(item);
			
			System.out.println(cont + split + line);
			
			cont++;
		}
		//
		System.out.println("");
		return list;
	}
	
	public static void add(Scanner in, List<Item> itemList, String i, String src) {
		
		/* Add to list */
		//
		System.out.print("Number of " + i + "s to add: ");
		int num = in.nextInt();
		
		for (int count = 1; count <= num; count++) {
			Item item = new Item();
			
			System.out.println("New " + i);
			
			System.out.println("Name: ");
			in.nextLine();
			item.setName(in.nextLine());
			
			System.out.println("Price: ");
			item.setPrice(in.nextDouble());

			itemList.add(item);
		}
		
		/* Print in file */
		//
		try {
			PrintWriter printFile = Files.toPrint(src);
	
			if (i.equals("food")) {
				printFile.println("PRATO;PRECO");
			} else if (i.equals("drink")) {
				printFile.println("PRECO\tBEBIDA");
			} else {
				printFile.println("PRECO\tVINHO");
			}
			
			for (Item item : itemList) {
				if (i.equals("food")) {
					printFile.println(item.getName() + ";" + item.getPrice());
				} else {
					printFile.println(item.getPrice() + "\t" + item.getName());
				}
			}
			
			printFile.close();
		
		} catch (IOException e) {
			System.out.println("ERROR: Can't read the directory.");
		}
	}
}
