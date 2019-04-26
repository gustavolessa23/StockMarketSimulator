package com.stockmarket.StockMarketSimulator.view;

import org.springframework.stereotype.Component;

import com.stockmarket.StockMarketSimulator.model.Company;
import com.stockmarket.StockMarketSimulator.model.Investor;

@Component
public class View {
	

	
	/**
	 * Method used to display the Welcome Log
	 */
	public void displayLogo(){
		display("\\    /\\    / |‾ ‾  |     /‾ ‾   /‾ ‾\\   /\\    /\\   |‾ ‾   \n" + 
				" \\  /  \\  /  |- -  |    |      |     | /  \\  /  \\  |- -    \n" + 
				"  \\/    \\/   |_ _  |_ _  \\_ _   \\_ _/ /    \\/    \\ |_ _   \n");
	}
	
	
	
	/**
	 * Method used to to display String
	 * @param string
	 */
	public void display(String string) {
		System.out.println(string);
	}
	

	
	/**
	 * Method used to display all the companies in the database
	 * @param company
	 */
	public void display(Company company) {
		display(company.toString());
	}
	
	/**
	 * Method used to display all the investors in the database
	 * @param investor
	 */
	public void display(Investor investor) {
		display(investor.toString());
	}



	public void displayError(String string) {
		System.err.println(string);
	}

}
