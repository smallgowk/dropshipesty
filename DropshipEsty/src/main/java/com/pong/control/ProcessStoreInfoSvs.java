/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pong.control;

import com.models.aliex.AliexProductFull;
import com.models.amazon.ProductAmz;
import com.models.aliex.store.AliexPageInfo;
import com.models.aliex.store.AliexStoreInfo;
import com.pong.server.obj.income.BaseIncomeRequestObject;
import com.utils.ComputerIdentifier;
import com.utils.Constants;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author PhanDuy
 */
public class ProcessStoreInfoSvs {

    static HashMap<String, AliexStoreInfo> mapStoreInfo = new HashMap<>();
    static HashMap<String, ArrayList<ProductAmz>> mapProducts = new HashMap<>();
    static HashMap<String, ArrayList<String>> mapBrandName = new HashMap<>();

    public void processStoreInfo(AliexStoreInfo aliexStoreInfo) {
        mapStoreInfo.put(genKey(ComputerIdentifier.diskSerial, aliexStoreInfo.getStoreSign()), aliexStoreInfo);

        for (int i = 0; i < aliexStoreInfo.getTotalPage(); i++) {
            String keyPage = genKey(ComputerIdentifier.diskSerial, aliexStoreInfo.getStoreSign(), (i + 1));

            if (mapProducts.containsKey(keyPage) && mapProducts.get(keyPage) != null) {
                mapProducts.get(keyPage).clear();
            }
        }
    }

    public void processProduct(AliexProductFull aliexProductFull) {
        String keyStore = genKey(ComputerIdentifier.diskSerial, aliexProductFull.getStoreSign());

        AliexStoreInfo aliexStoreInfo1 = mapStoreInfo.get(keyStore);

        ArrayList<ProductAmz> listProductAmz = ProcessTransformAliexToAmz.transform(aliexProductFull, aliexStoreInfo1);

        if (aliexProductFull.getBranName() != null) {
            ArrayList<String> listBranchName = null;
            if (mapBrandName.containsKey(keyStore)) {
                listBranchName = mapBrandName.get(keyStore);
            } else {
                listBranchName = new ArrayList<>();
                mapBrandName.put(keyStore, listBranchName);
            }
            if (!listBranchName.contains(aliexProductFull.getBranName())) {
                listBranchName.add(aliexProductFull.getBranName());
            }
        }

        if (listProductAmz != null) {
            String key = genKey(ComputerIdentifier.diskSerial, aliexProductFull.getStoreSign(), aliexProductFull.getPageIndex());
            if (!mapProducts.containsKey(key)) {
                mapProducts.put(key, listProductAmz);
            } else {
                ArrayList<ProductAmz> list = mapProducts.get(key);
                list.addAll(listProductAmz);
            }
        } else {
            System.out.println("Can not get any product from id: " + aliexProductFull.getId());
        }
    }

    public void processPageInfo(AliexPageInfo aliexPageInfo) {
        String keyProduct = genKey(ComputerIdentifier.diskSerial, aliexPageInfo.getStoreSign(), aliexPageInfo.getPageIndex());
        ArrayList<ProductAmz> list = mapProducts.get(keyProduct);

        String keyStore1 = genKey(ComputerIdentifier.diskSerial, aliexPageInfo.getStoreSign());
        AliexStoreInfo aliexStoreInfo2 = mapStoreInfo.get(keyStore1);

        if (list != null) {
            if (mapBrandName.get(keyStore1) != null && !mapBrandName.get(keyStore1).isEmpty()) {
                for (ProductAmz productAmz : list) {
                    productAmz.removeBrandInfo(mapBrandName.get(keyStore1));
                }
            }

            ProcessPageDataSvs.processPageData(list, aliexStoreInfo2, aliexPageInfo.getPageIndex());
        }

        if (aliexPageInfo.getPageIndex() == aliexStoreInfo2.getTotalPage()) {

            ArrayList<ProductAmz> listAll = new ArrayList<>();

            for (int i = 1; i < aliexPageInfo.getPageIndex(); i++) {
                String keyProductPage = genKey(ComputerIdentifier.diskSerial, aliexPageInfo.getStoreSign(), i);
                ArrayList<ProductAmz> listProductsPage = mapProducts.get(keyProductPage);
                if (listProductsPage != null) {
                    listAll.addAll(listProductsPage);
                }
            }

            if (!listAll.isEmpty()) {
                ProcessPageDataSvs.processPageData(listAll, aliexStoreInfo2);
            }
        }
    }

