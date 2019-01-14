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
@Table(name = "kustomer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kustomer.findAll", query = "SELECT k FROM Kustomer k"),
    @NamedQuery(name = "Kustomer.findByKodeKustomer", query = "SELECT k FROM Kustomer k WHERE k.kodeKustomer = :kodeKustomer"),
    @NamedQuery(name = "Kustomer.findByNamaKustomer", query = "SELECT k FROM Kustomer k WHERE k.namaKustomer = :namaKustomer"),
    @NamedQuery(name = "Kustomer.findByAlamat", query = "SELECT k FROM Kustomer k WHERE k.alamat = :alamat"),
    @NamedQuery(name = "Kustomer.findByNoTelepon", query = "SELECT k FROM Kustomer k WHERE k.noTelepon = :noTelepon"),
    @NamedQuery(name = "Kustomer.findByTerhapus", query = "SELECT k FROM Kustomer k WHERE k.terhapus = :terhapus")})
public class Kustomer implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "kode_kustomer")
    private String kodeKustomer;
    
    @Basic(optional = false)
    @Column(name = "nama_kustomer")
    private String namaKustomer;
    
    @Basic(optional = false)
    @Column(name = "alamat")
    private String alamat;
    
    @Basic(optional = false)
    @Column(name = "no_telepon")
    private String noTelepon;
    
    @Basic(optional = false)
    @Column(name = "terhapus")
    private boolean terhapus;
    
    @JoinColumn(name = "kelurahan", referencedColumnName = "id")
    @ManyToOne
    private Kelurahan kelurahan;

    public Kustomer() {
    }

    public Kustomer(String kodeKustomer) {
        this.kodeKustomer = kodeKustomer;
    }

    public Kustomer(String kodeKustomer, String namaKustomer, String alamat, String noTelepon, boolean terhapus) {
        this.kodeKustomer = kodeKustomer;
        this.namaKustomer = namaKustomer;
        this.alamat = alamat;
        this.noTelepon = noTelepon;
        this.terhapus = terhapus;
    }

    public String getKodeKustomer() {
        return kodeKustomer;
    }

    public void setKodeKustomer(String kodeKustomer) {
        this.kodeKustomer = kodeKustomer;
    }

    public String getNamaKustomer() {
        return namaKustomer;
    }

    public void setNamaKustomer(String namaKustomer) {
        this.namaKustomer = namaKustomer;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNoTelepon() {
        return noTelepon;
    }

    public void setNoTelepon(String noTelepon) {
        this.noTelepon = noTelepon;
    }

    public boolean getTerhapus() {
        return terhapus;
    }

    public void setTerhapus(boolean terhapus) {
        this.terhapus = terhapus;
    }

    public Kelurahan getKelurahan() {
        return kelurahan;
    }

    public void setKelurahan(Kelurahan kelurahan) {
        this.kelurahan = kelurahan;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kodeKustomer != null ? kodeKustomer.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kustomer)) {
            return false;
        }
        Kustomer other = (Kustomer) object;
        if ((this.kodeKustomer == null && other.kodeKustomer != null) || (this.kodeKustomer != null && !this.kodeKustomer.equals(other.kodeKustomer))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "aswajaclothes.entity.Kustomer[ kodeKustomer=" + kodeKustomer + " ]";
    }
    
}
