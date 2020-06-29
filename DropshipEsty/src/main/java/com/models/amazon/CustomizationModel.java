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
public class CustomizationModel {
    public String label;
    public String instruction;
    public ArrayList<OptionModel> listOptions;

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
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(label).append(", ").append(instruction).append("\n");
        if (listOptions != null) {
            sb.append("Options: \n");
            listOptions.forEach((option) -> {
                sb.append(option).append("\n");
            });
        }

        return sb.toString(); //To change body of generated methods, choose Tools | Templates.
    }
}
