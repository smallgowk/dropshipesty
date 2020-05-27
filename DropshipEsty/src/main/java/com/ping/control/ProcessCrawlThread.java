/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ping.control;

import com.google.gson.Gson;
import com.models.aliex.store.inputdata.SnakeBaseStoreOrderInfo;
import com.models.amazon.ProductAmz;
import com.models.esty.EstyCrawlDataPageBase;
import com.models.esty.EstyCrawlDataStoreBase;
import com.models.esty.EstyCrawlProductItem;
import com.models.esty.EstyScriptCrawl;
import com.ping.service.crawl.aliex.AliexCrawlSvs;
import com.ping.service.crawl.esty.EstyCrawlSvs;
import com.pong.control.ProcessPageDataSvs;
import com.pong.control.ProcessStoreInfoSvs;
import com.pong.control.ProcessTransformEstyToAmz;
import java.util.ArrayList;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author duyuno
 */
public class ProcessCrawlThread extends Thread {

    private static final Logger LOGGER = Logger.getLogger(ProcessCrawlThread.class.getSimpleName());

//    static HashMap<String, CrawlDataStoreBase> hashMapStoreInfo = new HashMap<>();
//    static HashMap<String, AliexStorePageWrap> hashMapPageInfo = new HashMap<>();
    SnakeBaseStoreOrderInfo baseStoreOrderInfo;
    CrawlProcessListener crawlProcessListener;
    ProcessStoreInfoSvs processStoreInfoSvs;

    int pageCount = 1;

//    StringBuffer sb;
    public ProcessCrawlThread(SnakeBaseStoreOrderInfo baseStoreOrderInfo, CrawlProcessListener crawlProcessListener) {
        this.baseStoreOrderInfo = baseStoreOrderInfo;
        this.crawlProcessListener = crawlProcessListener;
        processStoreInfoSvs = new ProcessStoreInfoSvs();
    }

    public boolean isStop = false;

    public void doStop() {
        isStop = true;

        try {
            interrupt();
        } catch (Exception ex) {

        }
    }

    @Override
    public void run() {
        //

//        EstyCrawlSvs.getInstance().goToPage(baseStoreOrderInfo.getLink());
//        EstyCrawlDataStoreBase estyCrawlDataStoreBase = EstyCrawlSvs.getInstance().crawlStoreInfo();
//        try {
//            EstyCrawlSvs.getInstance().scrollToBottom();
//            Thread.sleep(2000);
//        } catch (InterruptedException ex) {
//            java.util.logging.Logger.getLogger(AliexCrawlSvs.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        EstyCrawlDataPageBase estyCrawlDataPageBase = EstyCrawlSvs.getInstance().crawlPage();
        Document document = EstyCrawlSvs.getInstance().processPage(baseStoreOrderInfo.getLink());

        Elements script = document.select("script[type='application/ld+json']");

        Gson gson = new Gson();
        EstyScriptCrawl estyScriptCrawl = gson.fromJson(script.html(), EstyScriptCrawl.class);

        while (isNotStopCondition()) {
            EstyCrawlDataPageBase estyCrawlDataPageBase = null;
            if(pageCount == 1) {
                estyCrawlDataPageBase = EstyCrawlSvs.getInstance().crawlPage(document);
            } else {
               document = EstyCrawlSvs.getInstance().processPage(estyScriptCrawl.getUrl() + "?page=" + pageCount); 
               estyCrawlDataPageBase = EstyCrawlSvs.getInstance().crawlPage(document);
            }

            ArrayList<ProductAmz> listProducts = new ArrayList<>();

            if (estyCrawlDataPageBase.getListProductItems() != null) {
                for (EstyCrawlProductItem estyCrawlProductItem : estyCrawlDataPageBase.getListProductItems()) {
                    ArrayList<ProductAmz> list = ProcessTransformEstyToAmz.transform(estyCrawlProductItem, baseStoreOrderInfo);
                    if (list != null) {
                        listProducts.addAll(list);
                    }
                }

                ProcessPageDataSvs.processPageData(listProducts, "Esty.xlsx");
            }
            System.out.println("Finish page " + pageCount);
            System.out.println("========================");
            pageCount ++;
        }

    }
    
    private boolean isNotStopCondition() {
        return pageCount < 10;
    }
}
