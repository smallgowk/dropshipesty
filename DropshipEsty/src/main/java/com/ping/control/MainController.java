/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ping.control;

import com.models.aliex.store.inputdata.SnakeBaseStoreOrderInfo;
import java.util.ArrayList;
import java.util.Set;
import java.util.regex.Pattern;

/**
 *
 * @author PhanDuy
 */
public class MainController {

    public enum STATE {
        STOP,RUNNING,PAUSING
    }
       
    public STATE state = STATE.STOP;
    
    public int currentIndex;

    ProcessCrawlThread processCrawlThread;
    CrawlProcessListener crawlProcessListener;
    ActionListener actionListener;
    
//    Set<String> listColor;
//    Set<String> listSizes;
    String linkStore;
    String brandName;
    String category;
    String description;
    ArrayList<String> links;
    ArrayList<String> bullets;
    float basePrice;
    public String outerMaterialType;
    public String materialComposition;
    public int handlingTime;
    public boolean isFilterImage;

//    public Set<String> getListColor() {
//        return listColor;
//    }
//
//    public void setListColor(Set<String> listColor) {
//        this.listColor = listColor;
//    }
//
//    public Set<String> getListSizes() {
//        return listSizes;
//    }
//
//    public void setListSizes(Set<String> listSizes) {
//        this.listSizes = listSizes;
//    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageLinks(String linkData) {
        String[] linkParts = linkData.split(Pattern.quote("\n"));
        
        links = new ArrayList<>();
        
        for(String s : linkParts) {
            links.add(s);
        }
    }
    
    public void setBullets(String bulletData) {
        String[] bulletParts = bulletData.split(Pattern.quote("\n"));
        
        bullets = new ArrayList<>();
        
        for(String s : bulletParts) {
            bullets.add(s);
        }
    }
    
    public float getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(float basePrice) {
        this.basePrice = basePrice;
    }

    public String getLinkStore() {
        return linkStore;
    }

    public void setLinkStore(String linkStore) {
        this.linkStore = linkStore;
    }

    public boolean isIsFilterImage() {
        return isFilterImage;
    }

    public void setIsFilterImage(boolean isFilterImage) {
        this.isFilterImage = isFilterImage;
    }
    
    
    
//    public String getColorStr() {
//        if(listColor == null) return "";
//        
//        StringBuilder sb = new StringBuilder();
//        for(String s : listColor) {
//            if(sb.length() == 0) {
//                sb.append(s);
//            } else {
//                sb.append(", ").append(s);
//            }
//        }
//        
//        return sb.toString();
//    }
//    
//    
//    public String getSizeStr() {
//        if(listSizes == null) return "";
//        
//        StringBuilder sb = new StringBuilder();
//        for(String s : listSizes) {
//            if(sb.length() == 0) {
//                sb.append(s);
//            } else {
//                sb.append(", ").append(s);
//            }
//        }
//        
//        return sb.toString();
//    }

    public void setCrawlProcessListener(CrawlProcessListener crawlProcessListener) {
        this.crawlProcessListener = crawlProcessListener;
    }

    public void setActionListener(ActionListener actionListener) {
        this.actionListener = actionListener;
    }

    public void doAction() {
        
        if(null != state) switch (state) {
            case STOP:
                System.out.println("" + this.toString());
                SnakeBaseStoreOrderInfo baseStoreOrderInfo = SnakeBaseStoreOrderInfo.createInstance(linkStore, brandName,
                        null, null, 
                        category, description, 
                        links,
                        basePrice,
                        bullets,
                        outerMaterialType,
                        materialComposition,
                        handlingTime,
                        isFilterImage
                        );
                startCrawl(baseStoreOrderInfo);
                break;
            case RUNNING:
//                pause();
                break;
            case PAUSING:
//                resume();
                break;
            default:
                break;
        }
    }
    
    public boolean isStop() {
        return state == STATE.STOP;
    }

    public void startCrawl(SnakeBaseStoreOrderInfo baseStoreOrderInfo) {

        if (processCrawlThread != null) {
            processCrawlThread.doStop();
        }
        
        processCrawlThread = new ProcessCrawlThread(baseStoreOrderInfo, crawlProcessListener);
        processCrawlThread.start();

        state = STATE.RUNNING;
        
        actionListener.onFinish(state);

    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public void setOuterMaterialType(String outerMaterialType) {
        this.outerMaterialType = outerMaterialType;
    }

    public void setMaterialComposition(String materialComposition) {
        this.materialComposition = materialComposition;
    }

    public void setHandlingTime(int handlingTime) {
        this.handlingTime = handlingTime;
    }

    

    public void stopCrawl() {
        if (processCrawlThread != null) {
            processCrawlThread.doStop();
        }
        
        state = STATE.STOP;
        
        actionListener.onFinish(state);
        
    }
    
    public void finish() {
        if (processCrawlThread != null) {
            processCrawlThread.doStop();
        }
        
        currentIndex = 0;
        state = STATE.STOP;
    }
    
//    public void pause() {
//        if (processCrawlThread != null) {
//            processCrawlThread.doStop();
//        }
//        
//        SnakeBaseStoreOrderInfo snakeBaseStoreOrderInfo = (SnakeBaseStoreOrderInfo) getCurrentStore();
//        crawlProcessListener.onPushState(snakeBaseStoreOrderInfo.getStoreSign(), "Stopped");
//        
//        state = STATE.PAUSING;
//        
//        actionListener.onFinish(state);
//    }
    
//    public void resume() {
//        if (processCrawlThread != null) {
//            processCrawlThread.doStop();
//        }
//        BaseStoreOrderInfo baseStoreOrderInfo = getCurrentStore();
//        processCrawlThread = new ProcessCrawlThread((SnakeBaseStoreOrderInfo) baseStoreOrderInfo, crawlProcessListener);
//        processCrawlThread.start();
//        
//        state = STATE.RUNNING;
//        
//        actionListener.onFinish(state);
//    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(linkStore).append(", \n");
//        sb.append(getColorStr()).append(", \n");
//        sb.append(getSizeStr()).append(", \n");
        sb.append(getCategory()).append(", \n");
        sb.append(getBasePrice()).append(", \n");
        sb.append(getDescription()).append(", \n");
        return sb.toString(); //To change body of generated methods, choose Tools | Templates.
    }

    
}
