/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aswajaclothes.pdf;

import aswajaclothes.connection.ConnectionManager;
import aswajaclothes.entity.Barang;
import aswajaclothes.entity.InvoicePembelian;
import aswajaclothes.entity.InvoicePesanan;
import aswajaclothes.entity.Kustomer;
import aswajaclothes.entity.PembelianDetail;
import aswajaclothes.entity.Pesanan;
import aswajaclothes.entity.PesananDetail;
import aswajaclothes.entity.ReturPesanan;
import aswajaclothes.entity.Supplier;
import aswajaclothes.entity.SuratJalan;
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
        
        ArrayList<PesananDetail> daftar = new ArrayList<>();
        List<Barang> items = ConnectionManager.getDefaultEntityManager().createNamedQuery("Barang.findAll", Barang.class).getResultList();
        
        for (Barang item : items) {
            daftar.addAll(item.getPesananDetailList());
        }
        
        setupDaftarItemPesananLaporan(daftar, document);
                
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
        
        ArrayList<PembelianDetail> daftar = new ArrayList<>();
        List<Barang> items = ConnectionManager.getDefaultEntityManager().createNamedQuery("Barang.findAll", Barang.class).getResultList();
        
        for (Barang item : items) {
            List<PesananDetail> daftarPesanan = item.getPesananDetailList();
            for (PesananDetail pesananDetail : daftarPesanan) {
                for (PembelianDetail pembelianDetail : pesananDetail.getPesanan().getPembelianDetailList()) {
                    daftar.add(pembelianDetail);
                }
            }
        }
        
        setupDaftarItemPembelianLaporan(daftar, document);
                
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

    
    public static void cetakReturPenjualan(ReturPesanan returPesanan) throws IOException, DocumentException, BadElementException, FileNotFoundException {
        Document document = createDocument();
        File file = createFile(RESULT_PATH + "/retur-penjualan-"  + returPesanan.getKodeReturPesanan() + ".pdf");
        PdfWriter.getInstance(document, new FileOutputStream(file));

        document.open();
        
        insertKopSurat(document);
        
        HashMap<String, String> headerData = new HashMap<>();
        headerData.put("Date", new SimpleDateFormat("dd MMMM yyyy").format(new Date()));
        headerData.put("No. Retur", returPesanan.getKodeReturPesanan());
        headerData.put("No. Invoice", returPesanan.getInvoicePesanan().getKodeInvoice());
        setupHeader("Retur Penjualan", headerData, document);
        
        Kustomer customer = returPesanan.getInvoicePesanan().getPesanan().getKustomer();
        ArrayList<String> addressData = new ArrayList<>();
        addressData.add(customer.getNamaKustomer());
        addressData.add(customer.getAlamat());
        addressData.add(customer.getNoTelepon());
        setupAddres(addressData, document);
        
        insertSpacing(24, document);
        setupDaftarItemPesanan(returPesanan.getInvoicePesanan().getPesanan().getPesananDetailList(), document);
        
        Pesanan pesanan = returPesanan.getInvoicePesanan().getPesanan();
        
        ArrayList<HashMap<String, String>> details = new ArrayList<>();
        HashMap<String, String> item1 = new HashMap<>();
        item1.put("Subtotal", CurrencyUtil.getInstance().formatCurrency(pesanan.getTotal() - pesanan.getOngkir()));
        details.add(item1);
        HashMap<String, String> item2 = new HashMap<>();
        item2.put("Ongkir", CurrencyUtil.getInstance().formatCurrency(returPesanan.getOngkir()));
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
    
    public static void cetakSuratJalan(SuratJalan suratJalan) throws DocumentException, FileNotFoundException, BadElementException, IOException {
        Document document = createDocument();
        File file = createFile(RESULT_PATH + "/surat-jalan-"  + suratJalan.getKodeSuratJalan() + ".pdf");
        PdfWriter.getInstance(document, new FileOutputStream(file));

        document.open();
        
        insertKopSurat(document);
        
        HashMap<String, String> headerData = new HashMap<>();
        headerData.put("Date", new SimpleDateFormat("dd MMMM yyyy").format(new Date()));
        headerData.put("No. Surat Jalan", suratJalan.getKodeSuratJalan());
        headerData.put("No. Pesanan", suratJalan.getInvoicePesanan().getPesanan().getKodePesanan());
        setupHeader("Surat Jalan", headerData, document);
        
        Pesanan pesanan = suratJalan.getInvoicePesanan().getPesanan();
        
        Kustomer customer = pesanan.getKustomer();
        ArrayList<String> addressData = new ArrayList<>();
        addressData.add(customer.getNamaKustomer());
        addressData.add(customer.getAlamat());
        addressData.add(customer.getNoTelepon());
        setupAddres(addressData, document);
        
        insertSpacing(24, document);
        setupDaftarItemPesananSuratJalan(pesanan.getPesananDetailList(), document);
        
        insertSpacing(48, document);
        insertFooterText("Thank you for shopping with us!", document);
        
        document.close();
        Desktop.getDesktop().open(file);
    }
    
    public static void cetakInvoicePembelian(InvoicePembelian invoicePembelian) throws IOException, DocumentException {
        Document document = createDocument();
        File file = createFile(RESULT_PATH + "/purchase-order-"  + invoicePembelian.getKodeInvoice() + ".pdf");
        PdfWriter.getInstance(document, new FileOutputStream(file));

        document.open();
        
        insertKopSurat(document);
        
        HashMap<String, String> headerData = new HashMap<>();
        headerData.put("Date", new SimpleDateFormat("dd MMMM yyyy").format(new Date()));
        headerData.put("Purchase No.", invoicePembelian.getKodeInvoice());
        headerData.put("No. Pembelian", invoicePembelian.getPembelian().getKodePembelian());
        setupHeader("Purchase Order", headerData, document);
        
        insertSpacing(24, document);
        
        Supplier supplier = invoicePembelian.getPembelian().getSupplier();
        ArrayList<String> addressData = new ArrayList<>();
        addressData.add(supplier.getNamaSupplier());
        addressData.add(supplier.getAlamat());
        addressData.add(supplier.getNoTelepon());
        setupAddres(addressData, document);
        
        insertSpacing(24, document);
        setupDaftarItemPembelian(invoicePembelian.getPembelian().getPembelianDetailList(), document);
        
        int subTotal = 0;
        for (PembelianDetail pembelianDetail : invoicePembelian.getPembelian().getPembelianDetailList()) {
            for (PesananDetail pesananDetail : pembelianDetail.getPesanan().getPesananDetailList()) {
                subTotal += pesananDetail.getBarang().getHargaHpp() * pesananDetail.getQty();
            }
        }
        
        ArrayList<HashMap<String, String>> details = new ArrayList<>();
        HashMap<String, String> item1 = new HashMap<>();
        item1.put("Subtotal", CurrencyUtil.getInstance().formatCurrency(subTotal));
        details.add(item1);
        HashMap<String, String> item2 = new HashMap<>();
        item2.put("Ongkir", CurrencyUtil.getInstance().formatCurrency(invoicePembelian.getPembelian().getOngkir()));
        details.add(item2);
        HashMap<String, String> item4 = new HashMap<>();
        item4.put("Total Bayar", CurrencyUtil.getInstance().formatCurrency(subTotal + invoicePembelian.getPembelian().getOngkir()));
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
    
    private static void setupDaftarItemPesananLaporan(List<PesananDetail> daftarPesanan, Document document) throws DocumentException {
        PdfPTable table = new PdfPTable(6);
        table.setTotalWidth(new float[]{ 36, 36, 36, 24, 96, 96 });
        table.setWidthPercentage(100);
        
        PdfPCell blankCell = new PdfPCell();
        blankCell.setColspan(3);
        blankCell.setBorder(Rectangle.NO_BORDER);
        
        PdfPCell cell = new PdfPCell(new Phrase("Tanggal", SMALL_FONT));
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

        cell = new PdfPCell(new Phrase("Qty", SMALL_FONT));        
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Nama Pembeli", SMALL_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Nominal", SMALL_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);
        
        int i = 1;
        for (PesananDetail item : daftarPesanan) {
            cell = new PdfPCell(new Phrase(new SimpleDateFormat("dd-MM-yyyy").format(item.getPesanan().getTanggal()), SMALL_FONT));
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
            
            cell = new PdfPCell(new Phrase(item.getPesanan().getKustomer().getNamaKustomer(), SMALL_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase(CurrencyUtil.getInstance().formatCurrency(item.getQty()* item.getBarang().getHargaJualSatuan()), SMALL_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
            
            i++;
        }
        
        document.add(table);
    }
    
    private static void setupDaftarItemPesananSuratJalan(List<PesananDetail> daftarPesanan, Document document) throws DocumentException {
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
            
            i++;
        }
        
        document.add(table);
    }
    
    private static void setupDaftarItemPembelian(List<PembelianDetail> daftarPembelian, Document document) throws DocumentException {
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
        for (PembelianDetail item : daftarPembelian) {
            for (PesananDetail pesananDetail : item.getPesanan().getPesananDetailList()) {
                cell = new PdfPCell(new Phrase("" + i, SMALL_FONT));
                cell.setBorder(Rectangle.BOX);
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                Barang barang = pesananDetail.getBarang();

                cell = new PdfPCell(new Phrase(barang.getKodeBarang(), SMALL_FONT));
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(barang.getWarna(), SMALL_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(barang.getArea(), SMALL_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(barang.getUkuran(), SMALL_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase("-", SMALL_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase("" + pesananDetail.getQty(), SMALL_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(CurrencyUtil.getInstance().formatCurrency(barang.getHargaHpp()), SMALL_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(CurrencyUtil.getInstance().formatCurrency(pesananDetail.getQty() * barang.getHargaHpp()), SMALL_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);

                i++;
            }
        }
        
        document.add(table);
    }
    
    private static void setupDaftarItemPembelianLaporan(List<PembelianDetail> daftarPembelian, Document document) throws DocumentException {
        PdfPTable table = new PdfPTable(7);
        table.setTotalWidth(new float[]{ 24, 96, 48, 48, 48, 36, 48 });
        table.setWidthPercentage(100);
        
        PdfPCell blankCell = new PdfPCell();
        blankCell.setColspan(0);
        blankCell.setBorder(Rectangle.NO_BORDER);
        
        PdfPCell cell = new PdfPCell(new Phrase("No", SMALL_FONT));
        cell.setBorder(Rectangle.BOX);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Kode Pembelian", SMALL_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("Tanggal Pembelian", SMALL_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("Nama Supplier", SMALL_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("Nama Barang", SMALL_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("Jumlah Pembelian", SMALL_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Total", SMALL_FONT));        
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);
        
        int i = 1;
        for (PembelianDetail item : daftarPembelian) {
            for (PesananDetail pesananDetail : item.getPesanan().getPesananDetailList()) {
                cell = new PdfPCell(new Phrase("" + i, SMALL_FONT));
                cell.setBorder(Rectangle.BOX);
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                Barang barang = pesananDetail.getBarang();

                cell = new PdfPCell(new Phrase(item.getPembelian().getKodePembelian(), SMALL_FONT));
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(new SimpleDateFormat("dd-MM-yyyy").format(item.getPembelian().getTanggal()), SMALL_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(item.getPembelian().getSupplier().getNamaSupplier(), SMALL_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(barang.getNamaBarang(), SMALL_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase("" + pesananDetail.getQty(), SMALL_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase("" + item.getPesanan().getTotal(), SMALL_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);

                i++;
            }
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
