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

import com.stockmarket.StockMarketSimulator.model.Data;
import com.stockmarket.StockMarketSimulator.services.SimulationService;


@Component 
public class Report extends JFrame implements ActionListener{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextArea text;
	private JButton companiesHighestCapital;
	private JButton companiesLowestCapital;
	private JButton investorsWithTheHighestNumberOfShares;
	private JButton investorsThatHaveInvestedInTheMostCompanies;
	private JButton investorsWithTheLowestNumberOfShares;
	private JButton investorsLeastNumberOfCompanies;
	private JButton totalNumberOfTransactions;
	private JButton fullReport;
	static JProgressBar progressBar;
	
	private JPanel panel1;
	private JPanel panel;
	private JPanel panel4;
	
	@Autowired 
	private SimulationService simulation;
	
	@Autowired
	private Data data;

	
	public Report() {
		
		this.setTitle("Report");
		setSize(1100,500);
		//this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		this.setLayout(null);
		
		
		panel1 = new JPanel();
		panel1.setBorder(BorderFactory.createTitledBorder("Simulation Report"));
		panel1.validate();
		panel1.setBounds(5, 5, 610, 460);
		this.add(panel1);
		
		text = new JTextArea(25, 30);
		JScrollPane scrollPane = new JScrollPane(text);
		panel1.add(scrollPane);
		
		fullReport = new JButton("Full report");
		fullReport.setActionCommand("fullReport");
		fullReport.addActionListener(this);
		panel1.add(fullReport);
		
		progressBar = new JProgressBar();
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		panel1.add(progressBar);

		JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel2.setBorder(BorderFactory.createTitledBorder("Companies"));
		panel2.validate();
		panel2.setBounds(630, 9, 300, 150);
		this.add(panel2);
		
		companiesHighestCapital = new JButton("Companies Highest Capital");
		companiesHighestCapital.setBounds(630, 15, 40, 5);
		companiesHighestCapital.setActionCommand("companiesHighestCapital");
		companiesHighestCapital.addActionListener(this);
		panel2.add(companiesHighestCapital);
		
		companiesLowestCapital = new JButton("Companies Lowest Capital");
		companiesLowestCapital.setActionCommand("companiesLowestCapital");
		companiesLowestCapital.addActionListener(this);
		panel2.add(companiesLowestCapital);
		companiesLowestCapital.setBounds(630, 35, 40, 5);
		JButton button = new JButton("Get All Companies Detail");
		button.setActionCommand("companies");
		button.addActionListener(this);
		panel2.add(button);
		
		JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel3.setBorder(BorderFactory.createTitledBorder("Investors"));
		panel3.validate();
		panel3.setBounds(630, 160, 350, 250);
		this.add(panel3);
		
		
		investorsWithTheHighestNumberOfShares = new JButton("Investors with the highest number of shares");
		investorsWithTheHighestNumberOfShares.setActionCommand("investorsWithTheHighestNumberOfShares");
		investorsWithTheHighestNumberOfShares.addActionListener(this);
		panel3.add(investorsWithTheHighestNumberOfShares);
		
		investorsThatHaveInvestedInTheMostCompanies = new JButton("Investors with the most Companies");
		investorsThatHaveInvestedInTheMostCompanies.setActionCommand("investorsThatHaveInvestedInTheMostCompanies");
		investorsThatHaveInvestedInTheMostCompanies.addActionListener(this);
		panel3.add(investorsThatHaveInvestedInTheMostCompanies);
		
		investorsWithTheLowestNumberOfShares = new JButton("Investors with the lowest number of shares ");
		investorsWithTheLowestNumberOfShares.setActionCommand("investorsWithTheLowestNumberOfShares");
		investorsWithTheLowestNumberOfShares.addActionListener(this);
		panel3.add(investorsWithTheLowestNumberOfShares);
		
		
		
		investorsLeastNumberOfCompanies = new JButton("Least amount of Companies");
		investorsLeastNumberOfCompanies.setActionCommand("investorsLeastNumberOfCompanies");
		investorsLeastNumberOfCompanies.addActionListener(this);
		panel3.add(investorsLeastNumberOfCompanies);
		
		totalNumberOfTransactions = new JButton("Total number of transactions");
		totalNumberOfTransactions.setActionCommand("totalNumberOfTransactions");
		totalNumberOfTransactions.addActionListener(this);
		panel3.add(totalNumberOfTransactions);
	
		validate();
		repaint();
		this.setVisible (true);
		fill();
		
		
	}
	
