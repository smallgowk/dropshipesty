/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models.esty;

import com.models.aliex.crawl.*;
import java.util.ArrayList;

/**
 *
 * @author PhanDuy
 */
public class EstyCrawlDataPageBase {
    public int totalProduct;
    public ArrayList<EstyCrawlProductItem> listProductItems;

    public int getTotalProduct() {
        return totalProduct;
    }

    public void setTotalProduct(int totalProduct) {
        this.totalProduct = totalProduct;
    }

    public ArrayList<EstyCrawlProductItem> getListProductItems() {
        return listProductItems;
    }

    public void setListProductItems(ArrayList<EstyCrawlProductItem> listProductItems) {
        this.listProductItems = listProductItems;
    }

    
    
    public boolean isHasData() {
        return listProductItems != null && !listProductItems.isEmpty();
    }
}
