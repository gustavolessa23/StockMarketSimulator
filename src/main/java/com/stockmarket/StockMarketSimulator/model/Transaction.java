package com.stockmarket.StockMarketSimulator.model;

import java.util.Date;

public class Transaction {
	private final int transactionId;
	private static int lastId = 0;
	private Company company;
	private Investor investor;
	private Date date;
	
	public Transaction(Company comp, Investor inv) {
		this.transactionId = ++lastId;
		this.company = comp;
		this.investor = inv;
		this.date = new Date();
	}
	
	
	public void getTransactionDetails() {
		System.out.println("----------TRANSATION----------");
		System.out.println("TRANSACTION ID: \t" + this.getTransactionId());
		System.out.println("COMPANY: \t" + this.getCompany().getId()+": "+this.getCompany().getName()+" ("+this.getCompany().getShares().size()+")");
		System.out.println("INVESTOR: \t" + this.getInvestor().getId()+": "+this.getInvestor().getName() +" ($"+this.getInvestor().getBudget()+")");
		System.out.printf("TRADED AT: " + "\t$ %.2f %n",this.getCompany().getSharePrice());

		System.out.println();
	}

	public int getTransactionId() {
		return transactionId;
	}
	
	/**
	 * @return the companyId
	 */
	public Company getCompany() {
		return company;
	}	

	/**
	 * @return the investorId
	 */
	public Investor getInvestor() {
		return investor;
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