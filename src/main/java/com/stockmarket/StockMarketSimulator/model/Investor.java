package com.stockmarket.StockMarketSimulator.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Investor {
	
	@Id
	@GeneratedValue
	private final long id;
	private static long lastId = 0;
	private String name;
	private double budget;
	private int numberOfShares;
	private int numberOfCompanies;
	
	@Transient
	private Wallet wallet;


	private Investor(InvestorBuilder builder) {
		super();
		this.id = ++lastId;
		this.name = builder.name;
		this.budget = builder.budget;
		this.wallet = builder.wallet;
		this.numberOfCompanies = 0;
		this.numberOfShares = 0;

	}

	public long getId() {
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
	
	


	/**
	 * @return the numberOfShares
	 */
	public int getNumberOfShares() {
		numberOfShares = wallet.getAmountOfShares();
		return numberOfShares;
	}

	/**
	 * @return the numberOfCompanies
	 */
	public int getNumberOfCompanies() {
		numberOfCompanies = wallet.getAmountOfCompanies();
		return numberOfCompanies;
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