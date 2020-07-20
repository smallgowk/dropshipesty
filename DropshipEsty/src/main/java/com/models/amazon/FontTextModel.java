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

    public static String getFontLabelXpath(int i) {
        return "/html/body/div[1]/div[2]/div[1]/div/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[2]/div/div/div[1]/div[2]/div[1]/div["+(2 + i*3)+"]/div/div[2]/div/div/div[2]/div/div[1]/div/div[2]/div[1]/div[1]/div/kat-input/input";
    }
    
    public static String getFontInstructionXpath(int i) {
        return "/html/body/div[1]/div[2]/div[1]/div/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[2]/div/div/div[1]/div[2]/div[1]/div["+(2 + i*3)+"]/div/div[2]/div/div/div[2]/div/div[1]/div/div[2]/div[1]/div[2]/div/kat-input/input";
    }
    
    public static String getColorLabelXpath(int i) {
        return "/html/body/div[1]/div[2]/div[1]/div/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[2]/div/div/div[1]/div[2]/div[1]/div["+(2 + i*3)+"]/div/div[2]/div/div/div[2]/div/div[2]/div/div[2]/div[1]/div[1]/div/kat-input/input";
    }
    
    public static String getColorInstructionXpath(int i) {
        return "/html/body/div[1]/div[2]/div[1]/div/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[2]/div/div/div[1]/div[2]/div[1]/div["+(2 + i*3)+"]/div/div[2]/div/div/div[2]/div/div[2]/div/div[2]/div[1]/div[2]/div/kat-input/input";
    }
    
    public static String getTextBlockLabelXpath(int i, int j) {
        return "/html/body/div[1]/div[2]/div[1]/div/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[2]/div/div/div[1]/div[2]/div[1]/div["+(2 + i*3)+"]/div/div[2]/div/div/div[2]/div/div[3]/div/div[2]/div[2]/div[1]/div["+(2 + j*3)+"]/div/div[2]/div/div/div[1]/div/div[1]/div/div/div[2]/div/div[2]/div/div[1]/div/kat-input/input";
    }
    
    public static String getTextBlockInstructionXpath(int i, int j) {
        return "/html/body/div[1]/div[2]/div[1]/div/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[2]/div/div/div[1]/div[2]/div[1]/div["+(2 + i*3)+"]/div/div[2]/div/div/div[2]/div/div[3]/div/div[2]/div[2]/div[1]/div["+(2 + j*3)+"]/div/div[2]/div/div/div[1]/div/div[1]/div/div/div[2]/div/div[2]/div/div[2]/div/kat-input/input";
    }
    
    public static String getTextBlockXXpath(int i, int j) {
        return "/html/body/div[1]/div[2]/div[1]/div/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[2]/div/div/div[1]/div[2]/div[1]/div["+(2 + i*3)+"]/div/div[2]/div/div/div[2]/div/div[3]/div/div[2]/div[2]/div[1]/div["+(2 + j*3)+"]/div/div[2]/div/div/div[1]/div/div[2]/div[1]/div[1]/div[2]/kat-input[1]/input";
    }
    
    public static String getTextBlockYXpath(int i, int j) {
        return "/html/body/div[1]/div[2]/div[1]/div/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[2]/div/div/div[1]/div[2]/div[1]/div["+(2 + i*3)+"]/div/div[2]/div/div/div[2]/div/div[3]/div/div[2]/div[2]/div[1]/div["+(2 + j*3)+"]/div/div[2]/div/div/div[1]/div/div[2]/div[1]/div[1]/div[2]/kat-input[2]/input";
    }
    
    public static String getTextBlockSizeWidthXpath(int i, int j) {
        return "/html/body/div[1]/div[2]/div[1]/div/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[2]/div/div/div[1]/div[2]/div[1]/div["+(2 + i*3)+"]/div/div[2]/div/div/div[2]/div/div[3]/div/div[2]/div[2]/div[1]/div["+(2 + j*3)+"]/div/div[2]/div/div/div[1]/div/div[2]/div[1]/div[2]/div[2]/kat-input[1]/input";
    }
    
    public static String getTextBlockSizeHeightXpath(int i, int j) {
        return "/html/body/div[1]/div[2]/div[1]/div/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[2]/div/div/div[1]/div[2]/div[1]/div["+(2 + i*3)+"]/div/div[2]/div/div/div[2]/div/div[3]/div/div[2]/div[2]/div[1]/div["+(2 + j*3)+"]/div/div[2]/div/div/div[1]/div/div[2]/div[1]/div[2]/div[2]/kat-input[2]/input";
    }
    
    public static String getAddTextInputXpath(int i) {
        return "/html/body/div[1]/div[2]/div[1]/div/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[2]/div/div/div[1]/div[2]/div[1]/div["+(2 + i*3)+"]/div/div[2]/div/div/div[2]/div/div[3]/div/div[2]/div[2]/div[2]/div/kat-button/button";
    }
    
    public static String getAddColorButtonXpath(int i) {
        return "/html/body/div[1]/div[2]/div[1]/div/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[2]/div/div/div[1]/div[2]/div[1]/div["+(2 + i*3)+"]/div/div[2]/div/div/div[2]/div/div[2]/div/div[2]/div[2]/div[2]/div[2]/div[1]/div/kat-button/button";
    }
    
    public static String getAddColorNameXpath(int i) {
        return "/html/body/div[1]/div[2]/div[1]/div/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[2]/div/div/div[1]/div[2]/div[1]/div["+(2 + i*3)+"]/div/div[2]/div/div/div[2]/div/div[2]/div/div[2]/div[2]/kat-modal/kat-modal-content/kat-modal-body/div/div[2]/div[1]/div/kat-input/input";
    }
    
    public static String getAddColorValueXpath(int i) {
        return "/html/body/div[1]/div[2]/div[1]/div/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[2]/div/div/div[1]/div[2]/div[1]/div["+(2 + i*3)+"]/div/div[2]/div/div/div[2]/div/div[2]/div/div[2]/div[2]/kat-modal/kat-modal-content/kat-modal-body/div/div[2]/div[2]/div[1]/kat-input/input";
    }
    
    public static String getAddColorSave(int i) {
        return "/html/body/div[1]/div[2]/div[1]/div/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[2]/div/div/div[1]/div[2]/div[1]/div["+(2 + i*3)+"]/div/div[2]/div/div/div[2]/div/div[2]/div/div[2]/div[2]/kat-modal/kat-modal-content/kat-modal-footer/kat-button[2]/button";
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(label).append(", ").append(instruction);
        return sb.toString(); //To change body of generated methods, choose Tools | Templates.
    }
}
