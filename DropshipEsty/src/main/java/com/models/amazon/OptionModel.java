/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models.amazon;

/**
 *
 * @author PhanDuy
 */
public class OptionModel {
    public String label;
    public float price;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    
    public String getPriceStr() {
        if(price == 0) return "";
        
        return (int) price + ".00";
    }
    
    public static String getOptionLabelXpath(int index, int i) {
        switch (index) {
            case 0:
                return "/html/body/div[1]/div[2]/div[1]/div/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[2]/div/div/div[1]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[1]/div[4]/div/div[" + i + "]/div[1]/kat-box/div/div[1]/div/div[1]/div/kat-input/input";
            case 1:
                return "/html/body/div[1]/div[2]/div[1]/div/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[2]/div/div/div[1]/div[2]/div[1]/div[5]/div/div[2]/div/div/div[1]/div[4]/div/div[" + i + "]/div[1]/kat-box/div/div[1]/div/div[1]/div/kat-input/input";
            case 2:
                return "/html/body/div[1]/div[2]/div[1]/div/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[2]/div/div/div[1]/div[2]/div[1]/div[8]/div/div[2]/div/div/div[1]/div[4]/div/div[" + i + "]/div[1]/kat-box/div/div[1]/div/div[1]/div/kat-input/input";
        }
        return null;
    }

    public static String getOptionPriceXpath(int index, int i) {
        switch (index) {
            case 0:
                return "/html/body/div[1]/div[2]/div[1]/div/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[2]/div/div/div[1]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[1]/div[4]/div/div[" + i + "]/div[1]/kat-box/div/div[1]/div/div[2]/div/kat-input-group/kat-input-group/kat-input/input";
            case 1:
                return "/html/body/div[1]/div[2]/div[1]/div/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[2]/div/div/div[1]/div[2]/div[1]/div[5]/div/div[2]/div/div/div[1]/div[4]/div/div[" + i + "]/div[1]/kat-box/div/div[1]/div/div[2]/div/kat-input-group/kat-input-group/kat-input/input";
            case 2:
                return "/html/body/div[1]/div[2]/div[1]/div/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[2]/div/div/div[1]/div[2]/div[1]/div[8]/div/div[2]/div/div/div[1]/div[4]/div/div[" + i + "]/div[1]/kat-box/div/div[1]/div/div[2]/div/kat-input-group/kat-input-group/kat-input/input";
        }
        return null;
    }

    public static String getOptionPriceKatXpath(int index, int i) {
        switch (index) {
            case 0:
                return "/html/body/div[1]/div[2]/div[1]/div/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[2]/div/div/div[1]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[1]/div[4]/div/div[" + i + "]/div[1]/kat-box/div/div[1]/div/div[2]/div/kat-input-group/kat-input-group/kat-input";
            case 1:
                return "/html/body/div[1]/div[2]/div[1]/div/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[2]/div/div/div[1]/div[2]/div[1]/div[5]/div/div[2]/div/div/div[1]/div[4]/div/div[" + i + "]/div[1]/kat-box/div/div[1]/div/div[2]/div/kat-input-group/kat-input-group/kat-input";
            case 2:
                return "/html/body/div[1]/div[2]/div[1]/div/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[2]/div/div/div[1]/div[2]/div[1]/div[8]/div/div[2]/div/div/div[1]/div[4]/div/div[" + i + "]/div[1]/kat-box/div/div[1]/div/div[2]/div/kat-input-group/kat-input-group/kat-input";
        }
        return null;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(label).append(", ").append(price);
        return sb.toString(); //To change body of generated methods, choose Tools | Templates.
    }
}
