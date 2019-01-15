/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aswajaclothes.pdf;

import aswajaclothes.connection.ConnectionManager;
import aswajaclothes.entity.Barang;
import aswajaclothes.entity.InvoicePesanan;
import aswajaclothes.entity.Kustomer;
import aswajaclothes.entity.PesananDetail;
import aswajaclothes.model.master.InvoiceModel;
import aswajaclothes.model.master.ItemPesananModel;
import aswajaclothes.model.master.PembelianBarangModel;
import aswajaclothes.model.master.PembelianModel;
import aswajaclothes.model.master.PesananModel;
import aswajaclothes.model.master.SupplierModel;
import aswajaclothes.util.CurrencyUtil;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author littleflower
 */
public class PdfGenerator {
    
    private static final String RESULT_PATH = "./print";
    
    private static final Font SMALL_FONT = new Font(FontFamily.HELVETICA, 10);
    
    private static final Font HEADER_FONT = new Font(FontFamily.HELVETICA, 18);
    
    private static final Font BIG_HEADER_FONT = new Font(FontFamily.HELVETICA, 26);
    
    public static void cetakLaporanPenjualan(Date start, Date end) throws IOException, DocumentException, FileNotFoundException {
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dddd");
        
        Document document = createDocument();
        File file = createFile(RESULT_PATH + "/laporan-penjualan-"  + sdf.format(start) + "-" + sdf.format(end) + ".pdf");
        PdfWriter.getInstance(document, new FileOutputStream(file));

        document.open();
        
        insertKopSurat(document);
        
        HashMap<String, String> headerData = new HashMap<>();
        setupHeader("Laporan Penjualan Detail", headerData, document);
                
        document.close();
        Desktop.getDesktop().open(file);
    }
    
    public static void cetakLaporanPembelian(Date start, Date end) throws IOException, FileNotFoundException, DocumentException {
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dddd");
        
        Document document = createDocument();
        File file = createFile(RESULT_PATH + "/laporan-penjualan-"  + sdf.format(start) + "-" + sdf.format(end) + ".pdf");
        PdfWriter.getInstance(document, new FileOutputStream(file));

        document.open();
        
        insertKopSurat(document);
        
        HashMap<String, String> headerData = new HashMap<>();
        setupHeader("Laporan Pembelian Detail", headerData, document);
                
        document.close();
        Desktop.getDesktop().open(file);
    }
    
    public static void cetakLaporanBarangTerlaris(Date start, Date end) throws IOException, FileNotFoundException, DocumentException {
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dddd");
        
        Document document = createDocument();
        File file = createFile(RESULT_PATH + "/laporan-penjualan-"  + sdf.format(start) + "-" + sdf.format(end) + ".pdf");
        PdfWriter.getInstance(document, new FileOutputStream(file));

        document.open();
        
        insertKopSurat(document);
        
        HashMap<String, String> headerData = new HashMap<>();
        setupHeader("Laporan Barang Terlaris", headerData, document);
                
        document.close();
        Desktop.getDesktop().open(file);
    }
    
