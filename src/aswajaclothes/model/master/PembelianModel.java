/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aswajaclothes.model.master;

import aswajaclothes.model.common.KabupatenModel;
import java.util.List;

/**
 *
 * @author littleflower
 */
public class PembelianModel {
    
    private String kode;
    
    private SupplierModel supplier;
    
    private EkspedisiModel ekspedisi;
    
    private String dateString;
    
    private String alamatPengiriman;
    
    private KabupatenModel kotaTujuan;
    
    private int berat;
    
    private int ongkir;
    
    private List<String> selectedKodePesanan;

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public SupplierModel getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierModel supplier) {
        this.supplier = supplier;
    }

    public EkspedisiModel getEkspedisi() {
        return ekspedisi;
    }

    public void setEkspedisi(EkspedisiModel ekspedisi) {
        this.ekspedisi = ekspedisi;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public String getAlamatPengiriman() {
        return alamatPengiriman;
    }

    public void setAlamatPengiriman(String alamatPengiriman) {
        this.alamatPengiriman = alamatPengiriman;
    }

    public KabupatenModel getKotaTujuan() {
        return kotaTujuan;
    }

    public void setKotaTujuan(KabupatenModel kotaTujuan) {
        this.kotaTujuan = kotaTujuan;
    }

    public int getBerat() {
        return berat;
    }

    public void setBerat(int berat) {
        this.berat = berat;
    }

    public int getOngkir() {
        return ongkir;
    }

    public void setOngkir(int ongkir) {
        this.ongkir = ongkir;
    }

    public List<String> getSelectedKodePesanan() {
        return selectedKodePesanan;
    }

    public void setSelectedKodePesanan(List<String> selectedKodePesanan) {
        this.selectedKodePesanan = selectedKodePesanan;
    }
    
}
