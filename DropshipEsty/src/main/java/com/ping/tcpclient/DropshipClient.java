/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ping.tcpclient;


/**
 *
 * @author duyuno
 */
public class DropshipClient {

//    public Socket clientSocket;
//    public DataOutputStream outToServer;

    public DropshipClient() {

    }

//    public void initConnection() {
//        try {
//            clientSocket = new Socket("localhost", 9669);
//            outToServer = new DataOutputStream(clientSocket.getOutputStream());
//            System.out.println("Connect success!!");
//        } catch (IOException ex) {
//            Logger.getLogger(DropshipClient.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

//    public String sendAliexStoreCommon(AliexStoreCommon aliexStoreCommon) {
//        return DropApiCall.doSendAliexStoreCommon(aliexStoreCommon, null);
//    }
    
//    public void sendAliexStoreCommon(String data) {
////        Gson gson = new Gson();
////        String data = gson.toJson(aliexStoreCommon);
//        sendMessage(data, Constants.DataType.STORE_COMMON, Constants.MarketType.ALIEX);
//    }

//    public void sendAliexStorePage(String data) {
////        Gson gson = new Gson();
////        String data = gson.toJson(aliexPageInfo);
//        sendMessage(data, Constants.DataType.STORE_PAGE, Constants.MarketType.ALIEX);
//    }
    
//    public String sendAliexStorePage(AliexPageInfo aliexPageInfo) {
//        return DropApiCall.doSendAliexStorePage(aliexPageInfo, null);
//    }

//    public void sendMessage(String data, int dataType, String market) {
//        String pagekace = MessageWrapper.wrapStoreData(market, dataType, data);
//        
//        if(pagekace != null) {
//            try {
//                outToServer.writeBytes(pagekace);
//            } catch (IOException ex) {
//                Logger.getLogger(DropshipClient.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        
//    }
//
//    public void closeConnection() {
//
//        try {
//            if (outToServer != null) {
//                outToServer.close();
//            }
//
//            if (clientSocket != null) {
//                clientSocket.close();
//            }
//            System.out.println("Close success!!");
//        } catch (IOException ex) {
//            Logger.getLogger(DropshipClient.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
}
