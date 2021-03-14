package com.techelevator.utils;

import java.math.BigDecimal;

public class InsufficientFundsException extends VendingMachineException {

	private BigDecimal currency, cost;
	public InsufficientFundsException(BigDecimal currency, BigDecimal cost) {		
		super(String.format("Insufficiant Funds you need $%,.2f and only have $%,.2f", cost, currency));
		this.currency = currency;
		this.cost = cost;
	}
	public BigDecimal getCost() {
		return this.cost;
	}
	public BigDecimal getCurrency() {
		return this.currency;
	}
}
 