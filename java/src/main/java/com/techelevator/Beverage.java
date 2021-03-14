package com.techelevator;

import java.math.BigDecimal;

public class Beverage extends VendableItem {
	public Beverage(String name, BigDecimal cost) {
		super(name,5,cost);
	}
	
	@Override
	public String getSound(){
		return "Glug Glug, Yum!";

	}

}
  