/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pong.server.process;

import com.models.aliex.store.AliexStoreCommon;
import com.models.amazon.ProductAmz;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Admin
 */
public class FetchAliexToAmzSVThread {

    public boolean isComplete;
    public AliexStoreCommon aliexStoreCache;
    public String aliexId;
    public ArrayList<ProductAmz> listProductAmz;

    public FetchAliexToAmzSVThread(AliexStoreCommon aliexStoreCache, String aliexId) {
        this.aliexStoreCache = aliexStoreCache;
        this.aliexId = aliexId;
    }

    public ArrayList<ProductAmz> getListProductAmz() {
        return listProductAmz;
    }

    public void setListProductAmz(ArrayList<ProductAmz> listProductAmz) {
        this.listProductAmz = listProductAmz;
    }

//    @Override
    public void run() {
        listProductAmz = aliexStoreCache.fetchListProductAmz(aliexId);

        isComplete = true;
    }

    public boolean isIsComplete() {
        return isComplete;
    }

}
