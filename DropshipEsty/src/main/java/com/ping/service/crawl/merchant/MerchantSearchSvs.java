/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ping.service.crawl.merchant;

import com.config.AuthenConfig;
import com.google.gson.Gson;
import com.models.aliex.MerchantModel;
import static com.ping.service.crawl.CrawlerMachine.BTN_SUBMIT_MERCHANT;
import static com.ping.service.crawl.CrawlerMachine.EDT_ACCOUNT_ID_MERCHANT;
import static com.ping.service.crawl.CrawlerMachine.EDT_PASSWORD_MERCHANT;
import com.ping.service.crawl.aliex.AliexCrawlSvs;
import com.utils.AWSUtil;
import com.utils.StringUtils;
import com.utils.Utils;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author PhanDuy
 */
public class MerchantSearchSvs {

    public static final String MERCHANT_WORD_SEARCH_URL_TEMP = "https://www.merchantwords.com/search/us/{keyword}/sort-highest";

    private static MerchantSearchSvs merchantSearchSvs;

    private boolean isLogined;

    public static MerchantSearchSvs getInstance() {
        if (merchantSearchSvs == null) {
            merchantSearchSvs = new MerchantSearchSvs();
        }

        return merchantSearchSvs;
    }

    public ArrayList<String> searchKeywords(String mainKey) {

        if (StringUtils.isEmpty(mainKey)) {
            return null;
        }

        ArrayList listKeywords = new ArrayList<>();

//        if(!AliexCrawlSvs.getInstance().isReady()) {
//            AliexCrawlSvs.getInstance().initDriver();
//            isLogined = false;
//        }
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(MerchantSearchSvs.class.getName()).log(Level.SEVERE, null, ex);
//        }
        if (!isLogined) {
            try {
                login();
            } catch (Exception ex) {
                 ex.printStackTrace();
            }
        }

//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(MerchantSearchSvs.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        String fullKeys = mainKey.toLowerCase();
        listKeywords.add(mainKey);
        System.out.println("Start search " + mainKey);

//        WebElement webElementAccount = AliexCrawlSvs.getInstance().driver.findElement(By.id("usersearchbox"));
//        WebElement webElementSubmit = AliexCrawlSvs.getInstance().driver.findElement(By.className("btn btn-loader"));
//        if (webElementAccount != null) {
//            webElementAccount.sendKeys(mainKey);
//        }
//        webElementSubmit.click();
        String url = MERCHANT_WORD_SEARCH_URL_TEMP.replace("{keyword}", URLEncoder.encode(mainKey));

        AliexCrawlSvs.getInstance().goToPage(url);
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(MerchantSearchSvs.class.getName()).log(Level.SEVERE, null, ex);
        }
        AliexCrawlSvs.getInstance().scrollToBottom();

        String pageSource = AliexCrawlSvs.getInstance().getPageSource();
        Document doc = Jsoup.parse(pageSource);

//        Document doc = AliexCrawlSvs.getInstance().processPage(url);
//        Elements elements = doc.select("table[class='responsive'] > tbody");
        Elements elements = doc.select("input[type='checkbox']");
        Gson gson = new Gson();

        if (elements != null && !elements.isEmpty()) {
            for (Element element : elements) {

                String data = element.attr("data-collection");
                if (!StringUtils.isEmpty(data)) {
                    MerchantModel merchantModel = gson.fromJson(data, MerchantModel.class);
                    String text = merchantModel.getKwp();
                    if (StringUtils.isEmpty(text)) {
                        continue;
                    }

                    listKeywords.add(StringUtils.getPrefixCapitalWord(text));
                    if (listKeywords.size() >= 100) {
                        break;
                    }
                }
            }
        }

        System.out.println("" + listKeywords.toString());
        if (!listKeywords.isEmpty()) {
            Utils.saveMerchantInfo(mainKey, listKeywords);
        }
        return listKeywords;
    }
    
    public ArrayList<String> getFromCache(String mainKey) {
        return Utils.getMerchantInfo(mainKey);
    }

    public void login() {
        AliexCrawlSvs.getInstance().goToPage("https://www.merchantwords.com/login");
        
//        List<WebElement> we = AliexCrawlSvs.getInstance().driver.findElements(By.xpath("//form[@id='loginForm']"));
//        AliexCrawlSvs.getInstance().driver.switchTo().frame(we.get(0)); 
//        WebElement activeElement = AliexCrawlSvs.getInstance().driver.switchTo().activeElement();

//        WebElement webElementAccount = activeElement.findElement(By.id(EDT_ACCOUNT_ID_MERCHANT));
//        WebElement webElementPassword = activeElement.findElement(By.id(EDT_PASSWORD_MERCHANT));
//        WebElement webElementSubmit = activeElement.findElement(By.id(BTN_SUBMIT_MERCHANT));
        
    WebElement webElementAccount = (new WebDriverWait(AliexCrawlSvs.getInstance().driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id(EDT_ACCOUNT_ID_MERCHANT)));
    WebElement webElementPassword = (new WebDriverWait(AliexCrawlSvs.getInstance().driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id(EDT_PASSWORD_MERCHANT)));
    WebElement webElementSubmit = (new WebDriverWait(AliexCrawlSvs.getInstance().driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id(BTN_SUBMIT_MERCHANT)));


//        WebElement webElementAccount = AliexCrawlSvs.getInstance().driver.findElement(By.id(EDT_ACCOUNT_ID_MERCHANT));
//        WebElement webElementPassword = AliexCrawlSvs.getInstance().driver.findElement(By.id(EDT_PASSWORD_MERCHANT));
//        WebElement webElementSubmit = AliexCrawlSvs.getInstance().driver.findElement(By.id(BTN_SUBMIT_MERCHANT));

        if (webElementAccount != null) {
            webElementAccount.sendKeys(AuthenConfig.merchantUser);
//            webElementAccount.sendKeys("nguyenvantrungaliex@gmail.com");
        }

        if (webElementPassword != null) {
            webElementPassword.sendKeys(AuthenConfig.merchantPassword);
//            webElementPassword.sendKeys("HIEUHIEU1");
        }

        webElementSubmit.submit();

        isLogined = true;
    }
}
