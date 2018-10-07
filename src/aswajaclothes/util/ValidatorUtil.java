/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aswajaclothes.util;

import com.mysql.jdbc.StringUtils;

/**
 *
 * @author Satrio
 */
public class ValidatorUtil {
    
    private static ValidatorUtil instance;
    
    public static ValidatorUtil getInstance() {
        if (instance == null){
            instance = new ValidatorUtil();
        }
        return instance;
    }
    
    public ValidatorUtil(){
        
    }
   
    public String isEmpty(String text, String fieldName) throws Exception{
        if (text.isEmpty()){
            throw new Exception(fieldName + " tidak boleh kosong");
        }
        return text;
    }
    
    public String isNumber(String text, String fieldName) throws Exception{
        String noTelepon = "";
        try{
            noTelepon = isEmpty(text, fieldName);
            Double.parseDouble(noTelepon);
        }catch(NumberFormatException ex){
            throw new Exception(fieldName + " harus angka");
        }
        return noTelepon;
    }
    
}
