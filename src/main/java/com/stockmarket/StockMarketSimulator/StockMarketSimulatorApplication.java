package com.stockmarket.StockMarketSimulator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.stockmarket.StockMarketSimulator.services.SimulationService;

@SpringBootApplication
@EnableJpaAuditing
@EnableAsync //Allows for async methods calls 
public class StockMarketSimulatorApplication implements CommandLineRunner {

	@Autowired
	SimulationService simulation;
	
	@Bean(name="myThread")
    public TaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);
        executor.setMaxPoolSize(4);
        executor.setThreadNamePrefix("myThread");
        executor.setQueueCapacity(2);
        //executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.initialize();
      
        return executor;
    }
	
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(StockMarketSimulatorApplication.class);
		app.run(args);				
	}
	
    //access command line arguments
    @Override
    public void run(String... args) throws Exception {
	
        System.out.println("Application starting....");
		simulation.start();

    }

}