/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aswajaclothes.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author littleflower
 */
@Entity
@Table(name = "pembelian")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pembelian.findAll", query = "SELECT p FROM Pembelian p"),
    @NamedQuery(name = "Pembelian.findByKodePembelian", query = "SELECT p FROM Pembelian p WHERE p.kodePembelian = :kodePembelian"),
    @NamedQuery(name = "Pembelian.findByTanggal", query = "SELECT p FROM Pembelian p WHERE p.tanggal = :tanggal"),
    @NamedQuery(name = "Pembelian.findByBerat", query = "SELECT p FROM Pembelian p WHERE p.berat = :berat"),
    @NamedQuery(name = "Pembelian.findByOngkir", query = "SELECT p FROM Pembelian p WHERE p.ongkir = :ongkir")})
public class Pembelian implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "kode_pembelian")
    private String kodePembelian;
    
    @Column(name = "tanggal")
    @Temporal(TemporalType.DATE)
    private Date tanggal;
    
    @Column(name = "berat")
    private Integer berat;
    
    @Column(name = "ongkir")
    private Integer ongkir;
    
    @OneToMany(mappedBy = "pembelian")
    private List<PembelianDetail> pembelianDetailList;
    
    @JoinColumn(name = "ekspedisi", referencedColumnName = "kode_ekspedisi")
    @ManyToOne
    private Ekspedisi ekspedisi;
    
    @JoinColumn(name = "supplier", referencedColumnName = "kode_supplier")
    @ManyToOne
    private Supplier supplier;
    
    @Column(name = "alamat_pengiriman")
    private String alamatPengiriman;

    public Pembelian() {
    }

    public Pembelian(String kodePembelian) {
        this.kodePembelian = kodePembelian;
    }

    public String getKodePembelian() {
        return kodePembelian;
    }

    public void setKodePembelian(String kodePembelian) {
        this.kodePembelian = kodePembelian;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public Integer getBerat() {
        return berat;
    }

    public void setBerat(Integer berat) {
        this.berat = berat;
    }

    public Integer getOngkir() {
        return ongkir;
    }

    public void setOngkir(Integer ongkir) {
        this.ongkir = ongkir;
    }

    @XmlTransient
    public List<PembelianDetail> getPembelianDetailList() {
        return pembelianDetailList;
    }

    public void setPembelianDetailList(List<PembelianDetail> pembelianDetailList) {
        this.pembelianDetailList = pembelianDetailList;
    }

    public Ekspedisi getEkspedisi() {
        return ekspedisi;
    }

    public void setEkspedisi(Ekspedisi ekspedisi) {
        this.ekspedisi = ekspedisi;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kodePembelian != null ? kodePembelian.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pembelian)) {
            return false;
        }
        Pembelian other = (Pembelian) object;
        if ((this.kodePembelian == null && other.kodePembelian != null) || (this.kodePembelian != null && !this.kodePembelian.equals(other.kodePembelian))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "aswajaclothes.entity.Pembelian[ kodePembelian=" + kodePembelian + " ]";
    }

    public String getAlamatPengiriman() {
        return alamatPengiriman;
    }

    public void setAlamatPengiriman(String alamatPengiriman) {
        this.alamatPengiriman = alamatPengiriman;
    }
    
}
