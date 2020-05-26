/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pong.server.http;


/**
 *
 * @author duyuno
 */
public class HttpServerWrapper {
    
    public void start() {
        
        
//        Configs.init();
//        AliexParameterFactory.initClientId();
        
        HttpSnoopServer httpSnoopServer = new HttpSnoopServer();
        httpSnoopServer.init(); 
    }
    

}
