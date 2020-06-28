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
import javax.swing.JFrame;

/**
 *
 * @author PhanDuy
 */
public class AmzListingCrawlSvs extends CrawlerMachine {

    private static AmzListingCrawlSvs amzListingCrawlSvs;

    public static AmzListingCrawlSvs getInstance() {
        if (amzListingCrawlSvs == null) {
            amzListingCrawlSvs = new AmzListingCrawlSvs();
//            aliexCrawlSvs.initDriver();
        }
        return amzListingCrawlSvs;
    }

    public boolean doAutoClick(JFrame jFrame) {
        try {

            Screen s = new Screen();
            Pattern imageUpload = new Pattern(IMAGE_PATH + "imageUpload.PNG");
            Pattern fileInputTextBox = new Pattern(IMAGE_PATH + "input.PNG");
            Pattern openButton = new Pattern(IMAGE_PATH + "open.PNG");
            Pattern addCustomizeMore = new Pattern(IMAGE_PATH + "addCustomization.PNG");
            Pattern addCustomizeOption = new Pattern(IMAGE_PATH + "customizeBoxOption.PNG");
            Pattern addCustomizeConfirm = new Pattern(IMAGE_PATH + "addCustomConfirm.PNG");

//            ImageIcon iconUpload = new ImageIcon(jFrame.getClass().getResource("/Icons/imageUpload.png"));
//            ImageIcon iconFileInputTextBox = new ImageIcon(jFrame.getClass().getResource("/Icons/input.png"));
//            ImageIcon iconOpenButton = new ImageIcon(jFrame.getClass().getResource("/Icons/open.png"));
//
//            Pattern imageUpload = new Pattern(toBufferedImage(iconUpload.getImage()));
//            Pattern fileInputTextBox = new Pattern(toBufferedImage(iconFileInputTextBox.getImage()));
//            Pattern openButton = new Pattern(toBufferedImage(iconOpenButton.getImage()));
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

        scrollToBottom(10000);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(AliexCrawlProductInfoSvs.class.getName()).log(Level.SEVERE, null, ex);
        }

        String pageSource = getPageSource();
        Document doc = Jsoup.parse(pageSource);
        Elements rows = doc.select("tr[class='mt-row']");
        if (rows != null && !rows.isEmpty()) {
            for (Element row : rows) {
                AmzListingItem amzListingItem = new AmzListingItem();
                list.add(amzListingItem);
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
                                    System.out.println("" + amzListingItem);
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
