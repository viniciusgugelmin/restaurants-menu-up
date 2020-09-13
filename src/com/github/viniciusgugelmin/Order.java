package com.github.viniciusgugelmin;

import java.util.ArrayList;
import java.util.List;

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
	
	
}
