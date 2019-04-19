package com.stockmarket.StockMarketSimulator.model;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.stockmarket.StockMarketSimulator.exception.CompanyOutOfSharesException;

@Entity
public class Company {
	
	@Id
	@GeneratedValue
	private final int id;
	private static int lastId = 0;
	private String name;
	private double sharePrice;
	private double capital;
	private int sharesSold;
	
	@Transient
	private List<Share> shares;

	private Company(CompanyBuilder builder) {
		super();
		this.id = ++lastId;
		this.name = builder.name;
		this.sharePrice = builder.sharePrice;
		this.capital = builder.capital;
		this.sharesSold = builder.sharesSold;
		
		ipo(builder.shares); // Initial Public Offering -> to create the shares
		
	}

	private void ipo(int numberOfShares) {
		shares = new ArrayList<>(); // new list to hold the Share objects created
		
		for(int x = 0; x < numberOfShares; x++) 
			shares.add(new Share(this.id, this.sharePrice)); // create the chosen number of shares
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

	public List<Share> getShares() {
		return shares;
	}
	
	public int getNumberOfSharesAvailable() {
		return shares.size();
	}

	public double getSharePrice() {
		return sharePrice;
	}

	public void setSharePrice(double sharePrice) {
		this.sharePrice = sharePrice;
	}

	public double getCapital() {
		return capital;
	}

	public int getSharesSold() {
		return sharesSold;
	}
	
	public void getCompanyDetails() {
		System.out.println("COMAPNY ID: \t" + this.getId());
		System.out.println("COMPANY NAME: \t" + this.getName());
		System.out.println("SHARES: \t" + this.getNumberOfSharesAvailable());
		System.out.printf("SHARE PRICEL: \t" + "%.2f %n", this.getSharePrice());
		System.out.printf("CAPITAL: \t" + "%.2f %n", this.getCapital());
		System.out.println();
	}

	public Share sellShare() {
		if (shares.isEmpty()) {
			throw new CompanyOutOfSharesException("Company "+this.name+" has no shares left to sell."); // check if it's empty
		}
		
		sharesSold++; // increment sharesSold
		
		capital+=sharePrice; // increment capital by share price
		
		Share sold = shares.remove(0); // remove the first chair (ArrayList if not empty will always have item on index 0)
		sold.setPrice(sharePrice); // set price accordingly to current share price
		
		return sold; // return share
	}

	
	public static class CompanyBuilder{
		private String name;
		private int shares;
		private double sharePrice;
		private double capital;
		private int sharesSold;	
		
		public CompanyBuilder(String name) {
			super();
			this.name = name;
		}


		/**
		 * @param name the name to set
		 */
		public CompanyBuilder setName(String name) {
			this.name = name;
			return this;
		}


		/**
		 * @param shares the shares to set
		 */
		public CompanyBuilder setShares(int shares) {
			this.shares = shares;
			return this;
		}


		/**
		 * @param sharePrice the sharePrice to set
		 */
		public CompanyBuilder setSharePrice(double sharePrice) {
			this.sharePrice = sharePrice;
			return this;
		}


		/**
		 * @param capital the capital to set
		 */
		public CompanyBuilder setCapital(double capital) {
			this.capital = capital;
			return this;
		}


		/**
		 * @param sharesSold the sharesSold to set
		 */
		public CompanyBuilder setSharesSold(int sharesSold) {
			this.sharesSold = sharesSold;
			return this;
		}
		
		
		public Company build() {
			return new Company(this);
		}
		
		
		
	}
	
}