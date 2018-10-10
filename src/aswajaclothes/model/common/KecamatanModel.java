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
public class KecamatanModel {
    
    public String id;
    public String kabupatenId;
    public String name;
    
    public KecamatanModel(String id, String kabupatenId, String name){
        this.id = id;
        this.kabupatenId = kabupatenId;
        this.name = name;
    }

    public KecamatanModel() {
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKabupatenId() {
        return kabupatenId;
    }

    public void setKabupatenId(String kabupatenId) {
        this.kabupatenId = kabupatenId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
}
