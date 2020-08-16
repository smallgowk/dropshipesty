/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models.aliex.store.inputdata;

import com.config.Configs;
import com.utils.StringUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Set;

/**
 *
 * @author duyuno
 */
public class SnakeBaseStoreOrderInfo extends BaseStoreOrderInfo {

    public String main_key;
    public String tip;
    public String reasons;
    public String description;
    public ArrayList<String> links;
    public String itemType;
    public String department;
    public String outerMaterialType;
    public String materialComposition;
//    public Set<String> listColor;
//    public Set<String> listSizes;
    public float basePrice;
//    public ArrayList<EstyVariation> listVariation;
    ArrayList<String> listBullets;
    
    public String imageFolder;
    public String imageFolderName;
    public String ip;

    public static SnakeBaseStoreOrderInfo createInstance(String linkStore, String ip, String imageFolder, String brandName,
            Set<String> listColor, Set<String> listSizes, 
            String category, String description, 
            ArrayList<String> links, 
            float basePrice,
            ArrayList<String> bullets,
            String outerMaterialType,
            String materialComposition, 
            String skuPrefix
            ) {
        SnakeBaseStoreOrderInfo snakeBaseStoreOrderInfo = new SnakeBaseStoreOrderInfo();
        snakeBaseStoreOrderInfo.setLink(linkStore);
        snakeBaseStoreOrderInfo.setIp(ip);
        snakeBaseStoreOrderInfo.setImageFolder(imageFolder);
        snakeBaseStoreOrderInfo.setBrand_name(brandName);
        snakeBaseStoreOrderInfo.setDescription(description);
//        snakeBaseStoreOrderInfo.setListColor(listColor);
//        snakeBaseStoreOrderInfo.setListSizes(listSizes);
        snakeBaseStoreOrderInfo.setCategory(category);
        snakeBaseStoreOrderInfo.setLinks(links);
        snakeBaseStoreOrderInfo.setBasePrice(basePrice);
//        snakeBaseStoreOrderInfo.genListVariation();
        snakeBaseStoreOrderInfo.setItemType(Configs.hashMapCateType.get(category));
        snakeBaseStoreOrderInfo.setDepartment(Configs.hashMapDepartmentName.get(category));
        snakeBaseStoreOrderInfo.setListBullets(bullets);
        snakeBaseStoreOrderInfo.setOuterMaterialType(outerMaterialType);
        snakeBaseStoreOrderInfo.setMaterialComposition(materialComposition);
        snakeBaseStoreOrderInfo.setPrefix(skuPrefix);
        
        return snakeBaseStoreOrderInfo;
    }
    
    public boolean isEtsy() {
        return StringUtils.isEmpty(ip);
    }

    public String getImageFolder() {
        return imageFolder;
    }

    public void setImageFolder(String imageFolder) {
        
        if(imageFolder == null) return;
        
        this.imageFolder = imageFolder;
        
        File file = new File(imageFolder);
        if(file.exists()) {
            imageFolderName = file.getName();
        }
    }

    public String getImageFolderName() {
        return imageFolderName;
    }
    
    public String genMainUrlFromIp(String vpsFolder, String imageName) {
        return "http://" + ip + "/" + vpsFolder + "/" + imageName;
    }
    
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public ArrayList<String> getLinks() {
        return links;
    }

    public void setLinks(ArrayList<String> links) {
        this.links = links;
    }

    public void setListBullets(ArrayList<String> listBullets) {
        this.listBullets = listBullets;
    }
    
    public ArrayList<String> getListBullets() {
        return listBullets;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

//    public ArrayList<EstyVariation> getListVariation() {
//        return listVariation;
//    }
//
//    public void setListVariation(ArrayList<EstyVariation> listVariation) {
//        this.listVariation = listVariation;
//    }
//    
//    
//    
//    public void genListVariation() {
//        if (listColor != null && listSizes != null) {
//            listVariation = new ArrayList<>();
//            for (String color : listColor) {
//                for (String size : listSizes) {
//                    float addPrice = Configs.hashMapSizePrice.containsKey(size) ? Configs.hashMapSizePrice.get(size) : 0;
//                    listVariation.add(new EstyVariation(color, size, basePrice + addPrice));
//                }
//            }
//        }
//    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getOuterMaterialType() {
        return outerMaterialType;
    }

    public void setOuterMaterialType(String outerMaterialType) {
        this.outerMaterialType = outerMaterialType;
    }

    public String getMaterialComposition() {
        return materialComposition;
    }

    public void setMaterialComposition(String materialComposition) {
        this.materialComposition = materialComposition;
    }

    public String getVariationType() {
        return "colorsize";
    }

//    public Set<String> getListColor() {
//        return listColor;
//    }
//
//    public void setListColor(Set<String> listColor) {
//        this.listColor = listColor;
//    }
//
//    public Set<String> getListSizes() {
//        return listSizes;
//    }
//
//    public void setListSizes(Set<String> listSizes) {
//        this.listSizes = listSizes;
//    }

    public float getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(float basePrice) {
        this.basePrice = basePrice;
    }

    public String getMain_key() {
        return main_key;
    }

    public void setMain_key(String main_key) {
        this.main_key = main_key;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getReasons() {
        return reasons;
    }

    public void setReasons(String reasons) {
        this.reasons = reasons;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getImagesUrl(String mainUrl) {

        ArrayList<String> listUrl = new ArrayList<>();
        listUrl.add(mainUrl);
        listUrl.addAll(links);
        
        return listUrl.toArray(new String[listUrl.size()]);
//        return new String[]{
//            mainUrl,
//            link1,
//            link2
//        };
    }
    
    public String prefix;

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
    
    public String getPrefix() {

        if (prefix != null) {
            return prefix;
        }

        String brandBrief = StringUtils.getStringBrief(brand_name);
        if (brandBrief.length() > 2) {
            brandBrief = brandBrief.substring(0, 2);
        }
        prefix = brandBrief.toUpperCase();
        return prefix;
    }
}
