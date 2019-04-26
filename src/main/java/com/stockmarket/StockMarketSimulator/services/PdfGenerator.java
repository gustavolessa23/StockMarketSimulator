package com.stockmarket.StockMarketSimulator.services;

import java.io.FileOutputStream;

import org.springframework.stereotype.Component;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.FileNotFoundException;
import java.io.IOException;

@Component
public class PdfGenerator {

	public void generatePdf(String content) throws FileNotFoundException {
		
		PdfWriter writer = new PdfWriter("teste.pdf");
		PdfDocument pdf = new PdfDocument(writer);
		Document document = new Document(pdf);
		document.add(new Paragraph(content));
		document.close();
		
	}
	
	

	
	
	
	
	//	Document document = new Document(PageSize.A4, 15f, 15f, 75f, 75f);
//	
//	PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("iTextHelloWorld.pdf"));
//	PdfDocument pdf = new PdfDocument(writer);
//	Document document = new Document(pdf);
//	document.add(new Paragraph("Hello World!"));
//	document.close();
//	
}
