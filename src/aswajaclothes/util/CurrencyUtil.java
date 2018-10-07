/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aswajaclothes.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author Satrio
 */
public class CurrencyUtil {
    
    private static CurrencyUtil instance;
    
    public static CurrencyUtil getInstance(){
        if (instance == null){
            instance = new CurrencyUtil();
        }
        return instance;
    }
    
    public CurrencyUtil(){
        
    }
    
    public String formatCurrency(int amount){
        
        DecimalFormat formatter = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatterSymbol = new DecimalFormatSymbols();

        formatterSymbol.setCurrencySymbol("");
        formatterSymbol.setMonetaryDecimalSeparator(',');
        formatterSymbol.setGroupingSeparator('.');

        formatter.setDecimalFormatSymbols(formatterSymbol);
        return formatter.format(amount);
    }
    
}
