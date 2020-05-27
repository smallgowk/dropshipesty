/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models.aliex.store.inputdata;

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
    public String link1;
    public String link2;
    public Set<String> listColor;
    public Set<String> listSizes;
    public float basePrice;

    public static SnakeBaseStoreOrderInfo createInstance(String linkStore, Set<String> listColor, Set<String> listSizes, String category, String description, String link1, String link2, float basePrice) {
        SnakeBaseStoreOrderInfo snakeBaseStoreOrderInfo = new SnakeBaseStoreOrderInfo();
        snakeBaseStoreOrderInfo.setLink(linkStore);
        snakeBaseStoreOrderInfo.setDescription(description);
        snakeBaseStoreOrderInfo.setListColor(listColor);
        snakeBaseStoreOrderInfo.setListSizes(listSizes);
        snakeBaseStoreOrderInfo.setCategory(category);
        snakeBaseStoreOrderInfo.setLink1(link1);
        snakeBaseStoreOrderInfo.setLink2(link2);
        snakeBaseStoreOrderInfo.setBasePrice(basePrice);

        return snakeBaseStoreOrderInfo;
    }

    public String getLink1() {
        return link1;
    }

    public void setLink1(String link1) {
        this.link1 = link1;
    }

    public String getLink2() {
        return link2;
    }

    public void setLink2(String link2) {
        this.link2 = link2;
    }

    public Set<String> getListColor() {
        return listColor;
    }

    public void setListColor(Set<String> listColor) {
        this.listColor = listColor;
    }

    public Set<String> getListSizes() {
        return listSizes;
    }

    public void setListSizes(Set<String> listSizes) {
        this.listSizes = listSizes;
    }

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

}
