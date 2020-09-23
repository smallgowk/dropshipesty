/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models.amazon;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author PhanDuy
 */
public class CustomDTO {

    public String label;
    public String price;
    public String instruction;
    public String type;

    public String getLabel() {
        return label != null ? label : "";
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getPrice() {
        return price != null ? price : "";
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getInstruction() {
        return instruction != null ? instruction : "";
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void fetchCustomizeOptionData(Row fieldRow, DataFormatter formatter) {
        
        if(fieldRow == null) return;
        
        for (int i = 0; i < 4; i++) {
            switch (i) {
                case 0:
                    label = formatter.formatCellValue(fieldRow.getCell(i));
                    break;
                case 1:
                    price = formatter.formatCellValue(fieldRow.getCell(i));
                    break;
                case 2:
                    instruction = formatter.formatCellValue(fieldRow.getCell(i));
                    break;
                case 3:
                    type = formatter.formatCellValue(fieldRow.getCell(i));
                    break;
            }
        }
    }
    
    public void fetchCustomizeTextData(Row fieldRow, DataFormatter formatter) {
        
        if(fieldRow == null) return;
        
        for (int i = 0; i < 3; i++) {
            switch (i) {
                case 0:
                    label = formatter.formatCellValue(fieldRow.getCell(i));
                    break;
                case 1:
                    instruction = formatter.formatCellValue(fieldRow.getCell(i));
                    break;
                case 2:
                    type = formatter.formatCellValue(fieldRow.getCell(i));
                    break;
            }
        }
    }
    
    public float getPriceValue() {
        if(price == null || price.isEmpty()) return 0;
        
        try {
            return Float.parseFloat(price);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }
    
    public boolean isHasData() {
        return type != null && !type.trim().isEmpty();
    }
}
