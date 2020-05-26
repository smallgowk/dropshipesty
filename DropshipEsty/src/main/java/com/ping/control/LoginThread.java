/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ping.control;

import com.ping.service.crawl.aliex.AliexCrawlSvs;

/**
 *
 * @author PhanDuy
 */
public class LoginThread extends Thread{

    boolean isLogoutBefore;
    String redirectLink;

    public LoginThread(boolean isLogoutBefore, String redirectLink) {
        this.isLogoutBefore = isLogoutBefore;
        this.redirectLink = redirectLink;
    }
    
    
    @Override
    public void run() {
        if(isLogoutBefore) {
            AliexCrawlSvs.getInstance().logout();
        }
        
        AliexCrawlSvs.getInstance().autoLoginAliex(redirectLink);
    }
    
    
}