    public static void processCrawlData(BaseIncomeRequestObject bio, ProcessInputLister processInputLister) {

        switch (bio.getAction()) {
            case Constants.TCPAction.STORE_INFO:
                AliexStoreInfo aliexStoreInfo = bio.getStoreInfo();
                mapStoreInfo.put(genKey(bio, aliexStoreInfo.getStoreSign()), aliexStoreInfo);

                for (int i = 0; i < aliexStoreInfo.getTotalPage(); i++) {
                    String keyPage = genKey(bio, aliexStoreInfo.getStoreSign(), (i + 1));

                    if (mapProducts.containsKey(keyPage) && mapProducts.get(keyPage) != null) {
                        mapProducts.get(keyPage).clear();
//                        mapProducts.remove(keyPage);
                    }
                }

//                System.out.println("AliexStoreInfo " + aliexStoreInfo.getMain_key());
                processInputLister.responseToClient(Constants.ResultCode.SUCCESS);
                break;

            case Constants.TCPAction.PRODUCT:
                AliexProductFull aliexProductFull = bio.getProduct();

//                System.out.println("Received " + aliexProductFull.getId());
                String keyStore = genKey(bio, aliexProductFull.getStoreSign());

                AliexStoreInfo aliexStoreInfo1 = mapStoreInfo.get(keyStore);

                ArrayList<ProductAmz> listProductAmz = ProcessTransformAliexToAmz.transform(aliexProductFull, aliexStoreInfo1);

                if (aliexProductFull.getBranName() != null) {
                    ArrayList<String> listBranchName = null;
                    if (mapBrandName.containsKey(keyStore)) {
                        listBranchName = mapBrandName.get(keyStore);
                    } else {
                        listBranchName = new ArrayList<>();
                        mapBrandName.put(keyStore, listBranchName);
                    }
                    if (!listBranchName.contains(aliexProductFull.getBranName())) {
                        listBranchName.add(aliexProductFull.getBranName());
                    }
                }

                if (listProductAmz != null) {
                    String key = genKey(bio, aliexProductFull.getStoreSign(), aliexProductFull.getPageIndex());
                    if (!mapProducts.containsKey(key)) {
                        mapProducts.put(key, listProductAmz);
                    } else {
                        ArrayList<ProductAmz> list = mapProducts.get(key);
                        list.addAll(listProductAmz);
                    }
                } else {
                    System.out.println("Can not get any product from id: " + aliexProductFull.getId());
                }
                processInputLister.responseToClient(Constants.ResultCode.SUCCESS);
                break;

            case Constants.TCPAction.PAGE_INFO:
                AliexPageInfo aliexPageInfo = bio.getStorePage();
                String keyProduct = genKey(bio, aliexPageInfo.getStoreSign(), aliexPageInfo.getPageIndex());
                ArrayList<ProductAmz> list = mapProducts.get(keyProduct);

                String keyStore1 = genKey(bio, aliexPageInfo.getStoreSign());
                AliexStoreInfo aliexStoreInfo2 = mapStoreInfo.get(keyStore1);

                if (list != null) {
                    if (mapBrandName.get(keyStore1) != null && !mapBrandName.get(keyStore1).isEmpty()) {
                        for (ProductAmz productAmz : list) {
                            productAmz.removeBrandInfo(mapBrandName.get(keyStore1));
                        }
                    }

                    ProcessPageDataSvs.processPageData(list, aliexStoreInfo2, aliexPageInfo.getPageIndex());
                }

                if (aliexPageInfo.getPageIndex() == aliexStoreInfo2.getTotalPage()) {

                    ArrayList<ProductAmz> listAll = new ArrayList<>();

                    for (int i = 1; i < aliexPageInfo.getPageIndex(); i++) {
                        String keyProductPage = genKey(bio, aliexPageInfo.getStoreSign(), i);
                        ArrayList<ProductAmz> listProductsPage = mapProducts.get(keyProductPage);
                        if (listProductsPage != null) {
                            listAll.addAll(listProductsPage);
                        }
                    }

                    if (!listAll.isEmpty()) {
                        ProcessPageDataSvs.processPageData(listAll, aliexStoreInfo2);
                    }
                }

                processInputLister.responseToClient(Constants.ResultCode.SUCCESS);

                break;
        }
    }

    public static String genKey(BaseIncomeRequestObject bio, String storeSign) {
        return bio.getDiskSerialNumber() + storeSign;
    }

    public static String genKey(String diskSerialNumber, String storeSign) {
        return diskSerialNumber + storeSign;
    }

    public static String genKey(BaseIncomeRequestObject bio, String storeSign, int page) {
        return bio.getDiskSerialNumber() + storeSign + "page" + page;
    }

    public static String genKey(String diskSerialNumber, String storeSign, int page) {
        return diskSerialNumber + storeSign + "page" + page;
    }

}
