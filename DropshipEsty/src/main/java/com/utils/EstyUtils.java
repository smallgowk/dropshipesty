/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utils;

import com.config.Configs;
import static com.config.Configs.CONFIG_FOLDER_PATH;
import static com.config.Configs.changeConfigValues;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PhanDuy
 */
public class EstyUtils {
    
    private static final String DESCRIPTION_FILE = "esty_description.txt";
    private static final String BULLET_POINTS_FILE = "esty_bullet_points.txt";
    private static final String IMAGE_URL_FILE = "esty_image_url.txt";
    
    private static final String ESTY_CONFIG_FILE = "esty_config.conf";
    
    public static String configPath;
    
    public static String storeLink;
    public static String imageFolder;
    public static String brand;
    public static String price;
    public static String skuPrefix;
    
    public static String description;
    public static String bulletPoints;
    public static String imageUrls;
    
    public static void init() {
        
        configPath = CONFIG_FOLDER_PATH + Configs.pathChar + ESTY_CONFIG_FILE;
        
        File file = new File(configPath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(EstyUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        description = readData(CONFIG_FOLDER_PATH + Configs.pathChar + DESCRIPTION_FILE);
        bulletPoints = readData(CONFIG_FOLDER_PATH + Configs.pathChar + BULLET_POINTS_FILE);
        imageUrls = readData(CONFIG_FOLDER_PATH + Configs.pathChar + IMAGE_URL_FILE);
        
        loadContentConfig();
    }
    
    public static void loadContentConfig() {
        Properties cnfParamsTmp = new Properties();
        FileInputStream propsFile = null;
        try {
            propsFile = new FileInputStream(CONFIG_FOLDER_PATH + Configs.pathChar + ESTY_CONFIG_FILE);
            cnfParamsTmp.load(propsFile);

            storeLink = cnfParamsTmp.getProperty("storeLink", "");
            imageFolder = cnfParamsTmp.getProperty("imageFolder", "");
            brand = cnfParamsTmp.getProperty("brand", "");
            skuPrefix = cnfParamsTmp.getProperty("skuPrefix", "");
            
            String priceStr = cnfParamsTmp.getProperty("price", "");
            if (!priceStr.isEmpty()) {
                try {
                    Float.parseFloat(priceStr);
                    price = priceStr;
                } catch (NumberFormatException ex) {
                    price = "";
                }
            } else {
                price = "";
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Configs.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Configs.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (propsFile != null) {
                try {
                    propsFile.close();
                } catch (IOException e) {
                    // thong bao dong file loi
                }
            }
        }
    }
    
    public static String readData(String fileName) {
        BufferedReader br = null;
        StringBuilder sb = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            InputStreamReader clientSecretReader = new InputStreamReader(fileInputStream);

            br = new BufferedReader(clientSecretReader);

            String st;
            while ((st = br.readLine()) != null) {
                if (st != null && !st.isEmpty()) {
                    if (sb == null) {
                        sb = new StringBuilder();
                    }

                    if (sb.length() == 0) {
                        sb.append(st);
                    }
                }
            }
        } catch (IOException ex) {

        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException ex) {
                    Logger.getLogger(AWSUtil.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        return sb != null ? sb.toString() : "";
    }
    
    public static void changeStoreLink(String link) throws IOException {
        changeConfigValues(configPath, new String[]{"storeLink", link});
        storeLink = link;
    }
    
    public static void changeImageFolder(String folder) throws IOException {
        changeConfigValues(configPath, new String[]{"imageFolder", folder});
        imageFolder = folder;
    }
    
    public static void changeBrand(String brandStr) throws IOException {
        changeConfigValues(configPath, new String[]{"brand", brandStr});
        brand = brandStr;
    }
    
    
    public static void changePrefix(String prefixStr) throws IOException {
        changeConfigValues(configPath, new String[]{"skuPrefix", prefixStr});
        skuPrefix = prefixStr;
    }
    
    public static void changePrice(String priceStr) throws IOException {
        changeConfigValues(configPath, new String[]{"price", priceStr});
        price = priceStr;
    }
    
    public static void changeDescription(String description) {
        
    }
    
    public static void changeBulletPoints(String bullerPoints) {
        
    }
    
    public static void changeImageUrls(String imageUrls) {
        
    }
}
