/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.api.dropship;

import com.api.ApiBase;
import com.interfaces.CallApiInterface;
import com.ping.service.data.transform.TransformToServer;
import com.ping.tcpclient.ResponseObj;
import org.apache.http.entity.StringEntity;

/**
 *
 * @author PhanDuy
 */
public class DropApiCall {
    
    
//    public static ResponseObj doSendStoreInfo(AliexStoreInfoWrap aliexStoreInfoWrap, CallApiInterface callApiInterface) {
//        String url = DropUrlFactory.createDropUrl();
//        StringEntity stringEntity = DropParameterFactory.createSendStoreInfoEntity(aliexStoreInfoWrap);
//        String response = ApiBase.getInstance().sendPostStringEntity(url, stringEntity, null, callApiInterface);
//        return DropParseUtil.parseRespone(response);
//    }
    
//    public static ResponseObj doSendPageInfo(AliexStorePageWrap aliexStorePageWrap, CallApiInterface callApiInterface) {
//        String url = DropUrlFactory.createDropUrl();
//        StringEntity stringEntity = DropParameterFactory.createSendPageInfoEntity(aliexStorePageWrap);
//        String response =  ApiBase.getInstance().sendPostStringEntity(url, stringEntity, null, callApiInterface);
//        return DropParseUtil.parseRespone(response);
//    }
    
//    public static ResponseObj doSendProductInfo(AliexProductInfoWrap aliexProductInfoWrap, CallApiInterface callApiInterface) {
//        String url = DropUrlFactory.createDropUrl();
//        StringEntity stringEntity = DropParameterFactory.createSendProductInfoEntity(aliexProductInfoWrap);
//        String response =  ApiBase.getInstance().sendPostStringEntity(url, stringEntity, null, callApiInterface);
//        return DropParseUtil.parseRespone(response);
//    }
    
    public static ResponseObj doSendGetInfo(CallApiInterface callApiInterface) {
        String url = DropUrlFactory.createAuthenUrl();
        StringEntity stringEntity = DropParameterFactory.createGetAccountInfoReq(TransformToServer.getInstance().getAccountInfoWrap());
        String response =  ApiBase.getInstance().sendPostStringEntity(url, stringEntity, null, callApiInterface);
        return DropParseUtil.parseRespone(response);
    }
    
    public static ResponseObj doSendGetCert(CallApiInterface callApiInterface) {
        String url = DropUrlFactory.createAuthenUrl();
        StringEntity stringEntity = DropParameterFactory.createGetAccountInfoReq(TransformToServer.getInstance().getCertWrap());
        String response =  ApiBase.getInstance().sendPostStringEntity(url, stringEntity, null, callApiInterface);
        return DropParseUtil.parseRespone(response);
    }
    
    public static ResponseObj doSendUserInfo(String type, String data, int hash, CallApiInterface callApiInterface) {
        String url = DropUrlFactory.createAuthenUrl();
        StringEntity stringEntity = DropParameterFactory.createSendUserInfoEntity(type, data, hash);
        String response =  ApiBase.getInstance().sendPostStringEntity(url, stringEntity, null, callApiInterface);
        return DropParseUtil.parseRespone(response);
    }
    
//    public static ResponseObj doSendUserInfo(BaseStoreOrderInfo listOrders, CallApiInterface callApiInterface) {
//        String url = DropUrlFactory.createAuthenUrl();
//        StringEntity stringEntity = DropParameterFactory.createSendUserInfoEntity(listOrders);
//        String response =  ApiBase.getInstance().sendPostStringEntity(url, stringEntity, null, callApiInterface);
//        return DropParseUtil.parseRespone(response);
//    }
    
    public static ResponseObj doSendUpdateInfo(String message, CallApiInterface callApiInterface) {
        String url = DropUrlFactory.createAuthenUrl();
        StringEntity stringEntity = DropParameterFactory.createGetAccountInfoReq(TransformToServer.getInstance().updateInfoWrap(message));
        String response =  ApiBase.getInstance().sendPostStringEntity(url, stringEntity, null, callApiInterface);
        return DropParseUtil.parseRespone(response);
    }
}
