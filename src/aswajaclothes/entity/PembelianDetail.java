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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author littleflower
 */
@Entity
@Table(name = "pembelian_detail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PembelianDetail.findAll", query = "SELECT p FROM PembelianDetail p"),
    @NamedQuery(name = "PembelianDetail.findByKodePembelianDetail", query = "SELECT p FROM PembelianDetail p WHERE p.kodePembelianDetail = :kodePembelianDetail")})
public class PembelianDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "kode_pembelian_detail")
    private Integer kodePembelianDetail;
    
    @JoinColumn(name = "pembelian", referencedColumnName = "kode_pembelian")
    @ManyToOne
    private Pembelian pembelian;
    
    @JoinColumn(name = "pesanan", referencedColumnName = "kode_pesanan")
    @ManyToOne
    private Pesanan pesanan;

    public PembelianDetail() {
    }

    public PembelianDetail(Integer kodePembelianDetail) {
        this.kodePembelianDetail = kodePembelianDetail;
    }

    public Integer getKodePembelianDetail() {
        return kodePembelianDetail;
    }

    public void setKodePembelianDetail(Integer kodePembelianDetail) {
        this.kodePembelianDetail = kodePembelianDetail;
    }

    public Pembelian getPembelian() {
        return pembelian;
    }

    public void setPembelian(Pembelian pembelian) {
        this.pembelian = pembelian;
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
        hash += (kodePembelianDetail != null ? kodePembelianDetail.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PembelianDetail)) {
            return false;
        }
        PembelianDetail other = (PembelianDetail) object;
        if ((this.kodePembelianDetail == null && other.kodePembelianDetail != null) || (this.kodePembelianDetail != null && !this.kodePembelianDetail.equals(other.kodePembelianDetail))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "aswajaclothes.entity.PembelianDetail[ kodePembelianDetail=" + kodePembelianDetail + " ]";
    }
    
}
