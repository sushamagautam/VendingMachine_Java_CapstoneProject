package com.techelevator;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

public class VendingMachineTest {

	 
	VendingMachine vm;

	@Test
	public void feedMoneyTest() {
		VendingMachine vm = new VendingMachine();
		
		Assert.assertTrue(vm.getCurrencyHeld().equals(new BigDecimal(0)));
		
		vm.FeedMoney(new BigDecimal(5));
		
		Assert.assertTrue(vm.getCurrencyHeld().equals(new BigDecimal(5)));
		
	}
	
	@Test
	public void vendTest1() {
		
		VendingMachine vm = new VendingMachine();
		
		vm.FeedMoney(new BigDecimal(5));
		Assert.assertTrue(vm.Vend("A1").equals("Potato Crisps cost $3.05 remaining balance $1.95\nCrunch Crunch, Yum!"));
		
	}
	
	@Test
	public void vendTest2() {
		
		VendingMachine vm = new VendingMachine();
		
		vm.FeedMoney(new BigDecimal(5));
		Assert.assertTrue(vm.Vend("F5").equals("F5 is not a valid item slot!!"));
		
	}
	@Test
	public void vendTest3() {
		
		VendingMachine vm = new VendingMachine();
		Assert.assertTrue(vm.Vend("A1").equals("Insufficiant Funds you need $3.05 and only have $0.00"));
		
	} 
	
	@Test
	public void vendTest4() {
		
		VendingMachine vm = new VendingMachine();
		
		vm.FeedMoney(new BigDecimal(100));
		vm.Vend("A1");
		vm.Vend("A1");
		vm.Vend("A1");
		vm.Vend("A1");
		vm.Vend("A1");
		Assert.assertTrue(vm.Vend("A1").equals("Potato Crisps is out of stock!!"));
		
	}
	
	@Test
	public void vendTest5() {
		
		VendingMachine vm = new VendingMachine();
		
		vm.FeedMoney(new BigDecimal(5));
		vm.Vend("A1");
	
		Assert.assertTrue(vm.FinalizeTransaction().equals("Your change is 7 quarters 2 dimes 0 nickels and 0 pennies"));
	} 
	
}
