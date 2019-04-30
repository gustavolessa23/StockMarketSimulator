package com.stockmarket.StockMarketSimulator.setup;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.stockmarket.StockMarketSimulator.model.Data;
import com.stockmarket.StockMarketSimulator.model.Investor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InvestorGenerator {

	public static int numberOfInvestors = 100; //Number of investors to generate	
	public static double minBudget = 1000.00;
	public static double maxBudget = 10000.00;

	@Autowired
	private StoredData sd; //Object that holds random names and other data
	
	@Autowired
	private  Data data;

	private Random rG = new Random();

	/**
	 * Generates investors with random values and adds them to the investorList
	 */	
	public List<Investor> generateInvestors() {

		List<Investor> investors = new ArrayList<>();

		Investor.InvestorBuilder investorBuilder = new Investor.InvestorBuilder(""); //Create a builder for investors

		int randomShift = rG.nextInt(numberOfInvestors);

		for(int i=0;i<numberOfInvestors;i++) {

			String randomName = sd.investorName.get((i+randomShift)%numberOfInvestors); 
			double randomBudget = data.round(minBudget+(maxBudget-minBudget)*rG.nextDouble(),2); //create a random number for a budget between 1000 and 10000

			Investor randomInvestor = investorBuilder
					.setName(randomName)
					.setBudget(randomBudget)
					.build(); //use investor builder to assign values

			//randomInvestor.getInvestorDetails(); //Prints out the investor details (including wallet details) after being generated
			investors.add(randomInvestor);

		}	
		return investors;
	}

}

