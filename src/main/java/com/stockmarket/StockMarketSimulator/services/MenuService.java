package com.stockmarket.StockMarketSimulator.services;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stockmarket.StockMarketSimulator.view.View;
import com.stockmarket.StockMarketSimulator.view.report.ReportFactory;
import com.stockmarket.StockMarketSimulator.view.report.ReportType;


@Service
public class MenuService {
	
	private @Autowired View view;
	
	private @Autowired InputService input;
	
	private @Autowired SimulationService simulation;
		
	private @Autowired ReportService reportService;
	
	
	/**
	 * This method is used to display the main menu
	 * @return the number of option in the menu
	 */
	public int displayMainMenu() {
		int numberOfOptions = 10;
        view.display(
      "\n+-------------------------------------------------------------------+\n" +
        "|     Stock Market Simulator                                        |\n" +
        "|-------------------------------------------------------------------|\n" +  
        "| 1 - Run simulation again                                          |\n" +
        "| 2 - Companies with the highest capital                            |\n" +
        "| 3 - Companies with the lowest capital                             |\n" + 
        "| 4 - Investors with the highest number of shares                   |\n" + 
        "| 5 - Investors that have invested in the most companies            |\n" + 
        "| 6 - Investors with the lowest number of shares                    |\n" + 
        "| 7 - Investors that have invested in the least number of companies |\n" + 
        "| 8 - Total number of transactions                                  |\n" + 
        "| 9 - Full report                                                   |\n" + 
        "| 10 - Exit Program                                                 |\n" +        
        "+-------------------------------------------------------------------+\n");
        displayChooseOption();
		return numberOfOptions;
	}
	
	/**
	 * Method used to a message
	 */
	public void displayChooseOption() {
		view.display("Your simulation has finished, please choose an option: ");
	}
	
	
	public void start() {
		
		int option = 0;
		int maxOption = 0;
		
		do {
			
			maxOption = displayMainMenu();
			
			option = input.getNextInt(maxOption);
			
			switch(option) {
			case 1:
				simulation.start();
				break;
			case 2:
				view.display(simulation.highestCapital());
				break;
			case 3:
				view.display(simulation.lowestCapital());
				break;
			case 4:
				view.display(simulation.highestNumberOfShares());
				break;
			case 5:
				view.display(simulation.highestNumberOfCompanies());
				break;
			case 6:
				view.display(simulation.lowestNumberOfShares());
				break;
			case 7:
				view.display(simulation.lowestNumberOfCompanies());
				break;
			case 8:
				view.display(simulation.totalTransactions());
				break;
			case 9:
				String report = simulation.fullReport();
				view.display(report);
				simulation.generatePdfReport(report);
				simulation.generateDocxReport(report);
				simulation.generateTxtReport(report);
				
				break;
			case 10:
				view.display("Closing application...");
				System.exit(0);
				break;
			default:
				view.displayError("Option not recognized. Please try again.");
				break;
			}

		}while(option != maxOption); 
	}
	
	

}
