package com.techelevator.utils;

public class OutOfStockException extends VendingMachineException{
	private String name;
	public OutOfStockException(String name) {		
		super(name + " is out of stock!!");
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
}
