package com.stockmarket.StockMarketSimulator.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.stockmarket.StockMarketSimulator.model.Company;
import com.stockmarket.StockMarketSimulator.repositories.CompanyRepository;

public class CompanyService {
	
	@Autowired
	private CompanyRepository companyRepository;
	
	
	
	private List<Company> companies;
	
	/**
	 * This method is responsible for getting a Company be its ID
	 * @param id 
	 * @return the company ID which is a Long id.
	 */
	public Company getCompany(Integer id) {
		return companyRepository.getOne(id);
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
	
	/**
	 * This method is responsible for the update of the Company.
	 * @param company takes the Object Company
	 */
	public void updateCompany(Company company) {
		companyRepository.save(company);
	}
	
	/**
	 * 
	 * @param id
	 */
	public void deleteCompany(Company id) {
		companyRepository.delete(id);
	}
	

}
