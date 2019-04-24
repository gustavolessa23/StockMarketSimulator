package com.stockmarket.StockMarketSimulator.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stockmarket.StockMarketSimulator.model.Investor;

public interface InvestorRepository extends JpaRepository<Investor, Integer>{
	
	List<Investor> findInvestorID(Integer investorId);

}
