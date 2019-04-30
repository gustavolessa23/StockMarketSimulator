package com.stockmarket.StockMarketSimulator.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stockmarket.StockMarketSimulator.model.Report;
import com.stockmarket.StockMarketSimulator.model.TradingDay;
import com.stockmarket.StockMarketSimulator.repositories.ReportRepository;
import com.stockmarket.StockMarketSimulator.view.report.ReportFactory;
import com.stockmarket.StockMarketSimulator.view.report.ReportFile;
import com.stockmarket.StockMarketSimulator.view.report.ReportType;

@Service
public class ReportService {
	
	@Autowired
	ReportRepository reportRepository;
	
	@Autowired
	TradingDay td;
	
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
		
		reportRepository.save(report);
		
	}
	
	public void generatePdfReport(String content) {
		ReportFile file = ReportFactory.create(ReportType.PDF, "report.pdf", content);
		try {
			file.generate();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void generateDocxReport(String content) {
		ReportFile file = ReportFactory.create(ReportType.DOCX, "report.docx", content);
		try {
			file.generate();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void generateTxtReport(String content) {
		ReportFile file = ReportFactory.create(ReportType.TXT, "report.txt", content);
		try {
			file.generate();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

}
