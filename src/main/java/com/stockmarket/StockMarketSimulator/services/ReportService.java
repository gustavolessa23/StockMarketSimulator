package com.stockmarket.StockMarketSimulator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stockmarket.StockMarketSimulator.model.Report;
import com.stockmarket.StockMarketSimulator.model.TradingDay;
import com.stockmarket.StockMarketSimulator.repositories.ReportRepository;

@Service
public class ReportService {
	
	@Autowired
	ReportRepository reportRepository;
	
	@Autowired
	TradingDay td;
	
	public void saveReport() {
		
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

}
