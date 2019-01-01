/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aswajaclothes.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Header;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author littleflower
 */
public class PdfGenerator {
    
    private static final String RESULT_PATH = "./print";
    
    public static void cetakInvoicePembelian() {
        // Todo
    }
    
    public static void cetakInvoicePenjualan(String kodeInvoice, String kodePesanan, int ppn, Date tanggalInvoice) throws FileNotFoundException, DocumentException, IOException {
        Document document = createDocument();
        PdfWriter.getInstance(document, new FileOutputStream(createFile(RESULT_PATH + "/invoice-penjualan-"  + kodeInvoice + ".pdf")));
        
        Font smallfont = new Font(FontFamily.HELVETICA, 10);
        
        Font headerFont = new Font(FontFamily.HELVETICA, 18);
        headerFont.setStyle(Font.BOLD);

        document.open();
        
        Paragraph paragraph = new Paragraph("Invoice");
        paragraph.setAlignment(Rectangle.ALIGN_CENTER);
        paragraph.setFont(headerFont);
        paragraph.setSpacingAfter(24);
        document.add(paragraph);
        
        
        PdfPTable table = new PdfPTable(5);
        table.setTotalWidth(new float[]{ 100, 100, 100, 80, 160 });
        table.setWidthPercentage(100);
        
        PdfPCell blankCell = new PdfPCell();
        blankCell.setColspan(3);
        blankCell.setBorder(Rectangle.NO_BORDER);
        table.addCell(blankCell);
        
        PdfPCell cell = new PdfPCell(new Phrase("Date", smallfont));
        cell.setBorder(Rectangle.BOX);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase(new SimpleDateFormat("dd MMMM yyyy").format(tanggalInvoice), smallfont));
        cell.setBorder(Rectangle.BOX);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        
        table.addCell(blankCell);

        cell = new PdfPCell(new Phrase("Invoice No", smallfont));
        cell.setBorder(Rectangle.BOX);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase(kodeInvoice, smallfont));
        cell.setBorder(Rectangle.BOX);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        
        table.addCell(blankCell);

        cell = new PdfPCell(new Phrase("No Pesanan", smallfont));
        cell.setBorder(Rectangle.BOX);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase(kodePesanan, smallfont));
        cell.setBorder(Rectangle.BOX);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        
        document.add(table);
        document.close();
    }
    
    public static void demo() throws FileNotFoundException, DocumentException, IOException {
        Document document = createDocument();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(createFile(RESULT_PATH + "/document.pdf")));
        Font smallfont = new Font(FontFamily.HELVETICA, 10);
        document.open();
        document.add(new Paragraph("Hello World"));
        PdfPTable table = new PdfPTable(2);
        table.setTotalWidth(new float[]{ 160, 120 });
        table.setWidthPercentage(100);
        table.setLockedWidth(true);
        PdfContentByte cb = writer.getDirectContent();
        // first row
        PdfPCell cell = new PdfPCell(new Phrase("Some text here"));
        cell.setFixedHeight(30);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        table.addCell(cell);
        // second row
        cell = new PdfPCell(new Phrase("Some more text", smallfont));
        cell.setFixedHeight(30);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);
        Barcode128 code128 = new Barcode128();
        code128.setCode("14785236987541");
        code128.setCodeType(Barcode128.CODE128);
        Image code128Image = code128.createImageWithBarcode(cb, null, null);
        cell = new PdfPCell(code128Image, true);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setFixedHeight(30);
        table.addCell(cell);
        // third row
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("and something else here", smallfont));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(cell);
        document.add(table);
        document.close();
    }
    
    private static File createFile(String path) throws IOException {
        File file = new File(path);
        file.getParentFile().mkdirs();
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }
    
    private static Document createDocument() {
        return new Document(PageSize.A4);
    }
    
}
