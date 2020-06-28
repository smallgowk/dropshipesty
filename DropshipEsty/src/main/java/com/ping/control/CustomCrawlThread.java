/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ping.control;

import com.config.Configs;
import com.models.aliex.store.inputdata.SnakeBaseStoreOrderInfo;
import com.models.amazon.AmzListingItem;
import com.ping.service.crawl.aliex.AliexCrawlProductInfoSvs;
import com.ping.service.crawl.amzlisting.AmzListingCrawlSvs;
import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author duyuno
 */
public class CustomCrawlThread extends Thread {

    private static final Logger LOGGER = Logger.getLogger(ProcessCrawlThread.class.getSimpleName());

//    static HashMap<String, CrawlDataStoreBase> hashMapStoreInfo = new HashMap<>();
//    static HashMap<String, AliexStorePageWrap> hashMapPageInfo = new HashMap<>();
    SnakeBaseStoreOrderInfo baseStoreOrderInfo;
    CrawlProcessListener crawlProcessListener;

    String infoPath;
    String imageFolderPath;

    int pageCount = 1;

//    StringBuffer sb;
    public CustomCrawlThread(String infoPath, String imageFolderPath, CrawlProcessListener crawlProcessListener) {
        this.infoPath = infoPath;
        this.imageFolderPath = imageFolderPath;
        this.crawlProcessListener = crawlProcessListener;
    }

    public boolean isStop = false;

    public void doStop() {
        isStop = true;

        try {
            interrupt();
        } catch (Exception ex) {

        } finally {
//            crawlProcessListener.onPushState("", "Stopped");
//            crawlProcessListener.onFinishPage("");
        }
    }

    @Override
    public void run() {
        //

        if (isStop) {
            crawlProcessListener.onFinishPage("");
            return;
        }

        ArrayList<AmzListingItem> listItems = AmzListingCrawlSvs.getInstance().crawlData();
        crawlProcessListener.onPushState("", "Found " + listItems.size() + " result");

//        for (AmzListingItem item : listItems) {
//            if (isStop) {
//                crawlProcessListener.onFinishPage("");
//                return;
//            }
//            crawlProcessListener.onPushState("", "Processing for " + item.getSku());
//            process(item);
//        }
        
        process(listItems.get(0));

        crawlProcessListener.onFinishPage("");
    }

    private ArrayList<AmzListingItem> crawlData() {
        ArrayList<AmzListingItem> list = new ArrayList<>();

        return list;
    }

    private void process(AmzListingItem item) {
        AmzListingCrawlSvs.getInstance().goToPage(item.getCustomLink());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            java.util.logging.Logger.getLogger(AmzListingCrawlSvs.class.getName()).log(Level.SEVERE, null, ex);
        }
        
//        File imageFile = new File(imageFolderPath + Configs.pathChar + item.sku + ".jpg");
        File imageFile = new File(imageFolderPath + Configs.pathChar + "TLT96593_32602048616_3" + ".jpg");
        if(!imageFile.exists()) {
            return;
        }
        
        AmzListingCrawlSvs.getInstance().doFillBaseInfo(imageFile.getPath());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            java.util.logging.Logger.getLogger(AmzListingCrawlSvs.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for(int i = 0; i < 3; i++) {
            AmzListingCrawlSvs.getInstance().doAddingCustomizePanel();
        }
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            java.util.logging.Logger.getLogger(AmzListingCrawlSvs.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        AmzListingCrawlSvs.getInstance().doUpdateCustomizationIfno();
    }
}
