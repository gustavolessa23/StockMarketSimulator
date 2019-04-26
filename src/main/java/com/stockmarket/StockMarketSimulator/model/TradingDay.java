package com.stockmarket.StockMarketSimulator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.stockmarket.StockMarketSimulator.StockMarketSimulatorApplication;
import com.stockmarket.StockMarketSimulator.exception.CompanyOutOfSharesException;
import com.stockmarket.StockMarketSimulator.exception.InvestorHasInvestedInAllCompaniesException;
import com.stockmarket.StockMarketSimulator.exception.InvestorOutOfFundsException;
import com.stockmarket.StockMarketSimulator.services.CompanyService;
import com.stockmarket.StockMarketSimulator.services.InvestorService;
import com.stockmarket.StockMarketSimulator.services.TransactionService;
import com.stockmarket.StockMarketSimulator.setup.CompanyGenerator;
import com.stockmarket.StockMarketSimulator.setup.InvestorGenerator;
import org.springframework.beans.factory.annotation.Autowired;


@Component
public class TradingDay {

	@Autowired
	CompanyService companyService;

	@Autowired
	InvestorService investorService;
	
	@Autowired
	TransactionService transactionService;

	@Autowired
	private Data data; 

	public void trade() {
		trade(data.getCompanies(), data.getInvestors());
	}

	/*
	 * the core functionality method
	 */
	public void trade(List<Company> companyList, List<Investor>invList) {
		
		while(simulationCanContinue()) {

			//chooses a random investor
			Investor randomInvestor = investorService.getRandomInvestor();
			
			//get random company that is unique for chosen investor, if existent, or a random company
			List<Integer> companyIds = investorService.getDesirableCompaniesForInvestor(randomInvestor);
			
			boolean transactionDone = transactionService.tryTransaction(randomInvestor, companyIds);
			
			if(!transactionDone) {
				companyIds = investorService.getCompaniesIds(randomInvestor);
				transactionDone = transactionService.tryTransaction(randomInvestor, companyIds);
			}
		}
		System.out.println("Simulation ended");
		System.out.println("INVESTORS: "+invList.size());
		System.out.println("COMPANIES: "+companyList.size());
	}


	public boolean simulationCanContinue() {
		Investor highestBudget = investorService.getHighestBudget();
		Company cheapestShare = companyService.getCheapestAvailableShare();
		
		if(cheapestShare == null) return false;
		
		if(cheapestShare.getSharePrice() > highestBudget.getBudget()) return false;
		
		return true;
	}
	
	public List<Company> getHighestCapital(){
		List<Company> companies = new ArrayList<>();
		
		companies.add(companyService.getAllCompanies().get(0));
		
		for (int x = 1; x<companyService.getAllCompanies().size(); x++) {
			Company current = companyService.getAllCompanies().get(x);
			if (current.getCapital()>companies.get(0).getCapital()) {
				companies.clear();
				companies.add(current);
			} else if (current.getCapital()==companies.get(0).getCapital()) {
				companies.add(current);
			}
		}
		return companies;
	}
	
	public List<Company> getLowestCapital(){
		List<Company> companies = new ArrayList<>();
		
		companies.add(companyService.getAllCompanies().get(0));
		
		for (int x = 1; x<companyService.getAllCompanies().size(); x++) {
			Company current = companyService.getAllCompanies().get(x);
			if (current.getCapital()<companies.get(0).getCapital()) {
				companies.clear();
				companies.add(current);
			} else if (current.getCapital()==companies.get(0).getCapital()) {
				companies.add(current);
			}
		}
		
		return companies;
	}
	
	
	public List<Investor> getHighestNumberOfShares(){
		List<Investor> investors = new ArrayList<>();
		
		investors.add(investorService.getAllInvestors().get(0));
		
		for (int x = 1; x<investorService.getAllInvestors().size(); x++) {
			Investor current = investorService.getAllInvestors().get(x);
			if (current.getTotalNumberOfSharesBought()>investors.get(0).getTotalNumberOfSharesBought()) {
				investors.clear();
				investors.add(current);
			} else if (current.getTotalNumberOfSharesBought()==investors.get(0).getTotalNumberOfSharesBought()) {
				investors.add(current);
			}
    }
		return investors;
	}

	public List<Investor> getLowestNumberOfShares(){
		List<Investor> investors = new ArrayList<>();
		
		investors.add(investorService.getAllInvestors().get(0));
		
		for (int x = 1; x<investorService.getAllInvestors().size(); x++) {
			Investor current = investorService.getAllInvestors().get(x);
			if (current.getTotalNumberOfSharesBought()<investors.get(0).getTotalNumberOfSharesBought()) {
				investors.clear();
				investors.add(current);
			} else if (current.getTotalNumberOfSharesBought()==investors.get(0).getTotalNumberOfSharesBought()) {
				investors.add(current);
			}
		}
		return investors;
	}
	
	public List<Investor> getHighestNumberOfCompanies(){
		List<Investor> investors = new ArrayList<>();
		
		investors.add(investorService.getInvestorById(0));
		
		for (int x = 1; x<investorService.getAllInvestors().size(); x++) {
			Investor current = investorService.getAllInvestors().get(x);
			if (investorService.getAmountOfCompaniesInvestedIn(current)>investorService.getAmountOfCompaniesInvestedIn(investors.get(0))) {
				investors.clear();
				investors.add(current);
			} else if (investorService.getAmountOfCompaniesInvestedIn(current)==investorService.getAmountOfCompaniesInvestedIn(investors.get(0))) {
				investors.add(current);
			}
		}
		return investors;
	}
	
	public List<Investor> getLowestNumberOfCompanies(){
		List<Investor> investors = new ArrayList<>();
		
		investors.add(investorService.getInvestorById(0));
		
		for (int x = 1; x<investorService.getAllInvestors().size(); x++) {
			Investor current = investorService.getAllInvestors().get(x);
			if (investorService.getAmountOfCompaniesInvestedIn(current)<investorService.getAmountOfCompaniesInvestedIn(investors.get(0))) {
				investors.clear();
				investors.add(current);
			} else if (investorService.getAmountOfCompaniesInvestedIn(current)==investorService.getAmountOfCompaniesInvestedIn(investors.get(0))) {
				investors.add(current);
			}
		}
		return investors;
	}
	
	public long getTotalNumberOfTransactions() {
		return data.getTransactions().size();
	}

}
