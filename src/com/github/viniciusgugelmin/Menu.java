package com.github.viniciusgugelmin;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
	
	public static List<Item> getItems(Scanner inItems, String split) {
		
		/* Showing foods */
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

	public static void getOrder(Scanner in, Order order, List<Item> itemList, String opt, Boolean especial) {
		
		/* Requesting order*/
		//
		String moreItem = "yes";
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
