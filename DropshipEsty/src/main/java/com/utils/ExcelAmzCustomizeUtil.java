/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utils;

import com.models.amazon.BaseCustomize;
import com.models.amazon.CustomDTO;
import com.models.amazon.CustomizationOption;
import com.models.amazon.CustomizationText;
import com.models.amazon.OptionModel;
import com.models.amazon.SurfaceModel;
import com.models.amazon.TextModel;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *
 * @author PhanDuy
 */
public class ExcelAmzCustomizeUtil {

    public static SurfaceModel readDataInfo(String filePath) throws FileNotFoundException {

        SurfaceModel surfaceModel = new SurfaceModel();

        FileInputStream fis = new FileInputStream(filePath);

        try (Workbook workbook = WorkbookFactory.create(fis)) {

            /* CreationHelper helps us create instances of various things like DataFormat,
            Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way */
//            CreationHelper createHelper = workbook.getCreationHelper();
            //        Sheet btgSheet = workbook.createSheet("Employee");
            Sheet sheetOption = findSheet(workbook, "Option");
            if (sheetOption == null) {
                return null;
            }   // Create a Sheet

            fetchSurfaceOptionData(surfaceModel, sheetOption);

            Sheet sheetText = findSheet(workbook, "Text");
            if (sheetText == null) {
                return surfaceModel;
            }

            workbook.close();
        } catch (Exception ex) {

        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(ExcelAmzCustomizeUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return surfaceModel;
    }

    public static Sheet findSheet(Workbook workbook, String sheetName) {
        Iterator<Sheet> sheetIterator = workbook.sheetIterator();
        while (sheetIterator.hasNext()) {
            Sheet sh = sheetIterator.next();

            if (sh.getSheetName().equals(sheetName)) {
                return sh;
            }
        }

        return null;
    }

    public static void fetchSurfaceOptionData(SurfaceModel surfaceModel, Sheet sheet) {
        DataFormatter formatter = new DataFormatter();

        int i = 1;
        Row fieldRow = sheet.getRow(i);
        CustomDTO customDTO = new CustomDTO();
        customDTO.fetchCustomizeOptionData(fieldRow, formatter);

        BaseCustomize customizationModel = null;

        while (fieldRow != null && customDTO.isHasData()) {

            switch (customDTO.getType()) {
                case "Surface":
                    surfaceModel.setLabel(customDTO.getLabel());
                    surfaceModel.setInstruction(customDTO.getInstruction());
                    break;
                case "Customization-Option":
                    customizationModel = new CustomizationOption();
                    customizationModel.setLabel(customDTO.getLabel());
                    customizationModel.setInstruction(customDTO.getInstruction());
                    surfaceModel.addCustomization(customizationModel);

                    break;
                case "Customization-Text":
                    customizationModel = new CustomizationText();
                    customizationModel.setLabel(customDTO.getLabel());
                    customizationModel.setInstruction(customDTO.getInstruction());
                    surfaceModel.addCustomization(customizationModel);
                    break;
                case "Option":
                    OptionModel optionModel = new OptionModel();
                    optionModel.setLabel(customDTO.getLabel());
                    optionModel.setPrice(customDTO.getPriceValue());
                    if (customizationModel != null) {
                        customizationModel.addData(optionModel);
                    }

                    break;
            }

            i++;
            fieldRow = sheet.getRow(i);
            customDTO.fetchCustomizeOptionData(fieldRow, formatter);
        }
    }

    public static void fetchSurfaceTextData(SurfaceModel surfaceModel, Sheet sheet) {
        DataFormatter formatter = new DataFormatter();

        int i = 1;
        Row fieldRow = sheet.getRow(i);
        CustomDTO customDTO = new CustomDTO();
        customDTO.fetchCustomizeTextData(fieldRow, formatter);

        BaseCustomize customizationModel = new CustomizationText();
        surfaceModel.addCustomization(customizationModel);

        while (fieldRow != null && customDTO.isHasData()) {

            TextModel textModel = new TextModel();
            textModel.setLabel(customDTO.getLabel());
            textModel.setInstruction(customDTO.getInstruction());
            textModel.setType(customDTO.getType());
            customizationModel.addData(textModel);

            i++;
            fieldRow = sheet.getRow(i);
            customDTO.fetchCustomizeTextData(fieldRow, formatter);
        }
    }
}