	/**
	 * This method is responsible to return all the companies and all the details into a JTable
	 */
	public void getCompanies() {

		panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Companies"));	
		panel.setBounds(5, 5, 550, 490);
		panel.validate();
		panel.setVisible(true);
		panel.repaint();
		this.add(panel);
		
		DefaultTableModel model = new DefaultTableModel();

		model.addColumn("id");
		model.addColumn("capital");
		model.addColumn("name");
		model.addColumn("share_price");
		model.addColumn("shares_sold");
		model.addColumn("initial_capital");
		model.addColumn("initial_share_price");
		model.addColumn("initial_shares");
		
		table = new JTable(model);
		table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(2).setPreferredWidth(95);
		table.setPreferredScrollableViewportSize(new Dimension(500, 350));
		JScrollPane scrollPane = new JScrollPane(table);
		panel.add(scrollPane);
		
		for(int i = 0; i < data.getCompanies().size(); i++) {
			
			((DefaultTableModel)table.getModel()).addRow(new Object[] {
					
					data.getCompanies().get(i).getId(),
					data.getCompanies().get(i).getCapital(),
					data.getCompanies().get(i).getName(),
					data.getCompanies().get(i).getSharePrice(),
					data.getCompanies().get(i).getSharesSold(),
					data.getCompanies().get(i).getInitialCapital(),
					data.getCompanies().get(i).getInitialSharePrice(),
					data.getCompanies().get(i).getInitialShares()
			});
			
		}

	}
	
	public void getInvestors() {
		
		panel4 = new JPanel();
		panel4.setBorder(BorderFactory.createTitledBorder("Investors"));	
		panel4.setBounds(5, 5, 550, 490);
		panel4.validate();
		panel4.setVisible(true);
		panel4.repaint();
		this.add(panel4);
		
		DefaultTableModel model = new DefaultTableModel();

		model.addColumn("id");
		model.addColumn("budget");
		model.addColumn("name");
		model.addColumn("share_price");
		model.addColumn("shares_sold");
		model.addColumn("initial_capital");
		model.addColumn("initial_share_price");
		model.addColumn("initial_shares");
		
		table = new JTable(model);
		table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(2).setPreferredWidth(95);
		table.setPreferredScrollableViewportSize(new Dimension(500, 350));
		JScrollPane scrollPane = new JScrollPane(table);
		panel4.add(scrollPane);
		
	}
	public void start() {
		new Report();
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
                Thread.sleep(3000); 
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
			panel.setVisible(false);
			panel1.setVisible(true);
		text.setText(simulation.highestCapital());
			
		}else if(e.getActionCommand().equals("companiesLowestCapital")) {
			panel.setVisible(false);
			panel1.setVisible(true);
			text.setText(simulation.lowestCapital());
			
		}else if(e.getActionCommand().equals("investorsWithTheHighestNumberOfShares")) {
			panel.setVisible(false);
			panel1.setVisible(true);
			text.setText(simulation.highestNumberOfShares());
			
		}else if(e.getActionCommand().equals("investorsThatHaveInvestedInTheMostCompanies")) {
			panel.setVisible(false);
			panel1.setVisible(true);
			text.setText(simulation.highestNumberOfCompanies());
			
		}else if(e.getActionCommand().equals("investorsWithTheLowestNumberOfShares")) {
			panel.setVisible(false);
			panel1.setVisible(true);
			text.setText(simulation.lowestNumberOfShares());
			
		}else if(e.getActionCommand().equals("investorsLeastNumberOfCompanies")) {
			panel.setVisible(false);
			panel1.setVisible(true);
			text.setText(simulation.lowestNumberOfCompanies());
			
		}else if(e.getActionCommand().equals("totalNumberOfTransactions")) {
			panel.setVisible(false);
			panel1.setVisible(true);
			text.setText(simulation.totalTransactions());
			
		}else if(e.getActionCommand().equals("fullReport")) {
			panel.setVisible(false);
			panel1.setVisible(true);
			text.setText(simulation.fullReport());
			
		}else if(e.getActionCommand().equals("companies")) {
			panel1.setVisible(false);
			getCompanies();
		}
		
	}

}
