package com.github.viniciusgugelmin;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Order {
	private long id;
	private String name;
	private List<Item> foods = new ArrayList<>();
	private List<Item> drinks = new ArrayList<>();
	private List<Item> wines = new ArrayList<>();
	
	/* Getters */
	//
	public long getId() {
		return id;
	}
	//
	public String getName() {
		return name;
	}
	//
	public List<Item> getFoods() {
		return foods;
	}
	//
	public List<Item> getDrinks() {
		return drinks;
	}
	//
	public List<Item> getWines() {
		return wines;
	}
	
	/* Setters */
	//
	public void setId(long id) {
		this.id = id;
	}
	//
	public void setName(String name) {
		this.name = name;
	}
	//
	public void setFoods(Item food) {
		this.foods.add(food);
	}
	//
	public void setDrinks(Item drink) {
		this.drinks.add(drink);
	}
	//
	public void setWines(Item wine) {
		this.wines.add(wine);
	}
	
	/* Show */
	//
	public static void showGet(Order order, double bill) {
		
		/* Print order */
		//
		System.out.println("\n-----------");
		System.out.println("Order's ID: " + order.getId());
		System.out.println("Customer's name: " + order.getName());
		showGetOrderItem(order.getFoods(), "food");
		showGetOrderItem(order.getDrinks(), "drink");
		showGetOrderItem(order.getWines(), "wine");
		System.out.println("Bill: $" + bill);
		System.out.println("-----------\n");
	}
	//
	public static void showGetOrderItem(List<Item> list, String itemType) {
		
		/* Print items and bill */
		//
		if (list.size() > 0) {
			System.out.println(itemType + ":");
			for (Item item : list) {
				System.out.print(" - ");
				System.out.print(item.getName());
				System.out.print(" - $" + item.getPrice());
				System.out.print(" - " + item.getQuantity());
				if (!item.getNote().contentEquals("..."))
					System.out.print(" - " + item.getNote());
				
				System.out.println("");
			}
		}
	}
	
	/* Add */
	//
	public static void addToList(Scanner in, Order order, List<Item> itemList, String itemType) {
		
		/* Requesting order */
		//
		itemType = itemType.toLowerCase();
		String opt = null;
		//
		if (itemType.equals("food")) {
			opt = "eat";
		} else if (itemType.equals("drink")) {
			opt = "drink";
		} else if (itemType.equals("wine")) {
			opt = "drink";
		}
		//
		System.out.println("Would you like something to " + opt + "? [ANS: yes OR no]");
		String toDo = "";
		//
		do {
			toDo = in.nextLine().toLowerCase();
			
			if (!toDo.equals("yes") && !toDo.equals("no")) 
				System.out.println("ERROR: Invalid option. [MUST BE: yes OR no]");
			
		} while (!toDo.equals("yes") && !toDo.equals("no"));
		//
		System.out.println("");
		//
		if (toDo.equals("yes")) {
			String moreItem = "yes";
			//
			System.out.println("What do you want to " + opt +"? [ANS: 'item's number']");
			//
			int itemId = 0;
			//
			do {
				do {
					itemId = in.nextInt();
					
					if (itemId >= itemList.size() || itemId < 0) 
						System.out.println("ERROR: Invalid number. [MUST BE: greater than 0 or less/equal than " + (itemList.size() - 1));
					
				} while (itemId >= itemList.size() || itemId < 0);
				
				Item itemSelected = itemList.get(itemId);
				Item item = new Item();
				
				System.out.println("How many servings?");
				int servings = 0;
				
				do {
					servings = in.nextInt();
					
					if (servings <= 0) 
						System.out.println("ERROR: Invalid number. [MUST BE: greater than 0]");
					
				} while (servings <= 0);
				
				System.out.println("Any notes? [BLANK NOTE: '...']");
				in.nextLine();
				String note = in.nextLine();
				
				item = new Item(itemSelected.getName(), itemSelected.getPrice(), servings, note);
				
				if (itemType.equals("food")) {
					order.setFoods(item);
				} else if (itemType.equals("drink")) {
					order.setDrinks(item);
				} else if (itemType.equals("wine")) {
					order.setWines(item);
				}
				
				System.out.println("Anything else to " + opt + "? [ANS: yes OR no]");
				moreItem = in.nextLine();
				
				if (!moreItem.equals("yes") && !moreItem.equals("no")) 
					System.out.println("ERROR: Invalid option. [MUST BE: yes OR no]");
				
				if (moreItem.equals("yes"))
						System.out.println("What do you want to " + opt + "? [ANS: 'item's number']");
				
			} while (moreItem.equals("yes"));
		}
	}
	//
	public static double addOrderFile(Order order, PrintWriter printOrder) {
		
		/* Add to file */
		//
		printOrder.println("Order's ID: " + order.getId());
		printOrder.println("Customer's name: " + order.getName());
		//
		Double bill = 0.0;
		//
		bill = getOrderItem(printOrder, order, bill, "foods");
		bill = getOrderItem(printOrder, order, bill, "drinks");
		bill = getOrderItem(printOrder, order, bill, "wines");
		//
		printOrder.println("Bill: $" + bill);
		//
		return bill;
	}
	
	/* Get */
	//
	public static Double getOrderItem(PrintWriter printOrder, Order order, Double bill, String itemType) {

		/* Get and print items and bill */
		//
		itemType = itemType.toLowerCase();
		//
		List<Item> list = new ArrayList<>();
		//
		if (itemType.equals("foods")) {
			list = order.getFoods();
		} else if (itemType.equals("drinks")) {
			list = order.getDrinks();
		} else if (itemType.equals("wines")) {
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
