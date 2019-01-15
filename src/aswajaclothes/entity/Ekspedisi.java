/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aswajaclothes.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author littleflower
 */
@Entity
@Table(name = "ekspedisi")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ekspedisi.findAll", query = "SELECT e FROM Ekspedisi e"),
    @NamedQuery(name = "Ekspedisi.findByKodeEkspedisi", query = "SELECT e FROM Ekspedisi e WHERE e.kodeEkspedisi = :kodeEkspedisi"),
    @NamedQuery(name = "Ekspedisi.findByNamaEkspedisi", query = "SELECT e FROM Ekspedisi e WHERE e.namaEkspedisi = :namaEkspedisi"),
    @NamedQuery(name = "Ekspedisi.findByJenisLayanan", query = "SELECT e FROM Ekspedisi e WHERE e.jenisLayanan = :jenisLayanan")})
public class Ekspedisi implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ekspedisi")
    private List<Pesanan> pesananList;

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "kode_ekspedisi")
    private String kodeEkspedisi;
    
    @Basic(optional = false)
    @Column(name = "nama_ekspedisi")
    private String namaEkspedisi;
    
    @Column(name = "jenis_layanan")
    private String jenisLayanan;

    public Ekspedisi() {
    }

    public Ekspedisi(String kodeEkspedisi) {
        this.kodeEkspedisi = kodeEkspedisi;
    }

    public Ekspedisi(String kodeEkspedisi, String namaEkspedisi) {
        this.kodeEkspedisi = kodeEkspedisi;
        this.namaEkspedisi = namaEkspedisi;
    }

    public String getKodeEkspedisi() {
        return kodeEkspedisi;
    }

    public void setKodeEkspedisi(String kodeEkspedisi) {
        this.kodeEkspedisi = kodeEkspedisi;
    }

    public String getNamaEkspedisi() {
        return namaEkspedisi;
    }

    public void setNamaEkspedisi(String namaEkspedisi) {
        this.namaEkspedisi = namaEkspedisi;
    }

    public String getJenisLayanan() {
        return jenisLayanan;
    }

    public void setJenisLayanan(String jenisLayanan) {
        this.jenisLayanan = jenisLayanan;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kodeEkspedisi != null ? kodeEkspedisi.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ekspedisi)) {
            return false;
        }
        Ekspedisi other = (Ekspedisi) object;
        if ((this.kodeEkspedisi == null && other.kodeEkspedisi != null) || (this.kodeEkspedisi != null && !this.kodeEkspedisi.equals(other.kodeEkspedisi))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "aswajaclothes.entity.Ekspedisi[ kodeEkspedisi=" + kodeEkspedisi + " ]";
    }

    @XmlTransient
    public List<Pesanan> getPesananList() {
        return pesananList;
    }

    public void setPesananList(List<Pesanan> pesananList) {
        this.pesananList = pesananList;
    }
    
}
