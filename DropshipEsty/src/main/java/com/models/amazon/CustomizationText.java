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
public class CustomizationText extends BaseCustomize {

    public TextModel fontTextModel;
    public TextModel colorTextModel;
    public ArrayList<TextModel> colorModels;
    public ArrayList<TextModel> textBlockModels;

    @Override
    public void addData(Object object) {

        String type = ((TextModel) object).type;
        switch (type) {
            case "FontText":
                fontTextModel = (TextModel) object;
                break;
            case "ColorText":
                colorTextModel = (TextModel) object;
                break;
            case "TextBlock":
                if (textBlockModels == null) {
                    textBlockModels = new ArrayList<>();
                }
                textBlockModels.add((TextModel) object);
                break;
            case "Color":
                if (colorModels == null) {
                    colorModels = new ArrayList<>();
                }
                colorModels.add((TextModel) object);
                break;
        }

    }
    
    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CustomText: ").append(label).append(", ").append(instruction).append("\n");
        sb.append("fontTextModel: ").append(fontTextModel).append("\n");
        sb.append("colorTextModel: ").append(colorTextModel).append("\n");
        
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
