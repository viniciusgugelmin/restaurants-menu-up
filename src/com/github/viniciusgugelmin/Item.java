package com.github.viniciusgugelmin;

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
	
	
}
