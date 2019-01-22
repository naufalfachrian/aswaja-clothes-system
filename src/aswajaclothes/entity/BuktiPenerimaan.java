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
@Table(name = "bukti_penerimaan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BuktiPenerimaan.findAll", query = "SELECT b FROM BuktiPenerimaan b"),
    @NamedQuery(name = "BuktiPenerimaan.findByKodeBuktiPenerimaan", query = "SELECT b FROM BuktiPenerimaan b WHERE b.kodeBuktiPenerimaan = :kodeBuktiPenerimaan"),
    @NamedQuery(name = "BuktiPenerimaan.findByTanggal", query = "SELECT b FROM BuktiPenerimaan b WHERE b.tanggal = :tanggal")})
public class BuktiPenerimaan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "kode_bukti_penerimaan")
    private String kodeBuktiPenerimaan;
    @Column(name = "tanggal")
    @Temporal(TemporalType.DATE)
    private Date tanggal;
    @JoinColumn(name = "pembelian", referencedColumnName = "kode_pembelian")
    @ManyToOne
    private Pembelian pembelian;

    public BuktiPenerimaan() {
    }

    public BuktiPenerimaan(String kodeBuktiPenerimaan) {
        this.kodeBuktiPenerimaan = kodeBuktiPenerimaan;
    }

    public String getKodeBuktiPenerimaan() {
        return kodeBuktiPenerimaan;
    }

    public void setKodeBuktiPenerimaan(String kodeBuktiPenerimaan) {
        this.kodeBuktiPenerimaan = kodeBuktiPenerimaan;
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
        hash += (kodeBuktiPenerimaan != null ? kodeBuktiPenerimaan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BuktiPenerimaan)) {
            return false;
        }
        BuktiPenerimaan other = (BuktiPenerimaan) object;
        if ((this.kodeBuktiPenerimaan == null && other.kodeBuktiPenerimaan != null) || (this.kodeBuktiPenerimaan != null && !this.kodeBuktiPenerimaan.equals(other.kodeBuktiPenerimaan))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "aswajaclothes.entity.BuktiPenerimaan[ kodeBuktiPenerimaan=" + kodeBuktiPenerimaan + " ]";
    }
    
}
