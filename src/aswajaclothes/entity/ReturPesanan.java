/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aswajaclothes.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author littleflower
 */
@Entity
@Table(name = "retur_pesanan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReturPesanan.findAll", query = "SELECT r FROM ReturPesanan r"),
    @NamedQuery(name = "ReturPesanan.findByKodeReturPesanan", query = "SELECT r FROM ReturPesanan r WHERE r.kodeReturPesanan = :kodeReturPesanan"),
    @NamedQuery(name = "ReturPesanan.findByBerat", query = "SELECT r FROM ReturPesanan r WHERE r.berat = :berat"),
    @NamedQuery(name = "ReturPesanan.findByOngkir", query = "SELECT r FROM ReturPesanan r WHERE r.ongkir = :ongkir"),
    @NamedQuery(name = "ReturPesanan.findByTanggal", query = "SELECT r FROM ReturPesanan r WHERE r.tanggal = :tanggal")})
public class ReturPesanan implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "kode_retur_pesanan")
    private String kodeReturPesanan;
    
    @Column(name = "berat")
    private Integer berat;
    
    @Column(name = "ongkir")
    private Integer ongkir;
    
    @Column(name = "tanggal")
    @Temporal(TemporalType.DATE)
    private Date tanggal;
    
    @JoinColumn(name = "ekspedisi", referencedColumnName = "kode_ekspedisi")
    @ManyToOne
    private Ekspedisi ekspedisi;
    
    @JoinColumn(name = "invoice_pesanan", referencedColumnName = "kode_invoice")
    @ManyToOne
    private InvoicePesanan invoicePesanan;

    public ReturPesanan() {
    }

    public ReturPesanan(String kodeReturPesanan) {
        this.kodeReturPesanan = kodeReturPesanan;
    }

    public String getKodeReturPesanan() {
        return kodeReturPesanan;
    }

    public void setKodeReturPesanan(String kodeReturPesanan) {
        this.kodeReturPesanan = kodeReturPesanan;
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

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public Ekspedisi getEkspedisi() {
        return ekspedisi;
    }

    public void setEkspedisi(Ekspedisi ekspedisi) {
        this.ekspedisi = ekspedisi;
    }

    public InvoicePesanan getInvoicePesanan() {
        return invoicePesanan;
    }

    public void setInvoicePesanan(InvoicePesanan invoicePesanan) {
        this.invoicePesanan = invoicePesanan;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kodeReturPesanan != null ? kodeReturPesanan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReturPesanan)) {
            return false;
        }
        ReturPesanan other = (ReturPesanan) object;
        if ((this.kodeReturPesanan == null && other.kodeReturPesanan != null) || (this.kodeReturPesanan != null && !this.kodeReturPesanan.equals(other.kodeReturPesanan))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "aswajaclothes.entity.ReturPesanan[ kodeReturPesanan=" + kodeReturPesanan + " ]";
    }
    
}
