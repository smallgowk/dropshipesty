/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pong.server.process;

import com.models.aliex.store.AliexStoreCommon;
import com.models.amazon.ProductAmz;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class ValidateAmzProductsThread extends Thread {

    public boolean isComplete;
    public AliexStoreCommon aliexStoreCache;
    public ArrayList<ProductAmz> listProductAmz;

    public ValidateAmzProductsThread(AliexStoreCommon aliexStoreCache, ArrayList<ProductAmz> listProductAmz) {
        this.aliexStoreCache = aliexStoreCache;
        this.listProductAmz = listProductAmz;
    }

    public ArrayList<ProductAmz> getListProductAmz() {
        return listProductAmz;
    }

    public void setListProductAmz(ArrayList<ProductAmz> listProductAmz) {
        this.listProductAmz = listProductAmz;
    }
    
    @Override
    public void run() {
        isComplete = true;
    }

    public boolean isIsComplete() {
        return isComplete;
    }
    
    
}
