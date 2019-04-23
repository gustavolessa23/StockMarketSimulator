package com.stockmarket.StockMarketSimulator.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stockmarket.StockMarketSimulator.model.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
	


}
