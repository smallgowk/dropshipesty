/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ping.control;

import com.models.aliex.store.inputdata.SnakeBaseStoreOrderInfo;
import com.models.amazon.ProductAmz;
import com.models.esty.EstyCrawlDataPageBase;
import com.models.esty.EstyCrawlDataStoreBase;
import com.models.esty.EstyCrawlProductItem;
import com.ping.service.crawl.aliex.AliexCrawlSvs;
import com.ping.service.crawl.esty.EstyCrawlSvs;
import com.ping.service.local.GenAmazonFromImageSvs;
import com.pong.control.ProcessPageDataSvs;
import com.pong.control.ProcessStoreInfoSvs;
import com.pong.control.ProcessTransformEstyToAmz;
import com.utils.StringUtils;
import java.util.ArrayList;
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
