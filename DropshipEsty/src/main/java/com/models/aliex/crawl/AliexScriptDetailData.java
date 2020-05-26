/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models.aliex.crawl;

import com.api.ApiBase;
import com.models.aliex.PriceFull;
import com.models.aliex.ProductAttribute;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author PhanDuy
 */
public class AliexScriptDetailData extends CrawlDataBase{
    public AliexScriptDesModule descriptionModule;
    public AliexScriptImageModule imageModule;
    public AliexScriptPriceModule priceModule;
    public AliexScriptSpecModule specsModule;
    public AliexScriptSkuModule skuModule;
    public AliexScriptCrossLinkModule crossLinkModule;
    
    
    public String[] getProductImages() {
        return imageModule.imagePathList;
    }
    
    public ArrayList<ProductAttribute> getProductAttributes() {
        
        if(specsModule == null || specsModule.props == null || specsModule.props.isEmpty()) return null;
        
        ArrayList<ProductAttribute> listAttribute = new ArrayList<>();
        
        HashMap<String, ProductAttribute> hashMapAttribute = new HashMap<>();
        
        for(AliexScriptSpecProp aliexScriptSpecProp : specsModule.props) {
            
            String name = aliexScriptSpecProp.attrName.trim().toLowerCase();
            if(hashMapAttribute.containsKey(name)) {
                ProductAttribute productAttribute = hashMapAttribute.get(name);
                productAttribute.mergeValue(aliexScriptSpecProp.attrValue);
            } else {
                ProductAttribute productAttribute = new ProductAttribute(aliexScriptSpecProp);
                hashMapAttribute.put(name, productAttribute);
                listAttribute.add(productAttribute);
            }
            
            
        }
        
        return listAttribute;
    }
    
    public ArrayList<PriceFull> getPriceFulls() {
        return skuModule.getListPriceFull();
    }
    
    public String getHtmlDescription() {
        return ApiBase.getInstance().sendGet(descriptionModule.descriptionUrl, null);
    }
    
    public float getPromotionRate() {
        return priceModule.discount * 1f / 100;
    }
    
    public float getShippingPrice() {
        return -1;
    }
    
    public boolean isHasShipFrom() {
        return skuModule.isHasShipFrom();
    }
    
    public boolean isHasShipFromUS() {
        return skuModule.isHasShipFrom();
    }
    
    public ArrayList<String> getRelatedSearch() {
        return crossLinkModule.getRelatedSearch();
    }
    
    public void copy(AliexScriptDetailData data) {
        this.descriptionModule = data.descriptionModule;
        this.imageModule = data.imageModule;
        this.priceModule = data.priceModule;
        this.specsModule = data.specsModule;
        this.skuModule = data.skuModule;
        this.crossLinkModule = data.crossLinkModule;
    }
    
    public float getFirstPrice() {
        return priceModule.getMaxPrice();
    }
}