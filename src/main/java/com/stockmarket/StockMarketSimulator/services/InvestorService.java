package com.stockmarket.StockMarketSimulator.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stockmarket.StockMarketSimulator.model.Company;
import com.stockmarket.StockMarketSimulator.model.Investor;
import com.stockmarket.StockMarketSimulator.repositories.InvestorRepository;

@Service
public class InvestorService {
	
	@Autowired
	private InvestorRepository investorRepository;
	
	public void addInvestor(Investor investor) {
		investor.getInvestorDetails();
		investorRepository.save(investor);
	}

	/**
	 * Method to get and return all Companies in the Database
	 * @return return a list of companies in the added in the data base.
	 */
	public List<Investor> getAllInvestors(){
		return investorRepository.findAll();
	}
}