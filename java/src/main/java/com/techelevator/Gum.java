package com.techelevator;

import java.math.BigDecimal;

public class Gum extends VendableItem {
	public Gum(String name, BigDecimal cost) {
		super(name,5,cost);
	}
	@Override
	public String getSound() {
		return "Chew Chew, Yum!";

	}

}
