/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ping.view;

import javax.swing.JCheckBox;

/**
 *
 * @author PhanDuy
 */
public class ViewValue {
    
    public int type;
    public String name;
    
    public JCheckBox checkBox;
    
    public boolean isChose;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIsChose() {
        return isChose;
    }

    public void setIsChose(boolean isChose) {
        this.isChose = isChose;
    }

    public JCheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(JCheckBox checkBox) {
        this.checkBox = checkBox;
    }
    
    public boolean isChosed() {
        return checkBox != null && checkBox.isSelected();
    }
}
