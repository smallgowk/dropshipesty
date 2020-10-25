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
public class SurfaceModel {

    public String label;
    public String instruction;
    public ArrayList<BaseCustomize> listCustomization;
    public CustomizationText customizationText;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public ArrayList<BaseCustomize> getListCustomization() {
        return listCustomization;
    }

    public void setListCustomization(ArrayList<BaseCustomize> listCustomization) {
        this.listCustomization = listCustomization;
    }

    public void addCustomization(BaseCustomize model) {
        if (listCustomization == null) {
            listCustomization = new ArrayList<>();
        }

        listCustomization.add(model);
    }

    public CustomizationText getCustomizationText() {
        return customizationText;
    }

    public void setCustomizationText(CustomizationText customizationText) {
        this.customizationText = customizationText;
    }
    
    
    
    public BaseCustomize getCustomizationModel(int index) {
        if(index >= listCustomization.size()) {
            return null;
        }
        
        return listCustomization.get(index);
    }
    
    public static String getAddCustomizeButtonXpath() {
        return "/html/body/div[1]/div[2]/div[1]/div/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[2]/div/div/div[1]/div[2]/div[2]/div/kat-button/button";
    }
    
    public static String getSaveButtonXpath() {
        return "/html/body/div[1]/div[2]/div[1]/div/div[2]/div[2]/div/div[2]/kat-button[2]/button";
    }
    
    public static String getSaveButtonXpathOld() {
        return "/html/body/div[1]/div[2]/div[1]/div/div[2]/div[2]/div/kat-button[2]/button";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(label).append(", ").append(instruction).append("\n");
        if (listCustomization != null) {
            sb.append("Customs: ").append("\n");
            listCustomization.forEach((customizationModel) -> {
                sb.append(customizationModel).append("\n");
            });
        }
        
        sb.append(customizationText).append("\n");

        return sb.toString(); //To change body of generated methods, choose Tools | Templates.
    }
}
