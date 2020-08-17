/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ping.control;

import com.config.Configs;
import com.models.amazon.ProductAmz;
import com.pong.server.process.CrawlPageServerThread;
import com.utils.ExcelUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

/**
 *
 * @author PhanDuy
 */
public class EditController {

    public String imageFolder;
    public String excelPath;
    public String ip;

    public String getImageFolder() {
        return imageFolder;
    }

    public void setImageFolder(String imageFolder) {
        this.imageFolder = imageFolder;
    }

    public String getExcelPath() {
        return excelPath;
    }

    public void setExcelPath(String excelPath) {
        this.excelPath = excelPath;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public ArrayList<ProductAmz> readExcelData() {
        try {
            return ExcelUtils.getProductInfos(excelPath, "Template");
        } catch (IOException | InvalidFormatException ex) {
            Logger.getLogger(EditController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public HashSet<String> readImageFolder(File folder) {
        HashSet<String> hashSet = new HashSet<>();
        if (folder.exists()) {
            String[] paths = folder.list();
            for (String s : paths) {
                File image = new File(s);
                String name = image.getName();
                System.out.println("Image name: " + name);
                hashSet.add(name);
            }
        }
        return hashSet;
    }

    public void editData() {
        File folder = new File(imageFolder);
        File excelFile = new File(excelPath);
        String folderName = folder.getName();
        String fileName = excelFile.getName();

        int dotIndex = fileName.lastIndexOf(".");

        if (dotIndex < 0) {
            System.out.println("Invalid: " + fileName);
            return;
        }

        fileName = fileName.substring(0, dotIndex).trim().toLowerCase();

        ArrayList<ProductAmz> listProducts = readExcelData();
        HashSet<String> hashImages = readImageFolder(folder);

        if (CollectionUtils.isNotEmpty(listProducts) && CollectionUtils.isNotEmpty(hashImages)) {
            for (int size = listProducts.size(), i = size - 1; i >= 0; i--) {
                ProductAmz productAmz = listProducts.get(i);
                if (!hashImages.contains(productAmz.getItem_sku() + ".jpg")) {
                    listProducts.remove(i);
                } else {
                    productAmz.setMain_image_url(genMainUrlFromIp(ip, folderName, productAmz.getItem_sku() + ".jpg"));
                }
            }
        }

        try {
            ExcelUtils.saveListProductsToExcel(listProducts, fileName + "_editted.xlsx", Configs.excelSampleFilePath);
        } catch (EncryptedDocumentException | InvalidFormatException | IOException ex) {
            java.util.logging.Logger.getLogger(CrawlPageServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String genMainUrlFromIp(String ip, String vpsFolder, String imageName) {
        return "http://" + ip + "/" + vpsFolder + "/" + imageName;
    }
}
