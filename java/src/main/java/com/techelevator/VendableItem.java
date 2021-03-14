package com.techelevator;

import java.math.BigDecimal;

import com.techelevator.utils.OutOfStockException;

public abstract class VendableItem {
	
	private BigDecimal cost;
	private String name;
	private int stock;
	
	public BigDecimal getCost() {
		return cost;
	}

	public String getName() {
		return name;
	}

	public int getStock(){ 
		return stock;
	}
	
	//decrement in stock method
	public boolean decStock()  throws OutOfStockException{
		//If there is stock then dec it and return true
		//else cry about it to whoever called me
		if(stock > 0) {
			stock--; 
			return true;
		}else {
			throw new OutOfStockException(name);
		}
	}
	
	//CTOR
	public VendableItem(String name, int stock, BigDecimal cost) {
		this.cost = cost;
		this.name = name; 
		this.stock = stock;
	}
	
	public abstract String getSound();
} 