    public static void cetakLaporanCostRevenue(Date start, Date end) throws IOException, FileNotFoundException, DocumentException {
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dddd");
        
        Document document = createDocument();
        File file = createFile(RESULT_PATH + "/laporan-penjualan-"  + sdf.format(start) + "-" + sdf.format(end) + ".pdf");
        PdfWriter.getInstance(document, new FileOutputStream(file));

        document.open();
        
        insertKopSurat(document);
        
        HashMap<String, String> headerData = new HashMap<>();
        setupHeader("Laporan Cost Revenue", headerData, document);
                
        document.close();
        Desktop.getDesktop().open(file);
    }

    
    public static void cetakReturPenjualan(String kodeRetur, PesananModel pesanan, List<ItemPesananModel> items) throws IOException, DocumentException, BadElementException, FileNotFoundException {
        Document document = createDocument();
        File file = createFile(RESULT_PATH + "/retur-penjualan-"  + kodeRetur + ".pdf");
        PdfWriter.getInstance(document, new FileOutputStream(file));

        document.open();
        
        insertKopSurat(document);
        
        HashMap<String, String> headerData = new HashMap<>();
        headerData.put("Date", new SimpleDateFormat("dd MMMM yyyy").format(new Date()));
        headerData.put("No. Retur", kodeRetur);
        headerData.put("No. Pesanan", pesanan.getKodePesanan());
        setupHeader("Retur Penjualan", headerData, document);
        
        Kustomer customer = ConnectionManager.getDefaultEntityManager().createNamedQuery("Kustomer.findByKodeKustomer", Kustomer.class).setParameter("kodeKustomer", pesanan.getKodeKustomer()).getSingleResult();
        ArrayList<String> addressData = new ArrayList<>();
        addressData.add(customer.getNamaKustomer());
        addressData.add(customer.getAlamat());
        addressData.add(customer.getNoTelepon());
        setupAddres(addressData, document);
        
        insertSpacing(24, document);
//        setupDaftarItemPesanan(items, document);
        
        ArrayList<HashMap<String, String>> details = new ArrayList<>();
        HashMap<String, String> item1 = new HashMap<>();
        item1.put("Subtotal", CurrencyUtil.getInstance().formatCurrency(pesanan.getTotal() - pesanan.getOngkir()));
        details.add(item1);
        HashMap<String, String> item2 = new HashMap<>();
        item2.put("Ongkir", CurrencyUtil.getInstance().formatCurrency(pesanan.getOngkir()));
        details.add(item2);
        HashMap<String, String> item3 = new HashMap<>();
        item3.put("PPN", CurrencyUtil.getInstance().formatCurrency(0));
        details.add(item3);
        HashMap<String, String> item4 = new HashMap<>();
        item4.put("Total Bayar", CurrencyUtil.getInstance().formatCurrency(pesanan.getTotal()));
        details.add(item4);
        setupInvoiceTableFooter(details, document);
        
        insertSpacing(48, document);
        insertFooterText("We are aplogies for the inconvenience!", document);
        
        document.close();
        Desktop.getDesktop().open(file);
    }
    
    public static void cetakSuratJalan(String kodeSuratJalan, PesananModel pesanan, List<ItemPesananModel> items) throws DocumentException, FileNotFoundException, BadElementException, IOException {
        Document document = createDocument();
        File file = createFile(RESULT_PATH + "/surat-jalan-"  + kodeSuratJalan + ".pdf");
        PdfWriter.getInstance(document, new FileOutputStream(file));

        document.open();
        
        insertKopSurat(document);
        
        HashMap<String, String> headerData = new HashMap<>();
        headerData.put("Date", new SimpleDateFormat("dd MMMM yyyy").format(new Date()));
        headerData.put("No. Surat Jalan", kodeSuratJalan);
        headerData.put("No. Pesanan", pesanan.getKodePesanan());
        setupHeader("Surat Jalan", headerData, document);
        
        Kustomer customer = ConnectionManager.getDefaultEntityManager().createNamedQuery("Kustomer.findByKodeKustomer", Kustomer.class).setParameter("kodeKustomer", pesanan.getKodeKustomer()).getSingleResult();
        ArrayList<String> addressData = new ArrayList<>();
        addressData.add(customer.getNamaKustomer());
        addressData.add(customer.getAlamat());
        addressData.add(customer.getNoTelepon());
        setupAddres(addressData, document);
        
        insertSpacing(24, document);
        setupDaftarItemPesananSuratJalan(items, document);
        
        insertSpacing(48, document);
        insertFooterText("Thank you for shopping with us!", document);
        
        document.close();
        Desktop.getDesktop().open(file);
    }
    
