package com.stockmarket.StockMarketSimulator.services;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stockmarket.StockMarketSimulator.model.Report;
import com.stockmarket.StockMarketSimulator.model.TradingDay;
import com.stockmarket.StockMarketSimulator.repositories.ReportRepository;
import com.stockmarket.StockMarketSimulator.view.report.ReportFactory;
import com.stockmarket.StockMarketSimulator.view.report.ReportFile;
import com.stockmarket.StockMarketSimulator.view.report.ReportType;

import lombok.Getter;
import lombok.Setter;

/**
 * Class to handle the Report generation and persistence.
 * @author Gustavo Lessa (https://github.com/gustavolessa23/)
 *
 */
@Service
public class ReportService {
	
	
	@Autowired
	private ReportRepository reportRepository; //to handle DB operations
	
	@Autowired
	private TradingDay td; // to retrieve the stats from the simulation
	
	/**
	 * 
	 */
	public void saveReportToDb() {
		
		Report report = Report.builder()
				.highestCapital(td.getHighestCapital().get(0).getCapital())
				.highestCompanies(td.getHighestNumberOfCompanies().get(0).getNumberOfCompaniesInvestedIn())
				.highestShares(td.getHighestNumberOfShares().get(0).getTotalNumberOfSharesBought())
				.lowestCapital(td.getLowestCapital().get(0).getCapital())
				.lowestCompanies(td.getLowestNumberOfCompanies().get(0).getNumberOfCompaniesInvestedIn())
				.lowestShares(td.getLowestNumberOfShares().get(0).getTotalNumberOfSharesBought())
				.numberOfTransactions(td.getTotalNumberOfTransactions())
				.build();
		
		Report savedReport = reportRepository.save(report);
		
	}
	
	
	
	public void generatePdfReport(String content, String path) {
		ReportFile file = ReportFactory.create(ReportType.PDF, (path.endsWith(".pdf") ? path : path+".pdf"), content);
		try {
			file.generate();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void generateDocxReport(String content, String path) {
		ReportFile file = ReportFactory.create(ReportType.DOCX, (path.endsWith(".doc") || path.endsWith(".docx") ? path : path+".docx"), content);
		try {
			file.generate();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void generateTxtReport(String content, String path) {
		ReportFile file = ReportFactory.create(ReportType.TXT, (path.endsWith(".txt") ? path : path+".txt"), content);
		try {
			file.generate();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
