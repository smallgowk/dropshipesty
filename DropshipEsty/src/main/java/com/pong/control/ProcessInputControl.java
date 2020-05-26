/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pong.control;

import com.pong.server.obj.income.BaseIncomeRequestObject;

/**
 *
 * @author PhanDuy
 */
public class ProcessInputControl {

    public static void processInput(BaseIncomeRequestObject bio, ProcessInputLister processInputLister) {
        
        if(bio == null || bio.getAction() == null) return;
        
        ProcessStoreInfoSvs.processCrawlData(bio, processInputLister);
    }

}
