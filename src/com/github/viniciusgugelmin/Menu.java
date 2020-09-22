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
		/* Load variables */
		foods = new ArrayList<Item>();
		foods = getFileList("food");
		//
		drinks = new ArrayList<Item>();
		drinks = getFileList("drink");
		//
		wines = new ArrayList<Item>();
		wines = getFileList("wine");
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
	
	public static void add(Scanner in, List<Item> itemList, String itemType, String src) {
		
		/* Add to list */
		//
		System.out.print("Number of " + itemType + "s to add: ");
		int num = in.nextInt();
		//
		for (int count = 1; count <= num; count++) {
			Item item = new Item();
			
			System.out.println("New " + itemType);
			
			System.out.println("Name: ");
			in.nextLine();
			item.setName(in.nextLine());
			
			System.out.println("Price: ");
			item.setPrice(in.nextDouble());

			itemList.add(item);
		}
		
		PrintWriter printFile = Files.toPrint(src, false);
		
		for (Item item : itemList) {
			if (itemType.equals("food")) {
				printFile.println(item.getName() + ";" + item.getPrice());
			} else {
				printFile.println(item.getPrice() + "\t" + item.getName());
			}
		}
		
		printFile.close();
	}
	
	/* Get */
	//
	private static List<Item> getFileList(String itemType) {
		
		List<Item> itemList = new ArrayList<>();
		Scanner items = Files.toRead(Files.getDraftFile());
		
		if (itemType.toLowerCase().equals("food")) {
			items = Files.toRead(Files.getFoodsFile());
		} else if (itemType.toLowerCase().equals("drink")) {
			items = Files.toRead(Files.getDrinksFile());
		} else if (itemType.toLowerCase().equals("wine")) {
			items = Files.toRead(Files.getWinesFile());
		}

		items.nextLine();
		
		while (items.hasNext()) {
			String line = items.nextLine();
			Item item = new Item();
			
			if (itemType.toLowerCase().equals("food")) {
				String[] i = line.split(Files.getFoodsSplit());
				item = new Item(i[0], i[1]);
			} else if (itemType.toLowerCase().equals("drink")) {
				String[] i = line.split(Files.getWinesSplit());
				item = new Item(i[1], i[0]);
			} else if (itemType.toLowerCase().equals("wine")) {
				String[] i = line.split(Files.getWinesSplit());
				item = new Item(i[1], i[0]);
			}
			
			itemList.add(item);
		}
		
		items.close();
		
		return itemList;
	}
	//
	public static List<Item> getList(String itemType) {
		
		if (itemType.toLowerCase().equals("food")) {
			return foods;
		} else if (itemType.toLowerCase().equals("drink")) {
			return drinks;
		} else if (itemType.toLowerCase().equals("wine")) {
			return wines;
		}
		
		return null;
	}
	
	/* Add */
	//
	public static void addItemList(Item item, String itemType) {
		
		if (itemType.toLowerCase().equals("food")) {
			foods.add(item);
		} else if (itemType.toLowerCase().equals("drink")) {
			drinks.add(item);
		} else if (itemType.toLowerCase().equals("wine")) {
			wines.add(item);
		}
		
		addItemFile(item, itemType);
	}
	//
	private static void addItemFile(Item item, String itemType) {
		
		PrintWriter printer = Files.toPrint(Files.getDraftFile(), false);
		
		if (itemType.toLowerCase().equals("food")) {
			printer = Files.toPrint(Files.getFoodsFile(), false);
			printer.println(item.getName() + Files.getFoodsSplit() + item.getPrice());
		} else if (itemType.toLowerCase().equals("drink")) {
			printer = Files.toPrint(Files.getDrinksFile(), false);
			printer.println(item.getPrice() + Files.getDrinksSplit() + item.getName());
		} else if (itemType.toLowerCase().equals("wine")) {
			printer = Files.toPrint(Files.getWinesFile(), false);
			printer.println(item.getPrice() + Files.getWinesSplit() + item.getName());
		}
		
		printer.close();
	}
	
	/* Update */
	//
	public static void updateItem(Item item, String itemType) {
		
		List <Item> list = new ArrayList<>();
		
		if (itemType.toLowerCase().equals("food")) {
			list = foods;
		} else if (itemType.toLowerCase().equals("drink")) {
			list = drinks;
		} else if (itemType.toLowerCase().equals("wine")) {
			list = wines;
		}
		 
		for (Item itemToUpdate : list) {
			if (itemToUpdate.getName().equals(item.getName())) {
				itemToUpdate.setName(item.getName());
				itemToUpdate.setPrice(item.getPrice());
				break;
			}
		}
		
		updateList(list, itemType);
	}
	//
	private static void updateList(List <Item> list, String itemType) {
		
		PrintWriter printer = Files.toPrint(Files.getDraftFile(), true);

		if (itemType.toLowerCase().equals("food")) {
			printer = Files.toPrint(Files.getFoodsFile(), true);
			printer.println("PRATO;PRECO");
		} else if (itemType.toLowerCase().equals("drink")) {
			printer = Files.toPrint(Files.getDrinksFile(), true);
			printer.println("PRECO\tBEBIDA");
		} else if (itemType.toLowerCase().equals("wine")) {
			printer = Files.toPrint(Files.getWinesFile(), true);
			printer.println("PRECO\tVINHO");
		}
		
		for (Item item : list) {
			if (itemType.toLowerCase().equals("food")) {
				printer.println(item.getName() + Files.getFoodsSplit() + item.getPrice());
			} else if (itemType.toLowerCase().equals("drink")) {
				printer.println(item.getPrice() + Files.getDrinksSplit() + item.getName());
			} else if (itemType.toLowerCase().equals("wine")) {
				printer.println(item.getPrice() + Files.getWinesSplit() + item.getName());
			}
		}
		
		printer.close();
	}
	
	/* Remove */
	//
	public static void remove(Item item, String itemType) {
		
		List <Item> list = new ArrayList<>();
		
		if (itemType.toLowerCase().equals("food")) {
			foods.remove(item);
			list = foods;
		} else if (itemType.toLowerCase().equals("drink")) {
			drinks.remove(item);
			list = drinks;
		} else if (itemType.toLowerCase().equals("wine")) {
			wines.remove(item);
			list = wines;
		}
		
		updateList(list, itemType);
	}
}
