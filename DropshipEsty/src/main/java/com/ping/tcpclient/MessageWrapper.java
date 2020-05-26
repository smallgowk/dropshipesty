/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ping.tcpclient;

import com.utils.AppUtil;
import com.utils.ComputerIdentifier;
import com.utils.Constants;
import com.utils.StringUtils;

/**
 *
 * @author duyuno
 */
public class MessageWrapper {
    
    public static String wrapStoreData(String market, int dataType, String data) {
        if (StringUtils.isEmpty(data)) {
            System.out.println("Data is empty!");
            return null;
        }
        
//        try {
//            System.out.println("Clean data: " + data);
//            data = EncryptUtil.encrypt(data);
//            System.out.println("Encrypt data: " + data);
//        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
//            Logger.getLogger(MessageWrapper.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        if (StringUtils.isEmpty(data)) {
//            System.out.println("Data is error!");
//            return null;
//        }

        if (StringUtils.isEmpty(market)) {
            System.out.println("Market is empty!");
            return null;
        }
        StringBuilder sb = new StringBuilder();
        apppendByte(Constants.FrameHelper.PACKAGE_START_MARK, sb);
        
        sb.append(Constants.TCPAction.CRAWL_DATA);//0
        sb.append(Constants.FrameHelper.SPLIT);
        sb.append(ComputerIdentifier.getDiskSerialNumber());//1
        sb.append(Constants.FrameHelper.SPLIT);
        sb.append(AppUtil.getAppVersion());//2
        sb.append(Constants.FrameHelper.SPLIT);
        sb.append(Constants.FrameHelper.SPLIT);
        sb.append(market);//4
        sb.append(Constants.FrameHelper.SPLIT);
        sb.append(data);//5
        sb.append(Constants.FrameHelper.SPLIT);
        sb.append(dataType);//6
        apppendByte(Constants.FrameHelper.PACKAGE_END_MARK, sb);
        return sb.toString();
    }
    
    public static void apppendByte(byte[] data, StringBuilder sb) {
        for(int i = 0, length = data.length; i < length; i++) {
            sb.append((char)(data[i]));
        }
    }
    
    public static String wrapAuthenData(String userEmail) {
        
        if (StringUtils.isEmpty(userEmail)) {
            System.out.println("Email is empty!");
            return null;
        }
        
        StringBuilder sb = new StringBuilder();
        apppendByte(Constants.FrameHelper.PACKAGE_START_MARK, sb);
//        sb.append(Constants.TCPAction.AUTHEN);
//        sb.append(Constants.FrameHelper.SPLIT);
//        sb.append(ComputerIdentifier.getDiskSerialNumber());
//        sb.append(Constants.FrameHelper.SPLIT);
//        sb.append(AppUtil.getAppVersion());
//        sb.append(Constants.FrameHelper.SPLIT);
//        sb.append(Configs.userEmail);
//        apppendByte(Constants.FrameHelper.PACKAGE_END_MARK, sb);
        return sb.toString();
    }
}
