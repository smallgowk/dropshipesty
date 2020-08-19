/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ping.service.crawl.esty;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.models.aliex.store.AliexStoreInfo;
import com.models.aliex.crawl.CrawlDataPageBase;
import com.models.aliex.crawl.CrawlDataStoreBase;
import com.models.aliex.crawl.CrawlPageProductItem;
import com.models.esty.EstyCrawlDataPageBase;
import com.models.esty.EstyCrawlDataStoreBase;
import com.models.esty.EstyCrawlProductItem;
import com.models.esty.EstyScriptCrawl;
import com.ping.service.crawl.CrawlerMachine;
import com.utils.CookieUtil;
import com.utils.EncryptUtil;
import com.utils.StringUtils;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
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

    public EstyCrawlDataPageBase crawlPage() {
        String pageSource = getPageSource();
        Document doc = Jsoup.parse(pageSource);
        return crawlPage(doc);
    }

    public EstyCrawlDataPageBase crawlPage(EstyCrawlDataStoreBase estyCrawlDataStoreBase, int pageCount) {
        Document document = EstyCrawlSvs.getInstance().processPage(estyCrawlDataStoreBase.getPageLink(pageCount));
        return crawlPage(document);
    }

    public EstyCrawlDataPageBase crawlPage(Document doc) {
        EstyCrawlDataPageBase estyCrawlDataPageBase = new EstyCrawlDataPageBase();

        Elements items = doc.select("li");

        ArrayList<EstyCrawlProductItem> listEstyCrawlProductItems = new ArrayList<>();

        for (Element element : items) {
            if (!StringUtils.isEmpty(element.attr("data-listing-id"))) {
                EstyCrawlProductItem estyCrawlProductItem = new EstyCrawlProductItem();
                String id = element.attr("data-listing-id");
                estyCrawlProductItem.setId(id);

                Elements aElements = element.select("a");
                if (aElements != null) {
                    String title = aElements.first().attr("title");
                    estyCrawlProductItem.setTitle(title);

                    String detailLink = aElements.first().attr("href");
                    estyCrawlProductItem.setDetailUrl(detailLink);
                }
//                
                Elements elements = element.select("div[class='height-placeholder'] > img");
                String image = null;
                if (elements != null) {
                    image = elements.first().attr("src");
                    if (StringUtils.isEmpty(image)) {
                        image = elements.first().attr("data-src");
                    }
                }

                if (!StringUtils.isEmpty(image)) {
                    image = StringUtils.convertEstyImageLink(image);
                    estyCrawlProductItem.setImageUrl(image);
                }

                listEstyCrawlProductItems.add(estyCrawlProductItem);
            }
        }

        estyCrawlDataPageBase.setListProductItems(listEstyCrawlProductItems);

        return estyCrawlDataPageBase;
    }

    public EstyCrawlDataStoreBase crawlStoreInfo(String link) {
        EstyCrawlDataStoreBase estyCrawlDataStoreBase = new EstyCrawlDataStoreBase();

        Document document = EstyCrawlSvs.getInstance().processPage(link);

//        Elements script = document.select("script[type='application/ld+json']");
//        Gson gson = new Gson();
//        EstyScriptCrawl estyScriptCrawl = gson.fromJson(script.html(), EstyScriptCrawl.class);
//        estyCrawlDataStoreBase.setEstyScriptCrawl(estyScriptCrawl);

        Elements storeName = document.select("h1[class='mb-lg-1']");
        estyCrawlDataStoreBase.setStoreName(storeName.text());

        String pageRule = null;

        int page = 1;

        Elements pageInfos = document.select("li[class='wt-action-group__item-container'] > a");

        if (pageInfos != null) {
            int size = pageInfos.size();
            if (size < 5) {
                for (Element element : pageInfos) {
                    if (pageRule == null) {
                        String pageLink = element.attr("href");
                        if (pageLink != null && !pageLink.isEmpty()) {
                            try {
                                pageRule = removeQueryParameter(pageLink, "page");
                            } catch (URISyntaxException ex) {
                                pageRule = pageLink;
                            }
                            estyCrawlDataStoreBase.setPageRuleUrl(pageRule);
                        }
                    }

                    String pageStr = element.attr("data-page");
                    if (!StringUtils.isEmpty(pageStr)) {
                        int pageValue = Integer.parseInt(pageStr);
                        if (pageValue > page) {
                            page = pageValue;
                        }
                    }
                }
            } else {
                page = 5;
                for (int i = size - 1; i >= 0; i--) {
                    Element element = pageInfos.get(i);
                    if (pageRule == null) {
                        String pageLink = element.attr("href");
                        if (pageLink != null && !pageLink.isEmpty()) {
                            try {
                                pageRule = removeQueryParameter(pageLink, "page");
                            } catch (URISyntaxException ex) {
                                pageRule = pageLink;
                            }
                            estyCrawlDataStoreBase.setPageRuleUrl(pageRule);
                        }
                    }

                    String pageStr = element.attr("data-page");
                    if (!StringUtils.isEmpty(pageStr)) {
                        int pageValue = Integer.parseInt(pageStr);
                        if (pageValue > page) {
                            page = pageValue;
                        }
                    }
                    
                    if (page != 5 && pageRule != null) {
                        break;
                    }
                }
            }

        }

        estyCrawlDataStoreBase.setPageTotal(page);

        System.out.println("Total Page: " + page);

        return estyCrawlDataStoreBase;
    }

    public String removeQueryParameter(String url, String parameterName) throws URISyntaxException {
        URIBuilder uriBuilder = new URIBuilder(url);
        List<NameValuePair> queryParameters = uriBuilder.getQueryParams()
                .stream()
                .filter(p -> !p.getName().equals(parameterName))
                .collect(Collectors.toList());
        if (queryParameters.isEmpty()) {
            uriBuilder.removeQuery();
        } else {
            uriBuilder.setParameters(queryParameters);
        }
        return uriBuilder.build().toString();
    }

    public String crawlMainUrl(String detailLink) {
        Document doc = EstyCrawlSvs.getInstance().processPage(detailLink);
        Elements items = doc.select("li");
        String mailUrl = null;
        int count = 1;
        for (Element element : items) {
            if (!StringUtils.isEmpty(element.attr("data-image-id"))) {
                Elements aElements = element.select("img");
                if (aElements != null) {
                    mailUrl = aElements.first().attr("data-src-zoom-image");
                    if (count == 4) {
                        break;
                    }
                    count++;
                }
            }
        }

        return mailUrl;
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

//        if (!crawlDataPageAliex.isSuccess()) {
//            rechiveCookies();
////            System.out.println("Current cookies: " + cookies);
//            CookieUtil.saveCookies(cookies, "errorCookies_" + System.currentTimeMillis() + ".txt");
//            return crawlDataPageAliex;
//        }
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
//        file = new File(file.getAbsolutePath() + Configs.pathChar + aliexStoreInfo.getStoreSign() + "_page" + page + ".txt");
////
//        try {
//            FileUtils.writeStringToFile(file, encrytData);
//        } catch (IOException ex) {
//            Logger.getLogger(EstyCrawlSvs.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return crawlDataPageAliex;
    }

    public void scrollToBottom(int dy) {
        scrollPage(dy);
    }
}
