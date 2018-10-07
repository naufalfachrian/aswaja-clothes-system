/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aswajaclothes.common.model;

/**
 *
 * @author Satrio
 */
public class KabupatenModel {
    
    private String id;
    private String provinsiId;
    private String name;

    public KabupatenModel(String id, String provinsiId, String name){
        this.id = id;
        this.provinsiId = provinsiId;
        this.name = name;
    }
    
    public KabupatenModel(){
        
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProvinsiId() {
        return provinsiId;
    }

    public void setProvinsiId(String provinsiId) {
        this.provinsiId = provinsiId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
