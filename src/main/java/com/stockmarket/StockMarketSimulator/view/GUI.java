package com.stockmarket.StockMarketSimulator.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.stockmarket.StockMarketSimulator.model.Data;
import com.stockmarket.StockMarketSimulator.services.CompanyService;
import com.stockmarket.StockMarketSimulator.services.InvestorService;
import com.stockmarket.StockMarketSimulator.services.SimulationService;


@Component 
public class GUI extends JFrame implements ActionListener{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextArea text;
	private JButton companiesHighestCapital;
	private JButton companiesLowestCapital;
	private JButton investorsWithTheHighestNumberOfShares;
	private JButton investorsThatHaveInvestedInTheMostCompanies;
	private JButton investorsWithTheLowestNumberOfShares;
	private JButton investorsLeastNumberOfCompanies;
	private JButton totalNumberOfTransactions;
	private JButton getAllInvestors;
	private JButton fullReport;
	private JButton restartSimulation;
	private JButton exit;
	static JProgressBar progressBar;
	
	private JPanel mainPanel;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JPanel panel4;

	
	
	@Autowired 
	private SimulationService simulation;
	
	@Autowired
	private InvestorService investorService;
	
	@Autowired
	private CompanyService companyService;
	
	
	public GUI() {
		
		this.setTitle("Report");
		setSize(1100,580);
		//this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		this.setLayout(null);
		
		mainPanel = new JPanel();
		mainPanel.validate();
		mainPanel.setBounds(3, 2, 611, 550);
		
		this.add(mainPanel);
		
		panel1 = new JPanel();
		panel1.setBorder(BorderFactory.createTitledBorder("Simulation Report"));
		panel1.validate();
		panel1.setVisible(true);
		panel1.setBounds(5, 5, 550, 650);
		mainPanel.add(panel1);
		
		text = new JTextArea(24, 30);
		JScrollPane scrollPane = new JScrollPane(text);
		panel1.add(scrollPane);
		
		
		progressBar = new JProgressBar();
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		panel1.add(progressBar);

		panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel2.setBorder(BorderFactory.createTitledBorder("Companies"));
		panel2.validate();
		panel2.setVisible(true);
		panel2.setBounds(630, 5, 350, 120);
		this.add(panel2);
		
		companiesHighestCapital = new JButton("Companies highest capital");
		companiesHighestCapital.setActionCommand("companiesHighestCapital");
		companiesHighestCapital.setBounds(630, 15, 40, 5);
		companiesHighestCapital.addActionListener(this);
		panel2.add(companiesHighestCapital);
		
		companiesLowestCapital = new JButton("Companies lowest capital");
		companiesLowestCapital.setActionCommand("companiesLowestCapital");
		companiesLowestCapital.setBounds(630, 35, 40, 5);
		companiesLowestCapital.addActionListener(this);
		panel2.add(companiesLowestCapital);
		
		JButton button = new JButton("Get all company details");
		button.setActionCommand("companies");
		button.addActionListener(this);
		panel2.add(button);
		
		panel3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel3.setBorder(BorderFactory.createTitledBorder("Investors"));
		panel3.validate();
		panel3.setBounds(630, 150, 350, 190);
		this.add(panel3);
		
		investorsWithTheHighestNumberOfShares = new JButton("Highest number of shares");
		investorsWithTheHighestNumberOfShares.setActionCommand("investorsWithTheHighestNumberOfShares");
		investorsWithTheHighestNumberOfShares.addActionListener(this);
		panel3.add(investorsWithTheHighestNumberOfShares);
		
		investorsWithTheLowestNumberOfShares = new JButton("Lowest number of shares ");
		investorsWithTheLowestNumberOfShares.setActionCommand("investorsWithTheLowestNumberOfShares");
		investorsWithTheLowestNumberOfShares.addActionListener(this);
		panel3.add(investorsWithTheLowestNumberOfShares);
		
		investorsThatHaveInvestedInTheMostCompanies = new JButton("Most amount of companies");
		investorsThatHaveInvestedInTheMostCompanies.setActionCommand("investorsThatHaveInvestedInTheMostCompanies");
		investorsThatHaveInvestedInTheMostCompanies.addActionListener(this);
		panel3.add(investorsThatHaveInvestedInTheMostCompanies);
		
		investorsLeastNumberOfCompanies = new JButton("Least amount of companies");
		investorsLeastNumberOfCompanies.setActionCommand("investorsLeastNumberOfCompanies");
		investorsLeastNumberOfCompanies.addActionListener(this);
		panel3.add(investorsLeastNumberOfCompanies);
		
		getAllInvestors = new JButton("Get all investor details");
		getAllInvestors.setActionCommand("investors");
		getAllInvestors.addActionListener(this);
		panel3.add(getAllInvestors);
		
		panel4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel4.setBorder(BorderFactory.createTitledBorder("Simulation"));
		panel4.validate();
		panel4.setBounds(630, 364, 350, 120);
	
		this.add(panel4);
		
		fullReport = new JButton("Generate full report");
		fullReport.setActionCommand("fullReport");
		fullReport.addActionListener(this);
		panel4.add(fullReport);
		
		totalNumberOfTransactions = new JButton("View total number of transactions");
		totalNumberOfTransactions.setActionCommand("totalNumberOfTransactions");
		totalNumberOfTransactions.addActionListener(this);
		panel4.add(totalNumberOfTransactions);
		
		restartSimulation = new JButton("Restart the simulation");
		restartSimulation.setActionCommand("restartSimulation");
		restartSimulation.addActionListener(this);
		panel4.add(restartSimulation);
		
		exit = new JButton("Exit");
		exit.setActionCommand("exit");
		exit.addActionListener(this);
		panel4.add(exit);

		validate();
		repaint();
		this.setVisible (true);
		//fill();
		
		
	}
	
