package com.stockmarket.StockMarketSimulator.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import com.stockmarket.StockMarketSimulator.model.Data;
import com.stockmarket.StockMarketSimulator.services.CompanyService;
import com.stockmarket.StockMarketSimulator.services.InvestorService;
import com.stockmarket.StockMarketSimulator.services.SimulationService;
import com.stockmarket.StockMarketSimulator.services.TransactionService;


@SpringBootApplication
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
	private JButton savePDFFile;
	private JButton transactions;
	private String choosertitle = "";	
	
	private JFileChooser chooser;
	
	private JPanel mainPanel;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JPanel panel4;

	private List<JButton> buttonsList;
	
	
	@Autowired 
	private SimulationService simulation;
	
	@Autowired
	private InvestorService investorService;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired 
	private TransactionService transactionService;
	
	
	public GUI() {
		buttonsList = new ArrayList<>(); // create new list for buttons
		
		// set basic config
		this.setTitle("Report");
		setSize(1200,500);
		//this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		this.setLayout(null);
		
		// set main panel
		mainPanel = new JPanel();
		mainPanel.validate();
		mainPanel.setBounds(0, 2, 610, 460);

		this.add(mainPanel);
		
		// create save file panel
		JPanel saveFile = new JPanel();
		saveFile.validate();
		saveFile.setBounds(610, 20, 180, 80 );
		this.add(saveFile);
		
		// Button to save file
		savePDFFile = new JButton("Save PDF File");
		buttonsList.add(savePDFFile);
		savePDFFile.addActionListener(this);
		savePDFFile.setActionCommand("savePDF");
		saveFile.add(savePDFFile);
		
		
		// button to show transactions
		transactions = new JButton("Get All Transactions");
		buttonsList.add(transactions);
		transactions.addActionListener(this);
		transactions.setActionCommand("transactions");
		saveFile.add(transactions);
		
		// create panel to wrap text area
		panel1 = new JPanel();
		panel1.setBorder(BorderFactory.createTitledBorder("Simulation Report"));
		panel1.validate();
		panel1.setBounds(3, 5, 550, 450);
		mainPanel.add(panel1);
		
		// create text area
		text = new JTextArea(24, 30);
		text.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(text);
		panel1.add(scrollPane);
		
		// create panel for companies options
		panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel2.setBorder(BorderFactory.createTitledBorder("Companies"));
		panel2.validate();
		panel2.setVisible(true);
		panel2.setBounds(790, 5, 300, 150);
		this.add(panel2);
		
		// create button for highest capital
		companiesHighestCapital = new JButton("Companies Highest Capital");
		buttonsList.add(companiesHighestCapital);
		companiesHighestCapital.setBounds(630, 15, 40, 5);
		companiesHighestCapital.setActionCommand("companiesHighestCapital");
		companiesHighestCapital.addActionListener(this);
		panel2.add(companiesHighestCapital);
		
		// create button for lowest capital
		companiesLowestCapital = new JButton("Companies Lowest Capital");
		buttonsList.add(companiesLowestCapital);
		companiesLowestCapital.setActionCommand("companiesLowestCapital");
		companiesLowestCapital.addActionListener(this);
		panel2.add(companiesLowestCapital);
		companiesLowestCapital.setBounds(630, 35, 40, 5);
		
		// create button for all companies
		JButton button = new JButton("Get All Companies Detail");
		buttonsList.add(button);
		button.setActionCommand("companies");
		button.addActionListener(this);
		panel2.add(button);
		
		// create panel for investors
		panel3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel3.setBorder(BorderFactory.createTitledBorder("Investors"));
		panel3.validate();
		panel3.setBounds(790, 150, 350, 210);
		this.add(panel3);
		
		// create button for highest number of shares
		investorsWithTheHighestNumberOfShares = new JButton("Investors with the highest number of shares");
		buttonsList.add(investorsWithTheHighestNumberOfShares);
		investorsWithTheHighestNumberOfShares.setActionCommand("investorsWithTheHighestNumberOfShares");
		investorsWithTheHighestNumberOfShares.addActionListener(this);
		panel3.add(investorsWithTheHighestNumberOfShares);
		
		// create button for highest number of companies
		investorsThatHaveInvestedInTheMostCompanies = new JButton("Investors with the most Companies");
		buttonsList.add(investorsThatHaveInvestedInTheMostCompanies);
		investorsThatHaveInvestedInTheMostCompanies.setActionCommand("investorsThatHaveInvestedInTheMostCompanies");
		investorsThatHaveInvestedInTheMostCompanies.addActionListener(this);
		panel3.add(investorsThatHaveInvestedInTheMostCompanies);
		
		
		// create button for lowest number of shares
		investorsWithTheLowestNumberOfShares = new JButton("Investors with the lowest number of shares ");
		buttonsList.add(investorsWithTheLowestNumberOfShares);
		investorsWithTheLowestNumberOfShares.setActionCommand("investorsWithTheLowestNumberOfShares");
		investorsWithTheLowestNumberOfShares.addActionListener(this);
		panel3.add(investorsWithTheLowestNumberOfShares);
		
		
		// create button for lowest number of companies		
		investorsLeastNumberOfCompanies = new JButton("Least amount of Companies");
		buttonsList.add(investorsLeastNumberOfCompanies);
		investorsLeastNumberOfCompanies.setActionCommand("investorsLeastNumberOfCompanies");
		investorsLeastNumberOfCompanies.addActionListener(this);
		panel3.add(investorsLeastNumberOfCompanies);
		
		// create button for all investors information
		getAllInvestors = new JButton("Get all Investors");
		buttonsList.add(getAllInvestors);
		getAllInvestors.addActionListener(this);
		getAllInvestors.setActionCommand("investors");
		panel3.add(getAllInvestors);
		
		// create panel for simulation
		panel4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel4.setBorder(BorderFactory.createTitledBorder("Simulation"));
		panel4.validate();
		panel4.setBounds(790, 364, 350, 100);
		this.add(panel4);
		
		// create button for full report
		fullReport = new JButton("Full report");
		buttonsList.add(fullReport);
		fullReport.setActionCommand("fullReport");
		fullReport.addActionListener(this);
		panel4.add(fullReport);
		
		// create button for total number of transactions
		totalNumberOfTransactions = new JButton("Total number of transactions");
		buttonsList.add(totalNumberOfTransactions);
		totalNumberOfTransactions.setActionCommand("totalNumberOfTransactions");
		totalNumberOfTransactions.addActionListener(this);
		panel4.add(totalNumberOfTransactions);
		
		
		
		
	
		validate();
		repaint();
		this.setVisible (true);
		//fill();
		this.setButtonsActive(false);
		
		
	}
	
	public void setButtonsActive(boolean state) {
		for(JButton button : buttonsList) {
			button.setEnabled(state);
		}
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
	
		DefaultTableModel model = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column)
			 {
			     return false;
			 }
			  
		};

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
		
		TableRowSorter<DefaultTableModel> sort = new TableRowSorter<DefaultTableModel>(model);
		table.setRowSorter(sort);
		
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
		
		DefaultTableModel model1 = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column)
			 {
			     return false;
			 }
			  
		};

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
		
		TableRowSorter<DefaultTableModel> sort = new TableRowSorter<DefaultTableModel>(model1);
		table1.setRowSorter(sort);
		
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
	
	/**
	 * This method is responsible to create a transaction table, and returns all the transactions made by the simulation.
	 * @return
	 */
	public JPanel getAllTransactions() {
		
		JPanel panel =new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Transactions"));	
		panel.setBounds(5, 5, 450, 450);
		panel.validate();
		panel.repaint();
		panel.setVisible(true);
		
		DefaultTableModel model = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column)
			 {
			     return false;
			 }
			  
		};

		model.addColumn("id");
		model.addColumn("Companies");
		model.addColumn("Date");
		model.addColumn("Investors");
		
		
		
		JTable table = new JTable(model);
		table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(2).setPreferredWidth(95);
		table.setPreferredScrollableViewportSize(new Dimension(400, 350));
		JScrollPane scrollPane = new JScrollPane(table);
		panel.add(scrollPane);
		
		
		TableRowSorter<DefaultTableModel> sort = new TableRowSorter<DefaultTableModel>(model);
		table.setRowSorter(sort);
		for(int i = 0; i < transactionService.getAllTransactions().size(); i++) {
			
			((DefaultTableModel)table.getModel()).addRow(new Object[] {
					
					transactionService.getAllTransactions().get(i).getTransactionId(),
					transactionService.getAllTransactions().get(i).getCompany(),
					transactionService.getAllTransactions().get(i).getDate(),
					transactionService.getAllTransactions().get(i).getInvestor(),
					
					
			});
		}
		
		
		return panel;
		
	}
	public void start() {
		new GUI();
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
			text.setText(simulation.highestNumberOfShares());
			
		}else if(e.getActionCommand().equals("investorsThatHaveInvestedInTheMostCompanies")) {
			mainPanel.removeAll();
			mainPanel.add(panel1);
			panel1.setVisible(true);
			text.setText(simulation.highestNumberOfCompanies());
			
		}else if(e.getActionCommand().equals("investorsWithTheLowestNumberOfShares")) {
			mainPanel.removeAll();
			mainPanel.add(panel1);
			panel1.setVisible(true);
			text.setText(simulation.lowestNumberOfShares());
			
		}else if(e.getActionCommand().equals("investorsLeastNumberOfCompanies")) {
			mainPanel.removeAll();
			mainPanel.add(panel1);
			panel1.setVisible(true);
			text.setText(simulation.lowestNumberOfCompanies());
			
		}else if(e.getActionCommand().equals("totalNumberOfTransactions")) {
			mainPanel.removeAll();
			mainPanel.add(panel1);
			panel1.setVisible(true);
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
			
			getCompanies().setVisible(false);
			panel1.setVisible(false);
			
			this.revalidate();
			this.repaint();
			
			
		}else if(e.getActionCommand().equals("transactions")) {
			
			mainPanel.removeAll();
			
			JPanel transaction = getAllTransactions();
			transaction.validate();
			transaction.setVisible(true);
			transaction.repaint();
			mainPanel.add(transaction);
			
			this.revalidate();
			this.repaint();
			
		}else if(e.getActionCommand().equals("savePDF")) {
			
	    	
//		    chooser = new JFileChooser(); 
//		    chooser.setCurrentDirectory(new java.io.File("."));
//		    chooser.setDialogTitle((choosertitle.isEmpty() ? "report" : choosertitle));
//		    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//		    
//		    chooser.setAcceptAllFileFilterUsed(true);
//		    
//		    
//		    if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION ) { 
//
//		    	System.out.println("getCurrentDirectory(): " +  chooser.getCurrentDirectory());
//		    	System.out.println("getSelectedFile() : " +  chooser.getSelectedFile());
//
//		    } else {
//		    	System.out.println("No Selection ");
//		    }
			
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Specify a file to save");   
			 
			int userSelection = fileChooser.showSaveDialog(this);
			 
			if (userSelection == JFileChooser.APPROVE_OPTION) {
			    File fileToSave = fileChooser.getSelectedFile();
			    System.out.println("Save as file: " + fileToSave.getAbsolutePath());
			    simulation.generatePdfReport(fileToSave.getAbsolutePath());

			}
		    
			
		}
		
	}

}
