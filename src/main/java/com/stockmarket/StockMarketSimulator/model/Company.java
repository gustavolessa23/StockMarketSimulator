package com.stockmarket.StockMarketSimulator.model;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.Transient;

import com.stockmarket.StockMarketSimulator.StockMarketSimulatorApplication;
import com.stockmarket.StockMarketSimulator.exception.CompanyOutOfSharesException;
import com.stockmarket.StockMarketSimulator.setup.CompanyGenerator;

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
		
		ipo(builder.shares); // Initial Public Offering -> to create the shares
		
	}
	

	private void ipo(int numberOfShares) {
		shares = new ArrayList<>(); // new list to hold the Share objects created
		
		for(int x = 0; x < numberOfShares; x++) 
			shares.add(new Share(this.id, this.sharePrice)); // create the chosen number of shares
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

	public Share sellShare() {
		if (shares.isEmpty()) {
			throw new CompanyOutOfSharesException("Company "+this.name+" has no shares left to sell."); // check if it's empty
		}else {

			
			sharesSold++; // increment sharesSold
			capital+=sharePrice; // increment capital by share price
	
			Share sold = shares.remove(0); // remove the first share (ArrayList if not empty will always have item on index 0)
			
			sold.setPrice(sharePrice); // set price accordingly to current share price
			
			increasePrice(); //increased price after every 10 shares sold
			this.setHasSoldShare(true);
			return sold; // return share
		}

	}
	
	public void increasePrice() {
		double newPrice = getSharePrice()+((getSharePrice()*2/100)); //increase price by 2%
		boolean tenSharesSold = getSharesSold()%10==0; //check if 10 shares were sold

		if(tenSharesSold) { 
			this.setSharePrice(newPrice);
		}
	}
	
	public void decreasePrice() {
		double newPrice = getSharePrice()-((getSharePrice()*2)/100); //decrease price by 2%
		
		if(this.getSharePrice()>=0.00) {
			this.setSharePrice(newPrice);
		}else if(this.getSharePrice()<=0){
			this.setSharePrice(0); //set price to 0 if it goes below zero !!!!!!!!NEED TO CHANGE THIS
		}
	}

	
	public static class CompanyBuilder{
		private String name;
		private int shares;
		private double sharePrice;
		private double capital;
		private int sharesSold;
		private boolean hasSoldShare;
		
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
		
		/**
		 * @param sold set the hasSoldShare
		 */
		public CompanyBuilder setHasSoldShare(boolean sold) {
			this.hasSoldShare = sold;
			return this;
		}
		
		
		public Company build() {
			return new Company(this);
		}
		
		
		
	}
	
}