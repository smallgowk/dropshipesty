/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ping.control;

import com.ping.tcpclient.ResponseObj;


/**
 *
 * @author PhanDuy
 */
public interface CrawlProcessListener {
    public void onPushState(String storeSign, String state);
    public void onProgress(String percent);
    public void onPushErrorRequest(String storeSign, ResponseObj responseObj);
    public void onStartProcess(String storeSign, String accNo);
    public void onStop(String storeSign);
    public void onStopToLogin(String currentUrl, String storeSign);
    public void onFinishPage(String storeSign);
}
