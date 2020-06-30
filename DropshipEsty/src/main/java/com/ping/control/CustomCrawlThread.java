/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ping.control;

import com.config.Configs;
import com.models.aliex.store.inputdata.SnakeBaseStoreOrderInfo;
import com.models.amazon.AmzListingItem;
import com.models.amazon.SurfaceModel;
import com.ping.service.crawl.aliex.AliexCrawlProductInfoSvs;
import com.ping.service.crawl.amzlisting.AmzListingCrawlSvs;
import com.utils.ExcelAmzCustomizeUtil;
import java.io.File;
import java.io.FileNotFoundException;
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
        
        SurfaceModel surfaceModel = null;
        
        try {
            surfaceModel = ExcelAmzCustomizeUtil.readDataInfo(infoPath);
        } catch (FileNotFoundException ex) {
            crawlProcessListener.onPushState("", "Error: " + ex.getMessage());
            return;
        }

        ArrayList<AmzListingItem> listItems = AmzListingCrawlSvs.getInstance().crawlData();
        crawlProcessListener.onPushState("", "Found " + listItems.size() + " result");

        for (AmzListingItem item : listItems) {
            if (isStop) {
                crawlProcessListener.onFinishPage("");
                return;
            }
            
            process(item, surfaceModel);
        }

        crawlProcessListener.onFinishPage("");
    }

    private void process(AmzListingItem item, SurfaceModel surfaceModel) {
        crawlProcessListener.onPushState("", "Processing for " + item.getSku());
        AmzListingCrawlSvs.getInstance().goToPage(item.getCustomLink());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            java.util.logging.Logger.getLogger(AmzListingCrawlSvs.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        File imageFile = new File(imageFolderPath + Configs.pathChar + item.sku + ".jpg");
        if(!imageFile.exists()) {
            crawlProcessListener.onPushState("", "Not found image for " + item.sku);
            return;
        }
        
        AmzListingCrawlSvs.getInstance().doFillBaseInfo(imageFile.getPath(), surfaceModel);
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            java.util.logging.Logger.getLogger(AmzListingCrawlSvs.class.getName()).log(Level.SEVERE, null, ex);
        }
        
//        for(int i = 0; i < 3; i++) {
//            AmzListingCrawlSvs.getInstance().doAddingCustomizePanel();
//        }
        
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException ex) {
//            java.util.logging.Logger.getLogger(AmzListingCrawlSvs.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        AmzListingCrawlSvs.getInstance().doUpdateCustomizationIfno(surfaceModel);
        AmzListingCrawlSvs.getInstance().doSave();
        try {
            Thread.sleep(6000);
        } catch (InterruptedException ex) {
            java.util.logging.Logger.getLogger(AmzListingCrawlSvs.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
