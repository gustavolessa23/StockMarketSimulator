package com.stockmarket.StockMarketSimulator.view.report;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
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
	public void generate() {
		XWPFDocument document = new XWPFDocument();
		
		XWPFParagraph title = document.createParagraph();
		title.setAlignment(ParagraphAlignment.CENTER);
		
		XWPFRun titleRun = title.createRun();
		titleRun.setText("Stock Market Simulation Results");
		titleRun.setColor("00008B");
		titleRun.setBold(true);
		titleRun.setFontFamily("Courier");
		titleRun.setFontSize(18);
		
		XWPFParagraph content = document.createParagraph();
		title.setAlignment(ParagraphAlignment.LEFT);
		
		
		XWPFRun contentRun = content.createRun();
		
		List<String> text = convertContentToList();	
		
		for(int x = 0; x < text.size(); x++) {
			contentRun.setText(text.get(x));
			contentRun.addCarriageReturn();
		}
	
		try {
			FileOutputStream out = new FileOutputStream(new File(this.path));
			document.write(out);
			out.close();
			document.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private List<String> convertContentToList(){
		
		return Arrays.asList(this.content.split("\\r?\\n"));
	}

}
