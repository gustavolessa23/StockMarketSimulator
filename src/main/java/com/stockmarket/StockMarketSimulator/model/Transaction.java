package com.stockmarket.StockMarketSimulator.model;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
public class Transaction {
	private @Getter final int transactionId;
	private static int lastId = 0;
	private @Getter Company company;
	private @Getter Investor investor;
	private @Getter Date date;
	
	public Transaction(Company comp, Investor inv) {
		this.transactionId = ++lastId;
		this.company = comp;
		this.investor = inv;
		this.date = new Date();
	}
	
	private Transaction() {
		this.transactionId = -1;
	}
	
	
	public void getTransactionDetails() {
		System.out.println("----------TRANSATION----------");
		System.out.println("TRANSACTION ID: \t" + this.getTransactionId());
		System.out.println("COMPANY: \t" + this.getCompany().getId()+": "+this.getCompany().getName()+" ("+this.getCompany().getShares().size()+")");
		System.out.println("INVESTOR: \t" + this.getInvestor().getId()+": "+this.getInvestor().getName() +" ($"+this.getInvestor().getBudget()+")");
		System.out.printf("TRADED AT: " + "\t$ %.2f %n",this.getCompany().getSharePrice());

		System.out.println();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder()
		.append("----------TRANSATION----------\n")
		.append("TRANSACTION ID: \t" + this.getTransactionId())
		.append("COMPANY: \t" + this.getCompany().getId()+": "+this.getCompany().getName()+" ("+this.getCompany().getShares().size()+")")
		.append("INVESTOR: \t" + this.getInvestor().getId()+": "+this.getInvestor().getName() +" ($"+this.getInvestor().getBudget()+")")
		.append("TRADED AT: " + String.format("%.2f", this.getCompany().getSharePrice()));

		return sb.toString();
	}

}