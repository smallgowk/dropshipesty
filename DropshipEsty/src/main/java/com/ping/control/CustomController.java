/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ping.control;

/**
 *
 * @author PhanDuy
 */
public class CustomController {

    public enum STATE {
        STOP, RUNNING, PAUSING
    }

    public STATE state = STATE.STOP;

    public int currentIndex;

    CustomCrawlThread processCrawlThread;
    CrawlProcessListener crawlProcessListener;
    ActionListener actionListener;

    String infoPath;
    String imageFolderPath;
    boolean isIgnoreCustomed;
    boolean isTestMode;
    boolean isRunOnlyOne;
    boolean isSaveAfterFinish;
    boolean isAddText;
    String skuTest;

    public void setInfoPath(String infoPath) {
        this.infoPath = infoPath;
    }

    public void setImageFolderPath(String imageFolderPath) {
        this.imageFolderPath = imageFolderPath;
    }

    public void setIsIgnoreCustomed(boolean isIgnoreCustomed) {
        this.isIgnoreCustomed = isIgnoreCustomed;
    }

    public boolean isIsIgnoreCustomed() {
        return isIgnoreCustomed;
    }

    public void setIsTestMode(boolean isTestMode) {
        this.isTestMode = isTestMode;
    }

    public void setIsRunOnlyOne(boolean isRunOnlyOne) {
        this.isRunOnlyOne = isRunOnlyOne;
    }

    public void setIsSaveAfterFinish(boolean isSaveAfterFinish) {
        this.isSaveAfterFinish = isSaveAfterFinish;
    }

    public void setSkuTest(String skuTest) {
        this.skuTest = skuTest;
    }

    public boolean isIsAddText() {
        return isAddText;
    }

    public void setIsAddText(boolean isAddText) {
        this.isAddText = isAddText;
    }
    
    

    public void setCrawlProcessListener(CrawlProcessListener crawlProcessListener) {
        this.crawlProcessListener = crawlProcessListener;
    }

    public void setActionListener(ActionListener actionListener) {
        this.actionListener = actionListener;
    }

    public void doAction() {

        if (null != state) {
            switch (state) {
                case STOP:
                    System.out.println("" + this.toString());

                    startCrawl(infoPath, imageFolderPath);
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
    }

    public boolean isStop() {
        return state == STATE.STOP;
    }

    public boolean isRunning() {
        return state == STATE.RUNNING;
    }

    public void changeState(STATE state) {
        this.state = state;
    }

    public void startCrawl(String infoPath, String imageFolderPath) {

        if (processCrawlThread != null) {
            processCrawlThread.doStop();
        }

        processCrawlThread = new CustomCrawlThread(this, crawlProcessListener);
        processCrawlThread.start();

        state = STATE.RUNNING;

        actionListener.onStateChange(state);

    }

    public void stopCrawl() {
        if (processCrawlThread != null) {
            processCrawlThread.doStop();
        }

        state = STATE.STOP;

        actionListener.onStateChange(state);

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
        sb.append(infoPath).append(", \n");
        sb.append(imageFolderPath).append(", \n");
        return sb.toString(); //To change body of generated methods, choose Tools | Templates.
    }

    public interface ActionListener {

        public void onStateChange(STATE state);

        public void onNotAuthen();

        public void onLicenseInvalid();
    }

}
