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
    public ArrayList<CustomizationModel> listCustomization;

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

    public ArrayList<CustomizationModel> getListCustomization() {
        return listCustomization;
    }

    public void setListCustomization(ArrayList<CustomizationModel> listCustomization) {
        this.listCustomization = listCustomization;
    }

    public void addCustomization(CustomizationModel model) {
        if (listCustomization == null) {
            listCustomization = new ArrayList<>();
        }

        listCustomization.add(model);
    }
    
    public CustomizationModel getCustomizationModel(int index) {
        if(index >= listCustomization.size()) {
            return null;
        }
        
        return listCustomization.get(index);
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

        return sb.toString(); //To change body of generated methods, choose Tools | Templates.
    }
}
