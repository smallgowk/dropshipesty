/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ping.service.crawl.aliex;

import com.config.Configs;
import static com.config.Configs.CONFIG_FOLDER_PATH;
import static com.config.Configs.IMAGE_PATH;
//import static com.config.Configs.STORE_INFO_CACHE_DIR;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.models.aliex.store.AliexStoreInfo;
import com.models.aliex.crawl.CrawlDataPageBase;
import com.models.aliex.crawl.CrawlDataStoreBase;
import com.models.aliex.crawl.CrawlPageProductItem;
import com.ping.control.CrawlProcessListener;
import com.ping.service.crawl.CrawlerMachine;
import com.ping.view.ClientHomePanel;
import com.utils.CookieUtil;
import com.utils.EncryptUtil;
import com.utils.Utils;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

/**
 *
 * @author PhanDuy
 */
public class AliexCrawlSvs extends CrawlerMachine {

    private static AliexCrawlSvs aliexCrawlSvs;

    public static AliexCrawlSvs getInstance() {
        if (aliexCrawlSvs == null) {
            aliexCrawlSvs = new AliexCrawlSvs();
//            aliexCrawlSvs.initDriver();
        }
        return aliexCrawlSvs;
    }

    public boolean doAutoClick(JFrame jFrame) {
        try {

            Screen s = new Screen();
            Pattern imageUpload = new Pattern(IMAGE_PATH + "imageUpload.PNG");
            Pattern fileInputTextBox = new Pattern(IMAGE_PATH + "input.PNG");
            Pattern openButton = new Pattern(IMAGE_PATH + "open.PNG");
            Pattern addCustomizeMore = new Pattern(IMAGE_PATH + "addCustomization.PNG");
            Pattern addCustomizeOption = new Pattern(IMAGE_PATH + "customizeBoxOption.PNG");
            Pattern addCustomizeConfirm = new Pattern(IMAGE_PATH + "addCustomConfirm.PNG");

//            ImageIcon iconUpload = new ImageIcon(jFrame.getClass().getResource("/Icons/imageUpload.png"));
//            ImageIcon iconFileInputTextBox = new ImageIcon(jFrame.getClass().getResource("/Icons/input.png"));
//            ImageIcon iconOpenButton = new ImageIcon(jFrame.getClass().getResource("/Icons/open.png"));
//
//            Pattern imageUpload = new Pattern(toBufferedImage(iconUpload.getImage()));
//            Pattern fileInputTextBox = new Pattern(toBufferedImage(iconFileInputTextBox.getImage()));
//            Pattern openButton = new Pattern(toBufferedImage(iconOpenButton.getImage()));

            s.click(imageUpload);
            s.wait(fileInputTextBox, 20);
            s.type(fileInputTextBox, "D:\\ImageTest\\VPS_daup061601\\20119641.jpg");
            s.click(openButton);
            s.click(addCustomizeMore);
            s.click(addCustomizeOption);
            s.click(addCustomizeConfirm);
            s.click(imageUpload);

//            List<WebElement> activeElements = driver.findElements(By.className("sg-col-4-of-24 sg-col-4-of-12 sg-col-4-of-36 s-result-item s-asin sg-col-4-of-28 sg-col-4-of-16 AdHolder sg-col sg-col-4-of-20 sg-col-4-of-32"));
//            List<WebElement> activeElements = driver.findElements(By.className("s-result-item s-asin"));
//            List<WebElement> activeElements = driver.findElements(By.className("sg-col-4-of-24 sg-col-4-of-12 sg-col-4-of-36 s-result-item s-asin sg-col-4-of-28 sg-col-4-of-16 sg-col sg-col-4-of-20 sg-col-4-of-32"));
//            List<WebElement> activeElements = driver.findElements(By.xpath("//*[@class='image-upload-square']"));
//            List<WebElement> activeElements = driver.findElements(By.className("image-upload-square"));
//            if (activeElements != null && activeElements.size() > 0) {
//                activeElements.get(0).click();
//
//                Screen s = new Screen();
//                Pattern fileInputTextBox = new Pattern("D:\\Github\\DropshipEsty\\DropshipEsty\\input.PNG");
//                Pattern openButton = new Pattern("D:\\Github\\DropshipEsty\\DropshipEsty\\open.PNG");
//                s.wait(fileInputTextBox, 20);
//                s.type(fileInputTextBox, "D:\\ImageTest\\VPS_daup061601\\20119641.jpg");
//                s.click(openButton);
//
////                for (WebElement element : activeElements) {
////                    String asin = element.getAttribute("data-asin");
////                    if (asinClick.equals(asin)) {
////                        element.click();
////                        return true;
////                    }
////                }
//            }
        } catch (org.openqa.selenium.NoSuchElementException ex) {
            System.out.println("" + ex);
        } catch (FindFailed ex) {
            Logger.getLogger(AliexCrawlSvs.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("" + ex);
        }

        return false;
    }

    public static BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }

