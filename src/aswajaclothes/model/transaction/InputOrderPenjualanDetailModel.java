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
public class InputOrderPenjualanDetailModel {
    private String kodePesanan;
    private String kodeBarang;
    private int qty;
    private String tipeLengan;

    public InputOrderPenjualanDetailModel() {
    }

    public String getKodePesanan() {
        return kodePesanan;
    }

    public void setKodePesanan(String kodePesanan) {
        this.kodePesanan = kodePesanan;
    }

    public String getKodeBarang() {
        return kodeBarang;
    }

    public void setKodeBarang(String kodeBarang) {
        this.kodeBarang = kodeBarang;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getTipeLengan() {
        return tipeLengan;
    }

    public void setTipeLengan(String tipeLengan) {
        this.tipeLengan = tipeLengan;
    }
    
    
}
