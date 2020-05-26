/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models.aliex.crawl;

import com.models.aliex.PriceFull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

/**
 *
 * @author PhanDuy
 */
public class AliexScriptSkuModule {
    public ArrayList<AliexScriptSkuProperty> productSKUPropertyList;
    public ArrayList<AliexScriptSkuPrice> skuPriceList;
    
    public boolean isHasShipFrom() {
        if(productSKUPropertyList == null || productSKUPropertyList.isEmpty()) return false;
        
        for(AliexScriptSkuProperty aliexScriptSku : productSKUPropertyList) {
            if(aliexScriptSku.isShipFrom()) return true;
        }
        
        return false;
    }
    
    public boolean isHasShipFromUS() {
        if(productSKUPropertyList == null || productSKUPropertyList.isEmpty()) return false;
        
        for(AliexScriptSkuProperty aliexScriptSku : productSKUPropertyList) {
            if(aliexScriptSku.isHasShipFromUS()) return true;
        }
        
        return false;
    }
    
    public ArrayList<PriceFull> getListPriceFull() {
        
        if(productSKUPropertyList == null || productSKUPropertyList.isEmpty() || skuPriceList == null || skuPriceList.isEmpty()) return null;
        
        HashMap<String, SkuPropertyValue> hashMap = new HashMap<>();
        for(AliexScriptSkuProperty aliexScriptSkuProperty : productSKUPropertyList) {
            aliexScriptSkuProperty.sumData(hashMap);
        }
        
        ArrayList<PriceFull> listPrices = new ArrayList<>();
        
        for(AliexScriptSkuPrice aliexScriptSkuPrice : skuPriceList) {
            PriceFull priceFull = new PriceFull();
            
            priceFull.setOriginalPrice(aliexScriptSkuPrice.skuVal.skuAmount);
            
            String[] skuIds = aliexScriptSkuPrice.skuPropIds.split(Pattern.quote(","));
            for(String id: skuIds) {
                SkuPropertyValue skuPropertyValue = hashMap.get(id);
                if(skuPropertyValue != null) {
                    priceFull.addProperty(skuPropertyValue);
                    if(skuPropertyValue.skuPropertyImagePath != null) {
                        priceFull.setSkuImage(skuPropertyValue.skuPropertyImagePath);
                    }
                }
                
            }
            
            listPrices.add(priceFull);
        }
        
        return listPrices;
    }
}
