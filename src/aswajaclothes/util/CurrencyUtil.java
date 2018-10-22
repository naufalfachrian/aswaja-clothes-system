/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aswajaclothes.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import javax.swing.text.NumberFormatter;

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
        NumberFormatter numberFormatter = formatNumber();
        return numberFormatter.getFormat().format(amount);
    }
    
    public String clearFormat(String amount) throws ParseException{
        NumberFormat format = NumberFormat.getIntegerInstance();
        return format.parse(amount).toString();
    }
    
    public int clearFormatToInt(String amount) throws ParseException{
        NumberFormat format = NumberFormat.getIntegerInstance();
        return format.parse(amount).intValue();
    }
    
    public NumberFormatter formatNumber(){
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(0);
        
        NumberFormatter  numberFormatter = new NumberFormatter(numberFormat);
        numberFormatter.setAllowsInvalid(false);
        return numberFormatter;
    }
}
