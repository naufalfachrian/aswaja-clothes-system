/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aswajaclothes.model.master;

/**
 *
 * @author Satrio
 */
public class BarangModel {
    private String kode;
    private String name;
    private String warna;
    private String area;
    private String ukuran;
    private int hargaHPP;
    private int hargaJualSatuan;

    public BarangModel() {
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getUkuran() {
        return ukuran;
    }

    public void setUkuran(String ukuran) {
        this.ukuran = ukuran;
    }

    public int getHargaHPP() {
        return hargaHPP;
    }

    public void setHargaHPP(int hargaHPP) {
        this.hargaHPP = hargaHPP;
    }

    public int getHargaJualSatuan() {
        return hargaJualSatuan;
    }

    public void setHargaJualSatuan(int hargaJualSatuan) {
        this.hargaJualSatuan = hargaJualSatuan;
    }
    
}
