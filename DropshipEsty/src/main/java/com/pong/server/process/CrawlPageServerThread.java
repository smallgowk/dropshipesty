/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pong.server.process;

import com.models.aliex.store.AliexStoreCommon;
import com.models.amazon.ProductAmz;
import com.models.aliex.store.AliexPageInfo;
import java.util.ArrayList;

/**
 *
 * @author duyuno
 */
public class CrawlPageServerThread extends Thread {

    public static final int START_CRAWL = 1;
    public static final int FETCH_MARKET_TO_AMZ = 2;
    public static final int GET_PAGE_ITEM_INFO = 5;
    public static final int GET_MERCHANT_KEYWORD = 6;
    public static final int VALIDATE_DATA = 3;
    public static final int SAVE_INFO_TO_EXCEL = 4;
//    public static final int GET_PAGE_INFO = 1;
//    public static final int GET_PAGE_ITEM = 2;

//    public static final int RESUME_CRAWL = 6;
//    public static final int STOP_CRAWL = 7;
    public static final int CHECK_TRADEMARK = 8;
    public static final int FIXING_ISSUE = 9;

    private int mode = -1;

//    AlieXCrawler aliexCrawler;
//    String productType;
//    String brandName;
//    String storeName;
//    String inputWebUrl;
    int aliexProductTotal;
    int amzProductMaxCount;
    int fetchingIndex;

    public boolean isGetPageInfo, isRunning, isStoped;

    AliexPageInfo aliexPageInfo;
    AliexStoreCommon aliexStoreCache;

    public ArrayList<GetAliexItemInfoSVThread> listGetInfoThread = new ArrayList<>();
    public ArrayList<FetchAliexToAmzSVThread> listFetchAliexToAmzThread = new ArrayList<>();

    public ArrayList<ProductAmz> listProductAmzs;

//    public void setAliexCrawler(AlieXCrawler aliexCrawler) {
//        this.aliexCrawler = aliexCrawler;
//    }
    public void doStop() {
        isStoped = true;
    }

    public void doGetListALiexProducts(AliexStoreCommon aliexStoreCache, AliexPageInfo aliexPageInfo) {

        this.aliexStoreCache = aliexStoreCache;
        this.aliexPageInfo = aliexPageInfo;

        mode = START_CRAWL;

        System.out.println("Thread state: " + isStoped + " | " + mode + " " + getState().toString());

    }

    private void reset() {
//        lastMode = -1;
        mode = -1;
    }

    public void pauseCrawl() {
        mode = -1;
    }

    public void stopCrawl() {
        mode = -1;
        isStoped = true;
    }

    @Override
    public void run() {
        
    }

}
