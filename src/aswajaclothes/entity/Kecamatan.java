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
@Table(name = "kecamatan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kecamatan.findAll", query = "SELECT k FROM Kecamatan k"),
    @NamedQuery(name = "Kecamatan.findById", query = "SELECT k FROM Kecamatan k WHERE k.id = :id"),
    @NamedQuery(name = "Kecamatan.findByName", query = "SELECT k FROM Kecamatan k WHERE k.name = :name")})
public class Kecamatan implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    
    @JoinColumn(name = "kota", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Kota kota;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kecamatan")
    private List<Kelurahan> kelurahanList;

    public Kecamatan() {
    }

    public Kecamatan(String id) {
        this.id = id;
    }

    public Kecamatan(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Kota getKota() {
        return kota;
    }

    public void setKota(Kota kota) {
        this.kota = kota;
    }

    @XmlTransient
    public List<Kelurahan> getKelurahanList() {
        return kelurahanList;
    }

    public void setKelurahanList(List<Kelurahan> kelurahanList) {
        this.kelurahanList = kelurahanList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kecamatan)) {
            return false;
        }
        Kecamatan other = (Kecamatan) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return name;
    }
    
}
