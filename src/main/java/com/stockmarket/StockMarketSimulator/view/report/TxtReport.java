package com.stockmarket.StockMarketSimulator.view.report;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Component;

@Component
public class TxtReport extends ReportFile {

	public TxtReport() {
		super();
	}
	
	public TxtReport(String path, String content) {
		super(path, content);
	}

	@Override
	public void generate() throws IOException {

	    BufferedWriter writer = new BufferedWriter(new FileWriter(this.path));
	    writer.write(this.content);
	     
	    writer.close();
	
	}

}
