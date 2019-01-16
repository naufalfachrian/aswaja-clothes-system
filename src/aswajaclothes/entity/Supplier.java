/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aswajaclothes.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "supplier")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Supplier.findAll", query = "SELECT s FROM Supplier s"),
    @NamedQuery(name = "Supplier.findByKodeSupplier", query = "SELECT s FROM Supplier s WHERE s.kodeSupplier = :kodeSupplier"),
    @NamedQuery(name = "Supplier.findByNamaSupplier", query = "SELECT s FROM Supplier s WHERE s.namaSupplier = :namaSupplier"),
    @NamedQuery(name = "Supplier.findByAlamat", query = "SELECT s FROM Supplier s WHERE s.alamat = :alamat"),
    @NamedQuery(name = "Supplier.findByNoTelepon", query = "SELECT s FROM Supplier s WHERE s.noTelepon = :noTelepon"),
    @NamedQuery(name = "Supplier.findByNoFax", query = "SELECT s FROM Supplier s WHERE s.noFax = :noFax"),
    @NamedQuery(name = "Supplier.findByEmail", query = "SELECT s FROM Supplier s WHERE s.email = :email")})
public class Supplier implements Serializable {

    @OneToMany(mappedBy = "supplier")
    private List<Pembelian> pembelianList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "kode_supplier")
    private String kodeSupplier;
    
    @Basic(optional = false)
    @Column(name = "nama_supplier")
    private String namaSupplier;
    
    @Basic(optional = false)
    @Column(name = "alamat")
    private String alamat;
    
    @Basic(optional = false)
    @Column(name = "no_telepon")
    private String noTelepon;
    
    @Basic(optional = false)
    @Column(name = "no_fax")
    private String noFax;
    
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    
    @JoinColumn(name = "kelurahan", referencedColumnName = "id")
    @ManyToOne
    private Kelurahan kelurahan;

    public Supplier() {
    }

    public Supplier(String kodeSupplier) {
        this.kodeSupplier = kodeSupplier;
    }

    public Supplier(String kodeSupplier, String namaSupplier, String alamat, String noTelepon, String noFax, String email) {
        this.kodeSupplier = kodeSupplier;
        this.namaSupplier = namaSupplier;
        this.alamat = alamat;
        this.noTelepon = noTelepon;
        this.noFax = noFax;
        this.email = email;
    }

    public String getKodeSupplier() {
        return kodeSupplier;
    }

    public void setKodeSupplier(String kodeSupplier) {
        this.kodeSupplier = kodeSupplier;
    }

    public String getNamaSupplier() {
        return namaSupplier;
    }

    public void setNamaSupplier(String namaSupplier) {
        this.namaSupplier = namaSupplier;
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

    public String getNoFax() {
        return noFax;
    }

    public void setNoFax(String noFax) {
        this.noFax = noFax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        hash += (kodeSupplier != null ? kodeSupplier.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Supplier)) {
            return false;
        }
        Supplier other = (Supplier) object;
        if ((this.kodeSupplier == null && other.kodeSupplier != null) || (this.kodeSupplier != null && !this.kodeSupplier.equals(other.kodeSupplier))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "aswajaclothes.entity.Supplier[ kodeSupplier=" + kodeSupplier + " ]";
    }

    @XmlTransient
    public List<Pembelian> getPembelianList() {
        return pembelianList;
    }

    public void setPembelianList(List<Pembelian> pembelianList) {
        this.pembelianList = pembelianList;
    }
    
}