    public CrawlDataStoreBase crawlStoreInfo(AliexStoreInfo aliexStoreInfo) {

//        String cacheData = null;
//        File cache = new File(Configs.CACHE_PATH + STORE_INFO_CACHE_DIR + Configs.pathChar + aliexStoreInfo.getStoreSign() + Configs.pathChar + aliexStoreInfo.getStoreSign() + ".txt");
//
//        try {
//            if (cache.exists()) {
//                cacheData = FileUtils.readFileToString(cache);
//            }
//
//        } catch (IOException ex) {
//            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
//        }
////
//        if (cacheData != null) {
//            String cleanData = EncryptUtil.decrypt(cacheData);
//            Gson gson = new Gson();
//            return gson.fromJson(cleanData, CrawlDataStoreBase.class);
//        }
        goToPage(aliexStoreInfo.getLink());

        CrawlDataStoreBase crawlDataStoreAliex = new CrawlDataStoreBase();
        String pageSource = AliexCrawlSvs.getInstance().getPageSource();
        Document doc = Jsoup.parse(pageSource);
//        System.out.println("StoreUrl: " + aliexStoreInfo.getLink());

//        Document doc = AliexCrawlSvs.getInstance().processPage(aliexStoreInfo.getLink());
        crawlDataStoreAliex.updateStatus(doc);

        if (!crawlDataStoreAliex.isSuccess()) {
            return crawlDataStoreAliex;
        }

        int productTotal = 0;
        int pageTotal = 0;

        Elements paging = doc.select("div[id='pagination-bottom']");
        if (paging != null && !paging.isEmpty()) {
            String totalStr = paging.attr("data-total");
            if (totalStr != null && !totalStr.isEmpty()) {
                try {
                    productTotal = Integer.parseInt(totalStr);

                } catch (NumberFormatException ex) {

                }
            }

            String dataUrlRule = paging.attr("data-url-rule");
            String pagingUrl = "https:" + dataUrlRule.replace("*page*", CrawlDataStoreBase.PAGE_INDEX_PATTERN);
            crawlDataStoreAliex.setPageRuleUrl(pagingUrl);
        } else {
            return null;
        }

        if (productTotal > 0) {
            int total = productTotal / 36;
            int mode = productTotal % 36;

            pageTotal = mode == 0 ? total : total + 1;

        } else {
            return null;
        }

        crawlDataStoreAliex.setPageTotal(pageTotal);

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

        crawlDataStoreAliex.setListProductIds(results);

        Gson gson = new Gson();
        String dataClean = gson.toJson(crawlDataStoreAliex);
        String encrytData = EncryptUtil.encrypt(dataClean);
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
//        file = new File(file.getAbsolutePath() + Configs.pathChar + aliexStoreInfo.getStoreSign() + ".txt");
////
//        try {
//            FileUtils.writeStringToFile(file, encrytData);
//        } catch (IOException ex) {
//            Logger.getLogger(AliexCrawlSvs.class.getName()).log(Level.SEVERE, null, ex);
//        }

        return crawlDataStoreAliex;
    }

    public CrawlDataStoreBase crawlStoreInfo(Document doc) {
        CrawlDataStoreBase crawlDataStoreAliex = new CrawlDataStoreBase();
//        Document doc = AliexCrawlSvs.getInstance().processPage(storeurl);

        int productTotal = 0;
        int pageTotal = 0;

        Elements paging = doc.select("div[id='pagination-bottom']");
        if (paging != null && !paging.isEmpty()) {
            String totalStr = paging.attr("data-total");
            if (totalStr != null && !totalStr.isEmpty()) {
                try {
                    productTotal = Integer.parseInt(totalStr);

                } catch (NumberFormatException ex) {

                }
            }

            String dataUrlRule = paging.attr("data-url-rule");
            String pagingUrl = "https:" + dataUrlRule.replace("*page*", CrawlDataStoreBase.PAGE_INDEX_PATTERN);
            crawlDataStoreAliex.setPageRuleUrl(pagingUrl);
        }

        if (productTotal > 0) {
            int total = productTotal / 36;
            int mode = productTotal % 36;

            pageTotal = mode == 0 ? total : total + 1;

        }

        crawlDataStoreAliex.setPageTotal(pageTotal);

        return crawlDataStoreAliex;
    }

    public void logout() {

        if (!isReady()) {
            System.out.println("Logout but not ready!");
            return;
        }

//        goToPage("https://best.aliexpress.com");
        driver.get("https://best.aliexpress.com");

        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(AliexCrawlSvs.class.getName()).log(Level.SEVERE, null, ex);
        }

        String pageSource = getPageSource();

        Document doc = Jsoup.parse(pageSource);

        Elements elements = doc.select("div[class='flyout-user-signout'] > a");
        if (elements != null && !elements.isEmpty()) {
            Element element = elements.first();

            String url = element.attr("href");
            System.out.println("" + url);
            goToPage("https:" + url);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(AliexCrawlSvs.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (currentAccount != null) {
                Configs.hashMapAccountState.put(currentAccount, Boolean.TRUE);
            }

        }
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
        String pageSource = AliexCrawlSvs.getInstance().getPageSource();
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
//            Logger.getLogger(AliexCrawlSvs.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return crawlDataPageAliex;
    }

    public void scrollToBottom(int dy) {
        scrollPage(dy);
    }
}
