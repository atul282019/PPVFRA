package com.ppvfra.common;

import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFfileGeneration {

	private static String FILE = "c:/kns1/PDF/FirstPdf.pdf";
	private String[] images;
    private String logo; 
     
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,Font.BOLD);
    private Font textFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,Font.NORMAL);
	
	public static void pdfFile(String text) throws IOException, DocumentException{
		
		Document document = new Document();
		 PdfWriter writer =PdfWriter.getInstance(document, new FileOutputStream("AyushPdf.pdf"));
		 
		document.open();
		Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
		Chunk chunk = new Chunk(text, font);
		 
		document.add(chunk);
		document.close();
		writer.close();

		
	}
}
