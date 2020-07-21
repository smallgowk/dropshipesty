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
public class TextBlockModel {
    public String label;
    public String instruction;
    public String x;
    public String y;
    public String width;
    public String height;
    public String placeMent;

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

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getPlaceMent() {
        return placeMent;
    }

    public void setPlaceMent(String placeMent) {
        this.placeMent = placeMent;
    }
    
    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(label).append(", ").append(instruction)
                .append(", ").append(x)
                .append(", ").append(y)
                .append(", ").append(width)
                .append(", ").append(height)
                .append(", ").append(placeMent);
        return sb.toString(); //To change body of generated methods, choose Tools | Templates.
    }
}
