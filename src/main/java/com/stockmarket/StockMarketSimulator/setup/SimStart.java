package com.stockmarket.StockMarketSimulator.setup;

import com.stockmarket.StockMarketSimulator.*;
import com.stockmarket.StockMarketSimulator.model.Company;
import com.stockmarket.StockMarketSimulator.model.Investor;
import com.stockmarket.StockMarketSimulator.model.Wallet;

import java.util.ArrayList;
import java.util.Random;

public class SimStart {
	
	public static int numberOfCompanies = 100; //Number of companies to generate
	public static int numberOfInvestors = 100; //Number of investors to generate
	
	public static ArrayList <Company> companyList = new ArrayList<>();
	public static ArrayList <Investor> investorList = new ArrayList<>(); //Array lists for adding the newly generated objects to call them

	StoredData sd = new StoredData(); //Object that holds random names and other data
	Random rG = new Random();
	
	/**
	 * Generates companies with random values and adds them to the companyList
	 */
	public void generateCompanies() {
		
	Company.CompanyBuilder companyBuilder = new Company.CompanyBuilder(""); //Create a builder for companies
				
		for(int i=0;i<numberOfCompanies;i++) {
			
			String randomName = sd.companyName.get(i%13); //get a name from the StoredData.java file
			int randomShares = 500+rG.nextInt(500); //create a random number for a share between 500 and 1000
			double randomPrice = 10.00+(100.00-10.00)*rG.nextDouble(); //create a random number for a share price between 10.00 and 100.00
			
			Company randomCompany = companyBuilder.
					setName(randomName).
					setShares(randomShares).
					setSharePrice(randomPrice).
					setCapital(0.00).
					setSharesSold(0).build(); //use company builder to assign values
	
			//randomCompany.getCompanyDetails(); //Prints out the companies details after being generated
			companyList.add(randomCompany); 
		}
	}
	
	/**
	 * Generates investors with random values and adds them to the investorList
	 */	
	public void generateInvestors() {
			
		Investor.InvestorBuilder investorBuilder = new Investor.InvestorBuilder(""); //Create a builder for investors
				
		for(int i=0;i<numberOfInvestors;i++) {
			
			Wallet radnomWallet = new Wallet(); //create a new wallet for each investor
			String randomName = sd.investorName.get(i%13); 
			double randomBudget = 1000.00+(10000.00-1000.00)*rG.nextDouble(); //create a random number for a budget between 1000 and 10000

			Investor randomInvestor = investorBuilder.
					setName(randomName).
					setBudget(randomBudget).
					setWallet(radnomWallet).build(); //use investor builder to assign values
			
			//randomInvestor.getInvestorDetails(); //Prints out the investor details (including wallet details) after being generated
			
			investorList.add(randomInvestor);
		}
		
		
		
		/**
		 * For testing adding a share to the wallet
		 */
		companyList.get(0).getCompanyDetails();
		investorList.get(99).getInvestorDetails();
		
		System.out.println("----------------------AFTER-------------------------\n");
		
		for(int i=0;i<10; i++) {
			investorList.get(99).getWallet().addShare(companyList.get(0).sellShare()); //get the investor ID 100, get his wallet, add shares from company ID 1 (sold - update wallet)
			investorList.get(99).buyShare(companyList.get(0).getSharePrice()); //get the investor ID 100, buy his share for the share price of company 1 share (update budget)
		}
		
		companyList.get(0).getCompanyDetails();
		investorList.get(99).getInvestorDetails();
		
	
	};
	
}