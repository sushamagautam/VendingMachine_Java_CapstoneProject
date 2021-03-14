package com.techelevator;

import java.math.BigDecimal;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class StockReaderTest {
		
		StockReader stock;
		
		@Test
		public void StockReader_Test1() {
			
			
			try{
				stock = new StockReader();
				

			}
		catch(Exception ex){
			
		}
	
			
			Assert.assertTrue(stock != null);
			Map<String, VendableItem> tempStock = stock.getStock();
			VendableItem vendItem = tempStock.get("C2");
			Assert.assertTrue(vendItem.getName().equals("Dr. Salt"));
			Assert.assertEquals(vendItem.getCost() , (new BigDecimal("1.50")));
			Assert.assertTrue(vendItem.getSound().equals("Glug Glug, Yum!"));
			
			}
  
		
		@Test
		public void StockReader_Test2() {
			
			
			try{
				stock = new StockReader();
				

			}
		catch(Exception ex){
			
		}
	

			Map<String, VendableItem> tempStock = stock.getStock();
			VendableItem vendItem = tempStock.get("A2");
			Assert.assertTrue(vendItem.getName().equals("Stackers"));
			Assert.assertEquals(vendItem.getCost() , (new BigDecimal("1.45")));
			Assert.assertTrue(vendItem.getSound().equals("Crunch Crunch, Yum!"));
			
			}
		
		
		@Test
		public void StockReader_Test3() {
			
			
			try{
				stock = new StockReader();
				

			}
		catch(Exception ex){
			
		}
	

			Map<String, VendableItem> tempStock = stock.getStock();
			VendableItem vendItem = tempStock.get("D4");
			Assert.assertTrue(vendItem.getName().equals("Triplemint"));
			Assert.assertEquals(vendItem.getCost() , (new BigDecimal("0.75")));
			Assert.assertTrue(vendItem.getSound().equals("Chew Chew, Yum!"));
			
			} 
		
} 
