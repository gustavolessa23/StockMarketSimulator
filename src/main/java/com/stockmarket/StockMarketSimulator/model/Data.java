package com.stockmarket.StockMarketSimulator.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
public class Data {
	
	private @Getter @Setter List<Company> companies;
	private @Getter @Setter List<Investor> investors;
	private @Getter @Setter List<Transaction> transactions;
	private @Getter @Setter Map<Integer, Company> companiesMap;
	private @Getter @Setter Map<Integer, Investor> investorsMap;
	
	public Data() {
		super();
		this.companies = new ArrayList<>();
		this.investors = new ArrayList<>();
		this.transactions = new ArrayList<>();
		this.companiesMap = new HashMap<>();
		this.investorsMap = new HashMap<>();
	}
	


}
