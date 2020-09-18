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
	
	public static Scanner toRead(String root) throws FileNotFoundException {

		File file = new File(root);
		Scanner inFile = new Scanner(file);
		
		return inFile;
	}
	
	public static PrintWriter toPrint(String root) throws IOException {
		
		FileWriter writeFile = new FileWriter(root);
		PrintWriter printFile = new PrintWriter(writeFile);
		
		return printFile;
	}
}
