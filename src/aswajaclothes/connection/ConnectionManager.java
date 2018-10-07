/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aswajaclothes.connection;

import aswajaclothes.master.model.CustomerModel;
import aswajaclothes.common.model.KabupatenModel;
import aswajaclothes.common.model.KecamatanModel;
import aswajaclothes.common.model.KelurahanModel;
import aswajaclothes.common.model.ProvinsiModel;
import aswajaclothes.master.model.BarangModel;
import aswajaclothes.master.model.EkspedisiModel;
import aswajaclothes.master.model.SupplierModel;
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
            connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/aswaja_clothes?useSSL=false", "root", "");
            statement = connection.createStatement();
        } catch (SQLException ex) {
            System.out.print("Connection to database is failed");
            System.out.println(ex.getLocalizedMessage());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Common">
    public ArrayList<ProvinsiModel> getProvinsi() {
        String query = "SELECT * FROM PROVINCES";
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
        String query = "SELECT * FROM REGENCIES WHERE PROVINCE_ID = " + provinsiId;
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
        String query = "SELECT * FROM DISTRICTS WHERE REGENCY_ID = " + kabupatenId;
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
        String query = "SELECT * FROM VILLAGES WHERE DISTRICT_ID = " + kecamatanId;
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
        String query = "SELECT COUNT(*) 'total' FROM CUSTOMER";
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
        String query = "SELECT * FROM CUSTOMER";
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
        String query = "SELECT * FROM CUSTOMER WHERE NO_TELEPON LIKE '" + noTelepon + "%'";
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
                query = "UPDATE CUSTOMER SET NAMA_KUSTOMER ='" + model.getName() + "',"
                        + "PROVINSI_ID = '" + model.getProvinsiId() + "',"
                        + "KABUPATEN_ID = '" + model.getKabupatenId() + "',"
                        + "KECAMATAN_ID = '" + model.getKecamatanId() + "',"
                        + "KELURAHAN_ID = '" + model.getKelurahanId() + "',"
                        + "ALAMAT = '" + model.getAlamat() + "',"
                        + "NO_TELEPON = '" + model.getNoTelepon() + "' "
                        + "WHERE KODE_KUSTOMER ='" + model.getKode() + "'";
                if (statement.executeUpdate(query) > 0) {
                    isResult = true;
                } else {
                    isResult = false;
                }
            } else {
                query = "INSERT INTO CUSTOMER VALUES ('" + model.getKode() + "', "
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
    //</editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Master Barang">
    public String getKodeBarang() {
        String kode = "BR";
        String query = "SELECT COUNT(*) 'total' FROM BARANG";
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
                query = "UPDATE BARANG SET NAMA_BARANG ='" + model.getName()+ "',"
                        + "WARNA = '" + model.getWarna() + "',"
                        + "AREA = '" + model.getArea() + "',"
                        + "UKURAN = '" + model.getUkuran() + "',"
                        + "HARGA_HPP = '" + model.getHargaHPP()+ "',"
                        + "HARGA_JUAL_SATUAN = '" + model.getHargaJualSatuan() + "' "
                        + "WHERE KODE_BARANG ='" + model.getKode() + "'";
                if (statement.executeUpdate(query) > 0) {
                    isResult = true;
                } else {
                    isResult = false;
                }
            } else {
                query = "INSERT INTO BARANG VALUES ('" + model.getKode() + "', "
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
        String query = "SELECT * FROM BARANG";
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
        String query = "SELECT * FROM BARANG WHERE NAMA_BARANG LIKE '%"+ nama +"%'";
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
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Master Supplier">
    public String getKodeSupplier() {
        String kode = "SU";
        String query = "SELECT COUNT(*) 'total' FROM SUPPLIER";
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
        String query = "SELECT * FROM SUPPLIER";
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
        String query = "SELECT * FROM SUPPLIER WHERE NO_TELEPON LIKE '" + noTelepon + "%'";
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
                query = "UPDATE SUPPLIER SET NAMA_SUPPLIER ='" + model.getName() + "',"
                        + "PROVINSI_ID = '" + model.getProvinsiId() + "',"
                        + "KABUPATEN_ID = '" + model.getKabupatenId() + "',"
                        + "KECAMATAN_ID = '" + model.getKecamatanId() + "',"
                        + "KELURAHAN_ID = '" + model.getKelurahanId() + "',"
                        + "ALAMAT = '" + model.getAlamat() + "',"
                        + "NO_TELEPON = '" + model.getNoTelepon() + "',"
                        + "NO_FAX ='" + model.getNoFax() + "',"
                        + "EMAIL = '" + model.getEmail() + "' "
                        + "WHERE KODE_SUPPLIER ='" + model.getKode() + "'";
                if (statement.executeUpdate(query) > 0) {
                    isResult = true;
                } else {
                    isResult = false;
                }
            } else {
                query = "INSERT INTO SUPPLIER VALUES ('" + model.getKode() + "', "
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
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Master Ekspedisi">
    public String getKodeEkspedisi() {
        String kode = "EX";
        String query = "SELECT COUNT(*) 'total' FROM EKSPEDISI";
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
        String query = "SELECT * FROM EKSPEDISI";
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
                query = "UPDATE EKSPEDISI SET NAMA_EKSPEDISI ='" + model.getName() + "' "
                        + "WHERE KODE_EKSPEDISI ='" + model.getKode() + "'";
                if (statement.executeUpdate(query) > 0) {
                    isResult = true;
                } else {
                    isResult = false;
                }
            } else {
                query = "INSERT INTO EKSPEDISI VALUES ('" + model.getKode() + "', "
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
    // </editor-fold>
}
