package com.github.viniciusgugelmin;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Item {
	private String name;
	private double price;
	private int quantity;
	private String note;
	
	// Construct
	//
	public Item() {
		
	}
	//
	public Item(String name, double price) {
		this.name = name;
		this.price = price;
	}
	//
	public Item(String name, String price) {
		this.name = name;
		this.price = Double.parseDouble(price.replaceAll(",", "."));
	}
	//
	public Item(String name, double price, int quantity, String note) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.note = note;
	}
	
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
