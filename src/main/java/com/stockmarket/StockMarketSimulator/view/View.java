package com.stockmarket.StockMarketSimulator.view;

import com.stockmarket.StockMarketSimulator.model.Company;
import com.stockmarket.StockMarketSimulator.model.Investor;

public class View {
	
	
	/**
	 * This method is used to display the main menu
	 * @return the number of option in the menu
	 */
	public int displayMainMenu() {
		int numberOfOptions = 7;
        display(
      "\n+--------------------------------------------------------------+\n" +
        "|     Stock Market Simulator	 								|\n" +
        "|--------------------------------------------------------------|\n" +  
        "| 1 - Run a simulation again             						|\n" +
        "| 2 - Check companies with the highest capital     			|\n" +
        "| 3 - Check companies with the lowest capital     				|\n" + 
        "| 4 - Check Investors with the highest number of shares   		|\n" + 
        "| 5 - Check Investors that has invested in the most companies  |\n" + 
        "| 6 - Check Investors with the lowest number of shares 		|\n" + 
        "| 7 - Exit Program                 							|\n" +        
        "+--------------------------------------------------------------+\n");
        displayChooseOption();
		return numberOfOptions;
	}
	
	/**
	 * Method used to display the Welcome Log
	 */
	public static void displayLogo(){
		display("\\    /\\    / |‾ ‾  |     /‾ ‾   /‾ ‾\\   /\\    /\\   |‾ ‾   \n" + 
				" \\  /  \\  /  |- -  |    |      |     | /  \\  /  \\  |- -    \n" + 
				"  \\/    \\/   |_ _  |_ _  \\_ _   \\_ _/ /    \\/    \\ |_ _   \n" + 
				"");
	}
	
	/**
	 * Method used to to display String
	 * @param string
	 */
	public static void display(String string) {
		System.out.println(string);
	}
	
	/**
	 * Method used to a message
	 */
	public void displayChooseOption() {
		display("Your simulation has fineshed, please choose and opiont: ");
	}
	
	/**
	 * Method used to display all the companies in the database
	 * @param company
	 */
	public static void displayCompany(Company company) {
		display(company.toString());
	}
	
	/**
	 * Method used to display all the investors in the database
	 * @param investor
	 */
	public static void displayInvestor(Investor investor) {
		display(investor.toString());
	}

}
