package com.techelevator.utils;

public class InvalidItemTypeException extends VendingMachineException {
	private String type;
	public InvalidItemTypeException(String type) {		
		super(type + " is not a valid item type!!");
		this.type = type;
	}
	public String getType() {
		return this.type;
	}
}
