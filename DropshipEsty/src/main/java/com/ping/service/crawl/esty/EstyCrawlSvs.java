/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ping.service.crawl.esty;

import com.config.Configs;
import static com.config.Configs.STORE_INFO_CACHE_DIR;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.models.aliex.store.AliexStoreInfo;
import com.models.aliex.crawl.CrawlDataPageBase;
import com.models.aliex.crawl.CrawlDataStoreBase;
import com.models.aliex.crawl.CrawlPageProductItem;
import com.models.esty.EstyCrawlDataPageBase;
import com.models.esty.EstyCrawlDataStoreBase;
import com.ping.service.crawl.CrawlerMachine;
import com.utils.CookieUtil;
import com.utils.EncryptUtil;
import com.utils.Utils;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author PhanDuy
 */
public class EstyCrawlSvs extends CrawlerMachine {

    private static EstyCrawlSvs estyCrawlSvs;

    public static EstyCrawlSvs getInstance() {
        if (estyCrawlSvs == null) {
            estyCrawlSvs = new EstyCrawlSvs();
//            aliexCrawlSvs.initDriver();
        }
        return estyCrawlSvs;
    }

    public EstyCrawlDataPageBase crawlPage(EstyCrawlDataStoreBase storeBase) {
        EstyCrawlDataPageBase estyCrawlDataPageBase = new EstyCrawlDataPageBase();
        return estyCrawlDataPageBase;
    }
    
    public EstyCrawlDataStoreBase crawlStoreInfo() {
        EstyCrawlDataStoreBase estyCrawlDataStoreBase = new EstyCrawlDataStoreBase();
        return estyCrawlDataStoreBase;
    }

