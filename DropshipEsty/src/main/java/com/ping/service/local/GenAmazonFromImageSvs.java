/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ping.service.local;

import com.models.aliex.store.inputdata.SnakeBaseStoreOrderInfo;
import com.models.amazon.ProductAmz;
import com.utils.FuncUtil;
import com.utils.StringUtils;
import com.utils.Utils;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author PhanDuy
 */
public class GenAmazonFromImageSvs {

    private static GenAmazonFromImageSvs genAmazonFromImageSvs;

    public static GenAmazonFromImageSvs getInstance() {
        if (genAmazonFromImageSvs == null) {
            genAmazonFromImageSvs = new GenAmazonFromImageSvs();
        }
        return genAmazonFromImageSvs;
    }

    public ArrayList<ProductAmz> genFromImageLocal(SnakeBaseStoreOrderInfo snakeBaseStoreOrderInfo) {
        ArrayList<ProductAmz> results = null;

        File file = new File(snakeBaseStoreOrderInfo.getImageFolder());
        if (file.exists()) {
            String[] paths = file.list();
            for (String s : paths) {

                ProductAmz productAmz = genProductAmz(s, snakeBaseStoreOrderInfo);
                if (results == null) {
                    results = new ArrayList<>();
                }
                results.add(productAmz);
            }
        }
        return results;
    }

    private ProductAmz genProductAmz(String filePath, SnakeBaseStoreOrderInfo snakeBaseStoreOrderInfo) {

        File imageFile = new File(filePath);
        String name = imageFile.getName();
        String title = name.substring(0, name.lastIndexOf("."));

        ProductAmz productAmz = new ProductAmz();

        productAmz.setExternal_product_id_type("UPC");
        productAmz.setFeed_product_type("shirt");
        productAmz.setQuantity("200");
        productAmz.setFulfillment_latency("5");
        productAmz.setMfg_minimum("10");
        productAmz.setUnit_count("1");
        productAmz.setUnit_count_type("PC");
        productAmz.setItem_package_quantity("1");
        productAmz.setNumber_of_items("1");
        productAmz.setMaterial_type("other");
        productAmz.setBrand_name(snakeBaseStoreOrderInfo.getBrand_name());
        productAmz.setImageUrl(snakeBaseStoreOrderInfo.getImagesUrl(snakeBaseStoreOrderInfo.genMainUrlFromIp(name)));
        productAmz.setItem_type(snakeBaseStoreOrderInfo.getItemType());
//        productAmz.setVariation_theme(snakeBaseStoreOrderInfo.getVariationType());

        productAmz.setBulletPoints(snakeBaseStoreOrderInfo.getListBullets());
        productAmz.setItem_sku(snakeBaseStoreOrderInfo.getPrefix().toUpperCase() + FuncUtil.createSaltNumber(5) + "_" + System.currentTimeMillis());
        productAmz.setPart_number(productAmz.getItem_sku().substring(0, productAmz.getItem_sku().length() - 2));
        productAmz.setOuter_material_type1(snakeBaseStoreOrderInfo.getOuterMaterialType());
        productAmz.setMaterial_composition1(snakeBaseStoreOrderInfo.getMaterialComposition());
        productAmz.setIs_adult_product("TRUE");
        productAmz.setColor_map("White");
        productAmz.setColor_name("Black");
        productAmz.setSize_map("Large");
        productAmz.setSize_name("Large");

//        if(title == null) return null;
        productAmz.setItem_name(title);

        productAmz.setItem_type(snakeBaseStoreOrderInfo.getItemType());
//        productAmz.setTarget_audience_keywords(aliexStoreInfo.getAudienceKeyword());
//        productAmz.setTarget_audience_keywords1(aliexStoreInfo.getAudienceKeyword());
        productAmz.setDepartment_name(snakeBaseStoreOrderInfo.getDepartment());
//        productAmz.setTarget_audience_keywords(aliexStoreInfo.getAudienceKeyword());
//        productAmz.setTarget_audience_keywords1(aliexStoreInfo.getAudienceKeyword());

        productAmz.setUnit_count_type("PC");
        productAmz.setMaterial_type("other");
        productAmz.setManufacturer(productAmz.getBrand_name());
        productAmz.setStandard_price("" + Utils.getCEOPrice(snakeBaseStoreOrderInfo.getBasePrice()));
        
        productAmz.setGeneric_keywords(title);
        productAmz.genDescriptions(snakeBaseStoreOrderInfo.getDescription());

        return productAmz;
    }
}
