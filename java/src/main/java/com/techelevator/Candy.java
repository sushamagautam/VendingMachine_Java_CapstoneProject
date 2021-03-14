package com.techelevator;

import java.math.BigDecimal;

public class Candy extends VendableItem {
	
	public Candy(String name, BigDecimal cost) {
		super(name,5,cost);
	}
	@Override
	public String getSound() {
		return "Munch Munch, Yum!";

	}

 
}
