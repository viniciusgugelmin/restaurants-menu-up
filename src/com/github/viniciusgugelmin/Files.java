package com.github.viniciusgugelmin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Files {
	
	private static String draftFile = "D:\\www\\restaurants-menu-up\\files\\draft\\draft.txt";
	private static String foodsFile = "D:\\www\\restaurants-menu-up\\files\\menu\\foods.txt";
	private static String drinksFile = "D:\\www\\restaurants-menu-up\\files\\menu\\drinks.txt";
	private static String winesFile = "D:\\www\\restaurants-menu-up\\files\\menu\\wines.txt";
	
	private static String foodsSplit = ";";
	private static String drinksSplit = "\t";
	private static String winesSplit = "\t";
	
	/* Actions */
	//
	public static Scanner toRead(String root) {
		
		/* Read file to scanner */
		//
		Scanner inFile = new Scanner("D:\\www\\restaurants-menu-up\\files\\draft\\draft.txt");
		//
		try {
			File file = new File(root);
			inFile = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: Can't read the file. [SRC: " + root + "]");
		}
		//
		return inFile;
	}
	//
	public static PrintWriter toPrint(String root, boolean clear) {
		
		/* Read file to print */
		//
		FileWriter writeFile;
		PrintWriter printFile = null;
		//
		try {
			writeFile = new FileWriter("D:\\www\\restaurants-menu-up\\files\\draft\\draft.txt", clear);
			printFile = new PrintWriter(writeFile);
		} catch (IOException e1) {
			System.out.println("ERROR: Can't read the draft file.");
		}
		//
		try {
			writeFile = new FileWriter(root, clear);
			printFile = new PrintWriter(writeFile);
		} catch (IOException e) {

			System.out.println("ERROR: Can't read the file. [SRC: " + root + "]");
		}
		//
		return printFile;
	}
	
	/* Get */
	//
	public static String getDraftFile() {
		return draftFile;
	}
	//
	public static String getFoodsFile() {
		return foodsFile;
	}
	//
	public static String getDrinksFile() {
		return drinksFile;
	}
	//
	public static String getWinesFile() {
		return winesFile;
	}
	//
	public static String getFoodsSplit() {
		return draftFile;
	}
	//
	public static String getDrinksSplit() {
		return drinksFile;
	}
	//
	public static String getWinesSplit() {
		return winesFile;
	}
}
