package com.techelevator;

import java.math.BigDecimal;

public class Chip extends VendableItem {
	
	public Chip(String name, BigDecimal cost) {
		super(name,5,cost);
	}
	@Override
	public String getSound() {
		return "Crunch Crunch, Yum!";

	}


}
 