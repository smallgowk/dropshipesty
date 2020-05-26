/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ping.service.fetchinfo.aliex;

import com.google.gson.Gson;
import com.api.aliex.AliexApiCall;
import com.api.aliex.response.AliexProductFullResponse;
import com.config.Configs;
import com.models.aliex.AliexProductFull;
import com.utils.EncryptUtil;
import com.utils.StringUtils;
import com.utils.Utils;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author PhanDuy
 */
public class FetchAliexProductInfoSvs {

    private static FetchAliexProductInfoSvs fetchAliexProductInfoSvs;

    public static FetchAliexProductInfoSvs getInstance() {
        if (fetchAliexProductInfoSvs == null) {
            fetchAliexProductInfoSvs = new FetchAliexProductInfoSvs();
        }
        return fetchAliexProductInfoSvs;
    }
    
    public AliexProductFull getProductFromCache(String id, String storeSign) {
        String filePath = Configs.CACHE_PATH + Configs.PRODUCT_CACHE_DIR + Configs.pathChar + storeSign + Configs.pathChar + id + ".txt";
        String cacheData = null;

        File cache = new File(filePath);

        try {
            if (cache.exists()) {
                cacheData = FileUtils.readFileToString(cache);
            }

        } catch (IOException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (cacheData != null) {
            String cleanData = EncryptUtil.decrypt(cacheData);
            Gson gson = new Gson();
            return gson.fromJson(cleanData, AliexProductFull.class);
        }
        
        return null;
    }

//    public AliexProductFull getProductInfo(String id, String storeSign) {
//        
//        AliexProductFull aliexProductFull = null;
//
//        AliexProductFullResponse aliexProductFullResponse = AliexApiCall.getProductFullInfo(id);
//
//        if (aliexProductFullResponse != null && !StringUtils.isEmpty(aliexProductFullResponse.getHtmlDescription())) {
//            aliexProductFull = new AliexProductFull();
//            aliexProductFull.setDataApi(aliexProductFullResponse);
//            saveProductInfo(aliexProductFull, storeSign);
//        }
//
//        return aliexProductFull;
//    }

    public void saveProductInfo(AliexProductFull aliexProductFull, String storeSign) {
        Gson gson = new Gson();
        String data = gson.toJson(aliexProductFull);
        String encrytData = EncryptUtil.encrypt(data);

        File file = new File(Configs.CACHE_PATH + Configs.PRODUCT_CACHE_DIR + Configs.pathChar + storeSign);
        
        
//        System.out.println("" + file.getAbsolutePath());

        if (!file.exists()) {
            file.mkdir();
        }

        try {
            FileUtils.writeStringToFile(new File(file.getAbsolutePath() + Configs.pathChar + aliexProductFull.getId() + ".txt"), encrytData);
            
//            System.out.println("" + aliexProductFull.id + " cached success to ");
            
        } catch (IOException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
