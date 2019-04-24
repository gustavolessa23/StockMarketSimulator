package com.stockmarket.StockMarketSimulator.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.stockmarket.StockMarketSimulator.StockMarketSimulatorApplication;
import com.stockmarket.StockMarketSimulator.setup.CompanyGenerator;
import com.stockmarket.StockMarketSimulator.setup.InvestorGenerator;

public class TradingDay {
	
	/*
	 * the core functionality method
	 */
	public void trade(List<Company> companyList, ArrayList<Investor>invList) {
		Random rG = new Random();

		while(contiueSimulation()) {

			if(CompanyGenerator.companyList.isEmpty() || InvestorGenerator.investorList.isEmpty()) {
				System.out.println("Simulation ended");
				System.out.println("INVESTORS: "+InvestorGenerator.investorList.size());
				System.out.println("COMPANIES: "+CompanyGenerator.companyList.size());
				break;
			}else {
			
				Company randomCompany = companyList.get(rG.nextInt(CompanyGenerator.numberOfCompanies)); //chooses a random company
				Investor randomInvestor = invList.get(rG.nextInt(InvestorGenerator.numberOfInvestors)); //chooses a random investor
				
				double budget = randomInvestor.getBudget(); //get budget
				double sharePrice = randomCompany.getSharePrice(); //get share price
	
				List<Integer> companyInWallet = randomInvestor.getWallet().getRemainingCompaniesIds(CompanyGenerator.numberOfCompanies);
				
				if(budget>=sharePrice|| !companyInWallet.contains(randomCompany.getId())) { //if budget is higher than share price (&& investor has not invested in company)
					tradeShare(randomCompany, randomInvestor); // trades the shares between the company and investor if condition is true
						
				}else if(budget<=sharePrice ){ //else if not enough in the budget (|| OTHER CONDITION)
					randomInvestor = invList.get(rG.nextInt(InvestorGenerator.numberOfInvestors)); //find a different investor
				}
				
				checkSharesAmount(randomCompany); //checks the shares after the trade if the company should be removed from the simulation or not
				checkBudgetAmount(randomInvestor); //checks the budget after the trade if the investor should be removed from the simulation or not
			
				randomCompany.getCompanyDetails();
				randomInvestor.getInvestorDetails();
				System.out.println(companyInWallet);
			 
			}
		}
			
			
	}
	
	public void tradeShare (Company company, Investor investor) {
		Share soldShare = company.sellShare(); //returns a share that is sold from company (updates share lists)
		
		investor.buyShare(soldShare.getPrice()); //buy share for the share price (update budget)
		investor.getWallet().addShare(soldShare); //add sold share to wallet (update wallet)
		
		Transaction transaction = new Transaction(company,investor); //creates a transaction between the company and investor

		transaction.getTransactionDetails(); //displays the transactions
		
		afterTenTransactions(transaction); //a check for every 10 transactions in the simulation

	}
	
	public void afterTenTransactions(Transaction transaction) {
		if(transaction.getTransactionId()%10==0) { //checks after every 10 transactions
			System.out.println("Decrease in price for: ");
			for(int i = 0;i<CompanyGenerator.numberOfCompanies;i++) {
				Company company = CompanyGenerator.companyList.get(i);
				if(company.getHasSoldShare()==false){ //check if company has sold share or not
					company.decreasePrice(); //decrease the price if company has not sold share
					System.out.print("\t"+company.getId()+": "+company.getName()+" \t");
					System.out.printf("\tPrice: " + "\t$ %.2f %n", company.getSharePrice());
				}
				company.setHasSoldShare(false); //set all the companies soldShare state back to false
			}
			System.out.println();
		}
	}
	
	public void checkSharesAmount(Company company) {
		if(company.getShares().isEmpty()) { //checks if there are no shares
			CompanyGenerator.companyList.remove(company); //the company is removed from the simulation
			CompanyGenerator.numberOfCompanies = CompanyGenerator.numberOfCompanies-1; //update value so that trade will continue with the amount of companies in the list
			System.out.printf("Company removed --- "+company.getId()+": "+company.getName()+ "\t$ %.2f %n",company.getCapital());
			System.out.println();
		}
	}
	
	//checks the budget of an investor...if there are no more funds, the investor is removed from the simulation
	public void checkBudgetAmount(Investor investor) {
		if(investor.getBudget()<=0) { //this needs to change!!!!!!!!!!!!!!!!!!!!!
			InvestorGenerator.investorList.remove(investor);
			InvestorGenerator.numberOfInvestors = InvestorGenerator.numberOfInvestors-1; //update value so that trade will continue with the amount of investors in the list
			System.out.println("Investor removed: "+investor.getId()+" "+investor.getName()+" "+investor.getBudget()+"\n");
		}
	}
	
	public boolean contiueSimulation() {
		
		boolean numberOfCompanies = CompanyGenerator.companyList.isEmpty();
		boolean numberOfInvestors = InvestorGenerator.investorList.isEmpty();
		
		if(!numberOfCompanies || !numberOfInvestors) {
			return true;
		}else {
			return false;
		}
		
		
		
	}
		
}
