/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pong.server.process;

import com.api.aliex.AliexApiCall;
import com.api.aliex.AliexParseUtil;
import com.models.aliex.AliexOriginalInfo;
import com.api.aliex.response.AliexProductDescriptionRes;
import com.models.aliex.AliexProductDetail;
import com.api.aliex.response.AliexProductShipResponse;
import com.api.aliex.response.AliexProductVariationResponse;
import com.utils.Utils;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class GetAliexItemInfoSVThread {

    public boolean isComplete;
    public String aliexId;
    public String folderPath;
    public boolean isFetchedAmz;

    public GetAliexItemInfoSVThread(String id, String folderPath) {
        this.aliexId = id;
        this.folderPath = folderPath;
    }

    public String getAliexId() {
        return aliexId;
    }

    public void setAliexId(String aliexId) {
        this.aliexId = aliexId;
    }

    public String getFolderPath() {
        return folderPath;
    }

    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath;
    }

    public boolean isIsFetchedAmz() {
        return isFetchedAmz;
    }

    public void setIsFetchedAmz(boolean isFetchedAmz) {
        this.isFetchedAmz = isFetchedAmz;
    }

//    @Override
    public void run() {
        try {
            if (aliexId.equals("32272602093")) {
                System.out.println("DM");
            }

            AliexOriginalInfo aliexOriginalInfo = Utils.getAliexBasicInfoCache(folderPath, aliexId);
            if (aliexOriginalInfo == null) {
                aliexOriginalInfo = new AliexOriginalInfo();
                aliexOriginalInfo.setId(aliexId);

                String detail = AliexApiCall.getProductDetail(aliexId, null);
                AliexProductDetail aliexProductDetail = AliexParseUtil.parseProductDetailResponse(detail);
                if (aliexProductDetail != null) {
                    aliexOriginalInfo.setDetail(aliexProductDetail);
                } else {
                    System.out.println("No Detail for " + aliexId);
                    isComplete = true;
                    return;
                }

                // Getting variations
                String variation = AliexApiCall.getProductVariation(aliexId, null);
                AliexProductVariationResponse aliexProductVariationResponse = AliexParseUtil.parseProductVariationResponse(variation);

                if (aliexProductVariationResponse != null) {
                    aliexOriginalInfo.setVariation(aliexProductVariationResponse.getVariations());
                } else {
                    isComplete = true;
                    return;
                }

                // Getting description html
                String description = AliexApiCall.getProductDescription(aliexId, null);
                AliexProductDescriptionRes aliexProductDescriptionRes = AliexParseUtil.parseProductDesResponse(description);
                if (aliexProductDescriptionRes != null) {
                    aliexOriginalInfo.setDescriptionHtml(aliexProductDescriptionRes.getDescription());
                } else {
                    isComplete = true;
                    return;
                }

                // Getting shipping price
                String shippingResponse = AliexApiCall.getProductShipping(aliexId, null);
                AliexProductShipResponse aliexProductShipResponse = AliexParseUtil.parseProductShipResponse(shippingResponse);
                if (aliexProductShipResponse == null || aliexProductShipResponse.getShippingPrice() < 0) {
                    aliexOriginalInfo.setShippingPrice(-1);
                } else {
                    float shippingCost = aliexProductShipResponse.getShippingPrice();
                    aliexOriginalInfo.setShippingPrice(shippingCost);
                }

                try {
                    Utils.saveProductAliexToCache(folderPath, aliexOriginalInfo);
                } catch (IOException ex) {
                    Logger.getLogger(GetAliexItemInfoSVThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {

                if (aliexOriginalInfo.getShippingPrice() == 0 && aliexOriginalInfo.getStatus() == null) {
                    String shippingResponse = AliexApiCall.getProductShipping(aliexId, null);
                    AliexProductShipResponse aliexProductShipResponse = AliexParseUtil.parseProductShipResponse(shippingResponse);
                    if (aliexProductShipResponse == null || aliexProductShipResponse.getShippingPrice() < 0) {
                        aliexOriginalInfo.setShippingPrice(-1);
                        aliexOriginalInfo.setStatus("DONE");
                    } else {
                        float shippingCost = aliexProductShipResponse.getShippingPrice();
                        aliexOriginalInfo.setShippingPrice(shippingCost);
                        aliexOriginalInfo.setStatus("DONE");
                    }

                    try {
                        Utils.saveProductAliexToCache(folderPath, aliexOriginalInfo);
                    } catch (IOException ex) {
                        Logger.getLogger(GetAliexItemInfoSVThread.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            }
        } catch (Exception ex) {
             ex.printStackTrace();
        }

        isComplete = true;
    }

    public boolean isIsComplete() {
        return isComplete;
    }

}
