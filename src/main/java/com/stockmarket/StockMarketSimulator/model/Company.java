package com.stockmarket.StockMarketSimulator.model;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.Transient;


@Entity
public class Company {
	
//	@Id
//	@GeneratedValue
	@Id
	private final int id;
	private static int lastId = 0;
	private String name;
	private double sharePrice;
	@Column(scale=2)
	private double capital;
	private int sharesSold;
	private int initialShares;
	private double initialCapital;
	private double initialSharePrice;
	
	@Transient
	private boolean hasSoldShare;
	
	@Transient
	private List<Share> shares;

	private Company(CompanyBuilder builder) {
		super();
		this.id = ++lastId;
		this.name = builder.name;
		this.sharePrice = builder.sharePrice;
		this.capital = builder.capital;
		this.sharesSold = builder.sharesSold;
		this.hasSoldShare = builder.hasSoldShare;
		this.initialCapital = builder.initialCapital;
		this.initialSharePrice = builder.initialSharePrice;
		this.initialShares = builder.initialShares;
		this.shares = new ArrayList<>(); 
		
		
		for(int x = 0; x < builder.shares; x++) 
			shares.add(new Share(this.id, this.sharePrice)); 
		//ipo(builder.shares); // Initial Public Offering -> to create the shares
		
	}
	
	private Company() {
		super();
		this.id = 0;
		this.name = "";
		this.sharePrice = 0;
		this.capital = 0;
		this.sharesSold = 0;
		this.hasSoldShare = false;
		this.initialCapital = 0;
		this.initialSharePrice = 0;
		this.initialShares = 0;
		this.shares = new ArrayList<>();
		
		ipo(this.getShares().size()); // Initial Public Offering -> to create the shares

	}
	

	private void ipo(int numberOfShares) {
	//	shares = new ArrayList<>(); // new list to hold the Share objects created
		
		for(int x = 0; x < numberOfShares; x++) 
			shares.add(new Share(this.id, this.sharePrice)); // create the chosen number of shares
	}
	
	

	/**
	 * @return the initialShares
	 */
	public int getInitialShares() {
		return initialShares;
	}

	/**
	 * @return the initialCapital
	 */
	public double getInitialCapital() {
		return initialCapital;
	}

	/**
	 * @return the initialSharePrice
	 */
	public double getInitialSharePrice() {
		return initialSharePrice;
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
	
	public void setHasSoldShare(boolean soldShare) {
		this.hasSoldShare = soldShare;
	}

	public double getCapital() {
		return capital;
	}

	public int getSharesSold() {
		return sharesSold;
	}
	
	public boolean getHasSoldShare() {
		return hasSoldShare;
	}
	
	public void getCompanyDetails() {
		DecimalFormat df = new DecimalFormat("#.00");
		
		System.out.println("----------COMPANY----------");
		System.out.println("COMPANY ID: \t" + this.getId());
		System.out.println("COMPANY NAME: \t" + this.getName());
		System.out.println("CAPITAL: $" + df.format(this.getCapital()));
		System.out.println("SHARE DETAILS");
		System.out.println("\tAvailable: " + this.getNumberOfSharesAvailable());
		System.out.println("\tSold: " + this.getSharesSold());
		System.out.println("\tPrice: $" + df.format(this.getSharePrice()));	
		System.out.println();
	}

	
	public void incrementSharesSold() {
		this.sharesSold++;
	}
	
	public void incrementCapital(double amount) {
		this.capital = this.capital+amount;
	}

	public static class CompanyBuilder{
		private String name;
		private int shares;
		private double sharePrice;
		private double capital;
		private int sharesSold;
		private boolean hasSoldShare;
		private int initialShares;
		private double initialCapital;
		private double initialSharePrice;
		
		public CompanyBuilder(String name) {
			super();
			this.name = name;
			this.sharePrice = 0;
			this.capital = 0;
			this.sharesSold = 0;
			this.hasSoldShare = false;
			this.initialCapital = 0;
			this.initialSharePrice = 0;
			this.initialShares = 0;
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
			this.initialShares = shares;
			this.shares = shares;
			return this;
		}


		/**
		 * @param sharePrice the sharePrice to set
		 */
		public CompanyBuilder setSharePrice(double sharePrice) {
			this.initialSharePrice = sharePrice;
			this.sharePrice = sharePrice;
			return this;
		}


		/**
		 * @param capital the capital to set
		 */
		public CompanyBuilder setCapital(double capital) {
			this.initialCapital = capital;
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
		
		/**
		 * @param sold set the hasSoldShare
		 */
		public CompanyBuilder setHasSoldShare(boolean sold) {
			this.hasSoldShare = sold;
			return this;
		}
		
		public CompanyBuilder setInitialCapital(double initialCapital) {
			this.initialCapital = initialCapital;
			return this;
		}
		
		public CompanyBuilder setInitialInitialSharePrice(double initialSharePrice) {
			this.initialSharePrice = initialSharePrice;
			return this;
		}
		
		public CompanyBuilder setInitialShares(int initialShares) {
			this.initialShares = initialShares;
			return this;
		}
		
		public Company build() {
			return new Company(this);
		}
		
		
		
	}
	
}