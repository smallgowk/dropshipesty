/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utils;

import com.models.amazon.CustomDTO;
import com.models.amazon.CustomizationModel;
import com.models.amazon.OptionModel;
import com.models.amazon.SurfaceModel;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;
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
            Sheet sheet = null;
            Iterator<Sheet> sheetIterator = workbook.sheetIterator();
            while (sheetIterator.hasNext()) {
                Sheet sh = sheetIterator.next();

                if (sh.getSheetName().equals("Data")) {
                    sheet = sh;
                    break;
                }
            }
            if (sheet == null) {
                return null;
            }   // Create a Sheet

            DataFormatter formatter = new DataFormatter();

            int i = 1;
            Row fieldRow = sheet.getRow(i);
            CustomDTO customDTO = new CustomDTO();
            customDTO.fetchData(fieldRow, formatter);
            
            CustomizationModel customizationModel = null;

            while (fieldRow != null && customDTO.isHasData()) {
                
                switch(customDTO.getType()) {
                    case "Surface":
                        surfaceModel.setLabel(customDTO.getLabel());
                        surfaceModel.setInstruction(customDTO.getInstruction());
                        break;
                    case "Customization":
                        customizationModel = new CustomizationModel();
                        customizationModel.setLabel(customDTO.getLabel());
                        customizationModel.setInstruction(customDTO.getInstruction());
                        surfaceModel.addCustomization(customizationModel);
                        
                        break;
                    case "Option":
                        OptionModel optionModel = new OptionModel();
                        optionModel.setLabel(customDTO.getLabel());
                        optionModel.setPrice(customDTO.getPriceValue());
                        if(customizationModel != null) {
                            customizationModel.addOption(optionModel);
                        }
                        
                        break;
                }
                
                i++;
                fieldRow = sheet.getRow(i);
                customDTO.fetchData(fieldRow, formatter);
            }

            fis.close();
            workbook.close();
        } catch (Exception ex) {

        }
        
        return surfaceModel;
    }
}
