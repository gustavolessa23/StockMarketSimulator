package com.stockmarket.StockMarketSimulator.setup;

import java.util.ArrayList;
import java.util.Random;

import com.stockmarket.StockMarketSimulator.model.Investor;
import com.stockmarket.StockMarketSimulator.model.TradingDay;
import com.stockmarket.StockMarketSimulator.model.Wallet;
import com.stockmarket.StockMarketSimulator.services.InvestorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InvestorGenerator {
	
	public static int numberOfInvestors = 100; //Number of investors to generate	
	public static ArrayList <Investor> investorList = new ArrayList<>(); //Array lists for adding the newly generated objects to call them (testing)
	
	@Autowired
	InvestorService investorService;
	
	@Autowired
	StoredData sd; //Object that holds random names and other data
	
	Random rG = new Random();
	
	/**
	 * Generates investors with random values and adds them to the investorList
	 */	
	public void generateInvestors() {
			
		Investor.InvestorBuilder investorBuilder = new Investor.InvestorBuilder(""); //Create a builder for investors
				
		for(int i=0;i<numberOfInvestors;i++) {
			
			Wallet radnomWallet = new Wallet(); //create a new wallet for each investor
			String randomName = sd.investorName.get(i); 
			double randomBudget = 1000.00+(2000.00-1000.00)*rG.nextDouble(); //create a random number for a budget between 1000 and 10000

			Investor randomInvestor = investorBuilder.
					setName(randomName).
					setBudget(randomBudget).
					setWallet(radnomWallet).build(); //use investor builder to assign values
		
			//randomInvestor.getInvestorDetails(); //Prints out the investor details (including wallet details) after being generated
			investorService.addInvestor(randomInvestor);
			investorList.add(randomInvestor);
		}	
	};
}
