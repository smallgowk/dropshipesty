/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models.aliex;

import com.models.aliex.crawl.SkuPropertyValue;

/**
 *
 * @author duyuno
 */
public class PropertyFull {

    private long propertyId;
    private String propertyName;
    private long valueId;
    private String valueName;
    private String valueDisplayName;
//    private String sku;
    private String shipFromCountry;
    private String imageUrl;
    
    public PropertyFull() {
        
    }
    
    public PropertyFull(SkuPropertyValue skuPropertyValue) {
        propertyId = skuPropertyValue.propertyId;
        valueId = skuPropertyValue.propertyValueId;
        propertyName = skuPropertyValue.skuPropertyName;
        valueName = skuPropertyValue.propertyValueName;
        valueDisplayName = skuPropertyValue.propertyValueDisplayName;
        shipFromCountry = skuPropertyValue.skuPropertySendGoodsCountryCode;
        imageUrl = skuPropertyValue.skuPropertyImagePath;
    }

    public long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(long propertyId) {
        this.propertyId = propertyId;
    }

    public long getValueId() {
        return valueId;
    }

    public void setValueId(long valueId) {
        this.valueId = valueId;
    }

    public String getValueDisplayName() {
        return valueDisplayName != null ? valueDisplayName : valueName;
    }

    public void setValueDisplayName(String valueDisplayName) {
        this.valueDisplayName = valueDisplayName;
    }

//    public String getSku() {
//        return sku;
//    }
//
//    public void setSku(String sku) {
//        this.sku = sku;
//    }

    public String getShipFromCountry() {
        return shipFromCountry;
    }

    public void setShipFromCountry(String shipFromCountry) {
        this.shipFromCountry = shipFromCountry;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getValueName() {
        return valueName;
    }

    public void setValueName(String valueName) {
        this.valueName = valueName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    public boolean isColor() {
        if(propertyName == null) {
            return false;
        }
        String name = propertyName.toLowerCase();
        return name.contains("color") || name.contains("plug");
    }
    
    public boolean isSize() {
        return !isColor() && !isShipFrom();
    }
    
    public boolean isShipFrom() {
        
        if(propertyName == null) {
            return false;
        }
        
        String name = propertyName.toLowerCase();
        return name.contains("ships from") || name.contains("ship");
    }
    
    public boolean isShipFromUS() {
        return shipFromCountry != null && shipFromCountry.equals("US");
    }
}