	/**
	 * This method is responsible to return all the companies and all the details into a JTable
	 */
	public JPanel getCompanies() {

		JPanel panel =new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Companies"));	
		panel.setBounds(5, 5, 550, 450);
		panel.validate();
		panel.repaint();
		panel.setVisible(true);
	
		DefaultTableModel model = new DefaultTableModel();

		model.addColumn("id");
		model.addColumn("capital");
		model.addColumn("name");
		model.addColumn("share_price");
		model.addColumn("shares_sold");
		model.addColumn("initial_capital");
		model.addColumn("initial_share_price");
		model.addColumn("initial_shares");
		
		JTable table = new JTable(model);
		table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(2).setPreferredWidth(95);
		table.setPreferredScrollableViewportSize(new Dimension(500, 350));
		JScrollPane scrollPane = new JScrollPane(table);
		panel.add(scrollPane);
		
		for(int i = 0; i < companyService.getAllCompanies().size(); i++) {
			
			((DefaultTableModel)table.getModel()).addRow(new Object[] {
					
					companyService.getAllCompanies().get(i).getId(),
					companyService.getAllCompanies().get(i).getCapital(),
					companyService.getAllCompanies().get(i).getName(),
					companyService.getAllCompanies().get(i).getSharePrice(),
					companyService.getAllCompanies().get(i).getSharesSold(),
					companyService.getAllCompanies().get(i).getInitialCapital(),
					companyService.getAllCompanies().get(i).getInitialSharePrice(),
					companyService.getAllCompanies().get(i).getInitialShares()
			});
			
		}
		
		return panel;

	}
	
	public JPanel getInvestors() {
		
		JPanel panel4 = new JPanel();
		panel4.setBorder(BorderFactory.createTitledBorder("Investors"));	
		panel4.setBounds(5, 5, 550, 450);
		panel4.validate();
		panel4.setVisible(true);
		panel4.repaint();
		
		DefaultTableModel model1 = new DefaultTableModel();

		model1.addColumn("id");
		model1.addColumn("budget");
		model1.addColumn("initial_budget");
		model1.addColumn("name");
		model1.addColumn("number_of_companies_invested_in");
		model1.addColumn("total_number_of_shares_bought");
		
		JTable table1 = new JTable(model1);
		
		table1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table1.getColumnModel().getColumn(2).setPreferredWidth(95);
		table1.setPreferredScrollableViewportSize(new Dimension(500, 350));
		JScrollPane scrollPane = new JScrollPane(table1);
		panel4.add(scrollPane);
		
		for(int i = 0; i < investorService.getAllInvestors().size(); i++) {
			
			((DefaultTableModel)table1.getModel()).addRow(new Object[] {
					
					investorService.getAllInvestors().get(i).getId(),
					investorService.getAllInvestors().get(i).getBudget(),
					investorService.getAllInvestors().get(i).getInitialBudget(),
					investorService.getAllInvestors().get(i).getName(),
					investorService.getAllInvestors().get(i).getNumberOfCompaniesInvestedIn(),
					investorService.getAllInvestors().get(i).getTotalNumberOfSharesBought()
			});
		}
		
		return panel4;
		
	}
	public void start() {
		new GUI();
	}
	
