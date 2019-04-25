package com.stockmarket.StockMarketSimulator.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stockmarket.StockMarketSimulator.exception.CompanyOutOfSharesException;
import com.stockmarket.StockMarketSimulator.exception.InvestorOutOfFundsException;
import com.stockmarket.StockMarketSimulator.model.Company;
import com.stockmarket.StockMarketSimulator.model.Data;
import com.stockmarket.StockMarketSimulator.model.Investor;
import com.stockmarket.StockMarketSimulator.model.Transaction;

@Component
public class TransactionService {
	
	@Autowired 
	Data data;
	
	@Autowired 
	InvestorService investorService;
	
	@Autowired 
	CompanyService companyService;
	
	
	public void executeTransaction(Company company, Investor investor) throws InvestorOutOfFundsException, CompanyOutOfSharesException{
		if(investor.getBudget()<company.getSharePrice()) {
			throw new InvestorOutOfFundsException("Investor "+investor.getId()+", budget: "+investor.getBudget()+" cannot afford Company "+company.getId()+" share, priced "+company.getSharePrice());
		} else if (company.getNumberOfSharesAvailable() == 0){
			throw new CompanyOutOfSharesException("Company "+company.getId()+" has no more shares to sell!");
		} else {
			investorService.buyShare(investor, company.sellShare());	
			Transaction transaction = new Transaction(company, investor);
			data.getTransactions().add(transaction);
			
			afterTenTransactionsVerification(transaction);
		}
	}


	public boolean tryTransaction(Investor investor, List<Integer> companyIds) {
		while(!companyIds.isEmpty()) {
			Company randomCompany = companyService.getCompanyFromId(companyIds.remove(0));
			try {
				executeTransaction(randomCompany, investor);
				return true;
			}catch(InvestorOutOfFundsException | CompanyOutOfSharesException c) {
				System.out.println("Trying another company.");
			}	
		}
		return false;
	}
	
	public void afterTenTransactionsVerification(Transaction transaction) {
		if(transaction.getTransactionId()%10==0) { //checks after every 10 transactions
			System.out.println("Decrease in price for: ");
			for(int i = 0;i<data.getCompanies().size();i++) {
				Company company = data.getCompanies().get(i);
				if(company.getHasSoldShare()==false && !company.getShares().isEmpty()){ //check if company has sold share AND still has shares to sell
					company.decreasePrice(); //decrease the price if company has not sold share
					System.out.print("\t"+company.getId()+": "+company.getName()+" \t");
					System.out.printf("\tPrice: " + "\t$ %.2f %n", company.getSharePrice());
				}
				company.setHasSoldShare(false); //set all the companies soldShare state back to false
			}
			System.out.println();
		}
	}

}
