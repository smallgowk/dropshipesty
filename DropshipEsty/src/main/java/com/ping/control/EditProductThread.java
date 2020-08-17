/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ping.control;

import com.config.Configs;
import com.models.amazon.ProductAmz;
import com.pong.server.process.CrawlPageServerThread;
import com.utils.ExcelUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

/**
 *
 * @author duyuno
 */
public class EditProductThread extends Thread {

    private static final Logger LOGGER = Logger.getLogger(EditProductThread.class.getSimpleName());

    EditController editController;
    CrawlProcessListener crawlProcessListener;

    public EditProductThread(EditController editController, CrawlProcessListener crawlProcessListener) {
        this.editController = editController;
        this.crawlProcessListener = crawlProcessListener;
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

        if (isStop) {
            crawlProcessListener.onFinishPage("");
            return;
        }
        editController.editData();

        crawlProcessListener.onFinishPage("");

    }

}
