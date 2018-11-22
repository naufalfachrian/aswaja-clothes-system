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
public class ItemPesananModel {
    
    private String kodePesanan;
    
    private BarangModel barang;
    
    private Integer quantity;

    public String getKodePesanan() {
        return kodePesanan;
    }

    public void setKodePesanan(String kodePesanan) {
        this.kodePesanan = kodePesanan;
    }

    public BarangModel getBarang() {
        return barang;
    }

    public void setBarang(BarangModel barang) {
        this.barang = barang;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
}
