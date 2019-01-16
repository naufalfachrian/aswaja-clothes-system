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
@Table(name = "bukti_pembayaran")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BuktiPembayaran.findAll", query = "SELECT b FROM BuktiPembayaran b"),
    @NamedQuery(name = "BuktiPembayaran.findByKodeBukti", query = "SELECT b FROM BuktiPembayaran b WHERE b.kodeBukti = :kodeBukti"),
    @NamedQuery(name = "BuktiPembayaran.findByTanggal", query = "SELECT b FROM BuktiPembayaran b WHERE b.tanggal = :tanggal")})
public class BuktiPembayaran implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "kode_bukti")
    private String kodeBukti;
    @Column(name = "tanggal")
    @Temporal(TemporalType.DATE)
    private Date tanggal;
    @JoinColumn(name = "invoice_pesanan", referencedColumnName = "kode_invoice")
    @ManyToOne
    private InvoicePesanan invoicePesanan;

    public BuktiPembayaran() {
    }

    public BuktiPembayaran(String kodeBukti) {
        this.kodeBukti = kodeBukti;
    }

    public String getKodeBukti() {
        return kodeBukti;
    }

    public void setKodeBukti(String kodeBukti) {
        this.kodeBukti = kodeBukti;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
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
        hash += (kodeBukti != null ? kodeBukti.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BuktiPembayaran)) {
            return false;
        }
        BuktiPembayaran other = (BuktiPembayaran) object;
        if ((this.kodeBukti == null && other.kodeBukti != null) || (this.kodeBukti != null && !this.kodeBukti.equals(other.kodeBukti))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "aswajaclothes.entity.BuktiPembayaran[ kodeBukti=" + kodeBukti + " ]";
    }
    
}
