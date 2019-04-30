package com.stockmarket.StockMarketSimulator.view.report;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.stereotype.Component;

@Component
public class PdfReport extends ReportFile {

	public PdfReport() {
		super();
	}
	
	public PdfReport(String path, String content) {
		super(path, content);
	}

	
	@Override
	public void generate() throws IOException {
		PdfWriter writer = new PdfWriter(this.path);
		PdfDocument pdf = new PdfDocument(writer);
		Document document = new Document(pdf);
		document.add(new Paragraph(this.content));
		document.close();
	}
	
	public void generatePdf(String content) throws FileNotFoundException {
		
		PdfWriter writer = new PdfWriter("teste.pdf");
		PdfDocument pdf = new PdfDocument(writer);
		Document document = new Document(pdf);
		document.add(new Paragraph(content));
		document.close();
		
	}

}
