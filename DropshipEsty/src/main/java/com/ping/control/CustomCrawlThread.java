/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ping.control;

import com.models.aliex.store.inputdata.SnakeBaseStoreOrderInfo;
import com.models.amazon.AmzListingItem;
import com.ping.service.crawl.amzlisting.AmzListingCrawlSvs;
import java.util.ArrayList;
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

        for (AmzListingItem item : listItems) {
            if (isStop) {
                crawlProcessListener.onFinishPage("");
                return;
            }
            process(item);
        }

        crawlProcessListener.onFinishPage("Total: " + listItems.size());

    }

    private ArrayList<AmzListingItem> crawlData() {
        ArrayList<AmzListingItem> list = new ArrayList<>();

        return list;
    }

    private void process(AmzListingItem item) {
        
    }
}
