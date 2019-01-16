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
@Table(name = "surat_jalan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SuratJalan.findAll", query = "SELECT s FROM SuratJalan s"),
    @NamedQuery(name = "SuratJalan.findByKodeSuratJalan", query = "SELECT s FROM SuratJalan s WHERE s.kodeSuratJalan = :kodeSuratJalan"),
    @NamedQuery(name = "SuratJalan.findByTanggal", query = "SELECT s FROM SuratJalan s WHERE s.tanggal = :tanggal")})
public class SuratJalan implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "kode_surat_jalan")
    private String kodeSuratJalan;
    
    @Column(name = "tanggal")
    @Temporal(TemporalType.DATE)
    private Date tanggal;
    
    @JoinColumn(name = "invoice_pesanan", referencedColumnName = "kode_invoice")
    @ManyToOne
    private InvoicePesanan invoicePesanan;

    public SuratJalan() {
    }

    public SuratJalan(String kodeSuratJalan) {
        this.kodeSuratJalan = kodeSuratJalan;
    }

    public String getKodeSuratJalan() {
        return kodeSuratJalan;
    }

    public void setKodeSuratJalan(String kodeSuratJalan) {
        this.kodeSuratJalan = kodeSuratJalan;
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
        hash += (kodeSuratJalan != null ? kodeSuratJalan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SuratJalan)) {
            return false;
        }
        SuratJalan other = (SuratJalan) object;
        if ((this.kodeSuratJalan == null && other.kodeSuratJalan != null) || (this.kodeSuratJalan != null && !this.kodeSuratJalan.equals(other.kodeSuratJalan))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "aswajaclothes.entity.SuratJalan[ kodeSuratJalan=" + kodeSuratJalan + " ]";
    }
    
}
