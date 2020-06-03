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
            crawlProcessListener.onPushState("", "Stopped");
        }
    }

    @Override
    public void run() {
        //

        if (isStop) {
            return;
        }

//        EstyCrawlSvs.getInstance().goToPage(baseStoreOrderInfo.getLink());
        EstyCrawlDataStoreBase estyCrawlDataStoreBase = EstyCrawlSvs.getInstance().crawlStoreInfo(baseStoreOrderInfo.getLink());
//        try {
//            EstyCrawlSvs.getInstance().scrollToBottom();
//            Thread.sleep(2000);
//        } catch (InterruptedException ex) {
//            java.util.logging.Logger.getLogger(AliexCrawlSvs.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        EstyCrawlDataPageBase estyCrawlDataPageBase = EstyCrawlSvs.getInstance().crawlPage();

        while (pageCount <= estyCrawlDataStoreBase.getPageTotal()) {

            if (isStop) {
                return;
            }
            crawlProcessListener.onPushState("", "Đang cào trang " + pageCount);
            EstyCrawlDataPageBase estyCrawlDataPageBase = EstyCrawlSvs.getInstance().crawlPage(estyCrawlDataStoreBase, pageCount);

            ArrayList<ProductAmz> listProducts = new ArrayList<>();

            if (estyCrawlDataPageBase.getListProductItems() != null) {

//                int size = estyCrawlDataPageBase.getListProductItems().size();
//                int count = 1;

                for (EstyCrawlProductItem estyCrawlProductItem : estyCrawlDataPageBase.getListProductItems()) {
                    if (isStop) {
                        return;
                    }
//                    if (baseStoreOrderInfo.isFilterImage) {
//                        crawlProcessListener.onProgress("Hoàn thành " + (int) ((1f * (count++) / size) * 100) + " %");
//
//                        String mailUrl = EstyCrawlSvs.getInstance().crawlMainUrl(estyCrawlProductItem.getDetailUrl());
//                        if (StringUtils.isEmpty(mailUrl)) {
//                            continue;
//                        }
//                        estyCrawlProductItem.setImageUrl(mailUrl);
//                    }
                    ArrayList<ProductAmz> list = ProcessTransformEstyToAmz.transform(estyCrawlProductItem, baseStoreOrderInfo);
                    if (list != null) {
                        listProducts.addAll(list);
                    }
                }

                crawlProcessListener.onPushState("", "--> Đã hoàn thành trang " + pageCount);

                ProcessPageDataSvs.processPageData(listProducts, "Esty" + pageCount + ".xlsx");
            }
//            crawlProcessListener.onPushState("", "Finish page " + pageCount);
            pageCount++;
        }
        
        crawlProcessListener.onFinishPage("");

    }
}
