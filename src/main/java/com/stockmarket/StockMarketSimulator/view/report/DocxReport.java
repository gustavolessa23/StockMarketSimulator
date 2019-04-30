package com.stockmarket.StockMarketSimulator.view.report;

import java.io.File;
import java.io.IOException;

import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.springframework.stereotype.Component;

@Component
public class DocxReport extends ReportFile {
	
	public DocxReport() {
		super();
	}
	
	public DocxReport(String path, String content) {
		super(path, content);
	}

	@Override
	public void generate() throws IOException {
		WordprocessingMLPackage wordPackage;
		try {
			wordPackage = WordprocessingMLPackage.createPackage();

			MainDocumentPart mainDocumentPart = wordPackage.getMainDocumentPart();
			
			mainDocumentPart.addParagraphOfText(this.content);
			
			File exportFile = new File(this.path);

			wordPackage.save(exportFile);
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Docx4JException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
