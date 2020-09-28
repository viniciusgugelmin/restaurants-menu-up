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
	
	/* Show */
	//
	public static void showAdd(Scanner in, List<Item> itemList, String itemType) {
		
		/* Show to add item*/
		//
		itemType = itemType.toLowerCase();
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
			
			addItemList(item, itemType);
		}
	}
	//
	public static List<Item> showGet(String itemType) {
		
		/* Showing items of menu */
		//
		itemType = itemType.toLowerCase();
		List<Item> list = new ArrayList<>();
		list = getList(itemType);
		//
		System.out.println("");
		System.out.println("Here it is our menu...");
		int cont = 0;
		//
		for (Item item : list) {
			
			String split = null;
			String line = null;
			
			if (itemType.equals("food")) {
				split = Files.getFoodsSplit();
				line = item.getName() + Files.getFoodsSplit() + item.getPrice();
			} else if (itemType.equals("drink")) {
				split = Files.getDrinksSplit();
				line = item.getPrice() + Files.getDrinksSplit() + item.getName();
			} else if (itemType.equals("wine")) {
				split = Files.getWinesSplit();
				line = item.getPrice() + Files.getWinesSplit() + item.getName();
			}
			
			System.out.println(cont + split + line);
			cont++;
		}
		//
		System.out.println("");
		
		return list;
	}
	
	/* Get */
	//
	public static List<Item> getFileList(String itemType) {
		
		itemType = itemType.toLowerCase();
		List<Item> itemList = new ArrayList<>();
		Scanner items = Files.toRead(Files.getDraftFile());
		
		if (itemType.equals("food")) {
			items = Files.toRead(Files.getFoodsFile());
		} else if (itemType.equals("drink")) {
			items = Files.toRead(Files.getDrinksFile());
		} else if (itemType.equals("wine")) {
			items = Files.toRead(Files.getWinesFile());
		}

		items.nextLine();
		
		while (items.hasNext()) {
			String line = items.nextLine();
			Item item = new Item();
			
			if (itemType.equals("food")) {
				String[] i = line.split(Files.getFoodsSplit());
				item = new Item(i[0], i[1]);
			} else if (itemType.equals("drink")) {
				String[] i = line.split(Files.getWinesSplit());
				item = new Item(i[1], i[0]);
			} else if (itemType.equals("wine")) {
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
		
		itemType = itemType.toLowerCase();
		if (itemType.equals("food")) {
			return foods;
		} else if (itemType.equals("drink")) {
			return drinks;
		} else if (itemType.equals("wine")) {
			return wines;
		}
		
		return null;
	}
	
	/* Add */
	//
	public static void addItemList(Item item, String itemType) {
		
		itemType = itemType.toLowerCase();
		if (itemType.equals("food")) {
			foods.add(item);
		} else if (itemType.equals("drink")) {
			drinks.add(item);
		} else if (itemType.equals("wine")) {
			wines.add(item);
		}
		
		addItemFile(item, itemType);
	}
	//
	private static void addItemFile(Item item, String itemType) {
		
		itemType = itemType.toLowerCase();
		PrintWriter printer = Files.toPrint(Files.getDraftFile(), false);
		
		if (itemType.equals("food")) {
			printer = Files.toPrint(Files.getFoodsFile(), false);
			printer.println(item.getName() + Files.getFoodsSplit() + item.getPrice());
		} else if (itemType.equals("drink")) {
			printer = Files.toPrint(Files.getDrinksFile(), false);
			printer.println(item.getPrice() + Files.getDrinksSplit() + item.getName());
		} else if (itemType.equals("wine")) {
			printer = Files.toPrint(Files.getWinesFile(), false);
			printer.println(item.getPrice() + Files.getWinesSplit() + item.getName());
		}
		
		printer.close();
	}
	
	/* Update */
	//
	public static void updateItem(Item item, String itemType) {
		
		itemType = itemType.toLowerCase();
		List <Item> list = new ArrayList<>();
		
		if (itemType.equals("food")) {
			list = foods;
		} else if (itemType.equals("drink")) {
			list = drinks;
		} else if (itemType.equals("wine")) {
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
		
		itemType = itemType.toLowerCase();
		PrintWriter printer = Files.toPrint(Files.getDraftFile(), true);

		if (itemType.equals("food")) {
			printer = Files.toPrint(Files.getFoodsFile(), true);
			printer.println("PRATO;PRECO");
		} else if (itemType.equals("drink")) {
			printer = Files.toPrint(Files.getDrinksFile(), true);
			printer.println("PRECO\tBEBIDA");
		} else if (itemType.equals("wine")) {
			printer = Files.toPrint(Files.getWinesFile(), true);
			printer.println("PRECO\tVINHO");
		}
		
		for (Item item : list) {
			if (itemType.equals("food")) {
				printer.println(item.getName() + Files.getFoodsSplit() + item.getPrice());
			} else if (itemType.equals("drink")) {
				printer.println(item.getPrice() + Files.getDrinksSplit() + item.getName());
			} else if (itemType.equals("wine")) {
				printer.println(item.getPrice() + Files.getWinesSplit() + item.getName());
			}
		}
		
		printer.close();
	}
	
	/* Remove */
	//
	public static void remove(Item item, String itemType) {
		
		itemType = itemType.toLowerCase();
		List <Item> list = new ArrayList<>();
		
		if (itemType.equals("food")) {
			foods.remove(item);
			list = foods;
		} else if (itemType.equals("drink")) {
			drinks.remove(item);
			list = drinks;
		} else if (itemType.equals("wine")) {
			wines.remove(item);
			list = wines;
		}
		
		updateList(list, itemType);
	}
}
