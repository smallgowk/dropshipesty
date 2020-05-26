/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models.aliex;

import com.api.aliex.response.AliexProductFullResponse;
import com.models.aliex.crawl.AliexScriptDetailData;
import com.models.aliex.crawl.CrawlPageProductItem;
import com.utils.MarketUtil;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Admin
 */
public class AliexProductFull {

    public String id;
    public String categoryId;
    public String title;
    public String[] productImages;
    public ArrayList<ProductAttribute> attributes;
    public ArrayList<PriceFull> prices;
    public String htmlDescription;
    
    private float promotionRate;
    private float shippingPrice;
    private float originPrice;
    
    private boolean isHasShipFrom;
    private boolean isHasShipFromUS;
    
    public int pageIndex;
    public String storeSign;
    public ArrayList<String> listSearchTerms;

    public AliexProductFull() {
    }
    
    public AliexProductFull(CrawlPageProductItem crawlPageProductItem) {
        this.id = crawlPageProductItem.getId();
        this.title = crawlPageProductItem.getTitle();
    }

    public String getStoreSign() {
        return storeSign;
    }

    public void setStoreSign(String storeSign) {
        this.storeSign = storeSign;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getProductImages() {
        return productImages;
    }

    public void setProductImages(String[] productImages) {
        this.productImages = productImages;
    }

    public ArrayList<ProductAttribute> getAttributes() {
        return attributes;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public float getPromotionRate() {
        return promotionRate;
    }

    public ArrayList<PriceFull> getPrices() {
        return prices;
    }

    public void setPrices(ArrayList<PriceFull> prices) {
        this.prices = prices;
    }

    public ArrayList<String> getListSearchTerms() {
        return listSearchTerms;
    }

    public void setListSearchTerms(ArrayList<String> listSearchTerms) {
        this.listSearchTerms = listSearchTerms;
    }

    

    public String getHtmlDescription() {
        return htmlDescription;
    }

    public void setHtmlDescription(String htmlDescription) {
        this.htmlDescription = htmlDescription;
    }
    
    public String getBranName() {
        if(attributes == null || attributes.isEmpty()) return null;
        
        for(ProductAttribute productAttribute : attributes) {
            if(productAttribute.isBrandName()) {
                return productAttribute.getValue();
            }
        }
        
        return null;
    }
    
    public void addBrandName(String brandName) {
        if(attributes == null) {
            attributes = new ArrayList<>();
        }
        
        ProductAttribute productAttribute = new ProductAttribute();
        productAttribute.setName("Brand Name");
        productAttribute.setValue(brandName);
        attributes.add(productAttribute);
    }
    
    public void repairVariationImage(HashMap<String, String> hashmapProperties) {
        
        if(prices == null || prices.isEmpty() || hashmapProperties == null) return;
        
        for(PriceFull priceFull : prices) {
            if(priceFull.getProperties() != null) {
                for(PropertyFull propertyFull : priceFull.getProperties()) {
                    if(hashmapProperties.containsKey("" + propertyFull.getValueId())) {
                        priceFull.setSkuImage(MarketUtil.getVariationImageUrl(hashmapProperties.get("" + propertyFull.getValueId())));
                    } else if(propertyFull.getValueDisplayName() != null && hashmapProperties.containsKey("" + propertyFull.getValueDisplayName().toLowerCase())){
                        priceFull.setSkuImage(MarketUtil.getVariationImageUrl(hashmapProperties.get("" + propertyFull.getValueDisplayName().toLowerCase())));
                    }
                }
            }
        }
    }

    public float getShippingPrice() {
        return shippingPrice;
    }

    public void setShippingPrice(float shippingPrice) {
        this.shippingPrice = shippingPrice;
    }
    
    public boolean isHasVarition() {
        if (prices == null || prices.isEmpty()) {
            return false;
        }

        if (prices.size() == 1) {
            PriceFull priceFull = prices.get(0);
            return priceFull.isHasVariation();
        }

        for (PriceFull priceFull : prices) {
            if (priceFull.isHasVariation()) {
                return true;
            }
        }

        return false;
    }
    
    public float getFirstPrice() {
        return originPrice;
//        if (prices == null || prices.isEmpty()) {
//            return -1;
//        }
//
//        String priceStr = prices.get(0).getOriginalPrice().getValue();
//
//        return Float.parseFloat(priceStr);
    }
    
    public boolean isHasShipFrom() {
        return isHasShipFrom;
    }

    public boolean isHasShipFromUS() {
        return isHasShipFromUS;
    }
    
    public float getProductPrice(float price, float priceRate) {
//        float promotionRate = getPromotionRate();
//        return (price * (1 - promotionRate) + getShippingPrice()) * (priceRate - promotionRate);
//        System.out.println("ori_price: " + price + " promotionRate: " + promotionRate + " shipping: " + shippingPrice + " priceRate: " + priceRate + " "
//                + "productPrice: " + ((price * (1 - promotionRate) + shippingPrice) * (priceRate)));
        return (price * (1 - promotionRate) + shippingPrice) * (priceRate);
    }
    
    public void setDataApi(AliexProductFullResponse data) {
        this.id = data.id;
        this.title = data.title;
        this.productImages = data.productImages;
        this.attributes = data.attributes;
        this.prices = data.prices;
        this.originPrice = data.getFirstPrice();
        this.htmlDescription = data.htmlDescription;
        this.promotionRate = data.getPromotionRate();
        this.shippingPrice = data.getShippingPrice();
        this.isHasShipFrom = data.isHasShipFrom();
        this.isHasShipFromUS = data.isHasShipFromUS();
    }
    
    public void setDataCrawl(AliexScriptDetailData data) {
        this.productImages = data.getProductImages();
        this.attributes = data.getProductAttributes();
        this.prices = data.getPriceFulls();
        this.attributes = data.getProductAttributes();
        this.htmlDescription = data.getHtmlDescription();
        this.promotionRate = data.getPromotionRate();
        this.shippingPrice = data.getShippingPrice();
        this.isHasShipFrom = data.isHasShipFrom();
        this.isHasShipFromUS = data.isHasShipFromUS();
        setListSearchTerms(data.getRelatedSearch());
        this.originPrice = data.getFirstPrice();
    }
}
