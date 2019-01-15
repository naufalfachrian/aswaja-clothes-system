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
import javax.persistence.CascadeType;
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
@Table(name = "pesanan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pesanan.findAll", query = "SELECT p FROM Pesanan p"),
    @NamedQuery(name = "Pesanan.findByKodePesanan", query = "SELECT p FROM Pesanan p WHERE p.kodePesanan = :kodePesanan"),
    @NamedQuery(name = "Pesanan.findByOngkir", query = "SELECT p FROM Pesanan p WHERE p.ongkir = :ongkir"),
    @NamedQuery(name = "Pesanan.findByTotal", query = "SELECT p FROM Pesanan p WHERE p.total = :total"),
    @NamedQuery(name = "Pesanan.findByTanggal", query = "SELECT p FROM Pesanan p WHERE p.tanggal = :tanggal"),
    @NamedQuery(name = "Pesanan.findByBerat", query = "SELECT p FROM Pesanan p WHERE p.berat = :berat")})
public class Pesanan implements Serializable {

    @OneToMany(mappedBy = "pesanan")
    private List<InvoicePesanan> invoicePesananList;

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "kode_pesanan")
    private String kodePesanan;
    
    @Basic(optional = false)
    @Column(name = "ongkir")
    private int ongkir;
    
    @Basic(optional = false)
    @Column(name = "total")
    private int total;
    
    @Basic(optional = false)
    @Column(name = "tanggal")
    @Temporal(TemporalType.DATE)
    private Date tanggal;
    
    @Column(name = "berat")
    private Integer berat;
    
    @JoinColumn(name = "ekspedisi", referencedColumnName = "kode_ekspedisi")
    @ManyToOne(optional = false)
    private Ekspedisi ekspedisi;
    
    @JoinColumn(name = "kustomer", referencedColumnName = "kode_kustomer")
    @ManyToOne(optional = false)
    private Kustomer kustomer;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pesanan")
    private List<PesananDetail> pesananDetailList;

    public Pesanan() {
    }

    public Pesanan(String kodePesanan) {
        this.kodePesanan = kodePesanan;
    }

    public Pesanan(String kodePesanan, int ongkir, int total, Date tanggal) {
        this.kodePesanan = kodePesanan;
        this.ongkir = ongkir;
        this.total = total;
        this.tanggal = tanggal;
    }

    public String getKodePesanan() {
        return kodePesanan;
    }

    public void setKodePesanan(String kodePesanan) {
        this.kodePesanan = kodePesanan;
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

    public Ekspedisi getEkspedisi() {
        return ekspedisi;
    }

    public void setEkspedisi(Ekspedisi ekspedisi) {
        this.ekspedisi = ekspedisi;
    }

    public Kustomer getKustomer() {
        return kustomer;
    }

    public void setKustomer(Kustomer kustomer) {
        this.kustomer = kustomer;
    }

    @XmlTransient
    public List<PesananDetail> getPesananDetailList() {
        return pesananDetailList;
    }

    public void setPesananDetailList(List<PesananDetail> pesananDetailList) {
        this.pesananDetailList = pesananDetailList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kodePesanan != null ? kodePesanan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pesanan)) {
            return false;
        }
        Pesanan other = (Pesanan) object;
        if ((this.kodePesanan == null && other.kodePesanan != null) || (this.kodePesanan != null && !this.kodePesanan.equals(other.kodePesanan))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "aswajaclothes.entity.Pesanan[ kodePesanan=" + kodePesanan + " ]";
    }

    @XmlTransient
    public List<InvoicePesanan> getInvoicePesananList() {
        return invoicePesananList;
    }

    public void setInvoicePesananList(List<InvoicePesanan> invoicePesananList) {
        this.invoicePesananList = invoicePesananList;
    }
    
}
