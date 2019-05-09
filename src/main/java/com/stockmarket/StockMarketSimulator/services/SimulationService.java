package com.stockmarket.StockMarketSimulator.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stockmarket.StockMarketSimulator.model.Company;
import com.stockmarket.StockMarketSimulator.model.Data;
import com.stockmarket.StockMarketSimulator.model.Investor;
import com.stockmarket.StockMarketSimulator.model.TradingDay;
import com.stockmarket.StockMarketSimulator.model.Transaction;
import com.stockmarket.StockMarketSimulator.view.GUI;
import com.stockmarket.StockMarketSimulator.view.View;


@Service
public class SimulationService {

	
	@Autowired
	private CompanyService companyService; 
	
	@Autowired
	private InvestorService investorService;
	
	@Autowired
	private AsyncService asyncService; 
	
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private TradingDay td;
	
	@Autowired
	private Data data;
	
	@Autowired
	private View view;
	
	@Autowired
	private ReportService reportService;
	
	@Autowired
	private GUI gui;


	public void start() {
		

		view.display("Starting simulation...");
		
		companyService.clearCompanyTable();
		investorService.clearInvestors();
		
		
		generateObjects();

		td.trade(data.getCompanies(), data.getInvestors()); //run the trade
		
		companyService.updateCompanies();
		investorService.updateInvestors();
		
		
		if(td.isSimulationFinished()) { 
			
			gui.setButtonsActive(true);			
			
			reportService.saveReportToDb();

			
		}
		
	}
	
	public void restart() {
		
		td.setSimulationFinished(false);
		
		companyService.clearCompanyTable();
		investorService.clearInvestors();
		transactionService.clearTransactions();
		
		start();
	}
	
	
	/*
	 * @Async function to generate companies at the same time
	 */
	public void generateObjects() {

		Future<String> futureResult =  asyncService.genComapanies();
		Future<String> futureResult2 = asyncService.genInvestors();

		try {
			String result = futureResult.get();
			String result2 = futureResult.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
		e.printStackTrace();
		}
		
		
			
		}

	
	public String displayTransaction() {
		StringBuilder sb = new StringBuilder();

		sb.append("\nCompany/Companies with Highest capital:");
		for(Transaction t : data.getTransactions()) {
			sb.append(t.toString());
		}
		return sb.toString();
		
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
	
	public String allCompanies() {
		StringBuilder sb = new StringBuilder();

		List<Company> companies = companyService.getAllCompanies();
		
		sb.append("\nCompanies report: ");
		for(int x = 0; x < companies.size(); x++) {
			sb.append("\n"+companies.get(x).getId()+" - "+companies.get(x).getName()+" - Capital: "+companies.get(x).getCapital()+" - Shares sold: "+
					companies.get(x).getSharesSold()+" - Shares left: "+
					(companies.get(x).getSharesSold() - companies.get(x).getInitialShares())+
					" Share price: "+companies.get(x).getSharePrice());
		}
		return sb.toString();
	}
	
	
	public String allInvestors() {
		StringBuilder sb = new StringBuilder();

		sb.append("\nInvestors report");
		for(Investor i : investorService.getAllInvestors()) {
			sb.append("\n"+i.getId()+" - Name: "+i.getName()+" - Companies: "+
						investorService.getAmountOfCompaniesInvestedIn(i)+" - Shares: "+i.getTotalNumberOfSharesBought()+
						" - Initial Budget: "+i.getInitialBudget()+
						" - Final Budget: "+i.getTotalNumberOfSharesBought());
		}
		return sb.toString();
	}
	
	public String allTransactions() {
		StringBuilder sb = new StringBuilder();

		sb.append("\nTransactions report");
		for(Transaction i : transactionService.getAllTransactions()) {
			sb.append("\n"+i.getTransactionId()+" - Investor: "+i.getInvestor().getName()+" - Company: "+
						i.getCompany().getName()+" - Date: "+i.getDate().toString());
		}
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
	
	
	public void generatePdfReport(String content, String path) {
		reportService.generatePdfReport(content, path);
	}
	
	public void generateDocxReport(String content, String path) {
		reportService.generateDocxReport(content, path);
	}
	
	public void generateTxtReport(String content, String path) {
		reportService.generateTxtReport(content, path);
	}
}
