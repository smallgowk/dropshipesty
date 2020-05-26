/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ping.control;

import com.api.aliex.AliexApiCall;
import com.api.aliex.response.AliexProductShipResponse;
import com.api.dropship.DropApiCall;
import com.models.aliex.AliexProductFull;
import com.models.aliex.crawl.AliexScriptDetailData;
import com.models.aliex.store.AliexPageInfo;
import com.models.aliex.store.AliexStoreInfo;
import com.models.aliex.crawl.CrawlDataPageBase;
import com.models.aliex.crawl.CrawlDataStoreBase;
import com.models.aliex.crawl.CrawlPageProductItem;
import com.models.aliex.store.inputdata.SnakeBaseStoreOrderInfo;
import com.ping.service.crawl.aliex.AliexCrawlProductInfoSvs;
import com.ping.service.crawl.aliex.AliexCrawlSvs;
import com.ping.service.data.transform.TransformStoreInput;
import com.ping.service.fetchinfo.aliex.FetchAliexProductInfoSvs;
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
        // Transform raw data

//        sb = new StringBuffer();
//        crawlProcessListener.onPushState("Processing input data...");
//        sb.append("================= \n");
//        sb.append("Start crawl " + baseStoreOrderInfo.storeSign);
//        sb.append("\n");
        System.out.println("=================");
        System.out.println("Start crawl " + baseStoreOrderInfo.storeSign);

        AliexStoreInfo aliexStoreInfo = TransformStoreInput.getInstance().transformRawData(baseStoreOrderInfo);
        crawlProcessListener.onStartProcess(aliexStoreInfo.getStoreSign(), aliexStoreInfo.getAccNo());
        long start = System.currentTimeMillis();

//        if (AuthenConfig.isAllowGetMerchant()) {
//            // CrawlMerchant
//            System.out.println("Getting merchant data... " + aliexStoreInfo.getMain_key());
//            crawlProcessListener.onPushState(aliexStoreInfo.getStoreSign(), "Getting merchant data...");
//            ArrayList<String> listKeywords = MerchantSearchSvs.getInstance().getFromCache(aliexStoreInfo.getMain_key());
//            if (listKeywords != null && !listKeywords.isEmpty()) {
//                aliexStoreInfo.setListKeyWords(listKeywords);
//            } else {
//                start = System.currentTimeMillis();
//                listKeywords = MerchantSearchSvs.getInstance().searchKeywords(aliexStoreInfo.getMain_key());
//                System.out.println("Time get merchant data: " + (System.currentTimeMillis() - start));
//                if (listKeywords != null) {
//                    System.out.println("Merchantkeys: " + listKeywords);
//                    aliexStoreInfo.setListKeyWords(listKeywords);
//                }
//            }
//        }
//        if(AuthenConfig.isAlloeGetRelate() && (aliexStoreInfo.getListKeyWords() == null || aliexStoreInfo.getListKeyWords().isEmpty())) {
//            ArrayList<String> listRelatedSearch = Utils.getRelatedInfo(productId)
//        }
        // Crawl Store Info
        crawlProcessListener.onPushState(aliexStoreInfo.getStoreSign(), "Getting aliex store info...");
//        sb.append("Getting aliex store info...");
//        sb.append("\n");
        System.out.println("Getting aliex store info...");
        start = System.currentTimeMillis();
        CrawlDataStoreBase crawlDataStoreBase = AliexCrawlSvs.getInstance().crawlStoreInfo(aliexStoreInfo);

//        sb.append("Time get aliex store info: " + (System.currentTimeMillis() - start));
//        sb.append("\n");
        System.out.println("Time get aliex store info: " + (System.currentTimeMillis() - start));

        if (crawlDataStoreBase == null || !crawlDataStoreBase.isSuccess()) {
            isStop = true;
            crawlProcessListener.onStopToLogin(aliexStoreInfo.getLink(), aliexStoreInfo.getStoreSign());
            return;
        }

//        AliexCrawlSvs.getInstance().nextPage();
        aliexStoreInfo.setTotalPage(crawlDataStoreBase.getPageTotal());
        processStoreInfoSvs.processStoreInfo(aliexStoreInfo);
