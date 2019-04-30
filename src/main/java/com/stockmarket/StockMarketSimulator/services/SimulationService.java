package com.stockmarket.StockMarketSimulator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stockmarket.StockMarketSimulator.model.Company;
import com.stockmarket.StockMarketSimulator.model.Data;
import com.stockmarket.StockMarketSimulator.model.Investor;
import com.stockmarket.StockMarketSimulator.model.TradingDay;
import com.stockmarket.StockMarketSimulator.view.GUI;
import com.stockmarket.StockMarketSimulator.view.View;

@Service
public class SimulationService {

	

	@Autowired
	private CompanyService companyService; 
	
	@Autowired
	private InvestorService investorService;
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private TradingDay td;
	
	@Autowired
	private Data data;
	
	@Autowired
	private View view;
	
	@Autowired
	private ReportService reportService;
	
	

	
	public void start() {
		
		companyService.clearCompanyTable();
		investorService.clearInvestorTable();
	
		companyService.populateCompanies();
		investorService.populateInvestors();

		td.trade(data.getCompanies(), data.getInvestors()); //run the trade

		companyService.updateCompanies();
		investorService.updateInvestors();
		reportService.saveReportToDb();
		
		view.displayLogo();
		
		
		menuService.start();

	}

	
	public String highestCapital() {
		StringBuilder sb = new StringBuilder();

		sb.append("\nCompany/Companies with Highest capital:");
		for(Company c : td.getHighestCapital()) {
			sb.append("\n"+c.getId()+" - Name: "+c.getName()+" - Capital: "+data.round(c.getCapital(),2));
		}
		return sb.toString();
		
	}
	
	public String lowestCapital() {
		StringBuilder sb = new StringBuilder();

		sb.append("\nCompany/Companies with Lowest capital:");
		for(Company c : td.getLowestCapital()) {
			sb.append("\n"+c.getId()+" - Name: "+c.getName()+" - Capital: "+data.round(c.getCapital(),2));
		}
		return sb.toString();

	}
	
	public String highestNumberOfShares() {
		StringBuilder sb = new StringBuilder();

		sb.append("\nInvestor(s) with highest number of shares:");
		for(Investor i : td.getHighestNumberOfShares()) {
			sb.append("\n"+i.getId()+" - Name: "+i.getName()+" - Shares: "+i.getTotalNumberOfSharesBought());
		}
		return sb.toString();

	}
	
	public String lowestNumberOfShares() {
		StringBuilder sb = new StringBuilder();

		sb.append("\nInvestor(s) with lowest number of shares:");
		for(Investor i : td.getLowestNumberOfShares()) {
			sb.append("\n"+i.getId()+" - Name: "+i.getName()+" - Shares: "+i.getTotalNumberOfSharesBought());
		}
		return sb.toString();

	}
	
	public String highestNumberOfCompanies() {
		StringBuilder sb = new StringBuilder();

		sb.append("\nInvestor(s) with highest number of companies:");
		for(Investor i : td.getHighestNumberOfCompanies()) {
			sb.append("\n"+i.getId()+" - Name: "+i.getName()+" - Companies: "+investorService.getAmountOfCompaniesInvestedIn(i));
		}
		return sb.toString();

	}
	
	public String lowestNumberOfCompanies() {
		StringBuilder sb = new StringBuilder();

		sb.append("\nInvestor(s) with lowest number of companies:");
		for(Investor i : td.getLowestNumberOfCompanies()) {
			sb.append("\n"+i.getId()+" - Name: "+i.getName()+" - Companies: "+investorService.getAmountOfCompaniesInvestedIn(i));
		}
		return sb.toString();

	}
	
	public String totalTransactions() {
		StringBuilder sb = new StringBuilder();

		sb.append("\nTotal number of transactions: "+td.getTotalNumberOfTransactions());
		return sb.toString();

	}
	
	
	public String fullReport() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n----------REPORT----------");
		sb.append("\nCOMPANIES:");
		sb.append(highestCapital());
		sb.append("\n"+lowestCapital());
		sb.append("\n\nINVESTORS");
		sb.append(highestNumberOfShares());
		sb.append("\n"+lowestNumberOfShares());
		sb.append("\n"+highestNumberOfCompanies());
		sb.append("\n"+lowestNumberOfCompanies());
		sb.append("\n\nTRANSACTIONS");
		sb.append(totalTransactions());
		return sb.toString();
	}
	
	
	public void generatePdfReport(String path) {
		reportService.generatePdfReport(fullReport(), path);
	}
	
	public void generateDocxReport(String path) {
		reportService.generateDocxReport(fullReport(), path);
	}
	
	public void generateTxtReport(String path) {
		reportService.generateTxtReport(fullReport(), path);
	}
}
