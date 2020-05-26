/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ping.tcpclient;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author duyuno
 */
public class TestClient {

    public static void main(String[] str) throws Exception {

//        Socket clientSocket = new Socket("localhost", 9669);
//
//        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
//        AliexStoreCommon aliexStoreCache = Utils.getStoreCache("2784019_510936079");
//
//        Gson gson = new Gson();
//        String data = gson.toJson(aliexStoreCache);
//        String dataEncerupt = EncryptUtil.encrypt(data);
////        String dataCommon = "[START]" + gson.toJson(aliexStoreCache) + "[END]";
//        String dataCommon = "[" + dataEncerupt + "]";
//        
////        sendMessage(dataCommon);
//        
//        outToServer.writeBytes(dataCommon);
//
//        AliexPageInfo aliexPageInfo = Utils.getStorePageCache("2784019_510936079", 1);
//
//        data = gson.toJson(aliexPageInfo);
//        dataEncerupt = EncryptUtil.encrypt(data);
//        
////        String dataPage = "[START]" + gson.toJson(aliexPageInfo)+ "[END]";
//        String dataPage = "[" + dataEncerupt+ "]";
////        sendMessage(dataPage);
//        outToServer.writeBytes(dataPage);
//
//        outToServer.close();
//        clientSocket.close();

//        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
//        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//
//        AliexStoreCommon aliexStoreCache = Utils.getStoreCache("/Users/duyuno/Bitbucket/DropShippingTool/ServiceTool/Cache/StoreInfoCache/2784019_510936079/2784019_510936079.txt");
//
//        Gson gson = new Gson();
//        String dataCommon = gson.toJson(aliexStoreCache);
//
//        out.print("[START]" + dataCommon + "[END]");
//
//        AliexPageInfo aliexPageInfo = Utils.getStorePageCache("/Users/duyuno/Bitbucket/DropShippingTool/ServiceTool/Cache/StoreInfoCache/2784019_510936079/2784019_510936079_page1.txt");
//
//        String dataPage = gson.toJson(aliexPageInfo);
//
//        out.print("[START]" + dataPage + "[END]");
//
//        in.close();
//        out.close();
//        clientSocket.close();

    }
    
    public static void sendMessage(String message) throws IOException {
        Socket clientSocket = new Socket("localhost", 9669);

        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        outToServer.writeBytes(message);

        outToServer.close();
        clientSocket.close();
    }
}
