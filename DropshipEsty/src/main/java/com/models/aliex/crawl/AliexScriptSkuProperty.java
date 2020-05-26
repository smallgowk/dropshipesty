/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models.aliex.crawl;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author PhanDuy
 */
public class AliexScriptSkuProperty {

    public int skuPropertyId;
    public String skuPropertyName;
    public ArrayList<SkuPropertyValue> skuPropertyValues;

    public boolean isShipFrom() {

        if (skuPropertyName == null) {
            return false;
        }

        String name = skuPropertyName.toLowerCase();
        return name.contains("ships from") || name.contains("ship");
    }

    public boolean isHasShipFromUS() {
        
        if(!isShipFrom()) {
            return false;
        }
        
        for(SkuPropertyValue skuPropertyValue : skuPropertyValues) {
            if(skuPropertyValue.isShipFromUs()) return true;
        }
        
        return false;
    }
    
    public void sumData(HashMap<String, SkuPropertyValue> hashMap) {
        if(skuPropertyValues == null || skuPropertyValues.isEmpty()) return;
        
        for(SkuPropertyValue skuPropertyValue : skuPropertyValues) {
            skuPropertyValue.setSkuPropertyName(skuPropertyName);
            skuPropertyValue.setPropertyId(skuPropertyId);
            hashMap.put(String.valueOf(skuPropertyValue.propertyValueId), skuPropertyValue);
        }
    }
}