//        AliexStoreInfoWrap aliexStoreInfoWrap = TransformToServer.getInstance().transformData(aliexStoreInfo);
//        ResponseObj responseObj = DropApiCall.doSendStoreInfo(aliexStoreInfoWrap, null);
//        if (responseObj == null || !responseObj.isSuccess()) {
//            isStop = true;
//            crawlProcessListener.onStop(aliexStoreInfo.getStoreSign());
//            if (responseObj != null) {
//                crawlProcessListener.onPushErrorRequest(aliexStoreInfo.getStoreSign(), responseObj);
//            }
//            return;
//        }

        
//        System.out.println("" + sb.toString());
//        sb = new StringBuffer();
        // Crawl Page Info and get aliex product info
        crawlProcessListener.onPushState(aliexStoreInfo.getStoreSign(), "Getting product info...");

        CrawlDataPageBase crawlDataPageBase = crawlDataStoreBase;
        int page = 1;
        while (crawlDataPageBase != null && page <= crawlDataStoreBase.pageTotal) {

            if(!crawlDataPageBase.isSuccess()) {
                isStop = true;
                crawlProcessListener.onStopToLogin(aliexStoreInfo.getLink(), aliexStoreInfo.getStoreSign());
                return;
            }
            
            if (isStop) {
                return;
            }

            int size = crawlDataPageBase.listProductIds.size();

//            ArrayList<AliexProductFull> pageProducts = new ArrayList<>();
            for (int j = 0; j < size; j++) {

                if (isStop) {
                    return;
                }
                CrawlPageProductItem crawlPageProductItem = crawlDataPageBase.listProductIds.get(j);

                AliexProductFull aliexProductFull = FetchAliexProductInfoSvs.getInstance().getProductFromCache(crawlPageProductItem.getId(), aliexStoreInfo.getStoreSign());

                if (aliexProductFull == null) {
                    AliexScriptDetailData data = AliexCrawlProductInfoSvs.getInstance().crawlProductData(crawlPageProductItem.getUrl());
                    if (data == null || !data.isSuccess()) {
                        crawlProcessListener.onPushState(aliexStoreInfo.getStoreSign(), "Page (" + page + "/" + crawlDataStoreBase.pageTotal + ") " + (int) ((((j + 1) * 1f) / size) * 100) + "%");
                        continue;
                    }
//                    if (!data.isSuccess()) {
//                        isStop = true;
//                        crawlProcessListener.onStopToLogin(null, aliexStoreInfo.getStoreSign());
//                        return;
//                    }
                    aliexProductFull = new AliexProductFull(crawlPageProductItem);
                    aliexProductFull.setDataCrawl(data);

                    if (aliexProductFull.getShippingPrice() < 0) {
                        AliexProductShipResponse aliexProductShipResponse = AliexApiCall.getProductShippingInfo(crawlPageProductItem.getId());

                        if (aliexProductShipResponse == null) {
                            System.out.println("No shipping infor for: " + crawlPageProductItem.getId());
                            continue;
                        }

                        float shippingPrice = aliexProductShipResponse.getShippingPrice();

                        if (shippingPrice < 0) {
                            System.out.println("No shipping method for: " + crawlPageProductItem.getId());
                            continue;
                        }

                        aliexProductFull.setShippingPrice(shippingPrice);
                    }

                    aliexProductFull.setStoreSign(aliexStoreInfo.getStoreSign());
                    aliexProductFull.setPageIndex(page);
                    FetchAliexProductInfoSvs.getInstance().saveProductInfo(aliexProductFull, aliexStoreInfo.getStoreSign());
                }

//                AliexProductFull aliexProductFull = FetchAliexProductInfoSvs.getInstance().getProductFromCache(crawlPageProductItem.getId(), aliexStoreInfo.getStoreSign());
//                AliexProductFull aliexProductFull = crawlProductData(crawlPageProductItem);
//                System.out.println("" + aliexProductFull.getListSearchTerms());
//                if (aliexProductFull == null) {
//                    aliexProductFull = crawlProductData(crawlPageProductItem);
//
////                    if (aliexProductFull == null) {
////                        aliexProductFull = fetchAndCrawlProductData(crawlPageProductItem, aliexStoreInfo, i + 1);
////                    }
//
//                }
//                Document doc = AliexCrawlSvs.getInstance().processPage("https://ilogisticsaddress.aliexpress.com/AjaxQueryCountries?callback=__zoro_request_1");
//                System.out.println("12: " + doc.toString());
//                if (aliexProductFull != null) {
                
//                    if (AuthenConfig.isAlloeGetRelate() && (aliexStoreInfo.getListKeyWords() == null || aliexStoreInfo.getListKeyWords().isEmpty())) {
//                        ArrayList<String> listRelatedSearch = Utils.getRelatedInfo(aliexProductFull.getId());
////                        System.out.println("Realted search cache: " + listRelatedSearch);
//                        if (Utils.hashMapRelatedKeys.containsKey(aliexProductFull.getId())) {
//                            aliexProductFull.setListSearchTerms(listRelatedSearch);
//                        } else {
//                            CrawlProductDetail crawlProductInfo = AliexCrawlProductInfoSvs.getInstance().crawlProductInfo(crawlPageProductItem, aliexStoreInfo, false, false, true);
//                            if (crawlProductInfo != null) {
//                                aliexProductFull.setListSearchTerms(crawlProductInfo.getListRelatedSearch());
//                            }
//                        }
//
//                    }
                processStoreInfoSvs.processProduct(aliexProductFull);
//                AliexProductInfoWrap aliexProductInfoWrap = TransformToServer.getInstance().transformData(aliexProductFull);
//                DropApiCall.doSendProductInfo(aliexProductInfoWrap, null);
//                }

                if (!isStop) {
                    crawlProcessListener.onPushState(aliexStoreInfo.getStoreSign(), "Page (" + page + "/" + crawlDataStoreBase.pageTotal + ") " + (int) ((((j + 1) * 1f) / size) * 100) + "%");
                }
            }

            AliexPageInfo aliexPageInfo = new AliexPageInfo();
            aliexPageInfo.setPageIndex(page);
            aliexPageInfo.setTotalProduct(size);
            aliexPageInfo.setStoreSign(aliexStoreInfo.getStoreSign());
//            AliexStorePageWrap aliexStorePageWrap = TransformToServer.getInstance().transformData(aliexPageInfo);

            processStoreInfoSvs.processPageInfo(aliexPageInfo);
            
//            hashMapPageInfo.put(aliexStoreInfo.getStoreSign() + "page" + (i + 1), aliexStorePageWrap);
//            responseObj = DropApiCall.doSendPageInfo(aliexStorePageWrap, null);
//
//            if (responseObj == null || !responseObj.isSuccess()) {
//                isStop = true;
//                crawlProcessListener.onStop(aliexStoreInfo.getStoreSign());
//                if (responseObj != null) {
//                    crawlProcessListener.onPushState(aliexStoreInfo.getStoreSign(), responseObj.getMessage());
//                }
//
//                return;
//            }

            DropApiCall.doSendUpdateInfo("Get " + crawlDataPageBase.listProductIds.size() + " from " + aliexStoreInfo.getStoreSign() + " page " + page, null);

            page++;
            if(page <= crawlDataStoreBase.pageTotal) {
                crawlDataPageBase = AliexCrawlSvs.getInstance().crawlNextPageInfo(aliexStoreInfo, crawlDataStoreBase, page);
            }
            
        }

        crawlProcessListener.onPushState(aliexStoreInfo.getStoreSign(), "Done");
        crawlProcessListener.onFinishPage(aliexStoreInfo.getStoreSign());
    }

