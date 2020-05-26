/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pong.server.obj.income;

import com.pong.authen.Account;
import com.models.aliex.AliexProductFull;
import com.models.aliex.store.AliexStoreCommon;
import com.models.aliex.store.AliexPageInfo;
import com.models.aliex.store.AliexStoreInfo;
import com.utils.Constants;


/**
 *
 * @author taipa
 */
public class BaseIncomeRequestObject {

    private String action;
    private String version;
    private String diskSerialNumber;
    private String computerName;
    private String userEmail;
    private String license;
    private String data;
    private int dataType;
    
    private AliexStoreCommon storeCommon;
    private AliexPageInfo pageInfo;
    private AliexStoreInfo storeInfo;
    private AliexProductFull product;
    private Account account;
    
    public String genKey() {
        return diskSerialNumber;
    }
    
    public boolean isAuthen() {
        return action.equals(Constants.TCPAction.CHECK_LICENSE)
                || action.equals(Constants.TCPAction.UPDATE_LICENSE)
                || action.equals(Constants.TCPAction.GET_ACCOUNT)
                || action.equals(Constants.TCPAction.SIGNUP);
    }

    public AliexPageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(AliexPageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public BaseIncomeRequestObject() {
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDiskSerialNumber() {
        return diskSerialNumber;
    }

    public void setDiskSerialNumber(String diskSerialNumber) {
        this.diskSerialNumber = diskSerialNumber;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getAction() {
        return action;
    }

    public AliexStoreInfo getStoreInfo() {
        return storeInfo;
    }

    public void setStoreInfo(AliexStoreInfo storeInfo) {
        this.storeInfo = storeInfo;
    }

    public AliexProductFull getProduct() {
        return product;
    }

    public void setProduct(AliexProductFull product) {
        this.product = product;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    
    
    
//    public String getUserName() {
//        
//        if(account != null) {
//            return account.getUsername();
//        }
//        
//        return null;
//    }
//    
//    public String getEmail() {
//        
//        if(account != null) {
//            return account.getEmail();
//        }
//        
//        return null;
//    }
//    
//    public String getPassword() {
//        
//        if(account != null) {
//            return account.getPassword();
//        }
//        
//        return null;
//    }

    public void setAction(String action) {
        this.action = action;
    }

    public AliexStoreCommon getStoreCommon() {
        return storeCommon;
    }

    public void setStoreCommon(AliexStoreCommon storeCommon) {
        this.storeCommon = storeCommon;
    }

    public AliexPageInfo getStorePage() {
        return pageInfo;
    }

    public void setStorePage(AliexPageInfo storePage) {
        this.pageInfo = storePage;
    }

//    public Account getAccount() {
//        return account;
//    }
//
//    public void setAccount(Account account) {
//        this.account = account;
//    }
//
//    public Account getSignInObj() {
//        return account;
//    }
//
//    public void setSignInObj(Account signInObj) {
//        this.account = signInObj;
//    }
    
    

//    public String getEncryptData() {
//        return encryptData;
//    }
//
//    public void setEncryptData(String encryptData) {
//        this.encryptData = encryptData;
//    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }
    
    

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public String getComputerName() {
        return computerName;
    }

    public void setComputerName(String computerName) {
        this.computerName = computerName;
    }
    
    
}
