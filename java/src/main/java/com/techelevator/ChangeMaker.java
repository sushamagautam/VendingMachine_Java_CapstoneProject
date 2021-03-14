package com.techelevator;

import java.math.BigDecimal;

import com.techelevator.utils.InsufficientFundsException;

public class ChangeMaker {

	
	private BigDecimal currency;
	
	public ChangeMaker() {	
		//Set the currency to 0!
		currency = new BigDecimal(0);	
	} 
	
	public String MakeChange() {
			//convert change to an int so we can do penny math
			int change = (int) (currency.doubleValue()*100);
			
			//Divide change by 25 to get the quarters received
			//Store the remainder in change
			int quarters = change / 25;
			change = change % 25;
			//Divide change by 10 to get the dimes received
			//Store the remainder in change
			int dimes = change / 10;
			change = change % 10;
			//Divide change by 5 to get the nickels received
			//The remainder of this is the pennies! 
			int nickels = change / 5;
			change = change % 5;
			
			currency = new BigDecimal(0);
			
			return ("Your change is " + quarters +" quarters "+ dimes + " dimes " + nickels + " nickels and " + change+ " pennies" );
 		
	}
	public void addChange(BigDecimal addCurrency) {
		//Add some money!
		currency = currency.add(addCurrency);	
	}
	
	//method for insufficient funds
	public boolean spendChange(BigDecimal cost) throws InsufficientFundsException{
		//If we have enough to spend the cost, subtract it and return true
		//Otherwise throw an exception
		if (currency.compareTo(cost) >= 0) {    //if difference betn cost and your money is > 0, you're good.
		currency =	currency.subtract(cost);	//else throws exception
		return true;
		}
		throw new InsufficientFundsException(currency,cost);
	}
	
	public BigDecimal getCurrency() {
		return currency;
	}
	
	
	
}
