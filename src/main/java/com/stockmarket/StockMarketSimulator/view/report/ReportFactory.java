package com.stockmarket.StockMarketSimulator.view.report;

import org.springframework.stereotype.Component;

@Component
public class ReportFactory {
	
	private static ReportFactory instance;
	
	private ReportFactory() {
		
	}

	public ReportFile create(ReportType type, String path, String content) {
		
		if(type == ReportType.DOCX) {
			return new DocxReport(path, content);
		} else if(type == ReportType.PDF) {
			return new PdfReport(path, content);
		} else if(type == ReportType.TXT) {
			return new TxtReport(path, content);
		} else {
			return null;
		}
		
	}
	
	public static ReportFactory getInstance() {
		if(instance == null) {
			instance = new ReportFactory();
		}
		return instance;
	}
	
}
