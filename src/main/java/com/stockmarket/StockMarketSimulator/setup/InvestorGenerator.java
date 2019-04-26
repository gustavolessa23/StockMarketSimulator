<<<<<<< HEAD
package com.stockmarket.StockMarketSimulator.setup;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.stockmarket.StockMarketSimulator.model.Data;
import com.stockmarket.StockMarketSimulator.model.Investor;
import com.stockmarket.StockMarketSimulator.model.TradingDay;
import com.stockmarket.StockMarketSimulator.model.Wallet;
import com.stockmarket.StockMarketSimulator.services.InvestorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InvestorGenerator {

<<<<<<< HEAD
	public static int numberOfInvestors = 10; //Number of investors to generate	
	public static double minBudget = 1000.00;
	public static double maxBudget = 10000.00;

	@Autowired
	StoredData sd; //Object that holds random names and other data

	Random rG = new Random();
=======
	public static int numberOfInvestors = 100; //Number of investors to generate	
	public static double minBudget = 1000.00;
	public static double maxBudget = 10000.00;

	@Autowired
	private StoredData sd; //Object that holds random names and other data

	private @Autowired Data data;
	private Random rG = new Random();
>>>>>>> branch 'master' of https://github.com/gustavolessa23/StockMarketSimulator.git

	/**
	 * Generates investors with random values and adds them to the investorList
	 */	
	public List<Investor> generateInvestors() {

		List<Investor> investors = new ArrayList<>();

		Investor.InvestorBuilder investorBuilder = new Investor.InvestorBuilder(""); //Create a builder for investors

		int randomShift = rG.nextInt(numberOfInvestors);

		for(int i=0;i<numberOfInvestors;i++) {

			Wallet emptyWallet = new Wallet(); //create a new wallet for each investor
			String randomName = sd.investorName.get((i+randomShift)%numberOfInvestors); 
<<<<<<< HEAD
			double randomBudget = minBudget+(maxBudget-minBudget)*rG.nextDouble(); //create a random number for a budget between 1000 and 10000
=======
			double randomBudget = data.round(minBudget+(maxBudget-minBudget)*rG.nextDouble(),2); //create a random number for a budget between 1000 and 10000
>>>>>>> branch 'master' of https://github.com/gustavolessa23/StockMarketSimulator.git

			Investor randomInvestor = investorBuilder
					.setName(randomName)
					.setBudget(randomBudget)
					.setWallet(emptyWallet)
					.build(); //use investor builder to assign values

			//randomInvestor.getInvestorDetails(); //Prints out the investor details (including wallet details) after being generated
			investors.add(randomInvestor);

		}	
		return investors;
	}
<<<<<<< HEAD
=======
package com.stockmarket.StockMarketSimulator.setup;

import java.util.ArrayList;
import java.util.List;
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
	public static List <Investor> investorList; //Array lists for adding the newly generated objects to call them (testing)
	
	@Autowired
	InvestorService investorService;
	
	@Autowired
	StoredData sd; //Object that holds random names and other data
	
	Random rG = new Random();
	
	public InvestorGenerator() {
		investorList = new ArrayList<>();
	}
	
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
		//investorList = investorService.getAllInvestors();
	}

>>>>>>> branch 'erics_playground' of https://github.com/gustavolessa23/StockMarketSimulator.git
}
<<<<<<< HEAD
=======
}
>>>>>>> branch 'master' of https://github.com/gustavolessa23/StockMarketSimulator.git
=======

>>>>>>> branch 'erics_playground' of https://github.com/gustavolessa23/StockMarketSimulator.git