    public static void cetakInvoicePembelian(String purchaseNumber, PembelianModel pembelianModel, List<PembelianBarangModel> goods) throws IOException, DocumentException {
        Document document = createDocument();
        File file = createFile(RESULT_PATH + "/purchase-order-"  + purchaseNumber + ".pdf");
        PdfWriter.getInstance(document, new FileOutputStream(file));

        document.open();
        
        insertKopSurat(document);
        
        HashMap<String, String> headerData = new HashMap<>();
        headerData.put("Date", new SimpleDateFormat("dd MMMM yyyy").format(new Date()));
        headerData.put("Purchase No.", purchaseNumber);
        headerData.put("No. Pembelian", pembelianModel.getKode());
        setupHeader("Purchase Order", headerData, document);
        
        insertSpacing(24, document);
        
        SupplierModel supplier = pembelianModel.getSupplier();
        ArrayList<String> addressData = new ArrayList<>();
        addressData.add(supplier.getName());
        addressData.add(supplier.getAlamat());
        addressData.add(supplier.getNoTelepon());
        setupAddres(addressData, document);
        
        insertSpacing(24, document);
        setupDaftarItemPembelian(goods, document);
        
        int subTotal = 0;
        for (PembelianBarangModel good : goods) {
            subTotal += good.getHargaHpp() * good.getQuantity();
        }
        
        ArrayList<HashMap<String, String>> details = new ArrayList<>();
        HashMap<String, String> item1 = new HashMap<>();
        item1.put("Subtotal", CurrencyUtil.getInstance().formatCurrency(subTotal));
        details.add(item1);
        HashMap<String, String> item2 = new HashMap<>();
        item2.put("Ongkir", CurrencyUtil.getInstance().formatCurrency(pembelianModel.getOngkir()));
        details.add(item2);
        HashMap<String, String> item4 = new HashMap<>();
        item4.put("Total Bayar", CurrencyUtil.getInstance().formatCurrency(subTotal + pembelianModel.getOngkir()));
        details.add(item4);
        setupPurchaseOrderTableFooter(details, document);
        
        insertSpacing(48, document);
        insertFooterText("Thank you for your bussiness!", document);
        
        document.close();
        Desktop.getDesktop().open(file);
    }
    
    public static void cetakInvoicePesanan(InvoicePesanan invoicePesanan) throws FileNotFoundException, DocumentException, IOException {
        Document document = createDocument();
        File file = createFile(RESULT_PATH + "/invoice-penjualan-"  + invoicePesanan.getKodeInvoice() + ".pdf");
        PdfWriter.getInstance(document, new FileOutputStream(file));

        document.open();
        
        insertKopSurat(document);
        
        HashMap<String, String> headerData = new HashMap<>();
        headerData.put("Date", new SimpleDateFormat("dd MMMM yyyy").format(invoicePesanan.getTanggal()));
        headerData.put("Invoice No.", invoicePesanan.getKodeInvoice());
        headerData.put("No. Pesanan", invoicePesanan.getPesanan().getKodePesanan());
        setupHeader("Invoice", headerData, document);
        
        insertSpacing(24, document);
               
        Kustomer customer = invoicePesanan.getPesanan().getKustomer();
        ArrayList<String> addressData = new ArrayList<>();
        addressData.add(customer.getNamaKustomer());
        addressData.add(customer.getAlamat());
        addressData.add(customer.getNoTelepon());
        setupAddres(addressData, document);
        
        insertSpacing(24, document);
        
        List<PesananDetail> items = invoicePesanan.getPesanan().getPesananDetailList();
        setupDaftarItemPesanan(items, document);
        
        ArrayList<HashMap<String, String>> details = new ArrayList<>();
        HashMap<String, String> item1 = new HashMap<>();
        item1.put("Subtotal", CurrencyUtil.getInstance().formatCurrency(invoicePesanan.getPesanan().getTotal()));
        details.add(item1);
        HashMap<String, String> item2 = new HashMap<>();
        item2.put("Ongkir", CurrencyUtil.getInstance().formatCurrency(invoicePesanan.getPesanan().getOngkir()));
        details.add(item2);
        HashMap<String, String> item3 = new HashMap<>();
        item3.put("PPN", CurrencyUtil.getInstance().formatCurrency(invoicePesanan.getPpn()));
        details.add(item3);
        HashMap<String, String> item4 = new HashMap<>();
        item4.put("Total Bayar", CurrencyUtil.getInstance().formatCurrency(invoicePesanan.getPesanan().getTotal() + invoicePesanan.getPesanan().getOngkir() + invoicePesanan.getPpn()));
        details.add(item4);
        setupInvoiceTableFooter(details, document);
        
        insertSpacing(24, document);
        setupBankAccountPdfPrint(document);
        
        insertSpacing(48, document);
        insertFooterText("Thank you for shopping with us!", document);
        
        document.close();
        Desktop.getDesktop().open(file);
    }
    
