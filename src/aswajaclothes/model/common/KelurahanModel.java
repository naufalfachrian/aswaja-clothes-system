/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aswajaclothes.model.common;

/**
 *
 * @author Satrio
 */
public class KelurahanModel {
    public String id;
    public String kecamatanId;
    public String name;

    public KelurahanModel(String id, String kecamatanId, String name){
        this.id = id;
        this.kecamatanId = kecamatanId;
        this.name = name;
    }

    public KelurahanModel() {
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKecamatanId() {
        return kecamatanId;
    }

    public void setKecamatanId(String kecamatanId) {
        this.kecamatanId = kecamatanId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
