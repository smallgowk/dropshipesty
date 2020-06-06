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
import com.ping.service.crawl.esty.EstyCrawlSvs;
import com.ping.service.local.GenAmazonFromImageSvs;
import com.pong.control.ProcessPageDataSvs;
import com.pong.control.ProcessStoreInfoSvs;
import com.pong.control.ProcessTransformEstyToAmz;
import com.utils.StringUtils;
import java.util.ArrayList;
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

        } finally {
//            crawlProcessListener.onPushState("", "Stopped");
        }
    }

    @Override
    public void run() {
        //

        if (isStop) {
            return;
        }
        
        if(baseStoreOrderInfo.isEtsy()) {
            crawlEtsy();
        } else {
            genFromLocal();
        }

        crawlProcessListener.onFinishPage("");

    }

    public void crawlEtsy() {
        EstyCrawlDataStoreBase estyCrawlDataStoreBase = EstyCrawlSvs.getInstance().crawlStoreInfo(baseStoreOrderInfo.getLink());
        while (pageCount <= estyCrawlDataStoreBase.getPageTotal()) {

            if (isStop) {
                return;
            }
            crawlProcessListener.onPushState("", "Đang cào trang " + pageCount);
            EstyCrawlDataPageBase estyCrawlDataPageBase = EstyCrawlSvs.getInstance().crawlPage(estyCrawlDataStoreBase, pageCount);

            ArrayList<ProductAmz> listProducts = new ArrayList<>();

            if (estyCrawlDataPageBase.getListProductItems() != null) {

                for (EstyCrawlProductItem estyCrawlProductItem : estyCrawlDataPageBase.getListProductItems()) {
                    if (isStop) {
                        return;
                    }
                    ArrayList<ProductAmz> list = ProcessTransformEstyToAmz.transform(estyCrawlProductItem, baseStoreOrderInfo);
                    if (list != null) {
                        listProducts.addAll(list);
                    }
                }

                crawlProcessListener.onPushState("", "--> Đã hoàn thành trang " + pageCount);

                ProcessPageDataSvs.processPageData(listProducts, "Esty" + pageCount + ".xlsx");
            }
            pageCount++;
        }
    }
    
    public void genFromLocal() {
        ArrayList<ProductAmz> listProducts = GenAmazonFromImageSvs.getInstance().genFromImageLocal(baseStoreOrderInfo);
        ProcessPageDataSvs.processPageData(listProducts, baseStoreOrderInfo.getImageFolderName() + ".xlsx");
    }
}
