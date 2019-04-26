package com.stockmarket.StockMarketSimulator.setup;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stockmarket.StockMarketSimulator.model.Company;
import com.stockmarket.StockMarketSimulator.model.TradingDay;
import com.stockmarket.StockMarketSimulator.services.CompanyService;

@Component
public class CompanyGenerator {

	public static int numberOfCompanies = 100; //Number of companies to generate
	public static int minNumberOfShares = 500;
	public static int maxNumberOfShares = 1000;
	public static double minSharePrice = 10.00;
	public static double maxSharePrice = 100.00;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private StoredData sd; //Object that holds random names and other data

	private Random rG = new Random();



	/**
	 * Generates companies with random values and adds them to the companyList
	 */
	public List<Company> generateCompanies() {
		
		List<Company> companies = new ArrayList<>();

		Company.CompanyBuilder companyBuilder = new Company.CompanyBuilder(""); //Create a builder for companies

		int randomShift = rG.nextInt(numberOfCompanies); // to randomize the starting point of the companies names on the names list.

		for(int i=0;i<numberOfCompanies;i++) {

			String randomName = sd.companyName.get((i+randomShift)%numberOfCompanies); //get a name from the StoredData.java file
			int randomShares = minNumberOfShares+rG.nextInt(maxNumberOfShares-minNumberOfShares); //create a random number for a share between 500 and 1000
			double randomPrice = minSharePrice+(maxSharePrice-minSharePrice)*rG.nextDouble(); //create a random number for a share price between 10.00 and 100.00

			Company randomCompany = companyBuilder.
					setName(randomName).
					setShares(randomShares).
					setSharePrice(randomPrice).
					build(); //use company builder to assign values

			
			//randomCompany.getCompanyDetails(); //Prints out the companies details after being generated
			companies.add(randomCompany); 
		}

		return companies;
	}

}
