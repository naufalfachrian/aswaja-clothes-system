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
@Table(name = "invoice_pesanan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InvoicePesanan.findAll", query = "SELECT i FROM InvoicePesanan i"),
    @NamedQuery(name = "InvoicePesanan.findByKodeInvoice", query = "SELECT i FROM InvoicePesanan i WHERE i.kodeInvoice = :kodeInvoice"),
    @NamedQuery(name = "InvoicePesanan.findByPpn", query = "SELECT i FROM InvoicePesanan i WHERE i.ppn = :ppn"),
    @NamedQuery(name = "InvoicePesanan.findByTanggal", query = "SELECT i FROM InvoicePesanan i WHERE i.tanggal = :tanggal"),
    @NamedQuery(name = "InvoicePesanan.findByLunas", query = "SELECT i FROM InvoicePesanan i WHERE i.lunas = :lunas")})
public class InvoicePesanan implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "kode_invoice")
    private String kodeInvoice;
    
    @Column(name = "ppn")
    private Integer ppn;
    
    @Basic(optional = false)
    @Column(name = "tanggal")
    @Temporal(TemporalType.DATE)
    private Date tanggal;
    
    @Column(name = "lunas")
    private Boolean lunas;
    
    @JoinColumn(name = "pesanan", referencedColumnName = "kode_pesanan")
    @ManyToOne
    private Pesanan pesanan;

    public InvoicePesanan() {
    }

    public InvoicePesanan(String kodeInvoice) {
        this.kodeInvoice = kodeInvoice;
    }

    public String getKodeInvoice() {
        return kodeInvoice;
    }

    public void setKodeInvoice(String kodeInvoice) {
        this.kodeInvoice = kodeInvoice;
    }

    public Integer getPpn() {
        return ppn;
    }

    public void setPpn(Integer ppn) {
        this.ppn = ppn;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public Boolean getLunas() {
        return lunas;
    }

    public void setLunas(Boolean lunas) {
        this.lunas = lunas;
    }

    public Pesanan getPesanan() {
        return pesanan;
    }

    public void setPesanan(Pesanan pesanan) {
        this.pesanan = pesanan;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kodeInvoice != null ? kodeInvoice.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InvoicePesanan)) {
            return false;
        }
        InvoicePesanan other = (InvoicePesanan) object;
        if ((this.kodeInvoice == null && other.kodeInvoice != null) || (this.kodeInvoice != null && !this.kodeInvoice.equals(other.kodeInvoice))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "aswajaclothes.entity.InvoicePesanan[ kodeInvoice=" + kodeInvoice + " ]";
    }
    
}
