package com.techelevator.utils;

public class VendingMachineException extends Exception {

	public VendingMachineException(String message) {
		super(message);
	}
	public VendingMachineException() {
		super("an unspecified vending maching exception has occured");
	}
	
}
