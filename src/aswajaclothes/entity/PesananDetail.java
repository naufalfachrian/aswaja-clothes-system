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
@Table(name = "pesanan_detail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PesananDetail.findAll", query = "SELECT p FROM PesananDetail p"),
    @NamedQuery(name = "PesananDetail.findByKodePesananDetail", query = "SELECT p FROM PesananDetail p WHERE p.kodePesananDetail = :kodePesananDetail"),
    @NamedQuery(name = "PesananDetail.findByQty", query = "SELECT p FROM PesananDetail p WHERE p.qty = :qty"),
    @NamedQuery(name = "PesananDetail.findByTipeLengan", query = "SELECT p FROM PesananDetail p WHERE p.tipeLengan = :tipeLengan")})
public class PesananDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "kode_pesanan_detail")
    private Integer kodePesananDetail;
    
    @Basic(optional = false)
    @Column(name = "qty")
    private int qty;
    
    @Basic(optional = false)
    @Column(name = "tipe_lengan")
    private String tipeLengan;
    
    @JoinColumn(name = "barang", referencedColumnName = "kode_barang")
    @ManyToOne(optional = false)
    private Barang barang;
    
    @JoinColumn(name = "pesanan", referencedColumnName = "kode_pesanan")
    @ManyToOne(optional = false)
    private Pesanan pesanan;

    public PesananDetail() {
    }

    public PesananDetail(Integer kodePesananDetail) {
        this.kodePesananDetail = kodePesananDetail;
    }

    public PesananDetail(Integer kodePesananDetail, int qty, String tipeLengan) {
        this.kodePesananDetail = kodePesananDetail;
        this.qty = qty;
        this.tipeLengan = tipeLengan;
    }

    public Integer getKodePesananDetail() {
        return kodePesananDetail;
    }

    public void setKodePesananDetail(Integer kodePesananDetail) {
        this.kodePesananDetail = kodePesananDetail;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getTipeLengan() {
        return tipeLengan;
    }

    public void setTipeLengan(String tipeLengan) {
        this.tipeLengan = tipeLengan;
    }

    public Barang getBarang() {
        return barang;
    }

    public void setBarang(Barang barang) {
        this.barang = barang;
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
        hash += (kodePesananDetail != null ? kodePesananDetail.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PesananDetail)) {
            return false;
        }
        PesananDetail other = (PesananDetail) object;
        if ((this.kodePesananDetail == null && other.kodePesananDetail != null) || (this.kodePesananDetail != null && !this.kodePesananDetail.equals(other.kodePesananDetail))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "aswajaclothes.entity.PesananDetail[ kodePesananDetail=" + kodePesananDetail + " ]";
    }
    
}
