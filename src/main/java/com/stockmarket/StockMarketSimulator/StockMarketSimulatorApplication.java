package com.stockmarket.StockMarketSimulator;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class StockMarketSimulatorApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(StockMarketSimulatorApplication.class);
		app.run(args);
	}
	
    //access command line arguments
    @Override
    public void run(String... args) throws Exception {
	
        System.out.println("test");
		
    }

}
