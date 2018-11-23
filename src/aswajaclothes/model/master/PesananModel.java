/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aswajaclothes.model.master;

/**
 *
 * @author littleflower
 */
public class PesananModel {
        
    private String kodePesanan;
    
    private String kodeKustomer;
    
    private String namaKustomer;
    
    private String kodeEkspedisi;
    
    private String namaEkspedisi;
    
    private Integer ongkir;
    
    private Integer total;
        
    private String tanggalPemesanan;
    
    private boolean isLunas = false;
        
    public String getKodePesanan() {
        return kodePesanan;
    }

    public void setKodePesanan(String kodePesanan) {
        this.kodePesanan = kodePesanan;
    }

    public String getKodeKustomer() {
        return kodeKustomer;
    }

    public void setKodeKustomer(String kodeKustomer) {
        this.kodeKustomer = kodeKustomer;
    }

    public String getNamaKustomer() {
        return namaKustomer;
    }

    public void setNamaKustomer(String namaKustomer) {
        this.namaKustomer = namaKustomer;
    }

    public String getKodeEkspedisi() {
        return kodeEkspedisi;
    }

    public void setKodeEkspedisi(String kodeEkspedisi) {
        this.kodeEkspedisi = kodeEkspedisi;
    }

    public String getNamaEkspedisi() {
        return namaEkspedisi;
    }

    public void setNamaEkspedisi(String namaEkspedisi) {
        this.namaEkspedisi = namaEkspedisi;
    }

    public Integer getOngkir() {
        return ongkir;
    }

    public void setOngkir(Integer ongkir) {
        this.ongkir = ongkir;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getTanggalPemesanan() {
        return tanggalPemesanan;
    }

    public void setTanggalPemesanan(String tanggalPemesanan) {
        this.tanggalPemesanan = tanggalPemesanan;
    }

    public boolean isLunas() {
        return isLunas;
    }

    public void setLunas(boolean isLunas) {
        this.isLunas = isLunas;
    }
    
}
