package com.stockmarket.StockMarketSimulator;

import javax.swing.JFrame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.stockmarket.StockMarketSimulator.model.Company;
import com.stockmarket.StockMarketSimulator.model.Investor;
import com.stockmarket.StockMarketSimulator.model.TradingDay;
import com.stockmarket.StockMarketSimulator.services.SimulationService;
import com.stockmarket.StockMarketSimulator.setup.CompanyGenerator;
import com.stockmarket.StockMarketSimulator.setup.InvestorGenerator;
import com.stockmarket.StockMarketSimulator.view.Report;

@SpringBootApplication
@EnableJpaAuditing
public class StockMarketSimulatorApplication extends JFrame implements CommandLineRunner {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	SimulationService simulation;
	
	@Autowired
	Report report;
	
			
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(StockMarketSimulatorApplication.class);
		app.run(args);				
	}
	
    //access command line arguments
    @Override
    public void run(String... args) throws Exception {
	
        System.out.println("Application starting....");
		simulation.start();
		
		report.start();
		

    }

}
