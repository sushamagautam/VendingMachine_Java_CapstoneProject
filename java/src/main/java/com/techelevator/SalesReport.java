package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import com.techelevator.utils.InvalidItemTypeException;


public class SalesReport {
	//Make a hash map of all item names and how many times that are sold
	private Map<VendableItem,Integer> itemsSold = new HashMap<>();
	public SalesReport(Map<String,VendableItem> stockMap) {
		//go through all the stock to populate the list of items
		for(VendableItem item : stockMap.values()) {
			itemsSold.put(item, 0);
		} 
	}
	public void logSale(VendableItem item) throws InvalidItemTypeException{
		//If the items map contain the item we are trying to log of
		//Add 1 to the sales for that item!
		if(itemsSold.containsKey(item)) {
			itemsSold.put(item, itemsSold.get(item)+1);			
		}else {
			throw new InvalidItemTypeException(item.getName());
		}
	}
	public String printSalesReport() {
		File saleReport = new File( "Sales_Report_"+getFormatedDate()+".txt");
		  //saleReport = new File("log2.txt");
		try (PrintWriter salesWriter = new PrintWriter(saleReport)){
			saleReport.createNewFile();
			BigDecimal totalSalePrice = new BigDecimal(0);
			int totalSales = 0;
			//print the action with a time stamp
			for(VendableItem item : itemsSold.keySet()) {
				//get the amount of items sold for later use
				int soldNum = itemsSold.get(item);
				//write to file in form of "ITEMNAME|AMOUNTSOLD"
				salesWriter.println(item.getName()+"|"+soldNum);	
				//increase the amount of items sold
				totalSales+=soldNum;
				//Add the total price of all transactions for current item
				totalSalePrice =totalSalePrice.add(item.getCost().multiply(new BigDecimal(soldNum)));
			}
			//after printing all items pint total amount items sold and how much the total sales price was.
			salesWriter.printf("Total Sales: "+totalSales+" items, $%,.2f",totalSalePrice);		
		}
		catch(FileNotFoundException ex) {
			//call this if someone deleted salesreport.txt while the program was running!
			return(saleReport.getAbsolutePath() + " not found");
		}
		catch(IOException ex) {
			//something went wrong in file creation
			return("Failure to create "+saleReport.getAbsolutePath() );
		}
		return saleReport.getAbsolutePath();
	}
	private String getFormatedDate() {
		//This is far less cursed then what we were using
		
		//formatter basically creates the pattern we want the date displayed in
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
		//Get when it is right now and format it for output ez
		return LocalDateTime.now().format(formatter);
 
	}
} 
