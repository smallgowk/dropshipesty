/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.api.aliex.response;

import com.config.Configs;
import com.models.aliex.ShippingObj;
import java.util.ArrayList;

/**
 *
 * @author duyuno
 */
public class AliexProductShipResponse {

    private ArrayList<ShippingObj> options;

    public ArrayList<ShippingObj> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<ShippingObj> options) {
        this.options = options;
    }
    
    public float getShippingPrice() {
        float price = -1;
        if(Configs.isFilterEpacket) {
            price = getEpacketPrice();
            
            if(price != -1) return price;
        }
        
        if(Configs.isFilterAliexpress) {
            price = getAliexStandardPrice();
            if(price != -1) return price;
        }
        
        if(Configs.isFilterDHL) {
            price = getAliexDHLPrice();
            if(price != -1) return price;
        }
        
        return price;
    }
    
    public boolean hasAliexStandardOption() {
        if (options == null || options.isEmpty()) {
            return false;
        }
        
        for (ShippingObj shippingObj : options) {
            if (shippingObj.getCompany().equals("AliExpress Standard Shipping")) {
                return true;
            }
        }
        
        return false;
    }
    
    public boolean hasEpacketOption() {
        
        if (options == null || options.isEmpty()) {
            return false;
        }
        
        for (ShippingObj shippingObj : options) {
            if (shippingObj.getCompany().equals("ePacket")) {
                return true;
            }
        }
        
        return false;
    }

    private float getEpacketPrice() {
        if (options == null || options.isEmpty()) {
            return -1;
        }

        for (ShippingObj shippingObj : options) {
            if (shippingObj.getCompany().equals("ePacket")) {
                String value = shippingObj.getAmount().getValue();

                try {
                    return Float.parseFloat(value);
                } catch (NumberFormatException ex) {
                    return -1;
                } 
            }
        }

        return -1;
    }
    
    private float getAliexStandardPrice() {
        if (options == null || options.isEmpty()) {
            return -1;
        }

        for (ShippingObj shippingObj : options) {
            if (shippingObj.getCompany().equals("AliExpress Standard Shipping")) {
                String value = shippingObj.getAmount().getValue();

                try {
                    return Float.parseFloat(value);
                } catch (NumberFormatException ex) {
                    return -1;
                } 
            }
        }

        return -1;
    }
    
    private float getAliexDHLPrice() {
        if (options == null || options.isEmpty()) {
            return -1;
        }

        for (ShippingObj shippingObj : options) {
            if (shippingObj.getCompany().equals("DHL")) {
                String value = shippingObj.getAmount().getValue();

                try {
                    return Float.parseFloat(value);
                } catch (NumberFormatException ex) {
                    return -1;
                } 
            }
        }

        return -1;
    }
    
//    public float getShippingCost() {
//        float price = getEpacketPrice();
//        return price != -1 ? price : getAliexStandardPrice();
//    }
}
