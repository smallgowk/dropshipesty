/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ping.service.crawl;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PhanDuy
 */
public class DownloadMachine {
    
    public static void download(String imageUrl, String targetFilePath) {
        InputStream in = null;
        try {
            in = new URL(imageUrl).openStream();
            Files.copy(in, Paths.get(targetFilePath), StandardCopyOption.REPLACE_EXISTING);
        } catch (MalformedURLException ex) {
            Logger.getLogger(DownloadMachine.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DownloadMachine.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ex) {
                    Logger.getLogger(DownloadMachine.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }
}
