package com.stockmarket.StockMarketSimulator.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.stockmarket.StockMarketSimulator.repositories.CompanyRepository;

public class Wallet {
	
	private List<Share> shares; // holds all shares of a wallet
	//private List<Integer> companies;// holds all companyIDs, related to the shares, for faster access.
	private Map<Integer, Integer> companies; // maps companyID->amount of shares. 
	private CompanyRepository companyRepository;
	
	public Wallet() {
		this.shares = new ArrayList<>();
		this.companies = new HashMap<>();
	}
	
	// ----------- SHARES ---------------------
	
	public boolean addShare(Share s) {
		addCompanyId(s.getCompanyId());
		return this.shares.add(s);
	}
	
	public List<Share> getShares(){
		return this.shares;
	}
	
	public int getAmountOfShares() {
		return this.shares.size();
	}
	
	
	// ----------- COMPANIES ---------------------
	
	
	public int addCompanyId(int companyId) {
		return this.companies.merge(companyId, 1, Integer::sum);  // add
	}
	
	public List<Integer> getCompaniesIds() {
		List<Integer> companyIds = new ArrayList<>();
		
		this.companies.forEach((key, value) -> {
			companyIds.add(key);
		});
		
		return companyIds;
	}
	
	/**
	 * This method is responsible for getting a Company be its ID
	 * @param id 
	 * @return the company ID which is a Long id.
	 */
	public Optional<Company> getCompany(Long id) {
		return companyRepository.findById(id);
	}
	
	/**
	 * This method is used to create/add new company to the database using the "companyRepository"
	 * @param company
	 */
	public void addCompany(Company company) {
		companyRepository.save(company);
	}
	
	public int getAmountOfCompanies() {
		return this.companies.size();
	}
	
	/**
	 * Method to get and return all Companies in the Database
	 * @return return a list of companies in the added in the data base.
	 */
	public List<Company> getAllCompanies(){
		List<Company> companies = new ArrayList<>();
		
		companyRepository.findAll().forEach(companies::add);
		
		return companies;
	}
	
	public List<Integer> getRemainingCompaniesIds(int numberOfCompanies){
		List<Integer> remaining = new ArrayList<>();
		
		for (int x = 1; x <= numberOfCompanies; x++) {
			if (!companyRepository.existsById((long) x)) remaining.add(x);
		}		
		
		return remaining;
	}
	
	


}
