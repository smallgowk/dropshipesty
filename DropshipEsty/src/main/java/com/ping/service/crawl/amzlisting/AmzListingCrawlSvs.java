/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ping.service.crawl.amzlisting;

import com.config.Configs;
import static com.config.Configs.IMAGE_PATH;
//import static com.config.Configs.STORE_INFO_CACHE_DIR;
import com.models.amazon.AmzListingItem;
import com.models.amazon.BaseCustomize;
import com.models.amazon.ColorModel;
import com.models.amazon.CustomizationOption;
import com.models.amazon.CustomizationText;
import com.models.amazon.FontTextModel;
import com.models.amazon.OptionModel;
import com.models.amazon.SurfaceModel;
import com.models.amazon.TextBlockModel;
import com.models.amazon.TextModel;
import com.ping.service.crawl.CrawlerMachine;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JFrame;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author PhanDuy
 */
public class AmzListingCrawlSvs extends CrawlerMachine {

    private static AmzListingCrawlSvs amzListingCrawlSvs;

    public static Pattern imageUpload;
    public static Pattern fileInputTextBox;
    public static Pattern openButton;
    public static Pattern addCustomizeMore;
    public static Pattern addCustomizeOption;
    public static Pattern addCustomizeConfirm;
    public static Pattern imageUploaded;

    static {
        imageUpload = new Pattern(IMAGE_PATH + "imageUpload.PNG");
        fileInputTextBox = new Pattern(IMAGE_PATH + "input.PNG");
        openButton = new Pattern(IMAGE_PATH + "open.PNG");
        addCustomizeMore = new Pattern(IMAGE_PATH + "addCustomization.PNG");
        addCustomizeOption = new Pattern(IMAGE_PATH + "customizeBoxOption.PNG");
        addCustomizeConfirm = new Pattern(IMAGE_PATH + "addCustomConfirm.PNG");
        imageUploaded = new Pattern(IMAGE_PATH + "imageUploaded.PNG");
    }

    public static AmzListingCrawlSvs getInstance() {
        if (amzListingCrawlSvs == null) {
            amzListingCrawlSvs = new AmzListingCrawlSvs();
//            aliexCrawlSvs.initDriver();
        }
        return amzListingCrawlSvs;
    }
    
    public boolean isCustomized() {
        return false;
    }

