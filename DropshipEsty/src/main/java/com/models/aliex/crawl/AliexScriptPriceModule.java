/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models.aliex.crawl;

import com.models.aliex.PriceUnit;

/**
 *
 * @author PhanDuy
 */
public class AliexScriptPriceModule {
    public int discount;
    public boolean discountPromotion;
    public PriceUnit maxActivityAmount;
    public PriceUnit maxAmount;
    public PriceUnit minActivityAmount;
    public PriceUnit minAmount;
    
    public float getMaxPrice() {
        try {
            return Float.parseFloat(maxAmount.value);
        } catch (NumberFormatException ex) {
            return -1;
        }
//        return maxAmount.value;
    }
}
