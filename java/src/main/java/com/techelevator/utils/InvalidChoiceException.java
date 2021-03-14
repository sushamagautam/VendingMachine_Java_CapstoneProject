package com.techelevator.utils;

public class InvalidChoiceException extends VendingMachineException {
	private String slot;
	public InvalidChoiceException(String slot) {		
		super(slot + " is not a valid item slot!!");
		this.slot = slot;
	}
	public String getSlot() {
		return this.slot;
	}
}
