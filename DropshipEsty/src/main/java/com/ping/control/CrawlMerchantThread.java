/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ping.control;

import com.ping.service.crawl.merchant.MerchantSearchSvs;

/**
 *
 * @author duyuno
 */
public class CrawlMerchantThread extends Thread{

    String keyword;
    
    public CrawlMerchantThread(String keyword) {
        this.keyword = keyword;
    }
    
    @Override
    public void run() {
        MerchantSearchSvs.getInstance().login();
    }
    
}
