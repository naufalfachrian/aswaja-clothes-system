/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aswajaclothes.connection;

import aswajaclothes.model.master.InvoiceModel;
import aswajaclothes.model.common.KabupatenModel;
import aswajaclothes.model.common.ProvinsiModel;
import aswajaclothes.model.master.EkspedisiModel;
import aswajaclothes.model.master.PembelianBarangModel;
import aswajaclothes.model.master.PembelianModel;
import aswajaclothes.model.master.SupplierModel;
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
    
    public static String getKodeBuktiPenerimaan() {
        String kode = "BP";
        int totalCustomer = ConnectionManager.getDefaultEntityManager().createNamedQuery("BuktiPembayaran.findAll").getResultList().size();
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
    
    //<editor-fold defaultstate="collapsed" desc="Cetak Invoice Pembelian">
    
    public static String getKodeInvoicePembelian() {
        String kode = "PO";
        int totalPesanan = ConnectionManager.getDefaultEntityManager().createNamedQuery("InvoicePembelian.findAll").getResultList().size();
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
    
    public static String getKodeInvoicePesanan() {
        String kode = "INV-";
        int total = ConnectionManager.getDefaultEntityManager().createNamedQuery("InvoicePesanan.findAll").getResultList().size();
        int newId = total + 1;
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
    
    
    public static String getKodePembelian() {
        String kode = "PM";
        int totalPesanan = ConnectionManager.getDefaultEntityManager().createNamedQuery("Pembelian.findAll").getResultList().size();
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
    
    public static String getKodeSuratJalan() {
        String kode = "PO";
        int total = ConnectionManager.getDefaultEntityManager().createNamedQuery("SuratJalan.findAll").getResultList().size();
        int newId = total + 1;
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
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Retur Penjualan">
    
    public static String getKodeReturPenjualan() {
        String kode = "RJ";
        int total = ConnectionManager.getDefaultEntityManager().createNamedQuery("ReturPesanan.findAll").getResultList().size();
        int newId = total + 1;
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
    
    public static String getKodeBuktiPembayaran() {
        String kode = "PS";
        int total = ConnectionManager.getDefaultEntityManager().createNamedQuery("BuktiPembayaran.findAll").getResultList().size();
        int newId = total + 1;
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
    
}
