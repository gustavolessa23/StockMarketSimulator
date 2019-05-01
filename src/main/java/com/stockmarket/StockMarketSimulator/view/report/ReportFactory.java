package com.stockmarket.StockMarketSimulator.view.report;

import org.springframework.stereotype.Component;

@Component
public class ReportFactory {

	public static ReportFile create(ReportType type, String path, String content) {
		
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
	
}
