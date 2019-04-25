package com.stockmarket.StockMarketSimulator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.stockmarket.StockMarketSimulator.model.Company;
import com.stockmarket.StockMarketSimulator.model.Investor;
import com.stockmarket.StockMarketSimulator.model.TradingDay;
import com.stockmarket.StockMarketSimulator.setup.CompanyGenerator;
import com.stockmarket.StockMarketSimulator.setup.InvestorGenerator;

@SpringBootApplication
@EnableJpaAuditing
public class StockMarketSimulatorApplication implements CommandLineRunner {
	
	@Autowired
	CompanyGenerator cGen; 
	
	@Autowired
	InvestorGenerator iGen;	
	
	@Autowired
	TradingDay td;
	
			
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(StockMarketSimulatorApplication.class);
		app.run(args);
		

				
	}
	
    //access command line arguments
    @Override
    public void run(String... args) throws Exception {
	
        System.out.println("Application starting....");
		
		cGen.generateCompanies(); //generate companies
		iGen.generateInvestors(); //generate investors

		td.trade(CompanyGenerator.companyList, InvestorGenerator.investorList); //run the trade
		
    }

}