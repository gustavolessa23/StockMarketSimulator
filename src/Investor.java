

import java.util.ArrayList;

public class Investor<Company> {
	
	private int investorID;
	private double budget;
	public ArrayList<Company> potentialCompany;
	
	public Investor() {
		
	}

	public int getInvestorID() {
		return investorID;
	}

	public void setInvestorID(int investorID) {
		this.investorID = investorID;
	}

	public double getBudget() {
		return budget;
	}

	public void setBudget(double budget) {
		this.budget = budget;
	}

	public ArrayList<Company> getPotentialCompany() {
		return potentialCompany;
	}

	public void setPotentialCompany(ArrayList<Company> potentialCompany) {
		this.potentialCompany = potentialCompany;
	}
	
	
	
	
	
}

