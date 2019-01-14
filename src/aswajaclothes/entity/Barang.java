/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aswajaclothes.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author littleflower
 */
@Entity
@Table(name = "barang")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Barang.findAll", query = "SELECT b FROM Barang b"),
    @NamedQuery(name = "Barang.findByKodeBarang", query = "SELECT b FROM Barang b WHERE b.kodeBarang = :kodeBarang"),
    @NamedQuery(name = "Barang.findByNamaBarang", query = "SELECT b FROM Barang b WHERE b.namaBarang = :namaBarang"),
    @NamedQuery(name = "Barang.findByWarna", query = "SELECT b FROM Barang b WHERE b.warna = :warna"),
    @NamedQuery(name = "Barang.findByArea", query = "SELECT b FROM Barang b WHERE b.area = :area"),
    @NamedQuery(name = "Barang.findByUkuran", query = "SELECT b FROM Barang b WHERE b.ukuran = :ukuran"),
    @NamedQuery(name = "Barang.findByHargaHpp", query = "SELECT b FROM Barang b WHERE b.hargaHpp = :hargaHpp"),
    @NamedQuery(name = "Barang.findByHargaJualSatuan", query = "SELECT b FROM Barang b WHERE b.hargaJualSatuan = :hargaJualSatuan"),
    @NamedQuery(name = "Barang.findByDelete", query = "SELECT b FROM Barang b WHERE b.delete = :delete")})
public class Barang implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "kode_barang")
    private String kodeBarang;
    
    @Basic(optional = false)
    @Column(name = "nama_barang")
    private String namaBarang;
    
    @Basic(optional = false)
    @Column(name = "warna")
    private String warna;
    
    @Basic(optional = false)
    @Column(name = "area")
    private String area;
    
    @Basic(optional = false)
    @Column(name = "ukuran")
    private String ukuran;
    
    @Basic(optional = false)
    @Column(name = "harga_hpp")
    private int hargaHpp;
    
    @Basic(optional = false)
    @Column(name = "harga_jual_satuan")
    private int hargaJualSatuan;
    
    @Column(name = "terhapus")
    private Boolean delete;

    public Barang() {
    }

    public Barang(String kodeBarang) {
        this.kodeBarang = kodeBarang;
    }

    public Barang(String kodeBarang, String namaBarang, String warna, String area, String ukuran, int hargaHpp, int hargaJualSatuan) {
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.warna = warna;
        this.area = area;
        this.ukuran = ukuran;
        this.hargaHpp = hargaHpp;
        this.hargaJualSatuan = hargaJualSatuan;
    }

    public String getKodeBarang() {
        return kodeBarang;
    }

    public void setKodeBarang(String kodeBarang) {
        this.kodeBarang = kodeBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
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

    public int getHargaHpp() {
        return hargaHpp;
    }

    public void setHargaHpp(int hargaHpp) {
        this.hargaHpp = hargaHpp;
    }

    public int getHargaJualSatuan() {
        return hargaJualSatuan;
    }

    public void setHargaJualSatuan(int hargaJualSatuan) {
        this.hargaJualSatuan = hargaJualSatuan;
    }

    public Boolean getDelete() {
        return delete;
    }

    public void setDelete(Boolean delete) {
        this.delete = delete;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kodeBarang != null ? kodeBarang.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Barang)) {
            return false;
        }
        Barang other = (Barang) object;
        if ((this.kodeBarang == null && other.kodeBarang != null) || (this.kodeBarang != null && !this.kodeBarang.equals(other.kodeBarang))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "aswajaclothes.entity.Barang[ kodeBarang=" + kodeBarang + " ]";
    }
    
}
