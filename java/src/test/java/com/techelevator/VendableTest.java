package com.techelevator;
import java.math.BigDecimal;

import org.junit.*;


public class VendableTest {

		
		
		@Test
		public void Test_drink() {
			
		Beverage beverage;
		beverage = new Beverage("Coke", new BigDecimal("1.50"));
		
		Assert.assertTrue(beverage.getName().equals("Coke"));
		Assert.assertTrue(beverage.getSound().equals("Glug Glug, Yum!"));
		
		}
		  
		@Test
		
		public void Test_candy() {
			
			Candy candy;
			candy = new Candy("Hershey", new BigDecimal("1.75"));
			
			Assert.assertTrue(candy.getName().equals("Hershey"));
			Assert.assertTrue(candy.getSound().equals("Munch Munch, Yum!"));
		}
		
		@Test
		
		public void Test_chip() {
			
			Chip chip;
			chip = new Chip("Lays", new BigDecimal("1.50"));
			
			Assert.assertTrue(chip.getName().equals("Lays"));
			Assert.assertTrue(chip.getSound().equals("Crunch Crunch, Yum!"));
		}
		
		@Test
		
		public void Test_gum() {
			
			Gum gum;
			
			gum = new Gum("Mint", new BigDecimal("1.75"));
			
			Assert.assertTrue(gum.getName().equals("Mint"));
			Assert.assertTrue(gum.getSound().equals("Chew Chew, Yum!"));
		}
			
		
		
	

}
