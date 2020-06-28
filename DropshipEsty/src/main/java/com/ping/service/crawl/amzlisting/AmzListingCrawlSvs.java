/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ping.service.crawl.amzlisting;

import com.ping.service.crawl.aliex.*;
import com.config.Configs;
import static com.config.Configs.CONFIG_FOLDER_PATH;
import static com.config.Configs.IMAGE_PATH;
//import static com.config.Configs.STORE_INFO_CACHE_DIR;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.models.aliex.store.AliexStoreInfo;
import com.models.aliex.crawl.CrawlDataPageBase;
import com.models.aliex.crawl.CrawlDataStoreBase;
import com.models.aliex.crawl.CrawlPageProductItem;
import com.models.amazon.AmzListingItem;
import com.ping.control.CrawlProcessListener;
import com.ping.service.crawl.CrawlerMachine;
import com.ping.view.ClientHomePanel;
import com.utils.CookieUtil;
import com.utils.EncryptUtil;
import com.utils.Utils;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import org.apache.commons.io.FileUtils;
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
import java.util.HashSet;
import java.util.Set;
import javax.swing.JFrame;
import org.openqa.selenium.JavascriptExecutor;
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

    public void doFillBaseInfo(String imagePath) {

//        WebElement element1 = driver.findElement(By.xpath("//input[@placeholder='Label']"));
//        WebDriverWait wait1 = new WebDriverWait(driver, 100);
//        WebElement element1 = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[2]/div[1]/div/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[1]/div[1]/div/div[1]/div/div/kat-input/input")));
        WebElement element1 = findWithFullXPath("/html/body/div[1]/div[2]/div[1]/div/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[1]/div[1]/div/div[1]/div/div/kat-input/input");
//        WebElement element1 = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[1]/div/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[1]/div[1]/div/div[1]/div/div/kat-input/input"));
//        JavascriptExecutor executor = (JavascriptExecutor) driver;
//        executor.executeScript("arguments[0].clear();", element1);
//        executor.executeScript("arguments[0].sendKeys('imagePath');", element1);
//        WebElement element1 = driver.findElement(By.id("katal-id-1"));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            java.util.logging.Logger.getLogger(AmzListingCrawlSvs.class.getName()).log(Level.SEVERE, null, ex);
        }
////        JavascriptExecutor executor = (JavascriptExecutor) driver;
////        executor.executeScript("arguments[0].sendKeys();", element1);
        element1.clear();
        element1.sendKeys("imagePath");

//        WebDriverWait wait2 = new WebDriverWait(driver, 100);
//        WebElement element2 = wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[2]/div[1]/div/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[1]/div[1]/div/div[2]/div/div/div[2]/kat-textarea/textarea")));
        WebElement element2 = findWithFullXPath("/html/body/div[1]/div[2]/div[1]/div/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[1]/div[1]/div/div[2]/div/div/div[2]/kat-textarea/textarea");
