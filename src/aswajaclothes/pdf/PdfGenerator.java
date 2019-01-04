/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aswajaclothes.pdf;

import aswajaclothes.connection.ConnectionManager;
import aswajaclothes.model.master.CustomerModel;
import aswajaclothes.model.master.InvoiceModel;
import aswajaclothes.model.master.ItemPesananModel;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author littleflower
 */
public class PdfGenerator {
    
    private static final String RESULT_PATH = "./print";
    
    private static final Font SMALL_FONT = new Font(FontFamily.HELVETICA, 10);
    
    private static final Font HEADER_FONT = new Font(FontFamily.HELVETICA, 18);
    
    
    public static void cetakInvoicePembelian() {
        // Todo
    }
    
    public static void cetakInvoicePenjualan(String kodeInvoice, String kodePesanan, int ppn, Date tanggalInvoice) throws FileNotFoundException, DocumentException, IOException {
        Document document = createDocument();
        PdfWriter.getInstance(document, new FileOutputStream(createFile(RESULT_PATH + "/invoice-penjualan-"  + kodeInvoice + ".pdf")));

        document.open();
        
        HashMap<String, String> headerData = new HashMap<>();
        headerData.put("Date", new SimpleDateFormat("dd MMMM yyyy").format(tanggalInvoice));
        headerData.put("Invoice No.", kodeInvoice);
        headerData.put("No. Pesanan", kodePesanan);
        setupHeader("Invoice", headerData, document);
        
        insertSpacing(24, document);
        
        InvoiceModel invoice = new ConnectionManager().getInvoice(kodeInvoice);
        
        CustomerModel customer = new ConnectionManager().getCustomer(invoice.getKodeKustomer());
        ArrayList<String> addressData = new ArrayList<>();
        addressData.add(customer.getName());
        addressData.add(customer.getAlamat());
        addressData.add(customer.getNoTelepon());
        setupAddres(addressData, document);
        
        List<ItemPesananModel> items = new ConnectionManager().getDaftarPesananItem(kodePesanan);
        
        document.close();
    }
    
    private static void insertSpacing(float spaceValue, Document document) throws DocumentException {
        Paragraph space = new Paragraph("");
        space.setSpacingAfter(spaceValue);
        document.add(space);
    }
    
    private static void setupHeader(String title, HashMap<String, String> data, Document document) throws DocumentException {
        Paragraph paragraph = new Paragraph(title);
        paragraph.setAlignment(Rectangle.ALIGN_CENTER);
        paragraph.setFont(HEADER_FONT);
        paragraph.setSpacingAfter(24);
        document.add(paragraph);
        
        
        PdfPTable table = new PdfPTable(5);
        table.setTotalWidth(new float[]{ 100, 100, 100, 80, 160 });
        table.setWidthPercentage(100);
        
        PdfPCell blankCell = new PdfPCell();
        blankCell.setColspan(3);
        blankCell.setBorder(Rectangle.NO_BORDER);
        
        data.forEach((String key, String value) -> {
            table.addCell(blankCell);
        
            PdfPCell cell = new PdfPCell(new Phrase(key, SMALL_FONT));
            cell.setBorder(Rectangle.BOX);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(value, SMALL_FONT));
            cell.setBorder(Rectangle.BOX);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
        });
        
        document.add(table);
    }
    
    private static void setupAddres(List<String> data, Document document) throws DocumentException {
        String text = "Kepada Yth.\n";
        for (String value : data) {
            text += value + "\n";
        }
        Paragraph paragraph = new Paragraph(text);
        paragraph.setAlignment(Rectangle.ALIGN_RIGHT);
        paragraph.setFont(SMALL_FONT);
        paragraph.setSpacingAfter(0);
        document.add(paragraph);
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
