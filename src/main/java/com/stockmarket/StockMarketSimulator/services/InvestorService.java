package com.stockmarket.StockMarketSimulator.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.stockmarket.StockMarketSimulator.repositories.InvestorRepository;

public class InvestorService {
	
	@Autowired
	private InvestorRepository investorRepository;

}
