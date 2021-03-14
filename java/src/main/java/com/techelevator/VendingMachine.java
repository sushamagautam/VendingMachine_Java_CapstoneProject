package com.techelevator;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

import com.techelevator.utils.InvalidChoiceException;
import com.techelevator.utils.OutOfStockException;
import com.techelevator.utils.VendingMachineException;

public class VendingMachine {
	private Map<String, VendableItem> stock;
	private LogMaker logWriter;
	private SalesReport saleRep;
	private ChangeMaker changeMachine;
	public VendingMachine() {
		try { 
			//Initialize stockReader temporarily then get the stock from it
			//Debated keeping as a private member but we are never going to call it again
			StockReader stockRead = new StockReader();
			stock = stockRead.getStock();
		}catch(IOException ioEx) {
			System.out.println("!!!Warning Failure To Properly Load Machine!!!");
		} 
		//init ChangeMaker and LogMaker 
		changeMachine = new ChangeMaker();
		logWriter = new LogMaker();
		saleRep = new SalesReport(stock);
	}
	public boolean FeedMoney(BigDecimal amount) {
		//Check if the amount being fed is positive and only has 2 decimals
		if(amount.doubleValue()>0 && amount.scale()<= 0) {
			//Get the currency before the Transaction for the log
			BigDecimal prevCurrency = changeMachine.getCurrency();
			changeMachine.addChange(amount);
			//Log the transaction
			logWriter.LogFeed(prevCurrency, changeMachine.getCurrency());
			return true;
		} 
		return false;
	}
	public String Vend(String slot) {
		//get the amount of currency before the transaction and store it for later use
		BigDecimal prevCurrency = changeMachine.getCurrency();
		//Get the item we are vending
		
		VendableItem itemToVend =  stock.get(slot);
		try {
			
			//make sure it exists
			if(itemToVend!=null) {
				//Spend change both checks the if we can afford the item and spends it if we can
				if(changeMachine.spendChange(itemToVend.getCost())&&itemToVend.decStock()) {		
					//Tell the LogMaker to create a log for the vend action we just completed.
					logWriter.LogVend(slot, itemToVend.getName(), prevCurrency, changeMachine.getCurrency());
					saleRep.logSale(itemToVend);
					//return the cost, balance remaining. Then play snack sound
					return String.format(itemToVend.getName() +" cost $%,.2f remaining balance $%,.2f\n" + itemToVend.getSound()
					,itemToVend.getCost(),changeMachine.getCurrency());								
				}
			}else {
				//this is kindof silly at this point probably should just remove this exception
				// since why would be both throw and catch an exception in the same spot 
				throw new InvalidChoiceException(slot);
			}
		}catch(OutOfStockException oosEx) {
			//If we don't have any items to vend return the currency to the customer
			changeMachine.addChange(itemToVend.getCost());
			return oosEx.getMessage();
		}catch(VendingMachineException Ex) {
			//inform user that they input an invalid Choice
			return Ex.getMessage();
		}
		//This should never be called but Vend gets mad if we don't try and return something
		return "A critical Error has occured all is lost/nThere should be no way to see this error\nas all failing routes return error messages";
	 
	}
	public String FinalizeTransaction() {
		//Get the prior currency amount
		BigDecimal prevCurrency = changeMachine.getCurrency();
		//Get the change message
		String finalizeMessage = changeMachine.MakeChange();
		//log the get ChangeAction
		logWriter.LogChange(prevCurrency, changeMachine.getCurrency());
		//return change message
		return finalizeMessage;
		
	}
	
	//Get all the current stock
	public Map<String,VendableItem> getCurrentStock(){
		return stock;
	}
	//get $$$
	public BigDecimal getCurrencyHeld() {
		return changeMachine.getCurrency();
	}
	public String printSalesReport() {
		
		return saleRep.printSalesReport()+ " has been created.";
	}
}
