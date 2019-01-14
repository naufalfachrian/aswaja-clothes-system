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
@Table(name = "provinsi")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Provinsi.findAll", query = "SELECT p FROM Provinsi p"),
    @NamedQuery(name = "Provinsi.findById", query = "SELECT p FROM Provinsi p WHERE p.id = :id"),
    @NamedQuery(name = "Provinsi.findByName", query = "SELECT p FROM Provinsi p WHERE p.name = :name")})
public class Provinsi implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "provinsi")
    private List<Kota> kotaList;

    public Provinsi() {
    }

    public Provinsi(String id) {
        this.id = id;
    }

    public Provinsi(String id, String name) {
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

    @XmlTransient
    public List<Kota> getKotaList() {
        return kotaList;
    }

    public void setKotaList(List<Kota> kotaList) {
        this.kotaList = kotaList;
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
        if (!(object instanceof Provinsi)) {
            return false;
        }
        Provinsi other = (Provinsi) object;
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
