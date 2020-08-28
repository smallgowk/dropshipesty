/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ping.control;

import com.ping.view.CustomImageDesignPannel;
import java.util.logging.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author duyuno
 */
public class PaintThread extends Thread {

    private static final Logger LOGGER = Logger.getLogger(PaintThread.class.getSimpleName());
    CustomImageDesignPannel customImageDesignPannel;
    
    public PaintThread(CustomImageDesignPannel customImageDesignPannel) {
        this.customImageDesignPannel = customImageDesignPannel;
    }

    public boolean isStop = false;

    public void doStop() {
        isStop = true;

        try {
            interrupt();
        } catch (Exception ex) {

        } finally {
//            crawlProcessListener.onPushState("", "Stopped");
//            crawlProcessListener.onFinishPage("");
        }
    }

    @Override
    public void run() {
        //
        while (!isStop) {
            customImageDesignPannel.repaint();
            try {
                Thread.sleep(300);
            } catch (InterruptedException ex) {
                java.util.logging.Logger.getLogger(PaintThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }

    }

    
}