    public void logout() {

        
    }

//    public CrawlDataPageBase crawlPageInfo(AliexStoreInfo aliexStoreInfo, CrawlDataStoreBase crawlDataStoreBase, int pageIndex) {
//
//        String cacheData = null;
//        File cache = new File(Configs.CACHE_PATH + STORE_INFO_CACHE_DIR + Configs.pathChar + aliexStoreInfo.getStoreSign() + Configs.pathChar + aliexStoreInfo.getStoreSign() + "_page" + pageIndex + ".txt");
//        try {
//            if (cache.exists()) {
//                cacheData = FileUtils.readFileToString(cache);
//            }
//        } catch (IOException ex) {
//            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        if (cacheData != null) {
//            String cleanData = EncryptUtil.decrypt(cacheData);
//            Gson gson = new Gson();
//            try {
//                CrawlDataPageBase crawlDataPageBase = gson.fromJson(cleanData, CrawlDataPageBase.class);
//                return crawlDataPageBase;
//            } catch (Exception ex) {
//
//            }
//        }
//
////        
////        URI currentUri = URI.create(currentUrl);
////        currentUri.get
////        String pageUrl = crawlDataStoreBase.genPageUrl(pageIndex);
//        CrawlDataPageBase crawlDataPageAliex = new CrawlDataPageBase();
//
////        System.out.println("PageUrl: " + pageUrl);
////        goToPage(pageUrl);
//        String currentUrl = getCurrentUrl();
//
//        if (currentUrl.contains(aliexStoreInfo.getStoreSign())) {
//            if (!currentUrl.contains("search/") || currentUrl.contains("search/" + (pageIndex - 1))) {
//                nextPage();
//            } else {
//                goToPage(crawlDataStoreBase.genPageUrl(pageIndex));
//            }
//        } else {
//            goToPage(crawlDataStoreBase.genPageUrl(pageIndex));
//        }
//
////        if(currentUrl.contains(cacheData)currentUrl.contains("search/" + (pageIndex - 1))) {
////            nextPage();
////        }
//        String pageSource = AliexCrawlSvs.getInstance().getPageSource();
//        Document doc = Jsoup.parse(pageSource);
//
////        Document doc = AliexCrawlSvs.getInstance().processPage(pageUrl);
//        crawlDataPageAliex.updateStatus(doc);
//
//        if (!crawlDataPageAliex.isSuccess()) {
//            return crawlDataPageAliex;
//        }
//
//        Elements questions = doc.select("li[class='item']");
//
//        if (questions == null || questions.isEmpty()) {
//            return null;
//        }
//
//        ArrayList<CrawlPageProductItem> results = null;
//
//        for (Element link : questions) {
//            String productId = null;
//            String productDetailUrl = null;
//            String title = null;
//            Elements elementsId = link.select("div[class='info']").select("input[class='atc-product-id']");
//            if (elementsId != null && !elementsId.isEmpty()) {
//                Element element = elementsId.first();
//                productId = element.attr("value");
//            }
//
//            if (productId == null) {
//                continue;
//            }
//
//            Elements elementsDetail = link.select("div[class='detail']").select("a");
//            if (elementsDetail != null && !elementsDetail.isEmpty()) {
//                Element element = elementsDetail.first();
//                productDetailUrl = element.attr("href");
//                title = element.attr("title");
//            }
//
//            if (productDetailUrl == null || title == null) {
//                continue;
//            }
//
//            CrawlPageProductItem crawlPageProductItem = new CrawlPageProductItem();
//            crawlPageProductItem.setId(productId);
//            crawlPageProductItem.setUrl("https:" + productDetailUrl);
//            crawlPageProductItem.setTitle(title);
//
//            if (results == null) {
//                results = new ArrayList<>();
//            }
//            results.add(crawlPageProductItem);
//        }
//
//        if (results == null || results.isEmpty()) {
//            return null;
//        }
//
//        crawlDataPageAliex.setListProductIds(results);
//
//        Gson gson = new GsonBuilder().create();
//        String dataClean = gson.toJson(crawlDataPageAliex);
//        String encrytData = EncryptUtil.encrypt(dataClean);
//
//        File file = new File(Configs.CACHE_PATH + STORE_INFO_CACHE_DIR);
//        if (!file.exists()) {
//            file.mkdir();
//        }
////
//        file = new File(file.getAbsolutePath() + Configs.pathChar + aliexStoreInfo.getStoreSign());
//        if (!file.exists()) {
//            file.mkdir();
//        }
////
//        file = new File(file.getAbsolutePath() + Configs.pathChar + aliexStoreInfo.getStoreSign() + "_page" + pageIndex + ".txt");
////
//        try {
//            FileUtils.writeStringToFile(file, encrytData);
//        } catch (IOException ex) {
//            Logger.getLogger(AliexCrawlSvs.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return crawlDataPageAliex;
//    }
    public CrawlDataPageBase crawlNextPageInfo(AliexStoreInfo aliexStoreInfo, CrawlDataStoreBase crawlDataStoreBase, int page) {

        String cacheData = null;
//
        File cache = new File(Configs.CACHE_PATH + STORE_INFO_CACHE_DIR + Configs.pathChar + aliexStoreInfo.getStoreSign() + Configs.pathChar + aliexStoreInfo.getStoreSign() + "_page" + page + ".txt");

        try {
            if (cache.exists()) {
                cacheData = FileUtils.readFileToString(cache);
            }
        } catch (IOException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
//
        if (cacheData != null) {
            String cleanData = EncryptUtil.decrypt(cacheData);
            Gson gson = new Gson();
            try {
                CrawlDataPageBase crawlDataPageBase = gson.fromJson(cleanData, CrawlDataPageBase.class);
                return crawlDataPageBase;
            } catch (Exception ex) {

            }
        }

        boolean isLoadSuccess = false;
        String currentUrl = getCurrentUrl();

//        if (!currentUrl.contains("search/") || currentUrl.contains("search/" + (page - 1))) {
//            System.out.println("Next page");
//            isLoadSuccess = nextPage();
//        } else {
//            System.out.println("Load page");
//            isLoadSuccess = goToPage(crawlDataStoreBase.genPageUrl(page));
//        }

        if (currentUrl.contains(aliexStoreInfo.getStoreSign())) {
            if (!currentUrl.contains("search/") || currentUrl.contains("search/" + (page - 1))) {
                System.out.println("Next page");
                isLoadSuccess = nextPage();
            } else {
                System.out.println("Load page");
                isLoadSuccess = goToPage(crawlDataStoreBase.genPageUrl(page));
            }
        } else {
            System.out.println("Load Store");
            isLoadSuccess = goToPage(crawlDataStoreBase.genPageUrl(page));
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(AliexCrawlSvs.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//            if (isLoadSuccess) {
//                if (page > 1) {
//                    for (int i = 2; i <= page; i++) {
//                        isLoadSuccess = goToPage(crawlDataStoreBase.genPageUrl(i));
//                        if (!isLoadSuccess) {
//                            return null;
//                        }
//                        try {
//                            Thread.sleep(1000);
//                        } catch (InterruptedException ex) {
//                            Logger.getLogger(AliexCrawlSvs.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//                    }
//                }
//
//            }

        }

        if (!isLoadSuccess) {
            return null;
        }

//        String cacheData = null;
////
//        File cache = new File(Configs.CACHE_PATH + STORE_INFO_CACHE_DIR + Configs.pathChar + aliexStoreInfo.getStoreSign() + Configs.pathChar + aliexStoreInfo.getStoreSign() + "_page" + page + ".txt");
//
//        try {
//            if (cache.exists()) {
//                cacheData = FileUtils.readFileToString(cache);
//            }
//        } catch (IOException ex) {
//            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
//        }
////
//        if (cacheData != null) {
//            String cleanData = EncryptUtil.decrypt(cacheData);
//            Gson gson = new Gson();
//            try {
//                CrawlDataPageBase crawlDataPageBase = gson.fromJson(cleanData, CrawlDataPageBase.class);
//                return crawlDataPageBase;
//            } catch (Exception ex) {
//
//            }
//        }
//        String pageUrl = crawlDataStoreBase.genPageUrl(pageIndex);
        CrawlDataPageBase crawlDataPageAliex = new CrawlDataPageBase();

//        System.out.println("PageUrl: " + pageUrl);
//        goToPage(pageUrl);
        String pageSource = EstyCrawlSvs.getInstance().getPageSource();
        Document doc = Jsoup.parse(pageSource);

//        Document doc = AliexCrawlSvs.getInstance().processPage(pageUrl);
        crawlDataPageAliex.updateStatus(doc);

        if (!crawlDataPageAliex.isSuccess()) {
            rechiveCookies();
//            System.out.println("Current cookies: " + cookies);
            CookieUtil.saveCookies(cookies, "errorCookies_" + System.currentTimeMillis() + ".txt");
            return crawlDataPageAliex;
        }

        Elements questions = doc.select("li[class='item']");

        if (questions == null || questions.isEmpty()) {
            return null;
        }

        ArrayList<CrawlPageProductItem> results = null;

        for (Element link : questions) {
            String productId = null;
            String productDetailUrl = null;
            String title = null;
            Elements elementsId = link.select("div[class='info']").select("input[class='atc-product-id']");
            if (elementsId != null && !elementsId.isEmpty()) {
                Element element = elementsId.first();
                productId = element.attr("value");
            }

            if (productId == null) {
                continue;
            }

            Elements elementsDetail = link.select("div[class='detail']").select("a");
            if (elementsDetail != null && !elementsDetail.isEmpty()) {
                Element element = elementsDetail.first();
                productDetailUrl = element.attr("href");
                title = element.attr("title");
            }

            if (productDetailUrl == null || title == null) {
                continue;
            }

            CrawlPageProductItem crawlPageProductItem = new CrawlPageProductItem();
            crawlPageProductItem.setId(productId);
            crawlPageProductItem.setUrl("https:" + productDetailUrl);
            crawlPageProductItem.setTitle(title);

            if (results == null) {
                results = new ArrayList<>();
            }
            results.add(crawlPageProductItem);
        }

        if (results == null || results.isEmpty()) {
            return null;
        }

        crawlDataPageAliex.setListProductIds(results);

        Gson gson = new GsonBuilder().create();
        String dataClean = gson.toJson(crawlDataPageAliex);
        String encrytData = EncryptUtil.encrypt(dataClean);

        File file = new File(Configs.CACHE_PATH + STORE_INFO_CACHE_DIR);
        if (!file.exists()) {
            file.mkdir();
        }
//
        file = new File(file.getAbsolutePath() + Configs.pathChar + aliexStoreInfo.getStoreSign());
        if (!file.exists()) {
            file.mkdir();
        }
//
        file = new File(file.getAbsolutePath() + Configs.pathChar + aliexStoreInfo.getStoreSign() + "_page" + page + ".txt");
//
        try {
            FileUtils.writeStringToFile(file, encrytData);
        } catch (IOException ex) {
            Logger.getLogger(EstyCrawlSvs.class.getName()).log(Level.SEVERE, null, ex);
        }

        return crawlDataPageAliex;
    }

    public void scrollToBottom(int dy) {
        scrollPage(dy);
    }
}
