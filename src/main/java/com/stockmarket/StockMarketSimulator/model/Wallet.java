package com.stockmarket.StockMarketSimulator.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Wallet {
	
	private List<Share> shares; // holds all shares of a wallet
	//private List<Integer> companies;// holds all companyIDs, related to the shares, for faster access.
	private Map<Integer, Integer> companies; // maps companyID->amount of shares. 
	
	
	public void getWalletDetails() {
		
		System.out.println("WALLET DETAILS: \t");
		System.out.println("\tNumber of companies invested in: "+this.getAmountOfCompaniesInvestedIn());
		//System.out.println("\tShares: ");
		//for(int i=0;i<this.getShares().size();i++) {
			//System.out.print("\t"+(i+1)+" Company ID: "+this.getShares().get(i).getCompanyId());
			//System.out.printf("\t" + "\t$ %.2f",this.getShares().get(i).getPrice());
			//System.out.print("\t\t"+this.getShares().get(i).getDateSold());
			//System.out.println();
		//};
	}
	
	public Wallet() {
		this.shares = new ArrayList<>();
		this.companies = new HashMap<>();
	}
	
	// ----------- SHARES ---------------------
	
	public boolean addShare(Share s) {
		addCompanyId(s.getCompanyId());
		return this.shares.add(s);
	}
	
	public List<Share> getShares(){
		return this.shares;
	}
	
	public int getAmountOfShares() {
		return this.shares.size();
	}
	
	
	// ----------- COMPANIES ---------------------
	

	private int addCompanyId(int companyId) {
		return this.companies.merge(companyId, 1, Integer::sum);  // add
	}
	
	public List<Integer> getCompaniesIds() {
		List<Integer> companyIds = new ArrayList<>();
		
		this.companies.forEach((key, value) -> {
			companyIds.add(key);
		});
		
		return companyIds;
	}
	
	public int getAmountOfCompaniesInvestedIn() {
		return this.companies.size();
	}
	
	public List<Integer> getRemainingCompaniesIds(int numberOfCompanies){
		List<Integer> remaining = new ArrayList<>();
		
		for (int x = 1; x <= numberOfCompanies; x++) {
			if (!companies.containsKey(x)) remaining.add(x);
		}		
		
		return remaining;
	}
	


}
