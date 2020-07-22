/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models.amazon;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author PhanDuy
 */
public class CustomizationText extends BaseCustomize {

    public TextModel fontTextModel;
    public TextModel colorTextModel;
    public ArrayList<TextModel> colorModels;
    public ArrayList<TextModel> fontModels;
    public ArrayList<TextBlockModel> textBlockModels;
    
    private TextBlockModel textBlock = null;
    private Set<String> fontSet = new HashSet<String>();
    public ArrayList<String> fontAddedModels = new ArrayList<>();

    @Override
    public void addData(Object object) {

        TextModel data = ((TextModel) object);

        String type = data.type;

        switch (type) {
            case "FontText":
                fontTextModel = data;
                break;
            case "ColorText":
                colorTextModel = data;
                break;
            case "BlockText":
                if (textBlockModels == null) {
                    textBlockModels = new ArrayList<>();
                }

                textBlock = new TextBlockModel();

                textBlock.setLabel(data.getLabel());
                textBlock.setInstruction(data.getInstruction());

                textBlockModels.add(textBlock);
                break;
            case "Color":
                if (colorModels == null) {
                    colorModels = new ArrayList<>();
                }
                colorModels.add(data);
                break;
            case "Font":
                if (fontModels == null) {
                    fontModels = new ArrayList<>();
                }
                fontModels.add(data);
                fontSet.add(data.instruction);
                break;
            case "TextBlock_x":
                if (textBlock != null) {
                    textBlock.setX(data.getInstruction());
                }
                break;
            case "TextBlock_y":
                if (textBlock != null) {
                    textBlock.setY(data.getInstruction());
                }
                break;
            case "TextBlock_width":
                if (textBlock != null) {
                    textBlock.setWidth(data.getInstruction());
                }
                break;
            case "TextBlock_height":
                if (textBlock != null) {
                    textBlock.setHeight(data.getInstruction());
                }
                break;
            case "TextBlock_placement":
                if (textBlock != null) {
                    textBlock.setPlaceMent(data.getInstruction());
                }
                break;
        }

    }
    
    public boolean isContainFont(String fontName) {
        return fontSet.contains(fontName);
    }
    
    public void updateAddedFont(String fontName) {
        fontAddedModels.add(fontName);
    }
    
    public void clearFontAdded() {
        fontAddedModels.clear();
    }
    
    public boolean isFullChecked() {
        return fontAddedModels.size() == fontSet.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CustomText: ").append(label).append(", ").append(instruction).append("\n");
        sb.append("fontTextModel: ").append(fontTextModel).append("\n");
        sb.append("colorTextModel: ").append(colorTextModel).append("\n");

        if (fontModels != null) {
            sb.append("Fonts: \n");
            fontModels.forEach((option) -> {
                sb.append(option).append("\n");
            });
        }
        
        if (colorModels != null) {
            sb.append("Colors: \n");
            colorModels.forEach((option) -> {
                sb.append(option).append("\n");
            });
        }
        
        
        
        if (textBlockModels != null) {
            sb.append("TextBlocks: \n");
            textBlockModels.forEach((option) -> {
                sb.append(option).append("\n");
            });
        }

        return sb.toString(); //To change body of generated methods, choose Tools | Templates.
    }

}
