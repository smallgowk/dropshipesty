/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ping.control;

import com.config.Configs;
import com.models.aliex.store.inputdata.SnakeBaseStoreOrderInfo;
import com.models.amazon.ProductAmz;
import com.models.esty.EstyCrawlDataPageBase;
import com.models.esty.EstyCrawlDataStoreBase;
import com.models.esty.EstyCrawlProductItem;
import com.ping.service.crawl.DownloadMachine;
import com.ping.service.crawl.esty.EstyCrawlSvs;
import com.ping.service.local.GenAmazonFromImageSvs;
import com.pong.control.ProcessPageDataSvs;
import com.pong.control.ProcessStoreInfoSvs;
import com.pong.control.ProcessTransformEstyToAmz;
import com.utils.StringUtils;
import io.github.bonigarcia.wdm.Config;
import java.io.File;
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
                
                File imageFolder = new File(baseStoreOrderInfo.getPrefix());
                if (!imageFolder.exists()) {
                    imageFolder.mkdir();
                }
                
                for(int i = 0, size = listProducts.size(); i < size; i++) {
                    ProductAmz productAmz = listProducts.get(i);
                    productAmz.setItem_sku(baseStoreOrderInfo.getPrefix() + "_" + pageCount + "_" + (i + 1));
                    productAmz.setPart_number(productAmz.getItem_sku().substring(0, productAmz.getItem_sku().length() - 2));
                    DownloadMachine.download(productAmz.getMain_image_url(), imageFolder.getPath() + Configs.pathChar + productAmz.getItem_sku() + ".jpg");
                    crawlProcessListener.onPushState("", "Downloaded " + productAmz.getMain_image_url());
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
