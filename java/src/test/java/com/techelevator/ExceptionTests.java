package com.techelevator;
import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import com.techelevator.utils.InsufficientFundsException;
import com.techelevator.utils.InvalidChoiceException;
import com.techelevator.utils.InvalidItemTypeException;
import com.techelevator.utils.OutOfStockException;
import com.techelevator.utils.VendingMachineException;
public class ExceptionTests {
	@Test
	public void test_Generic_Vend_Ex() {
		VendingMachineException ex = new VendingMachineException();
		Assert.assertTrue(ex.getMessage().equals("an unspecified vending maching exception has occured"));
	}
	@Test
	public void test_Inval_Choice_Ex() {
		InvalidChoiceException ex = new InvalidChoiceException("A1");
		Assert.assertTrue(ex.getMessage().equals("A1 is not a valid item slot!!"));
		Assert.assertTrue(ex.getSlot().equals("A1"));
	}
	@Test
	public void test_Out_Of_Stock_Ex() {
		OutOfStockException ex = new OutOfStockException("Testy-O's");
		Assert.assertTrue(ex.getMessage().equals("Testy-O's is out of stock!!"));
		Assert.assertTrue(ex.getName().equals("Testy-O's"));
	}
	@Test
	public void test_Insuffient_Funds_Ex() {
		InsufficientFundsException ex = new InsufficientFundsException(new BigDecimal(5),new BigDecimal(2));
		Assert.assertTrue(ex.getMessage().equals("Insufficiant Funds you need $2.00 and only have $5.00"));
		Assert.assertTrue(ex.getCurrency().equals(new BigDecimal(5)));
		Assert.assertTrue(ex.getCost().equals(new BigDecimal(2)));
	}
	@Test
	public void test_Inval_Type_Ex() {
		InvalidItemTypeException ex = new InvalidItemTypeException("Crab");
		Assert.assertTrue(ex.getMessage().equals("Crab is not a valid item type!!"));
		Assert.assertTrue(ex.getType().equals("Crab"));
	}
	
}
