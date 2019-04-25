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
//		data.setCompanies(companyList);
//		data.setInvestors(invList);
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

//	public void tradeShare (Company company, Investor investor) {
//		try {
//			System.out.println("COMPANY SHAREEEES:"+company.getNumberOfSharesAvailable());
//			Share soldShare = company.sellShare(); //returns a share that is sold from company (updates share lists)
//
//			investor.buyShare(soldShare.getPrice()); //buy share for the share price (update budget)
//			investor.getWallet().addShare(soldShare); //add sold share to wallet (update wallet)
//
//			Transaction transaction = new Transaction(company,investor); //creates a transaction between the company and investor
//
//			transaction.getTransactionDetails(); //displays the transactions
//
//			afterTenTransactions(transaction); //a check for every 10 transactions in the simulation
//		} catch (CompanyOutOfSharesException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}

//	public void afterTenTransactions(Transaction transaction) {
//		if(transaction.getTransactionId()%10==0) { //checks after every 10 transactions
//			System.out.println("Decrease in price for: ");
//			for(int i = 0;i<CompanyGenerator.numberOfCompanies;i++) {
//				Company company = CompanyGenerator.companyList.get(i);
//				if(company.getHasSoldShare()==false){ //check if company has sold share or not
//					company.decreasePrice(); //decrease the price if company has not sold share
//					System.out.print("\t"+company.getId()+": "+company.getName()+" \t");
//					System.out.printf("\tPrice: " + "\t$ %.2f %n", company.getSharePrice());
//				}
//				company.setHasSoldShare(false); //set all the companies soldShare state back to false
//			}
//			System.out.println();
//		}
//	}

//	public void checkSharesAmount(Company company) {
//		if(company.getShares().isEmpty()) { //checks if there are no shares
//			CompanyGenerator.companyList.remove(company); //the company is removed from the simulation
//			CompanyGenerator.numberOfCompanies = CompanyGenerator.numberOfCompanies-1; //update value so that trade will continue with the amount of companies in the list
//			System.out.printf("Company removed --- "+company.getId()+": "+company.getName()+ "\t$ %.2f %n",company.getCapital());
//			System.out.println();
//		}
//	}
//
//	//checks the budget of an investor...if there are no more funds, the investor is removed from the simulation
//	public void checkBudgetAmount(Investor investor) {
//		if(investor.getBudget()<=0) { //this needs to change!!!!!!!!!!!!!!!!!!!!!
//			InvestorGenerator.investorList.remove(investor);
//			InvestorGenerator.numberOfInvestors = InvestorGenerator.numberOfInvestors-1; //update value so that trade will continue with the amount of investors in the list
//			System.out.println("Investor removed: "+investor.getId()+" "+investor.getName()+" "+investor.getBudget()+"\n");
//		}
//	}


}
