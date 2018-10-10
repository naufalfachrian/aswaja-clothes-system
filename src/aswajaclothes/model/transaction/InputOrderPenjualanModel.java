/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aswajaclothes.model.transaction;

/**
 *
 * @author Satrio
 */
public class InputOrderPenjualanModel {
    private String kodePesanan;
    private String kodeKustomer;
    private String kodeEkspedisi;
    private int ongkir;
    private int total;
    private String tanggal;

    public InputOrderPenjualanModel() {
    }

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

    public String getKodeEkspedisi() {
        return kodeEkspedisi;
    }

    public void setKodeEkspedisi(String kodeEkspedisi) {
        this.kodeEkspedisi = kodeEkspedisi;
    }

    public int getOngkir() {
        return ongkir;
    }

    public void setOngkir(int ongkir) {
        this.ongkir = ongkir;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
    
    
}
