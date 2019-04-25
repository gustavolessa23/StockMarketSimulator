package com.stockmarket.StockMarketSimulator.exception;

public class InvestorHasInvestedInAllCompaniesException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6468896000823529563L;

	public InvestorHasInvestedInAllCompaniesException(String errorMessage) {
		super(errorMessage);
	}
	
}