    private static void insertKopSurat(Document document) throws BadElementException, IOException, DocumentException {
        Image img = Image.getInstance("resources/header.png");
        img.scalePercent(50);
        document.add(img);
    }
    
    private static void insertSpacing(float spaceValue, Document document) throws DocumentException {
        Paragraph space = new Paragraph("");
        space.setSpacingAfter(spaceValue);
        document.add(space);
    }
    
    private static void setupHeader(String title, HashMap<String, String> data, Document document) throws DocumentException {
        Paragraph paragraph = new Paragraph(title);
        paragraph.setAlignment(Rectangle.ALIGN_CENTER);
        paragraph.setFont(BIG_HEADER_FONT);
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
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
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
    
    private static void setupDaftarItemPesanan(List<PesananDetail> daftarPesanan, Document document) throws DocumentException {
        PdfPTable table = new PdfPTable(6);
        table.setTotalWidth(new float[]{ 24, 100, 100, 48, 96, 96 });
        table.setWidthPercentage(100);
        
        PdfPCell blankCell = new PdfPCell();
        blankCell.setColspan(3);
        blankCell.setBorder(Rectangle.NO_BORDER);
        
        PdfPCell cell = new PdfPCell(new Phrase("No", SMALL_FONT));
        cell.setBorder(Rectangle.BOX);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Kode Barang", SMALL_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Nama Barang", SMALL_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Jumlah", SMALL_FONT));        
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Harga Satuan", SMALL_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Total", SMALL_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);
        
        int i = 1;
        for (PesananDetail item : daftarPesanan) {
            cell = new PdfPCell(new Phrase("" + i, SMALL_FONT));
            cell.setBorder(Rectangle.BOX);
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase(item.getBarang().getKodeBarang(), SMALL_FONT));
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase(item.getBarang().getNamaBarang(), SMALL_FONT));
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("" + item.getQty(), SMALL_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase(CurrencyUtil.getInstance().formatCurrency(item.getBarang().getHargaJualSatuan()), SMALL_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase(CurrencyUtil.getInstance().formatCurrency(item.getQty()* item.getBarang().getHargaJualSatuan()), SMALL_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
            
            i++;
        }
        
        document.add(table);
    }
    
    private static void setupDaftarItemPesananSuratJalan(List<ItemPesananModel> daftarPesanan, Document document) throws DocumentException {
        PdfPTable table = new PdfPTable(4);
        table.setTotalWidth(new float[]{ 24, 100, 100, 48 });
        table.setWidthPercentage(100);
        
        PdfPCell blankCell = new PdfPCell();
        blankCell.setColspan(3);
        blankCell.setBorder(Rectangle.NO_BORDER);
        
        PdfPCell cell = new PdfPCell(new Phrase("No", SMALL_FONT));
        cell.setBorder(Rectangle.BOX);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Kode Barang", SMALL_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Nama Barang", SMALL_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Jumlah", SMALL_FONT));        
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);
        
        int i = 1;
        for (ItemPesananModel item : daftarPesanan) {
            cell = new PdfPCell(new Phrase("" + i, SMALL_FONT));
            cell.setBorder(Rectangle.BOX);
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase(item.getBarang().getKode(), SMALL_FONT));
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase(item.getBarang().getName(), SMALL_FONT));
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("" + item.getQuantity(), SMALL_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
            
            i++;
        }
        
