/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pong.control;

import com.config.Configs;
import com.models.amazon.ProductAmz;
import com.models.aliex.store.AliexStoreInfo;
import com.models.esty.store.EstyPageInfo;
import com.pong.server.process.CrawlPageServerThread;
import com.pong.server.obj.income.BaseIncomeRequestObject;
import com.utils.ExcelUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

/**
 *
 * @author PhanDuy
 */
public class ProcessPageDataSvs {

    public static void processPageData(ArrayList<ProductAmz> listProducts, AliexStoreInfo aliexStoreInfo, int pageIndex) {
        try {
            String fileName = aliexStoreInfo.genExcelFileNameWithPage(pageIndex);
            ExcelUtils.saveListProductsToExcel(listProducts, fileName, Configs.excelSampleFilePath);
            
        } catch (EncryptedDocumentException | InvalidFormatException | IOException ex) {
            Logger.getLogger(CrawlPageServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void processPageData(ArrayList<ProductAmz> listProducts, AliexStoreInfo aliexStoreInfo) {
        try {
            String fileName = aliexStoreInfo.genExcelFileNameForStore();
            ExcelUtils.saveListProductsToExcel(listProducts, fileName, Configs.excelSampleFilePath);
            
        } catch (EncryptedDocumentException | InvalidFormatException | IOException ex) {
            Logger.getLogger(CrawlPageServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void processPageData(ArrayList<ProductAmz> listProducts, String fileName) {
        try {
            ExcelUtils.saveListProductsToExcel(listProducts, fileName, Configs.excelSampleFilePath);
        } catch (EncryptedDocumentException | InvalidFormatException | IOException ex) {
            Logger.getLogger(CrawlPageServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
