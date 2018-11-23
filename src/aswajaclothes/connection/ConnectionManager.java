/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aswajaclothes.connection;

import aswajaclothes.model.master.InvoiceModel;
import aswajaclothes.model.master.CustomerModel;
import aswajaclothes.model.common.KabupatenModel;
import aswajaclothes.model.common.KecamatanModel;
import aswajaclothes.model.common.KelurahanModel;
import aswajaclothes.model.common.ProvinsiModel;
import aswajaclothes.model.master.BarangModel;
import aswajaclothes.model.master.EkspedisiModel;
import aswajaclothes.model.master.ItemPesananModel;
import aswajaclothes.model.master.PesananModel;
import aswajaclothes.model.master.SupplierModel;
import aswajaclothes.model.transaction.InputOrderPenjualanDetailModel;
import aswajaclothes.model.transaction.InputOrderPenjualanModel;
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionManager {

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

    // <editor-fold defaultstate="collapsed" desc="Common">
    public ArrayList<ProvinsiModel> getProvinsi() {
        String query = "SELECT * FROM provinces";
        ArrayList<ProvinsiModel> listProvinsi = new ArrayList<>();
        try {
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                ProvinsiModel provinsi = new ProvinsiModel();
                provinsi.setId(result.getString("id"));
                provinsi.setName(result.getString("name"));
                listProvinsi.add(provinsi);
            }
        } catch (SQLException ex) {
            System.out.println("Ambil data provinsi gagal");
            return listProvinsi;
        }
        return listProvinsi;
    }

    public ArrayList<KabupatenModel> getKabupten(String provinsiId) {
        String query = "SELECT * FROM regencies WHERE province_id = " + provinsiId;
        ArrayList<KabupatenModel> listKabupaten = new ArrayList<>();
        try {
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                KabupatenModel kabupaten = new KabupatenModel();
                kabupaten.setId(result.getString("id"));
                kabupaten.setName(result.getString("name"));
                kabupaten.setProvinsiId(provinsiId);
                listKabupaten.add(kabupaten);
            }
        } catch (SQLException ex) {
            System.out.println("Ambil data kabupaten gagal");
            return listKabupaten;
        }

        return listKabupaten;
    }

    public ArrayList<KecamatanModel> getKecamatan(String kabupatenId) {
        String query = "SELECT * FROM districts WHERE regency_id = " + kabupatenId;
        ArrayList<KecamatanModel> listKecamatan = new ArrayList<>();
        try {
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                KecamatanModel kecamatan = new KecamatanModel();
                kecamatan.setId(result.getString("id"));
                kecamatan.setName(result.getString("name"));
                kecamatan.setKabupatenId(kabupatenId);
                listKecamatan.add(kecamatan);
            }
        } catch (SQLException ex) {
            System.out.println("Ambil data kecamatan gagal");
            return listKecamatan;
        }

        return listKecamatan;
    }

    public ArrayList<KelurahanModel> getKelurahan(String kecamatanId) {
        String query = "SELECT * FROM villages WHERE district_id = " + kecamatanId;
        ArrayList<KelurahanModel> listKelurahan = new ArrayList<>();
        try {
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                KelurahanModel kelurahan = new KelurahanModel();
                kelurahan.setId(result.getString("id"));
                kelurahan.setName(result.getString("name"));
                kelurahan.setKecamatanId(kecamatanId);
                listKelurahan.add(kelurahan);
            }
        } catch (SQLException ex) {
            System.out.println("Ambil data kelurahan gagal");
            return listKelurahan;
        }

        return listKelurahan;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Master Customer">
    public String getKodeCustomer() {
        String kode = "CS";
        String query = "SELECT COUNT(*) 'total' FROM customer";
        int totalCustomer = 0;
        try {
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                totalCustomer = result.getInt("total") + 1;
            }
            if (totalCustomer < 10) {
                kode += "000" + totalCustomer;
            } else if (totalCustomer < 100) {
                kode += "00" + totalCustomer;
            } else if (totalCustomer < 1000) {
                kode += "0" + totalCustomer;
            } else {
                kode += "" + totalCustomer;
            }
        } catch (SQLException ex) {
            System.out.println("Ambil data kustomer gagal");
            return kode;
        }
        return kode;
    }
    
    public ArrayList<CustomerModel> getCustomers() {
        ArrayList<CustomerModel> listCustomer = new ArrayList<>();
        String query = "SELECT * FROM customer WHERE deleted = false";
        try {
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                CustomerModel customer = new CustomerModel();
                customer.setKode(result.getString("kode_kustomer"));
                customer.setName(result.getString("nama_kustomer"));
                customer.setProvinsiId(result.getString("provinsi_id"));
                customer.setKabupatenId(result.getString("kabupaten_id"));
                customer.setKecamatanId(result.getString("kecamatan_id"));
                customer.setKelurahanId(result.getString("kelurahan_id"));
                customer.setAlamat(result.getString("alamat"));
                customer.setNoTelepon(result.getString("no_telepon"));
                listCustomer.add(customer);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return listCustomer;
        }

        return listCustomer;
    }
    
    public ArrayList<CustomerModel> getCustomersByNoTelepon(String noTelepon) {
        ArrayList<CustomerModel> listCustomer = new ArrayList<>();
        String query = "SELECT * FROM customer WHERE no_telepon LIKE '" + noTelepon + "%' AND deleted = false";
        try {
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                CustomerModel customer = new CustomerModel();
                customer.setKode(result.getString("kode_kustomer"));
                customer.setName(result.getString("nama_kustomer"));
                customer.setProvinsiId(result.getString("provinsi_id"));
                customer.setKabupatenId(result.getString("kabupaten_id"));
                customer.setKecamatanId(result.getString("kecamatan_id"));
                customer.setKelurahanId(result.getString("kelurahan_id"));
                customer.setAlamat(result.getString("alamat"));
                customer.setNoTelepon(result.getString("no_telepon"));
                listCustomer.add(customer);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return listCustomer;
        }

        return listCustomer;
    }
    
    public ArrayList<CustomerModel> getCustomersByNama(String nama) {
        ArrayList<CustomerModel> listCustomer = new ArrayList<>();
        String query = "SELECT * FROM customer WHERE nama_kustomer LIKE '%" + nama + "%' AND deleted = false";
        try {
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                CustomerModel customer = new CustomerModel();
                customer.setKode(result.getString("kode_kustomer"));
                customer.setName(result.getString("nama_kustomer"));
                customer.setProvinsiId(result.getString("provinsi_id"));
                customer.setKabupatenId(result.getString("kabupaten_id"));
                customer.setKecamatanId(result.getString("kecamatan_id"));
                customer.setKelurahanId(result.getString("kelurahan_id"));
                customer.setAlamat(result.getString("alamat"));
                customer.setNoTelepon(result.getString("no_telepon"));
                listCustomer.add(customer);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return listCustomer;
        }

        return listCustomer;
    }

    public Boolean saveCustomer(Boolean isUpdate, CustomerModel model) {
        Boolean isResult = false;
        try {
            String query = "";
            if (isUpdate) {
                query = "UPDATE customer SET nama_kustomer ='" + model.getName() + "',"
                        + "provinsi_id = '" + model.getProvinsiId() + "',"
                        + "kabupaten_id = '" + model.getKabupatenId() + "',"
                        + "kecamatan_id = '" + model.getKecamatanId() + "',"
                        + "kelurahan_id = '" + model.getKelurahanId() + "',"
                        + "alamat = '" + model.getAlamat() + "',"
                        + "no_telepon = '" + model.getNoTelepon() + "' "
                        + "WHERE kode_kustomer ='" + model.getKode() + "'";
                if (statement.executeUpdate(query) > 0) {
                    isResult = true;
                } else {
                    isResult = false;
                }
            } else {
                query = "INSERT INTO customer VALUES ('" + model.getKode() + "', "
                        + "'" + model.getName() + "',"
                        + "'" + model.getProvinsiId() + "',"
                        + "'" + model.getKabupatenId() + "',"
                        + "'" + model.getKecamatanId() + "',"
                        + "'" + model.getKelurahanId() + "',"
                        + "'" + model.getAlamat() + "',"
                        + "'" + model.getNoTelepon() + "')";
                if (statement.executeUpdate(query) > 0) {
                    isResult = true;
                } else {
                    isResult = false;
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
            isResult = false;
            return isResult;
        }
        return isResult;
    }
    
    public Boolean deleteCustomer(String customerId) {
        try {
            String query = "UPDATE customer SET deleted = true WHERE kode_kustomer = '" + customerId + "'";
            return statement.executeUpdate(query) > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    //</editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Master Barang">
    public String getKodeBarang() {
        String kode = "BR";
        String query = "SELECT COUNT(*) 'total' FROM barang";
        int totalBarang = 0;
        try {
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                totalBarang = result.getInt("total") + 1;
            }
            if (totalBarang < 10) {
                kode += "000" + totalBarang;
            } else if (totalBarang < 100) {
                kode += "00" + totalBarang;
            } else if (totalBarang < 1000) {
                kode += "0" + totalBarang;
            } else {
                kode += "" + totalBarang;
            }
        } catch (SQLException ex) {
            System.out.println("Ambil data barang gagal");
            return kode;
        }
        return kode;
    }
    
    public Boolean saveBarang(Boolean isUpdate, BarangModel model) {
        Boolean isResult = false;
        try {
            String query = "";
            if (isUpdate) {
                query = "UPDATE barang SET nama_barang ='" + model.getName()+ "',"
                        + "warna = '" + model.getWarna() + "',"
                        + "area = '" + model.getArea() + "',"
                        + "ukuran = '" + model.getUkuran() + "',"
                        + "harga_hpp = '" + model.getHargaHPP()+ "',"
                        + "harga_jual_satuan = '" + model.getHargaJualSatuan() + "' "
                        + "WHERE kode_barang ='" + model.getKode() + "'";
                if (statement.executeUpdate(query) > 0) {
                    isResult = true;
                } else {
                    isResult = false;
                }
            } else {
                query = "INSERT INTO barang VALUES ('" + model.getKode() + "', "
                        + "'" + model.getName() + "',"
                        + "'" + model.getWarna()+ "',"
                        + "'" + model.getArea() + "',"
                        + "'" + model.getUkuran() + "',"
                        + "'" + model.getHargaHPP() + "',"
                        + "'" + model.getHargaJualSatuan()+ "')";
                if (statement.executeUpdate(query) > 0) {
                    isResult = true;
                } else {
                    isResult = false;
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
            isResult = false;
            return isResult;
        }
        return isResult;
    }
    
    public ArrayList<BarangModel> getBarangs() {
        ArrayList<BarangModel> listBarang = new ArrayList<>();
        String query = "SELECT * FROM barang WHERE deleted = false";
        try {
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                BarangModel barang = new BarangModel();
                barang.setKode(result.getString("kode_barang"));
                barang.setName(result.getString("nama_barang"));
                barang.setWarna(result.getString("warna"));
                barang.setArea(result.getString("area"));
                barang.setUkuran(result.getString("ukuran"));
                barang.setHargaHPP(result.getInt("harga_hpp"));
                barang.setHargaJualSatuan(result.getInt("harga_jual_satuan"));
                listBarang.add(barang);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return listBarang;
        }

        return listBarang;
    }
    
    public ArrayList<BarangModel> getBarangsByNama(String nama) {
        ArrayList<BarangModel> listBarang = new ArrayList<>();
        String query = "SELECT * FROM barang WHERE nama_barang LIKE '%"+ nama +"%' AND deleted = false";
        try {
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                BarangModel barang = new BarangModel();
                barang.setKode(result.getString("kode_barang"));
                barang.setName(result.getString("nama_barang"));
                barang.setWarna(result.getString("warna"));
                barang.setArea(result.getString("area"));
                barang.setUkuran(result.getString("ukuran"));
                barang.setHargaHPP(result.getInt("harga_hpp"));
                barang.setHargaJualSatuan(result.getInt("harga_jual_satuan"));
                listBarang.add(barang);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return listBarang;
        }

        return listBarang;
    }
    
    public boolean deleteBarang(String barangId) {
        String query = "UPDATE barang SET deleted = true WHERE kode_barang = '"  + barangId + "'";
        try {
            return statement.executeUpdate(query) > 0 ;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Master Supplier">
    public String getKodeSupplier() {
        String kode = "SU";
        String query = "SELECT COUNT(*) 'total' FROM supplier";
        int totalSupplier = 0;
        try {
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                totalSupplier = result.getInt("total") + 1;
            }
            if (totalSupplier < 10) {
                kode += "000" + totalSupplier;
            } else if (totalSupplier < 100) {
                kode += "00" + totalSupplier;
            } else if (totalSupplier < 1000) {
                kode += "0" + totalSupplier;
            } else {
                kode += "" + totalSupplier;
            }
        } catch (SQLException ex) {
            System.out.println("Ambil data supplier gagal");
            return kode;
        }
        return kode;
    }
    
    public ArrayList<SupplierModel> getSuppliers() {
        ArrayList<SupplierModel> listSupplier = new ArrayList<>();
        String query = "SELECT * FROM supplier WHERE deleted = false";
        try {
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                SupplierModel supplier = new SupplierModel();
                supplier.setKode(result.getString("kode_supplier"));
                supplier.setName(result.getString("nama_supplier"));
                supplier.setProvinsiId(result.getString("provinsi_id"));
                supplier.setKabupatenId(result.getString("kabupaten_id"));
                supplier.setKecamatanId(result.getString("kecamatan_id"));
                supplier.setKelurahanId(result.getString("kelurahan_id"));
                supplier.setAlamat(result.getString("alamat"));
                supplier.setNoTelepon(result.getString("no_telepon"));
                supplier.setNoFax(result.getString("no_fax"));
                supplier.setEmail(result.getString("email"));
                listSupplier.add(supplier);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return listSupplier;
        }

        return listSupplier;
    }
    
    public ArrayList<SupplierModel> getSupplierByNoTelepon(String noTelepon) {
        ArrayList<SupplierModel> listSupplier = new ArrayList<>();
        String query = "SELECT * FROM supplier WHERE no_telepon LIKE '" + noTelepon + "%' AND deleted = false";
        try {
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                SupplierModel supplier = new SupplierModel();
                supplier.setKode(result.getString("kode_supplier"));
                supplier.setName(result.getString("nama_supplier"));
                supplier.setProvinsiId(result.getString("provinsi_id"));
                supplier.setKabupatenId(result.getString("kabupaten_id"));
                supplier.setKecamatanId(result.getString("kecamatan_id"));
                supplier.setKelurahanId(result.getString("kelurahan_id"));
                supplier.setAlamat(result.getString("alamat"));
                supplier.setNoTelepon(result.getString("no_telepon"));
                supplier.setNoFax(result.getString("no_fax"));
                supplier.setEmail(result.getString("email"));
                listSupplier.add(supplier);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return listSupplier;
        }

        return listSupplier;
    }
    
    public ArrayList<SupplierModel> getSupplierByNama(String nama) {
        ArrayList<SupplierModel> listSupplier = new ArrayList<>();
        String query = "SELECT * FROM supplier WHERE nama_supplier LIKE '" + nama + "%' AND deleted = false";
        try {
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                SupplierModel supplier = new SupplierModel();
                supplier.setKode(result.getString("kode_supplier"));
                supplier.setName(result.getString("nama_supplier"));
                supplier.setProvinsiId(result.getString("provinsi_id"));
                supplier.setKabupatenId(result.getString("kabupaten_id"));
                supplier.setKecamatanId(result.getString("kecamatan_id"));
                supplier.setKelurahanId(result.getString("kelurahan_id"));
                supplier.setAlamat(result.getString("alamat"));
                supplier.setNoTelepon(result.getString("no_telepon"));
                supplier.setNoFax(result.getString("no_fax"));
                supplier.setEmail(result.getString("email"));
                listSupplier.add(supplier);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return listSupplier;
        }

        return listSupplier;
    }
    
    public Boolean saveSupplier(Boolean isUpdate, SupplierModel model) {
        Boolean isResult = false;
        try {
            String query = "";
            if (isUpdate) {
                query = "UPDATE supplier SET nama_supplier ='" + model.getName() + "',"
                        + "provinsi_id = '" + model.getProvinsiId() + "',"
                        + "kabupaten_id = '" + model.getKabupatenId() + "',"
                        + "kecamatan_id = '" + model.getKecamatanId() + "',"
                        + "kelurahan_id = '" + model.getKelurahanId() + "',"
                        + "alamat = '" + model.getAlamat() + "',"
                        + "no_telepon = '" + model.getNoTelepon() + "',"
                        + "no_fax ='" + model.getNoFax() + "',"
                        + "email = '" + model.getEmail() + "' "
                        + "WHERE kode_supplier ='" + model.getKode() + "'";
                if (statement.executeUpdate(query) > 0) {
                    isResult = true;
                } else {
                    isResult = false;
                }
            } else {
                query = "INSERT INTO supplier VALUES ('" + model.getKode() + "', "
                        + "'" + model.getName() + "',"
                        + "'" + model.getProvinsiId() + "',"
                        + "'" + model.getKabupatenId() + "',"
                        + "'" + model.getKecamatanId() + "',"
                        + "'" + model.getKelurahanId() + "',"
                        + "'" + model.getAlamat() + "',"
                        + "'" + model.getNoTelepon()+ "',"
                        + "'" + model.getNoFax()+ "',"
                        + "'" + model.getEmail() + "')";
                if (statement.executeUpdate(query) > 0) {
                    isResult = true;
                } else {
                    isResult = false;
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
            isResult = false;
            return isResult;
        }
        return isResult;
    }
    
    public boolean deleteSupplier(String supplierId) {
        String query = "UPDATE supplier SET deleted = true WHERE kode_supplier = '" + supplierId + "'";
        try {
            return statement.executeUpdate(query) > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Master Ekspedisi">
    public String getKodeEkspedisi() {
        String kode = "EX";
        String query = "SELECT COUNT(*) 'total' FROM ekspedisi";
        int totalEkspedisi = 0;
        try {
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                totalEkspedisi = result.getInt("total") + 1;
            }
            if (totalEkspedisi < 10) {
                kode += "000" + totalEkspedisi;
            } else if (totalEkspedisi < 100) {
                kode += "00" + totalEkspedisi;
            } else if (totalEkspedisi < 1000) {
                kode += "0" + totalEkspedisi;
            } else {
                kode += "" + totalEkspedisi;
            }
        } catch (SQLException ex) {
            System.out.println("Ambil data ekspedisi gagal");
            return kode;
        }
        return kode;
    }
    
    public ArrayList<EkspedisiModel> getEkspedisis() {
        ArrayList<EkspedisiModel> listEkspedisi = new ArrayList<>();
        String query = "SELECT * FROM ekspedisi WHERE deleted = false";
        try {
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                EkspedisiModel ekspedisi = new EkspedisiModel();
                ekspedisi.setKode(result.getString("kode_ekspedisi"));
                ekspedisi.setName(result.getString("nama_ekspedisi"));
                listEkspedisi.add(ekspedisi);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return listEkspedisi;
        }

        return listEkspedisi;
    }
    
    public ArrayList<EkspedisiModel> getEkspedisiByNama(String nama) {
        ArrayList<EkspedisiModel> listEkspedisi = new ArrayList<>();
        String query = "SELECT * FROM ekspedisi WHERE nama_ekspedisi LIKE '%" + nama + "%' AND deleted = false";
        try {
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                EkspedisiModel ekspedisi = new EkspedisiModel();
                ekspedisi.setKode(result.getString("kode_ekspedisi"));
                ekspedisi.setName(result.getString("nama_ekspedisi"));
                listEkspedisi.add(ekspedisi);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return listEkspedisi;
        }

        return listEkspedisi;
    }

    public Boolean saveEkspedisi(Boolean isUpdate, EkspedisiModel model) {
        Boolean isResult = false;
        try {
            String query = "";
            if (isUpdate) {
                query = "UPDATE ekspedisi SET nama_ekspedisi ='" + model.getName() + "' "
                        + "WHERE kode_ekspedisi ='" + model.getKode() + "'";
                if (statement.executeUpdate(query) > 0) {
                    isResult = true;
                } else {
                    isResult = false;
                }
            } else {
                query = "INSERT INTO ekspedisi VALUES ('" + model.getKode() + "', "
                        + "'" + model.getName() + "')";
                if (statement.executeUpdate(query) > 0) {
                    isResult = true;
                } else {
                    isResult = false;
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
            isResult = false;
            return isResult;
        }
        return isResult;
    }
    
    public boolean deleteEkspedisi(String ekspedisiId) {
        String query = "UPDATE ekspedisi SET deleted = true WHERE kode_ekspedisi = '" + ekspedisiId + "'";
        try {
            return statement.executeUpdate(query) > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Input Order Penjualan">
    public String getKodePesanan() {
        String kode = "PS";
        String query = "SELECT COUNT(*) 'total' FROM input_order_penjualan";
        int totalPesanan = 0;
        try {
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                totalPesanan = result.getInt("total") + 1;
            }
            if (totalPesanan < 10) {
                kode += "000" + totalPesanan;
            } else if (totalPesanan < 100) {
                kode += "00" + totalPesanan;
            } else if (totalPesanan < 1000) {
                kode += "0" + totalPesanan;
            } else {
                kode += "" + totalPesanan;
            }
        } catch (SQLException ex) {
            System.out.println("Ambil data order pesanan gagal");
            return kode;
        }
        return kode;
    }
    
    public int simpanInputOrderPenjualan(InputOrderPenjualanModel item) throws SQLException {
        String tableName = "input_order_penjualan";
        String query = String.format("INSERT INTO %s VALUES ('%s', '%s', '%s', %d, %d, '%s')",
                tableName, item.getKodePesanan(), item.getKodeKustomer(),
                item.getKodeEkspedisi(), item.getOngkir(), item.getTotal(), item.getTanggal());
        int inserted = statement.executeUpdate(query);
        if (inserted > 0) {
            for (InputOrderPenjualanDetailModel itemDetail : item.getOrders()) {
                itemDetail.setKodePesanan(item.getKodePesanan());
                simpanInputOrderPenjualanDetail(itemDetail);
            }
        }
        return inserted;
    }
    
    public int simpanInputOrderPenjualanDetail(InputOrderPenjualanDetailModel item) throws SQLException {
        String tableName = "input_order_penjualan_detail";
        String query = String.format("INSERT INTO %s VALUES ('%s', '%s', %d, '%s')", tableName, item.getKodePesanan(), item.getKodeBarang(), item.getQty(), item.getTipeLengan());
        return statement.executeUpdate(query);
    }
    
    public int setStatusBayarOrderPenjualan(PesananModel pesananModel, boolean statusBayar) {
        try {
            String query = String.format("UPDATE input_order_penjualan SET is_lunas = %b WHERE kode_pesanan = '%s'", statusBayar, pesananModel.getKodePesanan());
            return statement.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public List<PesananModel> getDaftarPesanan() {
        ArrayList<PesananModel> items = new ArrayList<>();
        String query = "SELECT iop.kode_pesanan, iop.kode_kustomer, c.nama_kustomer, iop.kode_ekspedisi, e.nama_ekspedisi, iop.ongkir, iop.total, iop.tanggal as 'tanggal_pemesanan', iop.is_lunas " +
                "FROM input_order_penjualan iop " +
                "LEFT JOIN customer c ON iop.kode_kustomer = c.kode_kustomer " +
                "LEFT JOIN ekspedisi e ON iop.kode_ekspedisi = e.kode_ekspedisi;";
        try {
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                PesananModel item = new PesananModel();
                item.setKodePesanan(result.getString("kode_pesanan"));
                item.setKodeKustomer(result.getString("kode_kustomer"));
                item.setNamaKustomer(result.getString("nama_kustomer"));
                item.setKodeEkspedisi(result.getString("kode_ekspedisi"));
                item.setNamaEkspedisi(result.getString("nama_ekspedisi"));
                item.setOngkir(result.getInt("ongkir"));
                item.setTotal(result.getInt("total"));
                item.setTanggalPemesanan(result.getString("tanggal_pemesanan"));
                item.setLunas(result.getBoolean("is_lunas"));
                items.add(item);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return items;
        }
        return items;
    }
    
    public PesananModel getDaftarPesananById(String inputOrderPenjualanId) {
        try {
            String query = String.format("SELECT iop.kode_pesanan, iop.kode_kustomer, c.nama_kustomer, iop.kode_ekspedisi, e.nama_ekspedisi, iop.ongkir, iop.total, iop.tanggal as 'tanggal_pemesanan', iop.is_lunas " +
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
                item.setOngkir(result.getInt("ongkir"));
                item.setTotal(result.getInt("total"));
                item.setTanggalPemesanan(result.getString("tanggal_pemesanan"));
                item.setLunas(result.getBoolean("is_lunas"));
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
    
    public String getKodeInvoice() {
        return getKode("INV-", "cetak_invoice_penjualan");
    }
    
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
    
}
