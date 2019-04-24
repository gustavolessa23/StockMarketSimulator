package com.stockmarket.StockMarketSimulator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stockmarket.StockMarketSimulator.repositories.InvestorRepository;

@Service
public class InvestorService {
	
	@Autowired
	private InvestorRepository investorRepository;

}
