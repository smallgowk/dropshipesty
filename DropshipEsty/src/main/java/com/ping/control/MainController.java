/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ping.control;

import com.models.aliex.store.inputdata.BaseStoreOrderInfo;
import com.models.aliex.store.inputdata.SnakeBaseStoreOrderInfo;
import java.util.ArrayList;
import java.util.Set;

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
    
    Set<String> listColor;
    Set<String> listSizes;
    String linkStore;
    String category;
    String description;
    String link1;
    String link2;
    float basePrice;

    public Set<String> getListColor() {
        return listColor;
    }

    public void setListColor(Set<String> listColor) {
        this.listColor = listColor;
    }

    public Set<String> getListSizes() {
        return listSizes;
    }

    public void setListSizes(Set<String> listSizes) {
        this.listSizes = listSizes;
    }

    

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

    public String getLink1() {
        return link1;
    }

    public void setLink1(String link1) {
        this.link1 = link1;
    }

    public String getLink2() {
        return link2;
    }

    public void setLink2(String link2) {
        this.link2 = link2;
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
    
    
    
    public String getColorStr() {
        if(listColor == null) return "";
        
        StringBuilder sb = new StringBuilder();
        for(String s : listColor) {
            if(sb.length() == 0) {
                sb.append(s);
            } else {
                sb.append(", ").append(s);
            }
        }
        
        return sb.toString();
    }
    
    
    public String getSizeStr() {
        if(listSizes == null) return "";
        
        StringBuilder sb = new StringBuilder();
        for(String s : listSizes) {
            if(sb.length() == 0) {
                sb.append(s);
            } else {
                sb.append(", ").append(s);
            }
        }
        
        return sb.toString();
    }

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
                SnakeBaseStoreOrderInfo baseStoreOrderInfo = SnakeBaseStoreOrderInfo.createInstance(linkStore, listColor, listSizes, category, description, link1, link2, basePrice);
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
        sb.append(getColorStr()).append(", \n");
        sb.append(getSizeStr()).append(", \n");
        sb.append(getCategory()).append(", \n");
        sb.append(getBasePrice()).append(", \n");
        sb.append(getDescription()).append(", \n");
        return sb.toString(); //To change body of generated methods, choose Tools | Templates.
    }

    
}