	public static void fill() 
    { 
        int i = 0; 
        try { 
            while (i <= 100) { 
                // set text accoring to the level to which the bar is filled 
                if (i > 30 && i < 70) { 
                	progressBar.getAccessibleContext();
//                    progressBar.setString("wait for sometime"); 
                }else if (i > 70 && i < 99) { 
                    progressBar.setString("almost done"); 
                }else if(i == 100){
                	progressBar.setString("simulation finshed");
                }
                else
                    progressBar.setString("loading started"); 
  
                // fill the menu bar 
                progressBar.setValue(i + 10); 
  
                // delay the thread 
                Thread.sleep(4000); 
                i += 20; 
            } 
        } 
        catch (Exception e) { 
        } 
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("companiesHighestCapital")){
			mainPanel.removeAll();
			mainPanel.add(panel1);
			panel1.setVisible(true);
			getCompanies().setVisible(false);
		text.setText(simulation.highestCapital());
			
		}else if(e.getActionCommand().equals("companiesLowestCapital")) {
			mainPanel.removeAll();
			mainPanel.add(panel1);
			panel1.setVisible(true);
			getCompanies().setVisible(false);
			text.setText(simulation.lowestCapital());
			
		}else if(e.getActionCommand().equals("investorsWithTheHighestNumberOfShares")) {
			mainPanel.removeAll();
			mainPanel.add(panel1);
			panel1.setVisible(true);
			getCompanies().setVisible(false);
			text.setText(simulation.highestNumberOfShares());
			
		}else if(e.getActionCommand().equals("investorsThatHaveInvestedInTheMostCompanies")) {
			mainPanel.removeAll();
			mainPanel.add(panel1);
			panel1.setVisible(true);
			getCompanies().setVisible(false);
			text.setText(simulation.highestNumberOfCompanies());
			
		}else if(e.getActionCommand().equals("investorsWithTheLowestNumberOfShares")) {
			mainPanel.removeAll();
			mainPanel.add(panel1);
			panel1.setVisible(true);
			getCompanies().setVisible(false);
			text.setText(simulation.lowestNumberOfShares());
			
		}else if(e.getActionCommand().equals("investorsLeastNumberOfCompanies")) {
			mainPanel.removeAll();
			mainPanel.add(panel1);
			panel1.setVisible(true);
			getCompanies().setVisible(false);
			text.setText(simulation.lowestNumberOfCompanies());
			
		}else if(e.getActionCommand().equals("totalNumberOfTransactions")) {
			mainPanel.removeAll();
			mainPanel.add(panel1);
			panel1.setVisible(true);
			getCompanies().setVisible(false);
	
			text.setText(simulation.totalTransactions());
			
		}else if(e.getActionCommand().equals("fullReport")) {
			mainPanel.removeAll();
			mainPanel.add(panel1);
			panel1.setVisible(true);
			getCompanies().setVisible(false);
	
			text.setText(simulation.fullReport());
			
		}else if(e.getActionCommand().equals("companies")) {
			
			mainPanel.removeAll();
			
			JPanel companies = getCompanies();
			companies.validate();
			companies.setVisible(true);
			companies.repaint();
			mainPanel.add(companies);
			
//			mainPanel.remove(getInvestors());
			//mainPanel.add(getCompanies());
			//getCompanies().setVisible(true);
			
			//getInvestors().setVisible(false);
			panel1.setVisible(false);
			
			this.revalidate();
			this.repaint();
			
			
		}else if(e.getActionCommand().equals("investors")){
			
			mainPanel.removeAll();
			
			JPanel investors = getInvestors();
			investors.validate();
			investors.setVisible(true);
			investors.repaint();
			mainPanel.add(investors);
			
			//mainPanel.remove(getCompanies());
			//mainPanel.add(getInvestors());
	
			//getInvestors().setVisible(true);
			getCompanies().setVisible(false);
			panel1.setVisible(false);
			
			this.revalidate();
			this.repaint();
			
			
		}else if(e.getActionCommand().equals("restartSimulation")) {
			//this.dispose();
			simulation.restart();
			this.revalidate();
			this.repaint();

		}
		else if(e.getActionCommand().equals("exit")){
			System.exit(ABORT);;
			
		}
		
	}

}
