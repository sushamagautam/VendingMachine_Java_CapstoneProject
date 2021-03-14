package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;

import com.techelevator.utils.InvalidItemTypeException;


public class SalesReportTest {
	
	
	@Test
	public void SalesReport_Test1() {
		Map<String, VendableItem> tempStock = new HashMap<>();
		VendableItem item = new Chip("Lays", new BigDecimal(1));
		
		tempStock.put("A1", item);
		SalesReport salesReport = new SalesReport(tempStock);
		
		try {
		salesReport.logSale(item);
		}
		catch(InvalidItemTypeException ex) {
			
		}
		
		String file = salesReport.printSalesReport();
		
		Assert.assertTrue(compareLog("Total Sales: 1 items, $1.00", file));
		
		
	}
	@Test
	
	public void SalesReport_Test2() {
		Map<String, VendableItem> tempStock = new HashMap<>();
		VendableItem item = new Chip("Lays", new BigDecimal(1));
		
		tempStock.put("A1", item);
		SalesReport salesReport = new SalesReport(tempStock);
		
		String file = salesReport.printSalesReport();
		
		Assert.assertTrue(compareLog("Total Sales: 0 items, $0.00", file));
		
	}

	public boolean compareLog(String expectedLog, String fileName) {
		try(Scanner logScanner = new Scanner(new File(fileName))){
			String currentLine = "";
			while(logScanner.hasNextLine()){
				currentLine = logScanner.nextLine();		
			};
			return currentLine.contains(expectedLog);
		}catch(FileNotFoundException fnfEx) {
			Assert.assertTrue(false);
		}
		return false;
	}
	
}