        document.add(table);
    }
    
    private static void setupDaftarItemPembelian(List<PembelianBarangModel> daftarPembelian, Document document) throws DocumentException {
        PdfPTable table = new PdfPTable(9);
        table.setTotalWidth(new float[]{ 24, 96, 48, 48, 48, 36, 48, 72, 96 });
        table.setWidthPercentage(100);
        
        PdfPCell blankCell = new PdfPCell();
        blankCell.setColspan(3);
        blankCell.setBorder(Rectangle.NO_BORDER);
        
        PdfPCell cell = new PdfPCell(new Phrase("No", SMALL_FONT));
        cell.setBorder(Rectangle.BOX);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Kode Barang", SMALL_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("Warna", SMALL_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("Area", SMALL_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("Ukuran", SMALL_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("Ket", SMALL_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Jumlah", SMALL_FONT));        
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Harga Satuan", SMALL_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Total", SMALL_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);
        
        int i = 1;
        for (PembelianBarangModel item : daftarPembelian) {
            cell = new PdfPCell(new Phrase("" + i, SMALL_FONT));
            cell.setBorder(Rectangle.BOX);
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            
            Barang barang = ConnectionManager.getDefaultEntityManager().createNamedQuery("Barang.findByKodeBarang", Barang.class).setParameter("kodeBarang", item.getKodeBarang()).getSingleResult();
            
            cell = new PdfPCell(new Phrase(item.getKodeBarang(), SMALL_FONT));
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase(item.getWarna(), SMALL_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase(item.getArea(), SMALL_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase(item.getUkuran(), SMALL_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("-", SMALL_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("" + item.getQuantity(), SMALL_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase(CurrencyUtil.getInstance().formatCurrency(item.getHargaHpp()), SMALL_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase(CurrencyUtil.getInstance().formatCurrency(item.getQuantity() * item.getHargaHpp()), SMALL_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
            
            i++;
        }
        
        document.add(table);
    }
    
    private static void setupInvoiceTableFooter(ArrayList<HashMap<String,String>> details, Document document) throws DocumentException {
        PdfPTable table = new PdfPTable(6);
        table.setTotalWidth(new float[]{ 24, 100, 100, 48, 96, 96 });
        table.setWidthPercentage(100);
        
        PdfPCell blankCell = new PdfPCell();
        blankCell.setColspan(4);
        blankCell.setBorder(Rectangle.NO_BORDER);
        
        for (Map<String, String> detail : details) {
            
            for (Map.Entry<String, String> entry : detail.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                
                table.addCell(blankCell);
        
                PdfPCell cell = new PdfPCell(new Phrase(key, SMALL_FONT));
                cell.setBorder(Rectangle.BOX);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(value, SMALL_FONT));
                cell.setBorder(Rectangle.BOX);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);
            }
        }
        
        document.add(table);
    }
    
    private static void setupPurchaseOrderTableFooter(ArrayList<HashMap<String,String>> details, Document document) throws DocumentException {
        PdfPTable table = new PdfPTable(9);
        table.setTotalWidth(new float[]{ 24, 96, 48, 48, 48, 36, 48, 72, 96 });
        table.setWidthPercentage(100);
        
        PdfPCell blankCell = new PdfPCell();
        blankCell.setColspan(7);
        blankCell.setBorder(Rectangle.NO_BORDER);
        
        for (Map<String, String> detail : details) {
            
            for (Map.Entry<String, String> entry : detail.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                
                table.addCell(blankCell);
        
                PdfPCell cell = new PdfPCell(new Phrase(key, SMALL_FONT));
                cell.setBorder(Rectangle.BOX);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(value, SMALL_FONT));
                cell.setBorder(Rectangle.BOX);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);
            }
        }
        
        document.add(table);
    }
    
    private static void setupBankAccountPdfPrint(Document document) throws DocumentException {
        String text = "Pembayaran untuk invoice ini mohon ditransfer ke rekening:\n"
                + "Bank BRI Cab. Jakarta\n"
                + "No. Rek: 037-701-023-406-501\n"
                + "Atas Nama: Mazza Fakkar Alam";
        Paragraph paragraph = new Paragraph(text);
        paragraph.setAlignment(Rectangle.ALIGN_LEFT);
        paragraph.setFont(SMALL_FONT);
        paragraph.setSpacingAfter(0);
        document.add(paragraph);
    }
    
    private static void insertFooterText(String footerText, Document document) throws DocumentException {
        Paragraph paragraph = new Paragraph(footerText);
        paragraph.setAlignment(Rectangle.ALIGN_CENTER);
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
