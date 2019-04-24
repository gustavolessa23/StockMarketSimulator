package com.stockmarket.StockMarketSimulator.setup;

import java.util.ArrayList;
import java.util.Random;

import com.stockmarket.StockMarketSimulator.model.Company;
import com.stockmarket.StockMarketSimulator.model.TradingDay;

public class CompanyGenerator {

	public static int numberOfCompanies = 100; //Number of companies to generate
	public static ArrayList <Company> companyList = new ArrayList<>();
	
	StoredData sd = new StoredData(); //Object that holds random names and other data
	TradingDay td = new TradingDay();
	Random rG = new Random();
	
	/**
	 * Generates companies with random values and adds them to the companyList
	 */
	public void generateCompanies() {
		
	Company.CompanyBuilder companyBuilder = new Company.CompanyBuilder(""); //Create a builder for companies
				
		for(int i=0;i<numberOfCompanies;i++) {
			
			String randomName = sd.companyName.get(i); //get a name from the StoredData.java file
			int randomShares = 500+rG.nextInt(500); //create a random number for a share between 500 and 1000
			double randomPrice = 10.00+(100.00-10.00)*rG.nextDouble(); //create a random number for a share price between 10.00 and 100.00

			Company randomCompany = companyBuilder.
					setName(randomName).
					setShares(randomShares).
					setSharePrice(randomPrice).
					setCapital(0.00).
					setSharesSold(0).
					setHasSoldShare(false).
					build(); //use company builder to assign values
	
			//randomCompany.getCompanyDetails(); //Prints out the companies details after being generated
			companyList.add(randomCompany); 
		}
	}
	
}
