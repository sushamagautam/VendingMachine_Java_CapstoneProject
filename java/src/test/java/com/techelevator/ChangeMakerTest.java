package com.techelevator;
import java.math.BigDecimal;

import org.junit.*;

import com.techelevator.utils.InsufficientFundsException;
public class ChangeMakerTest {
	ChangeMaker cM;
	 
	@Test
	public void Test_1_CTor() {
		cM = new ChangeMaker();
		Assert.assertTrue(cM.getCurrency().equals(new BigDecimal(0)));
	}
	@Test
	public void Test_2_AddChange() {
		cM = new ChangeMaker();
		cM.addChange(new BigDecimal(5));
		Assert.assertTrue(cM.getCurrency().equals(new BigDecimal(5)));
	} 
	@Test
	public void Test_3_Spend() {
		cM = new ChangeMaker();
		cM.addChange(new BigDecimal(5));
		try {
			Assert.assertTrue(cM.spendChange((new BigDecimal(3.50))));
		}catch(InsufficientFundsException poorEx) {
			Assert.assertFalse("Insufficient Funds!",true);
		}
		Assert.assertTrue(cM.getCurrency().equals(new BigDecimal(1.50)));
	}
	@Test
	public void Test_3_Spend_Not_Enough() {
		cM = new ChangeMaker();
		cM.addChange(new BigDecimal(5));
		try {
			cM.spendChange((new BigDecimal(6)));
		}catch(InsufficientFundsException poorEx) {
			Assert.assertTrue(true);
		}
		Assert.assertTrue(cM.getCurrency().equals(new BigDecimal(5)));
	}
	@Test
	public void Test_4_GetChange() {
		cM = new ChangeMaker();
		cM.addChange(new BigDecimal(1.49));
		Assert.assertTrue(cM.MakeChange().equals("Your change is 5 quarters 2 dimes 0 nickels and 4 pennies"));
	}
	
}
