package com.stockmarket.StockMarketSimulator.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.stockmarket.StockMarketSimulator.exception.CompanyOutOfSharesException;
import com.stockmarket.StockMarketSimulator.model.Company;
import com.stockmarket.StockMarketSimulator.model.Data;
import com.stockmarket.StockMarketSimulator.model.Share;
import com.stockmarket.StockMarketSimulator.repositories.CompanyRepository;
import com.stockmarket.StockMarketSimulator.setup.CompanyGenerator;

@Service
public class CompanyService {
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private CompanyGenerator companyGenerator; 
	
	@Autowired
	private Data data; 
	
	/**
	 * This method populates the company list by calling the generator and setting the list.
	 */
	public void populateCompanies() {
		List<Company> companies = companyGenerator.generateCompanies();
		
		data.setCompanies(companies);
		
		Map<Integer, Company> companyMap = new HashMap<>();
		data.getCompanies().forEach(company ->
			companyMap.put(company.getId(), company)
				);
		data.setCompaniesMap(companyMap);
		
		companyRepository.saveAll(companies);
	}

	public Share sellShare(Company company) {
		if (company.getShares().isEmpty()) {
			throw new CompanyOutOfSharesException("Company "+company.getName()+" has no shares left to sell."); // check if it's empty
		}else {
			company.incrementSharesSold(); // increment sharesSold
			
			company.incrementCapital(data.round(company.getSharePrice(),2)); 
	
			Share sold = company.getShares().remove(0); // remove the first share (ArrayList if not empty will always have item on index 0)
			
			sold.setPrice(company.getSharePrice()); // set price accordingly to current share price
			
			company.setHasSoldShare(true);
			
			if(company.getSharesSold()%10 == 0) {
				increasePrice(company);
			}
			
			updateCompanyMap(company);
			
			//company.getCompanyDetails();
			return sold; // return share
		}

	}
	
	public Company getCheapestAvailableShare() {
		Company company = null;
		for(int x = 0; x < data.getCompanies().size(); x++) {
			Company current = data.getCompanies().get(x);
			
			if(company == null && current.getShares().size() > 0) {
				company = current;
			}
			if(company != null && current.getSharePrice() < company.getSharePrice() && !current.getShares().isEmpty()) {
				company = current;
			}
		}
		return company;
	}
	
	
	/**
	 * This method is responsible for getting a Company be its ID
	 * @param id 
	 * @return the company ID which is an int id.
	 */
	public Company getCompanyFromDb(Integer id) {
		return companyRepository.getOne(id);
	}
	
	public Company getCompanyFromId(int id) {
		return data.getCompaniesMap().get(id);
	}

	public void updateCompanyMap(Company company) {
		data.getCompaniesMap().replace(company.getId(), company);
	}
	
	/**
	 * 
	 * @param company
	 */
	public void addCompany(Company company) {
		//company.getCompanyDetails();
		data.getCompanies().add(company);
		data.getCompaniesMap().put(company.getId(), company);
		companyRepository.save(company);
	}
	
	
	/**
	 * Method to get and return all Companies in the Database
	 * @return return a list of companies in the added in the data base.
	 */
	public List<Company> getAllCompaniesFromDb(){
		return companyRepository.findAll();
	}
	
	/**
	 * Method to get and return all Companies in the Database
	 * @return return a list of companies in the added in the data base.
	 */
	public List<Company> getAllCompanies(){
		return data.getCompanies();
	}
	
	/**
	 * This method is responsible for the update of the Company.
	 * @param company takes the Object Company
	 */
	public void updateAllCompanies(Company company) {
		companyRepository.save(company);
	}
	
	public void updateCompanies() {
		companyRepository.saveAll(data.getCompanies());
	}
	
	/**
	 * 
	 * @param id
	 */
	public void deleteCompany(Company id) {
		companyRepository.delete(id);
	}
	
	
	public void increasePrice(Company c) {
		boolean tenSharesSold = c.getSharesSold()%10==0; //check if 10 shares were sold
		
		if(tenSharesSold) { 
			double newPrice = data.round((c.getSharePrice()*2),2); //increase price by 200%
			c.setSharePrice(newPrice);
		}
	}
	
	public void decreasePrice(Company c) {	
		double sharePriceRounded = data.round(c.getSharePrice(), 2);
		if(sharePriceRounded>0.00) {
			double newPrice = (c.getSharePrice()*0.98); //decrease price by 2%
			c.setSharePrice(data.round((newPrice),2));
		}
	}
	
	public void clearCompanyTable() {
		
		companyRepository.deleteAll();

	}
	

}