//        WebElement element2 = driver.findElement(By.xpath("//*[@id=\"katal-id-2\"]"));
//        WebElement element2 = driver.findElement(By.id("katal-id-2"));
        element2.clear();
        element2.sendKeys("Instruction " + imagePath);

        try {
            Screen s = new Screen();
            s.click(imageUpload);
            s.wait(fileInputTextBox, 20);
            s.type(fileInputTextBox, imagePath);
            s.click(openButton);

            s.wait(imageUploaded, 20);
        } catch (FindFailed ex) {
            Logger.getLogger(AmzListingCrawlSvs.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void doAddingCustomizePanel() {

//        try {
//        WebElement ele = driver.findElement(By.xpath("//kat-button[@label='Add customization']"));
//        WebDriverWait waitele = new WebDriverWait(driver, 100);
        WebElement ele = findWithFullXPath("/html/body/div[1]/div[2]/div[1]/div/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[2]/div/div/div[1]/div[2]/div[2]/div/kat-button/button");
//        WebElement ele = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[2]/div/div/div[1]/div[2]/div[2]/div/kat-button/button"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", ele);
//        ele.click();

        try {
            Thread.sleep(200);
        } catch (InterruptedException ex) {
            java.util.logging.Logger.getLogger(AmzListingCrawlSvs.class.getName()).log(Level.SEVERE, null, ex);
        }

        List<WebElement> optionElements = driver.findElements(By.className("choice-content__3DVcc"));
        if (optionElements != null && !optionElements.isEmpty()) {
            for (WebElement webElement : optionElements) {
                List<WebElement> infoElements = webElement.findElements(By.className("choice-header-wrapper__20fkp"));
                if (infoElements != null && !infoElements.isEmpty()) {
                    if (infoElements.get(0).getText().equals("Option Dropdown")) {
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
                    if (label.equals("Add customization")) {
                        webElement.click();
                        break;
                    }
                }
            }
        }

    }

    public void doUpdateCustomizationIfno() {
//        List<WebElement> customizationElements = driver.findElements(By.className("inner-border__NI92i"));

        for (int i = 0; i < 3; i++) {
            setCustomizationInfo(i);
        }
        
        
        doAddingOptions();
        
    }

    public void setCustomizationInfo(int index) {
//        WebElement elementOptionLabel = webElement.findElement(By.id("katal-id-8"));
        WebElement elementOptionLabel = findWithFullXPath(getCustomLabelXpath(index));

        elementOptionLabel.clear();
        elementOptionLabel.sendKeys("Customization " + index);

//        WebElement elementInstruction = webElement.findElement(By.id("katal-id-9"));
        WebElement elementInstruction = findWithFullXPath(getCustomInstructXpath(index));
        elementInstruction.clear();
        elementInstruction.sendKeys("Instruction Customization " + index);

//        for (int i = 0; i < 6; i++) {
//            doAddingOptionInfo(index);
//        }
    }

    public void doAddingOptions() {
        for (int i = 0; i < 3; i++) {
            for (int j = 3; j < 6; j++) {
                doAddingOptionInfo(i, j);
            }
        }
    }

    private String getCustomLabelXpath(int index) {
        switch (index) {
            case 0:
                return "/html/body/div[1]/div[2]/div[1]/div/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[2]/div/div/div[1]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[1]/div[2]/div/div[1]/div/kat-input/input";
            case 1:
                return "/html/body/div[1]/div[2]/div[1]/div/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[2]/div/div/div[1]/div[2]/div[1]/div[5]/div/div[2]/div/div/div[1]/div[2]/div/div[1]/div/kat-input/input";
            case 2:
                return "/html/body/div[1]/div[2]/div[1]/div/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[2]/div/div/div[1]/div[2]/div[1]/div[8]/div/div[2]/div/div/div[1]/div[2]/div/div[1]/div/kat-input/input";
        }
        return null;
    }

    private String getCustomInstructXpath(int index) {
        switch (index) {
            case 0:
                return "/html/body/div[1]/div[2]/div[1]/div/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[2]/div/div/div[1]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[1]/div[2]/div/div[2]/div/kat-input/input";
            case 1:
                return "/html/body/div[1]/div[2]/div[1]/div/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[2]/div/div/div[1]/div[2]/div[1]/div[5]/div/div[2]/div/div/div[1]/div[2]/div/div[2]/div/kat-input/input";
            case 2:
                return "/html/body/div[1]/div[2]/div[1]/div/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[2]/div/div/div[1]/div[2]/div[1]/div[8]/div/div[2]/div/div/div[1]/div[2]/div/div[2]/div/kat-input/input";
        }
        return null;
    }

    private String getAddOptionXpath(int index, int i) {
        
        if(i < 3) return "";
        
        switch (index) {
            case 0:
                return "/html/body/div[1]/div[2]/div[1]/div/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[2]/div/div/div[1]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[1]/div[4]/div/div["+i+"]/div/kat-button/button";
            case 1:
                return "/html/body/div[1]/div[2]/div[1]/div/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[2]/div/div/div[1]/div[2]/div[1]/div[5]/div/div[2]/div/div/div[1]/div[4]/div/div["+i+"]/div/kat-button/button";
            case 2:
                return "/html/body/div[1]/div[2]/div[1]/div/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[2]/div/div/div[1]/div[2]/div[1]/div[8]/div/div[2]/div/div/div[1]/div[4]/div/div["+i+"]/div/kat-button/button";
        }
        return null;
    }

    private WebElement findWithFullXPath(String xpath) {
        WebDriverWait waitele = new WebDriverWait(driver, 100);
        return waitele.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
    }

    public void doAddingOptionInfo(int index, int i) {
        WebElement ele = findWithFullXPath(getAddOptionXpath(index, i));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", ele);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            java.util.logging.Logger.getLogger(AmzListingCrawlSvs.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean doAutoClick(JFrame jFrame) {
        try {

            Screen s = new Screen();

            s.click(imageUpload);
            s.wait(fileInputTextBox, 20);
            s.type(fileInputTextBox, "D:\\ImageTest\\VPS_daup061601\\20119641.jpg");
            s.click(openButton);
            s.click(addCustomizeMore);
            s.click(addCustomizeOption);
            s.click(addCustomizeConfirm);
            s.click(imageUpload);

//            List<WebElement> activeElements = driver.findElements(By.className("sg-col-4-of-24 sg-col-4-of-12 sg-col-4-of-36 s-result-item s-asin sg-col-4-of-28 sg-col-4-of-16 AdHolder sg-col sg-col-4-of-20 sg-col-4-of-32"));
//            List<WebElement> activeElements = driver.findElements(By.className("s-result-item s-asin"));
//            List<WebElement> activeElements = driver.findElements(By.className("sg-col-4-of-24 sg-col-4-of-12 sg-col-4-of-36 s-result-item s-asin sg-col-4-of-28 sg-col-4-of-16 sg-col sg-col-4-of-20 sg-col-4-of-32"));
//            List<WebElement> activeElements = driver.findElements(By.xpath("//*[@class='image-upload-square']"));
//            List<WebElement> activeElements = driver.findElements(By.className("image-upload-square"));
//            if (activeElements != null && activeElements.size() > 0) {
//                activeElements.get(0).click();
//
//                Screen s = new Screen();
//                Pattern fileInputTextBox = new Pattern("D:\\Github\\DropshipEsty\\DropshipEsty\\input.PNG");
//                Pattern openButton = new Pattern("D:\\Github\\DropshipEsty\\DropshipEsty\\open.PNG");
//                s.wait(fileInputTextBox, 20);
//                s.type(fileInputTextBox, "D:\\ImageTest\\VPS_daup061601\\20119641.jpg");
//                s.click(openButton);
//
////                for (WebElement element : activeElements) {
////                    String asin = element.getAttribute("data-asin");
////                    if (asinClick.equals(asin)) {
////                        element.click();
////                        return true;
////                    }
////                }
//            }
        } catch (org.openqa.selenium.NoSuchElementException ex) {
            System.out.println("" + ex);
        } catch (FindFailed ex) {
            Logger.getLogger(AmzListingCrawlSvs.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("" + ex);
        }

        return false;
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
                                String text = option.text();

                                if (text.contains("Customization")) {
//                                    System.out.println("" + option.attr("data-action-string"));
                                    amzListingItem.setCustomLink(option.attr("data-action-string"));
                                    if (!setSkus.contains(amzListingItem.getSku())) {
                                        setSkus.add(amzListingItem.getSku());
                                        list.add(amzListingItem);
//                                        System.out.println("" + amzListingItem);
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
