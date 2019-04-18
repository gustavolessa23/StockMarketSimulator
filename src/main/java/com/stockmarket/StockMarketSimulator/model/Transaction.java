package com.stockmarket.StockMarketSimulator.model;

import java.util.Date;

public class Transaction {
	
	private int companyId;
	private int investorId;
	private Date date;
	
	/**
	 * @return the companyId
	 */
	public int getCompanyId() {
		return companyId;
	}
	/**
	 * @param companyId the companyId to set
	 */
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	/**
	 * @return the investorId
	 */
	public int getInvestorId() {
		return investorId;
	}
	/**
	 * @param investorId the investorId to set
	 */
	public void setInvestorId(int investorId) {
		this.investorId = investorId;
	}
	
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	

}
