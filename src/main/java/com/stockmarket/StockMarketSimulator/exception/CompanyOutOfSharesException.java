package com.stockmarket.StockMarketSimulator.exception;

public class CompanyOutOfSharesException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CompanyOutOfSharesException(String errorMessage) {
		super(errorMessage);
	}

}