//    private AliexProductFull crawlProductData(CrawlPageProductItem crawlPageProductItem) {
//        AliexScriptDetailData data = AliexCrawlProductInfoSvs.getInstance().crawlProductData(crawlPageProductItem.getUrl());
//        if (data == null) {
//            return null;
//        }
//        AliexProductFull aliexProductFull = new AliexProductFull(crawlPageProductItem);
//        aliexProductFull.setDataCrawl(data);
//        return aliexProductFull;
//    }

//    private AliexProductFull fetchAndCrawlProductData(CrawlPageProductItem crawlPageProductItem, AliexStoreInfo aliexStoreInfo, int pageIndex) {
//        AliexProductFullResponse data = AliexApiCall.getProductFullInfo(crawlPageProductItem.getId());
//        if (data != null) {
//
//            AliexProductFull aliexProductFull = new AliexProductFull();
//            aliexProductFull.setDataApi(data);
//
//            boolean isNeedToGetDesctipion = StringUtils.isEmpty(aliexProductFull.getHtmlDescription());
//            boolean isNeedToGetRelate = false;
//            if (AuthenConfig.isAlloeGetRelate() && (aliexStoreInfo.getListKeyWords() == null || aliexStoreInfo.getListKeyWords().isEmpty())) {
//                ArrayList<String> listRelatedSearch = Utils.getRelatedInfo(aliexProductFull.getId());
//                if (Utils.hashMapRelatedKeys.containsKey(aliexProductFull.getId())) {
//                    aliexProductFull.setListSearchTerms(listRelatedSearch);
//                } else {
//                    isNeedToGetRelate = true;
//                }
//            }
//
//            boolean isMissBrandname = aliexProductFull.getBranName() == null;
//
//            if (isNeedToGetDesctipion || isNeedToGetRelate || isMissBrandname) {
////                            start = System.currentTimeMillis();
//                CrawlProductDetail crawlProductInfo = AliexCrawlProductInfoSvs.getInstance().crawlProductInfo(crawlPageProductItem, aliexStoreInfo, isMissBrandname, isNeedToGetDesctipion, isNeedToGetRelate);
//                if (crawlProductInfo != null) {
//                    if (isMissBrandname && crawlProductInfo.getBrandName() != null) {
//                        aliexProductFull.addBrandName(crawlProductInfo.getBrandName());
//                    }
//                    System.out.println(crawlPageProductItem.getId() + ": " + crawlProductInfo.getBrandName());
//                    if (crawlProductInfo.getHashProperties() != null) {
//                        aliexProductFull.repairVariationImage(crawlProductInfo.getHashProperties());
//                    }
//
//                    if (isNeedToGetRelate && crawlProductInfo.getListRelatedSearch() != null && !crawlProductInfo.getListRelatedSearch().isEmpty()) {
//                        aliexProductFull.setListSearchTerms(crawlProductInfo.getListRelatedSearch());
////                                    System.out.println("Realted search: " + crawlProductInfo.getListRelatedSearch());
//                    }
//
//                    if (isNeedToGetDesctipion && !StringUtils.isEmpty(crawlProductInfo.getHtml_description())) {
////                                    System.out.println("HtmlDes: " + crawlProductInfo.getHtml_description());
//                        aliexProductFull.setHtmlDescription(crawlProductInfo.getHtml_description());
//
//                    }
//                }
//            }
//
//            return aliexProductFull;
//
//        }
//
//        return null;
//    }

}
