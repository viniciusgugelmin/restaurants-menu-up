package com.github.viniciusgugelmin;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Item {
	private String name;
	private double price;
	private int quantity;
	private String note;
	
	
	/* Getters */
	//
	public String getName() {
		return name;
	}
	//
	public double getPrice() {
		return price;
	}
	//
	public int getQuantity() {
		return quantity;
	}
	//
	public String getNote() {
		return note;
	}
	
	/* Setters */
	//
	public void setName(String name) {
		this.name = name;
	}
	//
	public void setPrice(double price) {
		this.price = price;
	}
	//
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	//
	public void setNote(String note) {
		this.note = note;
	}
	
	public static Double getAll(PrintWriter printOrder, Order order, Double bill, String itemType) {

		/* Get and print items and bill */
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
