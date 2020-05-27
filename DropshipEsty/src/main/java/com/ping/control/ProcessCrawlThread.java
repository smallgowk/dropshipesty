/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ping.control;

import com.models.aliex.store.inputdata.SnakeBaseStoreOrderInfo;
import com.models.esty.EstyCrawlDataPageBase;
import com.models.esty.EstyCrawlDataStoreBase;
import com.ping.service.crawl.esty.EstyCrawlSvs;
import com.pong.control.ProcessStoreInfoSvs;
import org.apache.log4j.Logger;

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
        EstyCrawlDataStoreBase estyCrawlDataStoreBase = EstyCrawlSvs.getInstance().crawlStoreInfo();
        
        EstyCrawlDataPageBase estyCrawlDataPageBase = EstyCrawlSvs.getInstance().crawlPage(estyCrawlDataStoreBase);
        
        
    }
}
