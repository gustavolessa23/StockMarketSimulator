package com.stockmarket.StockMarketSimulator;

import javax.annotation.PostConstruct;
import javax.swing.JFrame;
import javax.swing.UIManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

import com.stockmarket.StockMarketSimulator.services.SimulationService;
import com.stockmarket.StockMarketSimulator.view.GUI;

@EnableJpaAuditing

@EnableAsync
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class StockMarketSimulatorApplication implements ApplicationRunner{	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private SimulationService simulation;


	public static void main(String[] args) {

		
		try {
			UIManager.setLookAndFeel(
			        UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		} 
		ConfigurableApplicationContext context = new SpringApplicationBuilder(StockMarketSimulatorApplication.class).headless(false).run(args);
		GUI appFrame = context.getBean(GUI.class);	
		

	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
	
		System.out.println("Application starting....");
		simulation.start();
	}

}