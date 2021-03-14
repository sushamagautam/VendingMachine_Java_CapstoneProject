package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.techelevator.utils.InvalidItemTypeException;

public class StockReader {
	private File stockFile;
	public StockReader() throws IOException{
		stockFile = new File("vendingmachine.csv");
		if(!stockFile.exists()) {
			throw new IOException();
		}
	} 
	public Map<String,VendableItem> getStock(){
		//Create a map to temporarily store the Stock items read from the file
		Map<String,VendableItem> tempStock = new HashMap<>();
		
		//Create a new scanner reading the stockfile
		try(Scanner stockScan = new Scanner(stockFile)){
			//as long are there is more stock in the file
			while(stockScan.hasNextLine()) {	
				
				//Split the next line into the parts of a vendable object
				String[] vendComponents = 	stockScan.nextLine().split("\\|");
				
				//What is the slot we are storing the object in example "8B" "5F"
				String slot = vendComponents[0];				
				String name = vendComponents[1];
				//Here we are converting the string value to a double before making the bigDecimal
				//as you cannot convert directly from a string to a big decimal
				//We are using BigDecimal because they are more accurate than floats
				BigDecimal cost = new BigDecimal(vendComponents[2]);
				//The type of object we are creating
				String type = vendComponents[3];
				
				
				//Make a new object based on what type it is, if the type
				//is invalid throw an Invalid item Type exception
				//Each item start
				if(type.equals("Candy")) {
					tempStock.put(slot,new Candy(name,cost));
				}else if(type.equals("Drink")) {
					tempStock.put(slot,new Beverage(name,cost));
				}else if(type.equals("Gum")) {
					tempStock.put(slot,new Gum(name,cost));
				}else if(type.equals("Chip")) {
					tempStock.put(slot,new Chip(name,cost));
				}else {
					throw new InvalidItemTypeException(type);
				}
			}
		}catch(FileNotFoundException fnfEx){
			System.out.println(stockFile.getAbsolutePath() + " is not a valid file");
		}catch(InvalidItemTypeException invalEx) {
			System.out.println(invalEx.getMessage());
		}
		return tempStock;
	}
}
