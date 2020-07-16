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

    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(label).append(", ").append(instruction);
        return sb.toString(); //To change body of generated methods, choose Tools | Templates.
    }
}
