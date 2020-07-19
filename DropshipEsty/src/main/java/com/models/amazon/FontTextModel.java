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
public class FontTextModel {
    public String label;
    public String instruction;

    public static String getFontLabelXpath() {
        return "/html/body/div[1]/div[2]/div[1]/div/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[2]/div/div/div[1]/div[2]/div[1]/div[11]/div/div[2]/div/div/div[2]/div/div[1]/div/div[2]/div[1]/div[1]/div/kat-input/input";
    }
    
    public static String getFontInstructionXpath() {
        return "/html/body/div[1]/div[2]/div[1]/div/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[2]/div/div/div[1]/div[2]/div[1]/div[11]/div/div[2]/div/div/div[2]/div/div[1]/div/div[2]/div[1]/div[2]/div/kat-input/input";
    }
    
    public static String getColorLabelXpath() {
        return "/html/body/div[1]/div[2]/div[1]/div/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[2]/div/div/div[1]/div[2]/div[1]/div[11]/div/div[2]/div/div/div[2]/div/div[2]/div/div[2]/div[1]/div[1]/div/kat-input/input";
    }
    
    public static String getColorInstructionXpath() {
        return "/html/body/div[1]/div[2]/div[1]/div/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[2]/div/div/div[1]/div[2]/div[1]/div[11]/div/div[2]/div/div/div[2]/div/div[2]/div/div[2]/div[1]/div[2]/div/kat-input/input";
    }
    
    public static String getTextBlockLabelXpath() {
        return "/html/body/div[1]/div[2]/div[1]/div/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[2]/div/div/div[1]/div[2]/div[1]/div[11]/div/div[2]/div/div/div[2]/div/div[3]/div/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[1]/div/div[1]/div/div/div[2]/div/div[2]/div/div[1]/div/kat-input/input";
    }
    
    public static String getTextBlockInstructionXpath() {
        return "/html/body/div[1]/div[2]/div[1]/div/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[2]/div/div/div[1]/div[2]/div[1]/div[11]/div/div[2]/div/div/div[2]/div/div[3]/div/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[1]/div/div[1]/div/div/div[2]/div/div[2]/div/div[2]/div/kat-input/input";
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(label).append(", ").append(instruction);
        return sb.toString(); //To change body of generated methods, choose Tools | Templates.
    }
}
