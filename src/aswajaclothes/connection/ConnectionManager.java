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
            System.out.println("Ambil data provinsi gagal");
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
        String query = "SELECT * FROM CUSTOMER WHERE NO_TELEPON ='" + noTelepon + "'";
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
}
