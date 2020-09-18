package com.github.viniciusgugelmin;

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
	
	public static void add(Scanner in, Order order, List<Item> itemList, String opt, Boolean especial) {
		
		/* Requesting order */
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
				
				item.setName(itemSelected.getName());
				item.setPrice(itemSelected.getPrice());
				item.setQuantity(servings);
				item.setNote(note);
				
				if (opt.equals("eat")) {
					order.setFoods(item);
				} else if (opt.equals("drink") && !especial) {
					order.setDrinks(item);
				} else if (opt.equals("drink") && especial) {
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
}
