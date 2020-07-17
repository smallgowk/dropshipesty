/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models.amazon;

import java.util.ArrayList;


/**
 *
 * @author PhanDuy
 */
public class CustomizationOption extends BaseCustomize{

   public ArrayList<OptionModel> listOptions;

    public ArrayList<OptionModel> getListOptions() {
        return listOptions;
    }

    public void setListOptions(ArrayList<OptionModel> listOptions) {
        this.listOptions = listOptions;
    }
    
    public void addOption(OptionModel model) {
        if(listOptions == null) {
            listOptions = new ArrayList<>();
        }
        
        listOptions.add(model);
    }
    
    public OptionModel getOptionModel(int index) {
        if(index >= listOptions.size()) {
            return null;
        }
        
        return listOptions.get(index);
    }
    
    public static String getCustomLabelXpath(int index) {
        switch (index) {
            case 0:
                return "/html/body/div[1]/div[2]/div[1]/div/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[2]/div/div/div[1]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[1]/div[2]/div/div[1]/div/kat-input/input";
            case 1:
                return "/html/body/div[1]/div[2]/div[1]/div/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[2]/div/div/div[1]/div[2]/div[1]/div[5]/div/div[2]/div/div/div[1]/div[2]/div/div[1]/div/kat-input/input";
            case 2:
                return "/html/body/div[1]/div[2]/div[1]/div/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[2]/div/div/div[1]/div[2]/div[1]/div[8]/div/div[2]/div/div/div[1]/div[2]/div/div[1]/div/kat-input/input";
        }
        return null;
    }
    
    public static String getCustomInstructXpath(int index) {
        switch (index) {
            case 0:
                return "/html/body/div[1]/div[2]/div[1]/div/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[2]/div/div/div[1]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[1]/div[2]/div/div[2]/div/kat-input/input";
            case 1:
                return "/html/body/div[1]/div[2]/div[1]/div/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[2]/div/div/div[1]/div[2]/div[1]/div[5]/div/div[2]/div/div/div[1]/div[2]/div/div[2]/div/kat-input/input";
            case 2:
                return "/html/body/div[1]/div[2]/div[1]/div/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[2]/div/div/div[1]/div[2]/div[1]/div[8]/div/div[2]/div/div/div[1]/div[2]/div/div[2]/div/kat-input/input";
        }
        return null;
    }
    
    public static String getAddOptionXpath(int index, int i) {

        if (i < 3) {
            return "";
        }

        switch (index) {
            case 0:
                return "/html/body/div[1]/div[2]/div[1]/div/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[2]/div/div/div[1]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[1]/div[4]/div/div[" + i + "]/div/kat-button/button";
            case 1:
                return "/html/body/div[1]/div[2]/div[1]/div/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[2]/div/div/div[1]/div[2]/div[1]/div[5]/div/div[2]/div/div/div[1]/div[4]/div/div[" + i + "]/div/kat-button/button";
            case 2:
                return "/html/body/div[1]/div[2]/div[1]/div/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[2]/div/div/div[1]/div[2]/div[1]/div[8]/div/div[2]/div/div/div[1]/div[4]/div/div[" + i + "]/div/kat-button/button";
        }
        return null;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CustomOption: ").append(label).append(", ").append(instruction).append("\n");
        if (listOptions != null) {
            sb.append("Options: \n");
            listOptions.forEach((option) -> {
                sb.append(option).append("\n");
            });
        }

        return sb.toString(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addData(Object object) {
        addOption((OptionModel) object);
    }
}
