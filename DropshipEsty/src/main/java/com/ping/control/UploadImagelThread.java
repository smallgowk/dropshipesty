/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ping.control;

import com.ping.service.crawl.aliex.AliexCrawlSvs;
import javax.swing.JFrame;
import org.apache.log4j.Logger;

/**
 *
 * @author duyuno
 */
public class UploadImagelThread extends Thread {

    private static final Logger LOGGER = Logger.getLogger(UploadImagelThread.class.getSimpleName());
    JFrame jFrame;

//    static HashMap<String, CrawlDataStoreBase> hashMapStoreInfo = new HashMap<>();
//    static HashMap<String, AliexStorePageWrap> hashMapPageInfo = new HashMap<>();

//    StringBuffer sb;
    public UploadImagelThread(JFrame jFrame) {
        this.jFrame = jFrame;
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
        AliexCrawlSvs.getInstance().doAutoClick(jFrame);
        

    }

    
}
