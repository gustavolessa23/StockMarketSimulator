package com.stockmarket.StockMarketSimulator.model;


import java.text.DecimalFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Investor {
	
	@Id
	private final int id;
	private static int lastId = 0;
	private String name;
	private double budget;
	private int totalNumberOfSharesBought;
	
	@Transient
	private Wallet wallet;


	private Investor(InvestorBuilder builder) {
		super();
		this.id = ++lastId;
		this.name = builder.name;
		this.budget = builder.budget;
		this.wallet = builder.wallet;
	}
	
	private Investor() {
		super();
		this.id = 0;
		this.name = "";
		this.budget = 0;
		this.wallet = null;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the budget
	 */
	public double getBudget() {
		return budget;
	}

	/**
	 * @param budget the budget to set
	 */
	public void setBudget(double budget) {
		this.budget = budget;
	}

	/**
	 * @return the wallet
	 */
	public Wallet getWallet() {
		return wallet;
	}

	/**
	 * @param wallet the wallet to set
	 */
	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}
	
	public void getInvestorDetails() {
		DecimalFormat df = new DecimalFormat("#.00");
		
		System.out.println("----------INVESTOR----------");
		System.out.println("INVESTOR ID: \t" + this.getId());
		System.out.println("INVESTOR NAME: \t" + this.getName());
		System.out.println("BUDGET: $ " +  df.format(this.getBudget()));
		System.out.println("SHARES BOUGHT: " +  this.getTotalNumberOfSharesBought());
		this.getWallet().getWalletDetails();
		System.out.println();
	}
	
//	public void buyShare(double sharePrice) {
//		budget-=sharePrice; // decrement budget by share price
//		totalNumberOfSharesBought +=1;
//	}
	

	public void incrementSharesBought() {
		this.totalNumberOfSharesBought++;
	}

	public int getTotalNumberOfSharesBought() {
		return totalNumberOfSharesBought;
	}

	public void setTotalNumberOfSharesBought(int totalNumberOfSharesBought) {
		this.totalNumberOfSharesBought = totalNumberOfSharesBought;
	}

	public static class InvestorBuilder{
		private String name;
		private double budget;
		private Wallet wallet;

		
		public InvestorBuilder(String name) {
			super();
			this.name = name;
		}

		/**
		 * @param name the name to set
		 */
		public InvestorBuilder setName(String name) {
			this.name = name;
			return this;
		}

		/**
		 * @param shares the shares to set
		 */
		public InvestorBuilder setBudget(double budget) {
			this.budget = budget;
			return this;
		}

		/**
		 * @param sharePrice the sharePrice to set
		 */
		public InvestorBuilder setWallet(Wallet wallet) {
			this.wallet = wallet;
			return this;
		}
		
		public Investor build() {
			return new Investor(this);
		}
		
		
		
	}


	
}