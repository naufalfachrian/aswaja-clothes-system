/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aswajaclothes.connection;

import aswajaclothes.model.master.InvoiceModel;
import aswajaclothes.model.common.KabupatenModel;
import aswajaclothes.model.common.ProvinsiModel;
import aswajaclothes.model.master.BarangModel;
import aswajaclothes.model.master.EkspedisiModel;
import aswajaclothes.model.master.ItemPesananModel;
import aswajaclothes.model.master.PembelianBarangModel;
import aswajaclothes.model.master.PembelianModel;
import aswajaclothes.model.master.PesananModel;
import aswajaclothes.model.master.SupplierModel;
import aswajaclothes.model.transaction.InputOrderPenjualanDetailModel;
import aswajaclothes.model.transaction.InputOrderPenjualanModel;
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class ConnectionManager {
    
    private static EntityManager entityManager;
    
    public static EntityManager getDefaultEntityManager() {
        if (entityManager == null) {
            entityManager = Persistence.createEntityManagerFactory("AswajaClothesPU").createEntityManager();
        }
        return entityManager;
    }

    private static ConnectionManager instance;
    private Connection connection;
    private Statement statement;

    public static ConnectionManager getConnection() {
        if (instance == null) {
            instance = new ConnectionManager();
        }
        return instance;
    }

    public ConnectionManager() {
        try {
            connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/" + 
                    ConnectionSecret.databaseName + "?useSSL=false", 
                    ConnectionSecret.databaseUsername, 
                    ConnectionSecret.databasePassword);
            statement = connection.createStatement();
        } catch (SQLException ex) {
            System.out.print("Connection to database is failed");
            System.out.println(ex.getLocalizedMessage());
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="Master Customer">
    
    public static String getKodeCustomer() {
        String kode = "CS";
        int totalCustomer = ConnectionManager.getDefaultEntityManager().createNamedQuery("Kustomer.findAll").getResultList().size();
        int newId = totalCustomer + 1;
        if (newId < 10) {
            kode += "000" + newId;
        } else if (newId < 100) {
            kode += "00" + newId;
        } else if (newId < 1000) {
            kode += "0" + newId;
        } else {
            kode += "" + newId;
        }
        return kode;
    }
        
    public static String getKodeBarang() {
        String kode = "BR";
        int count = ConnectionManager.getDefaultEntityManager().createNamedQuery("Barang.findAll").getResultList().size();
        int newId = count + 1;
        if (newId < 10) {
            kode += "000" + newId;
        } else if (newId < 100) {
            kode += "00" + newId;
        } else if (newId < 1000) {
            kode += "0" + newId;
        } else {
            kode += "" + newId;
        }
        return kode;
    }
    
    // <editor-fold defaultstate="collapsed" desc="Master Supplier">
    public static String getKodeSupplier() {
        String kode = "SU";
        int totalSupplier = ConnectionManager.getDefaultEntityManager().createNamedQuery("Supplier.findAll").getResultList().size();
        int newId = totalSupplier + 1;
        if (newId < 10) {
            kode += "000" + newId;
        } else if (newId < 100) {
            kode += "00" + newId;
        } else if (newId < 1000) {
            kode += "0" + newId;
        } else {
            kode += "" + newId;
        }
        return kode;
    }
    
    // <editor-fold defaultstate="collapsed" desc="Master Ekspedisi">
    public static String getKodeEkspedisi() {
        String kode = "EX";
        int totalEkspedisi = ConnectionManager.getDefaultEntityManager().createNamedQuery("Ekspedisi.findAll").getResultList().size();
        int newId = totalEkspedisi + 1;
        if (newId < 10) {
            kode += "000" + newId;
        } else if (newId < 100) {
            kode += "00" + newId;
        } else if (newId < 1000) {
            kode += "0" + newId;
        } else {
            kode += "" + newId;
        }
        return kode;
    }
    
    // <editor-fold defaultstate="collapsed" desc="Input Order Penjualan">
    public static String getKodePesanan() {
        String kode = "PS";
        int totalPesanan = ConnectionManager.getDefaultEntityManager().createNamedQuery("Pesanan.findAll").getResultList().size();
        int newId = totalPesanan + 1;
        if (newId < 10) {
            kode += "000" + newId;
        } else if (newId < 100) {
            kode += "00" + newId;
        } else if (newId < 1000) {
            kode += "0" + newId;
        } else {
            kode += "" + newId;
        }
        return kode;
    }
    
    public PesananModel getDaftarPesananById(String inputOrderPenjualanId) {
        try {
            String query = String.format("SELECT iop.kode_pesanan, iop.kode_kustomer, c.nama_kustomer, iop.kode_ekspedisi, e.nama_ekspedisi, e.jenis_layanan, iop.ongkir, iop.total, iop.tanggal as 'tanggal_pemesanan' " +
                    "FROM input_order_penjualan iop " +
                    "LEFT JOIN customer c ON iop.kode_kustomer = c.kode_kustomer " +
                    "LEFT JOIN ekspedisi e ON iop.kode_ekspedisi = e.kode_ekspedisi " +
                    "WHERE iop.kode_pesanan = '%s';", inputOrderPenjualanId);
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                PesananModel item = new PesananModel();
                item.setKodePesanan(result.getString("kode_pesanan"));
                item.setKodeKustomer(result.getString("kode_kustomer"));
                item.setNamaKustomer(result.getString("nama_kustomer"));
                item.setKodeEkspedisi(result.getString("kode_ekspedisi"));
                item.setNamaEkspedisi(result.getString("nama_ekspedisi"));
                item.setJenisLayanan(result.getString("jenis_layanan"));
                item.setOngkir(result.getInt("ongkir"));
                item.setTotal(result.getInt("total"));
                item.setTanggalPemesanan(result.getString("tanggal_pemesanan"));
                return item;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List<ItemPesananModel> getDaftarPesananItem(String kodePesanan) {
        ArrayList<ItemPesananModel> items = new ArrayList<>();
        String query = String.format("SELECT * FROM input_order_penjualan_detail iopd "
                + "LEFT JOIN barang b ON iopd.kode_barang = b.kode_barang "
                + "WHERE iopd.kode_pesanan = '%s'", kodePesanan);
        try {
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                ItemPesananModel item = new ItemPesananModel();
                item.setKodePesanan(kodePesanan);
                
                BarangModel barang = new BarangModel();
                barang.setKode(result.getString("kode_barang"));
                barang.setName(result.getString("nama_barang"));
                barang.setWarna(result.getString("warna"));
                barang.setArea(result.getString("area"));
                barang.setUkuran(result.getString("ukuran"));
                barang.setHargaHPP(result.getInt("harga_hpp"));
                barang.setHargaJualSatuan(result.getInt("harga_jual_satuan"));
                item.setBarang(barang);
                
                item.setQuantity(result.getInt("qty"));
                items.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return items;
    }
    
    // </editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="Cetak Invoice Pembelian">
    
    public String getKodeInvoicePembelian() {
        return getKode("PO", "cetak_invoice_pembelian");
    }
    
    public List<PembelianBarangModel> getDaftarPembelianBarang(String kodePembelian) {
        String query = String.format("SELECT kode_pesanan as 'KodePesanan', "
                + "iopd.kode_barang as 'KodeBarang', "
                + "qty as 'Quantity', "
                + "tipe_lengan as 'TipeLengan', "
                + "nama_barang as 'NamaBarang', "
                + "warna as 'Warna', "
                + "area as 'Area', "
                + "ukuran as 'Ukuran', "
                + "harga_hpp as 'HargaHPP', "
                + "harga_jual_satuan as 'HargaJualSatuan' "
                + "FROM input_order_penjualan_detail iopd "
                + "INNER JOIN barang ON iopd.kode_barang = barang.kode_barang "
                + "WHERE kode_pesanan IN "
                + "(SELECT kode_pesanan FROM input_order_pembelian_detail WHERE kode_pembelian = '%s');", kodePembelian);
        ArrayList<PembelianBarangModel> items = new ArrayList<>();
        try {
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                PembelianBarangModel item = new PembelianBarangModel();
                item.setArea(result.getString("Area"));
                item.setHargaHpp(result.getInt("HargaHPP"));
                item.setHargaJualSatuan(result.getInt("HargaJualSatuan"));
                item.setKodeBarang(result.getString("KodeBarang"));
                item.setKodePesanan(result.getString("KodePesanan"));
                item.setNamaBarang(result.getString("NamaBarang"));
                item.setQuantity(result.getInt("Quantity"));
                item.setTipeLengan(result.getString("TipeLengan"));
                item.setUkuran(result.getString("Ukuran"));
                item.setWarna(result.getString("Warna"));
                items.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return items;
    }
    
    // </editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="Cetak Invoice Penjualan">
    
    public String getKodeInvoicePenjualan() {
        return getKode("PJ", "cetak_invoice_penjualan");
    }
    
    public boolean insertInvoicePenjualan(String kodeInvoice, String kodePesanan, int ppn, Date tanggalInvoice) {
        try {
            if (invoicePenjualanBelumAda(kodeInvoice)) {
                String dateString = new SimpleDateFormat("ddMMyyyy").format(tanggalInvoice);
                String query = String.format("INSERT INTO cetak_invoice_penjualan VALUES('%s', '%s', '%d', '%s', '%d')", kodeInvoice, kodePesanan, ppn, dateString, 0);
                return statement.executeUpdate(query) > 0;
            }
            // success, but not inserted to table because invoice has been already exists..
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    private boolean invoicePenjualanBelumAda(String kodeInvoice) {
        try {
            String query = String.format("SELECT COUNT(*) 'total' FROM cetak_invoice_penjualan WHERE kode_invoice = '%s'", kodeInvoice);
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                int itemsCount = resultSet.getInt("total");
                return itemsCount == 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public List<InvoiceModel> getInvoices() {
        ArrayList<InvoiceModel> invoices = new ArrayList<>();
        String query = String.format("SELECT kode_invoice as 'KodeInvoice', "
                + "iop.kode_pesanan as 'KodePesanan', "
                + "customer.kode_kustomer as 'KodeKustomer', "
                + "customer.nama_kustomer as 'NamaKustomer', "
                + "ekspedisi.kode_ekspedisi as 'KodeEkspedisi', "
                + "ekspedisi.nama_ekspedisi as 'NamaEkspedisi', "
                + "ekspedisi.jenis_layanan as 'JenisLayanan', "
                + "ongkir as 'Ongkir', total as 'Total', "
                + "ppn as 'Ppn', iop.tanggal as 'TanggalPemesanan', "
                + "inv.tanggal as 'TanggalInvoice', "
                + "inv.is_lunas as 'IsLunas' "
                + "from cetak_invoice_penjualan inv "
                + "INNER JOIN input_order_penjualan iop ON iop.kode_pesanan = inv.kode_pesanan "
                + "INNER JOIN customer ON customer.kode_kustomer = iop.kode_kustomer "
                + "INNER JOIN ekspedisi ON ekspedisi.kode_ekspedisi = iop.kode_ekspedisi;");
        try {
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                InvoiceModel invoice = new InvoiceModel();
                invoice.setKodeInvoice(result.getString("KodeInvoice"));
                invoice.setKodePesanan(result.getString("KodePesanan"));
                invoice.setKodeKustomer(result.getString("KodeKustomer"));
                invoice.setNamaKustomer(result.getString("NamaKustomer"));
                invoice.setKodeEkspedisi(result.getString("KodeEkspedisi"));
                invoice.setNamaEkspedisi(result.getString("NamaEkspedisi"));
                invoice.setJenisLayanan(result.getString("JenisLayanan"));
                invoice.setOngkir(result.getInt("Ongkir"));
                invoice.setTotal(result.getInt("Total"));
                invoice.setPpn(result.getInt("Ppn"));
                invoice.setTanggalInvoice(result.getString("TanggalInvoice"));
                invoice.setTanggalPemesanan(result.getString("TanggalPemesanan"));
                invoice.setLunas(result.getBoolean("IsLunas"));
                invoices.add(invoice);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return invoices;
    }
    
    public InvoiceModel getInvoice(String kodeInvoice) {
        ArrayList<InvoiceModel> invoices = new ArrayList<>();
        String query = String.format("SELECT kode_invoice as 'KodeInvoice', "
                + "iop.kode_pesanan as 'KodePesanan', "
                + "customer.kode_kustomer as 'KodeKustomer', "
                + "customer.nama_kustomer as 'NamaKustomer', "
                + "ekspedisi.kode_ekspedisi as 'KodeEkspedisi', "
                + "ekspedisi.nama_ekspedisi as 'NamaEkspedisi', "
                + "ekspedisi.jenis_layanan as 'JenisLayanan', "
                + "ongkir as 'Ongkir', total as 'Total', "
                + "ppn as 'Ppn', iop.tanggal as 'TanggalPemesanan', "
                + "inv.tanggal as 'TanggalInvoice', "
                + "inv.is_lunas as 'IsLunas' "
                + "from cetak_invoice_penjualan inv "
                + "INNER JOIN input_order_penjualan iop ON iop.kode_pesanan = inv.kode_pesanan "
                + "INNER JOIN customer ON customer.kode_kustomer = iop.kode_kustomer "
                + "INNER JOIN ekspedisi ON ekspedisi.kode_ekspedisi = iop.kode_ekspedisi "
                + "WHERE kode_invoice = '%s';", kodeInvoice);
        try {
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                InvoiceModel invoice = new InvoiceModel();
                invoice.setKodeInvoice(result.getString("KodeInvoice"));
                invoice.setKodePesanan(result.getString("KodePesanan"));
                invoice.setKodeKustomer(result.getString("KodeKustomer"));
                invoice.setNamaKustomer(result.getString("NamaKustomer"));
                invoice.setKodeEkspedisi(result.getString("KodeEkspedisi"));
                invoice.setNamaEkspedisi(result.getString("NamaEkspedisi"));
                invoice.setJenisLayanan(result.getString("JenisLayanan"));
                invoice.setOngkir(result.getInt("Ongkir"));
                invoice.setTotal(result.getInt("Total"));
                invoice.setPpn(result.getInt("Ppn"));
                invoice.setTanggalInvoice(result.getString("TanggalInvoice"));
                invoice.setTanggalPemesanan(result.getString("TanggalPemesanan"));
                invoice.setLunas(result.getBoolean("IsLunas"));
                invoices.add(invoice);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if (invoices.size() > 0) {
            return invoices.get(0);
        }
        return null;
    }
    
    public int setStatusBayarInvoice(InvoiceModel invoice, boolean statusBayar) {
        try {
            String query = String.format("UPDATE cetak_invoice_penjualan SET is_lunas = %b WHERE kode_invoice = '%s'", statusBayar, invoice.getKodeInvoice());
            return statement.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="get kode">
    
    
    public String getKodePembelian() {
        return getKode("PM", "input_order_pembelian");
    }
    
    
    public String getKode(String prefix, String namaTable) {
        String query = String.format("SELECT COUNT(*) 'total' FROM %s", namaTable);
        String kode = prefix;
        int total = 0;
        try {
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                total = result.getInt("total") + 1;
            }
            if (total < 10) {
                kode += "000" + total;
            } else if (total < 100) {
                kode += "00" + total;
            } else if (total < 1000) {
                kode += "0" + total;
            } else {
                kode += "" + total;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
            return "";
        }
        return kode;
    }
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Input Order Pembelian">
    
    public boolean simpanInputOrderPembelian(PembelianModel pembelian, boolean isUpdate) {
        if (isUpdate) {
            return updateInputOrderPembelian(pembelian);
        } else {
            return insertInputOrderPembelian(pembelian);
        }
    }
    
    public List<PembelianModel> getDaftarPembelian() {
        String query = String.format("SELECT kode_pembelian as 'KodePembelian', "
                + "tanggal as 'TanggalPembelian', "
                + "alamat_pengiriman as 'AlamatPengiriman', "
                + "kota_tujuan_id as 'KotaTujuanID', "
                + "kota_tujuan as 'KotaTujuan', "
                + "berat as 'Berat', "
                + "ongkir as 'Ongkir', "
                + "iop.kode_supplier as 'KodeSupplier', "
                + "nama_supplier as 'NamaSupplier', "
                + "no_telepon as 'NoTelpSupplier', "
                + "alamat as 'AlamatSupplier', "
                + "email as 'EmailSupplier', "
                + "iop.kode_ekspedisi as 'KodeEkspedisi', "
                + "nama_ekspedisi as 'NamaEkspedisi', "
                + "jenis_layanan as 'JenisLayananEkspedisi' "
                + "FROM input_order_pembelian iop "
                + "INNER JOIN supplier ON supplier.kode_supplier = iop.kode_supplier "
                + "INNER JOIN ekspedisi ON iop.kode_ekspedisi = ekspedisi.kode_ekspedisi;");
        ArrayList<PembelianModel> pembelians = new ArrayList<>();
        try {
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                PembelianModel pembelian = new PembelianModel();
                pembelian.setAlamatPengiriman(result.getString("AlamatPengiriman"));
                pembelian.setBerat(result.getInt("Berat"));
                pembelian.setOngkir(result.getInt("Ongkir"));
                pembelian.setKode(result.getString("KodePembelian"));
                
                KabupatenModel kabupaten = new KabupatenModel();
                kabupaten.setId(result.getString("KotaTujuanID"));
                kabupaten.setName(result.getString("KotaTujuan"));
                pembelian.setKotaTujuan(kabupaten);
                
                SupplierModel supplier = new SupplierModel();
                supplier.setKode(result.getString("KodeSupplier"));
                supplier.setName(result.getString("NamaSupplier"));
                supplier.setNoTelepon(result.getString("NoTelpSupplier"));
                supplier.setAlamat(result.getString("AlamatSupplier"));
                supplier.setEmail(result.getString("EmailSupplier"));
                pembelian.setSupplier(supplier);
                
                EkspedisiModel ekspedisi = new EkspedisiModel();
                ekspedisi.setKode(result.getString("KodeEkspedisi"));
                ekspedisi.setName(result.getString("NamaEkspedisi"));
                ekspedisi.setJenisLayanan(result.getString("JenisLayananEkspedisi"));
                pembelian.setEkspedisi(ekspedisi);
                
                pembelians.add(pembelian);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return pembelians;
    }
    
    private boolean updateInputOrderPembelian(PembelianModel pembelian) {
        String query = String.format("UPDATE input_order_pembelian SET "
                + "kode_supplier = '%s', "
                + "kode_ekspedisi = '%s', "
                + "tanggal = '%s', "
                + "alamat_pengiriman = '%s', "
                + "kota_tujuan_id = '%s', "
                + "kota_tujuan = '%s', "
                + "berat = '%d', "
                + "ongkir = '%d' "
                + "WHERE kode_pembelian = '%s'", 
                pembelian.getSupplier().getKode(), 
                pembelian.getEkspedisi().getKode(), 
                pembelian.getDateString(), 
                pembelian.getAlamatPengiriman(),
                pembelian.getKotaTujuan().getId(),
                pembelian.getKotaTujuan().getName(),
                pembelian.getBerat(),
                pembelian.getOngkir(),
                pembelian.getKode());
        try {
            boolean result = statement.executeUpdate(query) > 0;
            boolean applyDetail = applyInputOrderPembelianDetail(pembelian.getKode(), pembelian.getSelectedKodePesanan());
            return result && applyDetail;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private boolean insertInputOrderPembelian(PembelianModel pembelian) {
        try {
            String query = String.format("INSERT INTO input_order_pembelian VALUES ( '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%d', '%d' )", 
                    pembelian.getKode(), 
                    pembelian.getSupplier().getKode(), 
                    pembelian.getEkspedisi().getKode(),
                    pembelian.getDateString(), 
                    pembelian.getAlamatPengiriman(),
                    pembelian.getKotaTujuan().getId(),
                    pembelian.getKotaTujuan().getName(),
                    pembelian.getBerat(),
                    pembelian.getOngkir());
            boolean result = statement.executeUpdate(query) > 0;
            boolean applyDetail = applyInputOrderPembelianDetail(pembelian.getKode(), pembelian.getSelectedKodePesanan());
            return result && applyDetail;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    private boolean applyInputOrderPembelianDetail(String kodePembelian, List<String> selectedKodePesanan) {
        boolean terhapus = hapusSemuaInputOrderPembelianDetail(kodePembelian);
        int inserted = 0;
        for (String kodePesanan : selectedKodePesanan) {
            try {
                String query = String.format("INSERT INTO input_order_pembelian_detail VALUES ('%s', '%s')", kodePembelian, kodePesanan);
                inserted += statement.executeUpdate(query);
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return inserted == selectedKodePesanan.size();
    }
    
    private boolean hapusSemuaInputOrderPembelianDetail(String kodePembelian) {
        try {
            String query = String.format("DELETE FROM input_order_pembelian_detail WHERE kode_pembelian = '%s'", kodePembelian);
            return statement.execute(query);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Surat Jalan">
    
    public String getKodeSuratJalan() {
        return getKode("SJ", "cetak_surat_jalan");
    }
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Retur Penjualan">
    
    public String getKodeReturPenjualan() {
        return getKode("RJ", "retur_penjualan");
    }
    
    //</editor-fold>

    public List<KabupatenModel> getCities() {
        ArrayList<KabupatenModel> cities = new ArrayList<>();
        String query = "SELECT rc.id as 'CityId', rc.name as 'CityName', "
                + "rp.id as 'ProvinceId', rp.name as 'ProvinceName' "
                + "FROM rajaongkir_cities rc "
                + "INNER JOIN rajaongkir_provinces rp on rc.province_id = rp.id ORDER BY rc.name;";
        try {
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                ProvinsiModel province = new ProvinsiModel();
                province.setId(result.getString("ProvinceId"));
                province.setName(result.getString("ProvinceName"));
                KabupatenModel city = new KabupatenModel();
                city.setId(result.getString("CityId"));
                city.setName(result.getString("CityName"));
                city.setProvince(province);
                city.setProvinsiId(province.getId());
                cities.add(city);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return cities;
    }
    
    public List<KabupatenModel> searchCitiesByName(String searchQuery) {
        ArrayList<KabupatenModel> cities = new ArrayList<>();
        String query = String.format("SELECT rc.id as 'CityId', rc.name as 'CityName', "
                + "rp.id as 'ProvinceId', rp.name as 'ProvinceName' "
                + "FROM rajaongkir_cities rc INNER JOIN rajaongkir_provinces rp "
                + "on rc.province_id = rp.id WHERE rc.name LIKE '%s' "
                + "ORDER BY rc.name;", "%" + searchQuery + "%");
        try {
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                ProvinsiModel province = new ProvinsiModel();
                province.setId(result.getString("ProvinceId"));
                province.setName(result.getString("ProvinceName"));
                KabupatenModel city = new KabupatenModel();
                city.setId(result.getString("CityId"));
                city.setName(result.getString("CityName"));
                city.setProvince(province);
                city.setProvinsiId(province.getId());
                cities.add(city);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return cities;
    }
    
}