    public boolean doFillBaseInfo(String imageFolder, String sku, SurfaceModel surfaceModel) {

//        WebElement element1 = driver.findElement(By.xpath("//input[@placeholder='Label']"));
//        WebDriverWait wait1 = new WebDriverWait(driver, 100);
//        WebElement element1 = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[2]/div[1]/div/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[1]/div[1]/div/div[1]/div/div/kat-input/input")));
        WebElement element1 = findWithFullXPath("/html/body/div[1]/div[2]/div[1]/div/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[1]/div[1]/div/div[1]/div/div/kat-input");
        executor.executeScript("arguments[0].setAttribute('value', arguments[1])", element1, surfaceModel.getLabel());
//        WebElement element1 = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[1]/div/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[1]/div[1]/div/div[1]/div/div/kat-input/input"));
//        JavascriptExecutor executor = (JavascriptExecutor) driver;
//        executor.executeScript("arguments[0].clear();", element1);
//        executor.executeScript("arguments[0].sendKeys('imagePath');", element1);
//        WebElement element1 = driver.findElement(By.id("katal-id-1"));

//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException ex) {
//            java.util.logging.Logger.getLogger(AmzListingCrawlSvs.class.getName()).log(Level.SEVERE, null, ex);
//        }
////        JavascriptExecutor executor = (JavascriptExecutor) driver;
////        executor.executeScript("arguments[0].sendKeys();", element1);
//        element1.clear();
//        element1.sendKeys(surfaceModel.getLabel());
//        WebDriverWait wait2 = new WebDriverWait(driver, 100);
//        WebElement element2 = wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[2]/div[1]/div/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[1]/div[1]/div/div[2]/div/div/div[2]/kat-textarea/textarea")));
        WebElement element2 = findWithFullXPath("/html/body/div[1]/div[2]/div[1]/div/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[1]/div[1]/div/div[2]/div/div/div[2]/kat-textarea");
//        WebElement element2 = driver.findElement(By.xpath("//*[@id=\"katal-id-2\"]"));
//        WebElement element2 = driver.findElement(By.id("katal-id-2"));
//        element2.clear();
//        element2.sendKeys(surfaceModel.getInstruction());
        executor.executeScript("arguments[0].setAttribute('value', arguments[1])", element2, surfaceModel.getInstruction());

        Screen s = new Screen();
        
        boolean isFoundImage = false;

        try {
            s.wait(imageUploaded, Configs.timeDelay);
            isFoundImage = true;
        } catch (FindFailed ex) {
            Logger.getLogger(AmzListingCrawlSvs.class.getName()).log(Level.SEVERE, null, ex);
            try {

                File imageFile = new File(imageFolder + Configs.pathChar + sku + ".jpg");
                if (!imageFile.exists()) {
                    System.out.println("Not found image for " + sku);
                    return false;
                }

                WebElement uploadImageElement = findWithFullXPath("/html/body/div[1]/div[2]/div[1]/div/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[1]/div[2]/div/div[1]/div[2]/div/div/div");
                uploadImageElement.click();
                s.wait(fileInputTextBox, 20);
                s.type(fileInputTextBox, imageFile.getPath());
                s.click(openButton);

                s.wait(imageUploaded, 20);
            } catch (FindFailed ex1) {
                Logger.getLogger(AmzListingCrawlSvs.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        
        return isFoundImage;

    }

    public void doAddingCustomizePanel(String type) {

        WebElement ele = findWithFullXPath(SurfaceModel.getAddCustomizeButtonXpath());
        executor.executeScript("arguments[0].click();", ele);
        try {
            Thread.sleep(200);
        } catch (InterruptedException ex) {
            java.util.logging.Logger.getLogger(AmzListingCrawlSvs.class.getName()).log(Level.SEVERE, null, ex);
        }

        String select = type.equals(BaseCustomize.TYPE_OPTION) ? "Option Dropdown" : "Text";

        List<WebElement> optionElements = driver.findElements(By.className("choice-content__3DVcc"));
        if (optionElements != null && !optionElements.isEmpty()) {
            for (WebElement webElement : optionElements) {
                List<WebElement> infoElements = webElement.findElements(By.className("choice-header-wrapper__20fkp"));
                if (infoElements != null && !infoElements.isEmpty()) {
                    if (infoElements.get(0).getText().equals(select)) {
                        webElement.click();
                        break;
                    }
                }
            }
        }

        List<WebElement> confirmElements = driver.findElements(By.className("primary"));
        if (confirmElements != null && !confirmElements.isEmpty()) {
            for (WebElement webElement : confirmElements) {
                String text = webElement.getAttribute("type");
                if (text != null && text.equals("button")) {
                    String label = webElement.getText();
                    if (label.equals("Add customization") || label.equals("Add customisation")) {
                        webElement.click();
                        break;
                    }
                }
            }
        }

    }

    public void doUpdateCustomizationIfno(SurfaceModel surfaceModel, boolean isAddText) {
        for (int i = 0; i < surfaceModel.listCustomization.size(); i++) {
            BaseCustomize customizationModel = surfaceModel.getCustomizationModel(i);
            if (customizationModel instanceof CustomizationOption) {
                setCustomizationOption(i, (CustomizationOption) customizationModel);
            }
        }

        if (isAddText && surfaceModel.customizationText != null) {
            surfaceModel.customizationText.clearFontAdded();
            setCustomizationText(3, surfaceModel.customizationText);
        }

    }

    public void doSave() {
        WebElement ele = findWithFullXPath(SurfaceModel.getSaveButtonXpath());
        executor.executeScript("arguments[0].click();", ele);
    }

    public void setCustomizationText(int index, CustomizationText customizationModel) {
        WebElement elementFontTextLabel = findWithFullXPath(FontTextModel.getFontLabelXpath(index));
        
        while(elementFontTextLabel != null) {
            WebElement deleteTextElement = findWithFullXPath(FontTextModel.getDeleteButtonXpath(index));
            executor.executeScript("arguments[0].click();", deleteTextElement);
            
            WebElement confirmDeleteTextElement = findWithFullXPath(FontTextModel.getConfirmDeleteButtonXpath(index));
            executor.executeScript("arguments[0].click();", confirmDeleteTextElement);
            
            elementFontTextLabel = findWithFullXPath(FontTextModel.getFontLabelXpath(index));
        }
        
        doAddingCustomizePanel(BaseCustomize.TYPE_TEXT);

        elementFontTextLabel = findWithFullXPath(FontTextModel.getFontLabelXpath(index));
        elementFontTextLabel.clear();
        elementFontTextLabel.sendKeys(customizationModel.fontTextModel.label);

        WebElement elementFontTextInstruction = findWithFullXPath(FontTextModel.getFontInstructionXpath(index));
        elementFontTextInstruction.clear();
        elementFontTextInstruction.sendKeys(customizationModel.fontTextModel.instruction);

        WebElement elementColorLabel = findWithFullXPath(FontTextModel.getColorLabelXpath(index));
        elementColorLabel.clear();
        elementColorLabel.sendKeys(customizationModel.colorTextModel.label);

        WebElement elementColorInstruction = findWithFullXPath(FontTextModel.getColorInstructionXpath(index));
        elementColorInstruction.clear();
        elementColorInstruction.sendKeys(customizationModel.colorTextModel.instruction);

        WebElement elementAddFontButton = findWithFullXPath(FontTextModel.getAddFontButtonXpath(index));
        executor.executeScript("arguments[0].click();", elementAddFontButton);

        WebElement elementFontGroup = findWithFullXPath(FontTextModel.getTableFontXpath(index));
        updateFont(elementFontGroup, customizationModel);

        while (!customizationModel.isFullChecked()) {

            WebElement nextTab = findWithFullXPath(FontTextModel.getNextTabXpath(index));
            executor.executeScript("arguments[0].click();", nextTab);

            elementFontGroup = findWithFullXPath(FontTextModel.getTableFontXpath(index));
            updateFont(elementFontGroup, customizationModel);

        }

        WebElement elementDoneFontButton = findWithFullXPath(FontTextModel.getDoneAddFontXpath(index));
        executor.executeScript("arguments[0].click();", elementDoneFontButton);

        System.out.println("Added full font!");

        for (int j = 0, size = customizationModel.colorModels.size(); j < size; j++) {
            TextModel colorModel = customizationModel.colorModels.get(j);
            WebElement elementAddColorButton = findWithFullXPath(FontTextModel.getAddColorButtonXpath(index));
            executor.executeScript("arguments[0].click();", elementAddColorButton);

            WebElement elementColorName = findWithFullXPath(FontTextModel.getAddColorNameXpath(index));
            elementColorName.clear();
            elementColorName.sendKeys(colorModel.label);

            WebElement elementColorValue = findWithFullXPath(FontTextModel.getAddColorValueXpath(index));
            elementColorValue.clear();
            elementColorValue.sendKeys(colorModel.instruction);
            elementColorValue.sendKeys(Keys.ENTER);

            WebElement elementAddColorSaveButton = findWithFullXPath(FontTextModel.getAddColorSave(index));
            executor.executeScript("arguments[0].click();", elementAddColorSaveButton);
        }
//        
        for (int j = 0, size = customizationModel.textBlockModels.size(); j < size; j++) {
            TextBlockModel textBlockModel = customizationModel.textBlockModels.get(j);
            WebElement elementTextBlockLabel = findWithFullXPath(FontTextModel.getTextBlockLabelXpath(index, j));

            if (elementTextBlockLabel == null) {
                doAddingTextBlockPanel(index);
                elementTextBlockLabel = findWithFullXPath(FontTextModel.getTextBlockLabelXpath(index, j));
            }

            elementTextBlockLabel.clear();
            elementTextBlockLabel.sendKeys(textBlockModel.label);

            WebElement elementTextBlockInstruction = findWithFullXPath(FontTextModel.getTextBlockInstructionXpath(index, j));
            elementTextBlockInstruction.clear();
            elementTextBlockInstruction.sendKeys(textBlockModel.instruction);

            WebElement elementTextBlockX = findWithFullXPath(FontTextModel.getTextBlockXXpath(index, j));
            elementTextBlockX.clear();
            elementTextBlockX.sendKeys(textBlockModel.x);

            WebElement elementTextBlockY = findWithFullXPath(FontTextModel.getTextBlockYXpath(index, j));
            elementTextBlockY.clear();
            elementTextBlockY.sendKeys(textBlockModel.y);

            WebElement elementTextBlockSizeWidth = findWithFullXPath(FontTextModel.getTextBlockSizeWidthXpath(index, j));
            elementTextBlockSizeWidth.clear();
            elementTextBlockSizeWidth.sendKeys(textBlockModel.width);

            WebElement elementTextBlockSizeHeight = findWithFullXPath(FontTextModel.getTextBlockSizeHeightXpath(index, j));
            elementTextBlockSizeHeight.clear();
            elementTextBlockSizeHeight.sendKeys(textBlockModel.height);
            
            if("1".equals(textBlockModel.placeMent)) {
                WebElement elementTextBlockPlacement = findWithFullXPath(FontTextModel.getTextBlockPlacementXpath(index, j));
                executor.executeScript("arguments[0].click();", elementTextBlockPlacement);
            }
        }

    }

    public void updateFont(WebElement elementFontGroup, CustomizationText customizationModel) {
        List<WebElement> items = elementFontGroup.findElements(By.tagName("kat-table-row"));
        if (items != null) {
            for (WebElement webElement : items) {
                List<WebElement> itemCells = webElement.findElements(By.tagName("kat-table-cell"));
                if (itemCells != null && itemCells.size() >= 2) {

                    WebElement cellLabel = itemCells.get(1);
                    String fontLabel = cellLabel.getText();

                    if (customizationModel.isContainFont(fontLabel)) {
                        System.out.println("Found " + fontLabel);

                        WebElement cellCheckBox = itemCells.get(0);
//                        WebElement katCheckbox = cellCheckBox.findElement(By.tagName("kat-checkbox"));
                        WebElement checkbox = cellCheckBox.findElement(By.tagName("div"));
                        executor.executeScript("arguments[0].click();", checkbox);
//                        katCheckbox.click();
                        customizationModel.updateAddedFont(fontLabel);
                    }
                }

            }
        }
    }

    public void setCustomizationOption(int index, CustomizationOption customizationModel) {
        WebElement elementOptionLabel = findWithFullXPath(CustomizationOption.getCustomLabelXpath(index));

        if (elementOptionLabel == null) {
            doAddingCustomizePanel(BaseCustomize.TYPE_OPTION);
        }
        elementOptionLabel = findWithFullXPath(CustomizationOption.getCustomLabelXpath(index));

        elementOptionLabel.clear();
        elementOptionLabel.sendKeys(customizationModel.label);

        WebElement elementInstruction = findWithFullXPath(CustomizationOption.getCustomInstructXpath(index));
        elementInstruction.clear();
        elementInstruction.sendKeys(customizationModel.instruction);
        for (int i = 0, size = customizationModel.getListOptions().size(); i < size; i++) {
            OptionModel optionModel = customizationModel.getOptionModel(i);
            WebElement optionLabel = findWithFullXPath(OptionModel.getOptionLabelXpath(index, (i + 1)));

            if (optionLabel == null) {
                doAddingOptionPanel(index, i + 1);
                optionLabel = findWithFullXPath(OptionModel.getOptionLabelXpath(index, (i + 1)));
            }

            optionLabel.clear();
            optionLabel.sendKeys(optionModel.label);
            optionLabel.sendKeys(Keys.ENTER);
            if (!optionModel.getPriceStr().trim().isEmpty()) {

                String xpath = OptionModel.getOptionPriceXpath(index, (i + 1));
                String xpathGroup = OptionModel.getOptionPriceKatXpath(index, (i + 1));
                WebElement optionPriceKat = findWithFullXPath(xpathGroup);
                String value = optionPriceKat.getAttribute("value");

                WebElement optionPrice = findWithFullXPath(xpath);
                for (int j = 0, length = value.length(); j < length; j++) {
                    optionPrice.sendKeys(Keys.BACK_SPACE);
                }

                optionPrice.sendKeys(optionModel.getPriceStr());
                optionLabel.sendKeys(Keys.ENTER);

            }
        }

    }

    private WebElement findWithFullXPath(String xpath) {
        try {
            WebDriverWait waitele = new WebDriverWait(driver, 2);
            waitele.ignoring(StaleElementReferenceException.class);
            return waitele.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        } catch (Exception ex) {
            return null;
        }

    }

    public void doAddingOptionPanel(int customIndex, int optionIndex) {
        WebElement ele = findWithFullXPath(CustomizationOption.getAddOptionXpath(customIndex, optionIndex));
        executor.executeScript("arguments[0].click();", ele);
    }

    public void doAddingTextBlockPanel(int customIndex) {
        WebElement ele = findWithFullXPath(FontTextModel.getAddTextInputXpath(customIndex));
        executor.executeScript("arguments[0].click();", ele);
    }

    public static BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }

    public ArrayList<AmzListingItem> crawlData() {
        ArrayList<AmzListingItem> list = new ArrayList<>();

//        scrollToBottom(10000);
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(AliexCrawlProductInfoSvs.class.getName()).log(Level.SEVERE, null, ex);
//        }
        String pageSource = getPageSource();
        Document doc = Jsoup.parse(pageSource);
        Elements rows = doc.select("tr[class='mt-row']");
        Set<String> setSkus = new HashSet<>();
        if (rows != null && !rows.isEmpty()) {
            for (Element row : rows) {
                AmzListingItem amzListingItem = new AmzListingItem();

                Elements skus = row.select("span[class='a-splitdropdown-container']");

                if (skus != null && !skus.isEmpty()) {
                    for (Element sku : skus) {
                        Elements options = sku.select("option");
                        if (options != null && !options.isEmpty()) {
                            for (Element option : options) {
                                String id = option.attr("id");
                                if (id != null && id.contains("addEditCustomization")) {
                                    amzListingItem.setCustomLink(option.attr("data-action-string"));
                                    if (!setSkus.contains(amzListingItem.getSku())) {
                                        setSkus.add(amzListingItem.getSku());
                                        list.add(amzListingItem);
                                    }

                                }

                            }

                        }

                    }
                }
            }
        }

        return list;
    }

    public void scrollToBottom(int dy) {
        scrollPage(dy);
    }
}
