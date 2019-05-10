package com.stockmarket.StockMarketSimulator.view.report;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public abstract class ReportFile {
	
	protected String path;
	protected String content;
	
	public abstract void generate() throws IOException;
	
	public ReportFile() {
		
	}
	
	public ReportFile(String path, String content) {
		this.path = path;
		this.content = content;
	}

	public void setPath(String path) {
		this.path = path;
	}
	public String getPath() {
		return this.path;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getContenth() {
		return this.content;
	}
	
	public List<String> convertContentToList(){
		
		return Arrays.asList(this.content.split("\\r?\\n"));
	}

}
