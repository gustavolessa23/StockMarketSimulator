package com.stockmarket.StockMarketSimulator.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.stockmarket.StockMarketSimulator.exception.InvestorHasInvestedInAllCompaniesException;
import com.stockmarket.StockMarketSimulator.model.Data;
import com.stockmarket.StockMarketSimulator.model.Investor;
import com.stockmarket.StockMarketSimulator.model.Share;
import com.stockmarket.StockMarketSimulator.repositories.InvestorRepository;
import com.stockmarket.StockMarketSimulator.setup.InvestorGenerator;
import com.stockmarket.StockMarketSimulator.view.View;

@Service
public class InvestorService {

	@Autowired
	private InvestorRepository investorRepository;

	@Autowired
	private InvestorGenerator investorGenerator;	
	
	@Autowired
	private CompanyService companyService;

	@Autowired
	private Data data; 

	@Autowired
	private View view;
	
	/**
	 * Method to populate the investor list using generator.
	 */
	public void populateInvestors() {
		view.display("Generating investors...");
		List<Investor> investors = investorGenerator.generateInvestors();
		data.setInvestors(investors);
		investorRepository.saveAll(investors);
	}

	public Investor getRandomInvestor() {
		Random rG = new Random();
		return data.getInvestors().get(rG.nextInt(data.getInvestors().size()));
	}
	
	public List<Investor> getAllInvestors() {
		return data.getInvestors();
	}
	
	public Investor getInvestorById(int id) {
		return data.getInvestors().get(id);
	}
	
	public void updateInvestors() {
		investorRepository.saveAll(data.getInvestors());
	}
	
	/**
	 * Method to add an investor to the DB using JPA and Hibernate.
	 * @param investor
	 */
	public void addInvestorToDb(Investor investor) {
		investorRepository.save(investor);
	}

	/**
	 * Method to get and return all Investors in the Database.
	 * @return return a list of investors from database.
	 */
	public List<Investor> getAllInvestorsFromDb(){
		return investorRepository.findAll();
	}

	// ----------- SHARES ---------------------

//	/**
//	 * Method to add a share to an investor's wallet.
//	 * @param investor
//	 * @param share
//	 * @return
//	 */
//	public boolean addShare(Investor investor, Share share) {
//		addCompanyId(investor, share.getCompanyId());
//		return investor.getWallet().getShares().add(share);
//	}

	/**
	 * Method to get the list of shares an investor has.
	 * @param investor
	 * @return
	 */
	public List<Share> getShares(Investor investor){
		return investor.getWallet().getShares();
	}

	/**
	 * Method to get the amount of shares an investor has.
	 * @param investor
	 * @return
	 */
	public int getAmountOfShares(Investor investor) {
		return investor.getWallet().getShares().size();
	}


	public void buyShare(Investor investor, Share share) {
		investor.setBudget(data.round(investor.getBudget()-share.getPrice(),2));// decrement budget by share price
		investor.getWallet().getShares().add(share);
		addCompanyId(investor, share.getCompanyId());
		investor.incrementSharesBought();;		
	}

	/**
	 * Method to add a new entry to an investor's map of amount of shares (value) for each company ID (key)
	 * @param investor
	 * @param companyId
	 * @return
	 */
	private void addCompanyId(Investor investor, int companyId) {
		// set the value for 1 or increment existing value
		investor.getWallet().getCompaniesShares().merge(companyId, 1, Integer::sum);  // add
		
		investor.setNumberOfCompaniesInvestedIn(investor.getWallet().getCompaniesShares().size());
	}

	/**
	 * Method to return a list of company IDs from the shares an investor owns.
	 * @param investor
	 * @return
	 */
	public List<Integer> getCompaniesIds(Investor investor) {
		List<Integer> companyIds = new ArrayList<>();

		investor.getWallet().getCompaniesShares().forEach((key, value) -> {
			companyIds.add(key);
		});

		return companyIds;
	}

	/**
	 * Method to return the number of companies and investor has invested in.
	 * @param investor
	 * @return
	 */
	public int getAmountOfCompaniesInvestedIn(Investor investor) {
		return investor.getWallet().getCompaniesShares().size();
	}

	/**
	 * Method to return a list of the company IDs an investor hasn't invested in.
	 * @param investor
	 * @param numberOfCompanies
	 * @return
	 */
	public List<Integer> getRemainingCompaniesIds(Investor investor, int numberOfCompanies) throws InvestorHasInvestedInAllCompaniesException{
		List<Integer> remaining = new ArrayList<>();
		if (investor.getWallet().getCompaniesShares().size() == numberOfCompanies) {
			throw new InvestorHasInvestedInAllCompaniesException("Investor "+investor.getId()+" has invested in all companies!");
		} else {
			for (int x = 1; x <= numberOfCompanies; x++) {
				if (!investor.getWallet().getCompaniesShares().containsKey(x)) remaining.add(x);
			}	
		}

		return remaining;
	}
	
	public List<Integer> getDesirableCompaniesForInvestor(Investor investor) {
		List<Integer> desirableCompanyIds = new ArrayList<>();

		try {
			//get the list of company IDs the investor has no shares of
			desirableCompanyIds = getRemainingCompaniesIds(investor, data.getCompanies().size());
		} catch (InvestorHasInvestedInAllCompaniesException e) {
			desirableCompanyIds = getCompaniesIds(investor);
		}
		
		desirableCompanyIds.removeIf(id -> companyService.getCompanyFromId(id).getShares().size() == 0);
		
		desirableCompanyIds.removeIf(id -> companyService.getCompanyFromId(id).getSharePrice() > investor.getBudget());

		Collections.shuffle(desirableCompanyIds);
		return desirableCompanyIds;
	}
	
	public Investor getHighestBudget() {
		Investor investor = data.getInvestors().get(0);
		for(int x = 1; x < data.getInvestors().size(); x++) {
			Investor current = data.getInvestors().get(x);
			if(current.getBudget() > investor.getBudget()) {
				investor = current;
			}
		}
		return investor;
	}
	
	public void clearInvestorTable() {
		 
		investorRepository.deleteAll();
		data.setInvestors(new ArrayList<Investor>());
		Investor.lastId = 0;
	}
	

}

