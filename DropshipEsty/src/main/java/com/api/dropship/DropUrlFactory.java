/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.api.dropship;

import com.api.WebService;
import static com.api.WebService.*;
import com.config.AppConfig;

/**
 *
 * @author PhanDuy
 */
public class DropUrlFactory {
//    public static String createDropUrl() {
//        return WebService.PONG_IP + ":" + AppConfig.port;
//    }
    
    public static String createAuthenUrl() {
//        return "http://localhost:69";
        return AUTHEN_URL;
    }
    
    public static String createContentUrl() {
        return SERVER_IP + ":" + AUTHEN_PORT;
    }
            
}
