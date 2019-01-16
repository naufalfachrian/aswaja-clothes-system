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
@Table(name = "invoice_pembelian")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InvoicePembelian.findAll", query = "SELECT i FROM InvoicePembelian i"),
    @NamedQuery(name = "InvoicePembelian.findByKodeInvoice", query = "SELECT i FROM InvoicePembelian i WHERE i.kodeInvoice = :kodeInvoice"),
    @NamedQuery(name = "InvoicePembelian.findByTanggal", query = "SELECT i FROM InvoicePembelian i WHERE i.tanggal = :tanggal")})
public class InvoicePembelian implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "kode_invoice")
    private String kodeInvoice;
    
    @Column(name = "tanggal")
    @Temporal(TemporalType.DATE)
    private Date tanggal;
    
    @JoinColumn(name = "pembelian", referencedColumnName = "kode_pembelian")
    @ManyToOne
    private Pembelian pembelian;

    public InvoicePembelian() {
    }

    public InvoicePembelian(String kodeInvoice) {
        this.kodeInvoice = kodeInvoice;
    }

    public String getKodeInvoice() {
        return kodeInvoice;
    }

    public void setKodeInvoice(String kodeInvoice) {
        this.kodeInvoice = kodeInvoice;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public Pembelian getPembelian() {
        return pembelian;
    }

    public void setPembelian(Pembelian pembelian) {
        this.pembelian = pembelian;
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
        if (!(object instanceof InvoicePembelian)) {
            return false;
        }
        InvoicePembelian other = (InvoicePembelian) object;
        if ((this.kodeInvoice == null && other.kodeInvoice != null) || (this.kodeInvoice != null && !this.kodeInvoice.equals(other.kodeInvoice))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "aswajaclothes.entity.InvoicePembelian[ kodeInvoice=" + kodeInvoice + " ]";
    }
    
}
