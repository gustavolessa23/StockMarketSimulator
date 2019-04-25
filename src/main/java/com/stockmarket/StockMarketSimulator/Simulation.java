package com.stockmarket.StockMarketSimulator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stockmarket.StockMarketSimulator.model.Company;
import com.stockmarket.StockMarketSimulator.model.Data;
import com.stockmarket.StockMarketSimulator.model.Investor;
import com.stockmarket.StockMarketSimulator.model.TradingDay;
import com.stockmarket.StockMarketSimulator.services.CompanyService;
import com.stockmarket.StockMarketSimulator.services.InvestorService;
import com.stockmarket.StockMarketSimulator.setup.CompanyGenerator;
import com.stockmarket.StockMarketSimulator.setup.InvestorGenerator;

@Component
public class Simulation {
	
	
	@Autowired
	CompanyService companyService; 
	
	@Autowired
	InvestorService investorService;
	
	@Autowired
	TradingDay td;
	
	@Autowired
	Data data;

	
	public void start() {
	
		companyService.populateCompanies();
		investorService.populateInvestors();

		td.trade(data.getCompanies(), data.getInvestors()); //run the trade
		
		
		System.out.println("\n\n----------REPORT----------");
		System.out.println("\nCOMPANIES:");
		System.out.println("Highest capital:");
		for(Company c : td.getHighestCapital()) {
			System.out.println(c.getId()+" - Name: "+c.getName()+" - Capital: "+c.getCapital());
		}
		
		System.out.println("\nLowest capital:");
		for(Company c : td.getLowestCapital()) {
			System.out.println(c.getId()+" - Name: "+c.getName()+" - Capital: "+c.getCapital());
		}
		
		System.out.println("\n\nINVESTORS");
		
		System.out.println("Highest number of shares:");
		for(Investor i : td.getHighestNumberOfShares()) {
			System.out.println(i.getId()+" - Name: "+i.getName()+" - Shares: "+i.getTotalNumberOfSharesBought());
		}
		
		System.out.println("\nLowest number of shares:");
		for(Investor i : td.getLowestNumberOfShares()) {
			System.out.println(i.getId()+" - Name: "+i.getName()+" - Shares: "+i.getTotalNumberOfSharesBought());
		}
		
		
		System.out.println("\nHighest number of companies:");
		for(Investor i : td.getHighestNumberOfCompanies()) {
			System.out.println(i.getId()+" - Name: "+i.getName()+" - Companies: "+investorService.getAmountOfCompaniesInvestedIn(i));
		}
		
		System.out.println("\nLowest number of companies:");
		for(Investor i : td.getLowestNumberOfCompanies()) {
			System.out.println(i.getId()+" - Name: "+i.getName()+" - Companies: "+investorService.getAmountOfCompaniesInvestedIn(i));
		}
		
		System.out.println("\n\nTotal number of transactions: "+td.getTotalNumberOfTransactions());
		
	}
	


}
