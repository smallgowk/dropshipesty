/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ping.view;

import com.config.Configs;
import com.ping.control.CrawlProcessListener;
import com.ping.control.MainController;
import com.models.aliex.store.BaseStoreInfo;
import com.models.aliex.store.inputdata.BaseStoreOrderInfo;
import com.ping.control.CustomController;
import com.ping.control.CustomController.ActionListener;
import com.ping.control.CustomController.STATE;
import com.ping.service.crawl.aliex.AliexCrawlSvs;
import com.ping.service.crawl.amzlisting.AmzListingCrawlSvs;
import com.ping.tcpclient.ResponseObj;
import com.utils.DialogUtil;
import com.utils.OSUtil;
import com.utils.WindowsShortcut;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author Admin
 */
public class ClientHomePanel extends BasePanel {

    JFrame topFrame;
    JFileChooser chooser;
    String choosertitle;

    ArrayList<BaseStoreInfo> listStorePage = new ArrayList<>();

    CustomController mainController;

    JPanel controls;

//    ProcessPannel processPannel;
    public ClientHomePanel() {
        initComponents();

        setTitle("Home");
        setMenuActionCommand("MainHome");
        initData();

//        fakeData();
    }

//    private void fakeData() {
//        txtProfilePath.setText("C:\\Users\\PhanDuy\\Desktop\\Profiles\\long (SnakeAccount) - Chrome.lnk");
//    }

    public void initData() {

        mainController = new CustomController();
        mainController.setCrawlProcessListener(crawlProcessListener);
        mainController.setActionListener(actionListener);

        topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);

        String pathStr = null;
        if (OSUtil.isWindows()) {
            pathStr = Configs.CONFIG_FOLDER_PATH + "chromedriver.exe";
        } else {
            pathStr = Configs.CONFIG_FOLDER_PATH + "chromedriver";
        }

        if (pathStr != null) {
            System.setProperty("webdriver.chrome.driver", pathStr);
        }
        
        txtProfilePath.setText(Configs.profilePath);
        txtInfoPath.setText(Configs.infoCustomPath);
        txtImageFolder.setText(Configs.imageCustomPath);
    }

    public void disableButton() {

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        lbStatus = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtProfilePath = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtInfoPath = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtImageFolder = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnStartCrawl = new javax.swing.JButton();
        btnStop = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        txtLogs = new javax.swing.JTextArea();

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Cài đặt"));
        jPanel3.setFocusTraversalPolicyProvider(true);

        jLabel2.setText("Profile Path");

        jButton1.setText("Browse...");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Customization Info");

        jButton3.setText("Browse...");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel3.setText("Image folder");

        jButton4.setText("Browse...");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtImageFolder, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtInfoPath, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtProfilePath, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtProfilePath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtInfoPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtImageFolder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        btnStartCrawl.setText("Start");
        btnStartCrawl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartCrawlActionPerformed(evt);
            }
        });

        btnStop.setText("Stop");
        btnStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopActionPerformed(evt);
            }
        });

        jButton2.setText("Load Profile");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnStartCrawl, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnStop, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnStartCrawl, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnStop, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(20, 20, 20))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Logs"));

        txtLogs.setColumns(20);
        txtLogs.setLineWrap(true);
        txtLogs.setRows(5);
        txtLogs.setWrapStyleWord(true);
        jScrollPane12.setViewportView(txtLogs);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    BaseStoreInfo storePageInfo;

    public void browsePage() {

    }

    public void loadStore(BaseStoreInfo storePageInfo) {

    }

    public void startCrawl() {

    }

    public long lastLoad;
//    public int pageCount;

    public void nextPage() {

    }

    public void stopCrawl() {
//        aliexCrawlThread.stopCrawl();
    }

    private void btnStartCrawlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartCrawlActionPerformed
//        UploadImagelThread uploadImagelThread = new UploadImagelThread(topFrame);
//        uploadImagelThread.start();

        txtLogs.setText("");

        if(!mainController.isStop()) return;
        
        if (txtInfoPath.getText().trim().isEmpty()) {
            DialogUtil.showErrorMessage(topFrame, "", "Vui lòng nhập đường dẫn file info!");
            return;
        }

        mainController.setInfoPath(txtInfoPath.getText().trim());

        if (txtImageFolder.getText().trim().isEmpty()) {
            DialogUtil.showErrorMessage(topFrame, "", "Vui lòng nhập đường dẫn thư mục ảnh!");
            return;
        }
        mainController.setImageFolderPath(txtImageFolder.getText().trim());

        mainController.doAction();
    }//GEN-LAST:event_btnStartCrawlActionPerformed

    CrawlProcessListener crawlProcessListener = new CrawlProcessListener() {

        @Override
        public void onPushState(String storeSign, String state) {
//            if (processPannel != null) {
//                processPannel.updateStatus(storeSign, state);
//            }
            txtLogs.append(state);
            txtLogs.append("\n");
        }

        @Override
        public void onStop(String storeSign) {
//            mainController.pause();
//            mainController.stopCrawl();
        }

        @Override
        public void onStartProcess(String storeSign, String accNo) {
//            ProcessState processState = ProcessState.createInstance(storeSign, accNo);
//            processPannel.addData(processState);
            txtLogs.setText("");
        }

        @Override
        public void onStopToLogin(String currentUrl, String storeSign) {
//            mainController.pause();
//            processPannel.updateStatus(storeSign, "Need to login...");
//            CookieUtil.deleteCookies();

//            LoginThread loginThread = new LoginThread(true, currentUrl);
//            loginThread.start();
//            AliexCrawlSvs.getInstance().goToLogin(currentUrl);
//            DialogUtil.showInfoMessage(null, "Vui lòng thực hiện đăng nhập lại sau đó ấn Resume!");
        }

        @Override
        public void onFinishPage(String storeSign) {
            mainController.finish();
            actionListener.onStateChange(STATE.STOP);
            txtLogs.append("Done!");
            txtLogs.append("\n");
        }

        @Override
        public void onPushErrorRequest(String storeSign, ResponseObj responseObj) {
            DialogUtil.showErrorMessage(topFrame, "", responseObj.getMessage());
        }

        @Override
        public void onProgress(String percent) {
            lbStatus.setText(percent);
        }

    };

    ActionListener actionListener = new ActionListener() {
        @Override
        public void onStateChange(STATE state) {
            switch (state) {
                case STOP:
//                    btnStartCrawl.setText("Start");
//                    btnStartCrawl.setEnabled(true);
//                    btnStop.setEnabled(false);
                    lbStatus.setText("");
                    break;
                case RUNNING:
//                    btnStartCrawl.setText("Pause");
//                    btnStop.setEnabled(true);
//                    btnStartCrawl.setEnabled(false);
//                    btnStop.setEnabled(true);
                    break;
                case PAUSING:
//                    btnStartCrawl.setText("Resume");
//                    btnStop.setEnabled(true);
                    break;
            }
        }

        @Override
        public void onNotAuthen() {
        }

        @Override
        public void onLicenseInvalid() {
        }
    };

//    public void creatStoreState(BaseStoreOrderInfo baseStoreOrderInfo) {
//        JPanel rowWords = new JPanel();
//
//        rowWords.add(DialogUtil.createLabel(baseStoreOrderInfo.acc_no, 80, null));
//        rowWords.add(new JSeparator(), BorderLayout.CENTER);
//        
//        rowWords.add(DialogUtil.createLabel(baseStoreOrderInfo.acc_no, 80, null));
//        rowWords.add(new JSeparator(), BorderLayout.CENTER);
//        
//        controls.add(rowWords);
//    }
    public static JScrollPane displayData(ArrayList<BaseStoreOrderInfo> listTradeMarkItems, int width, int height) {
//        ArrayList<TradeMarkCheckItem> listTradeMarkItems = new ArrayList<>();

        JPanel controls = new JPanel();
//        SpringLayout layout = new SpringLayout();
//        controls.setLayout(layout);
        controls.setLayout(new BoxLayout(controls, BoxLayout.Y_AXIS));

        for (int i = 0, size = listTradeMarkItems.size(); i < size; i++) {

            BaseStoreOrderInfo tradeMarkItem = listTradeMarkItems.get(i);

//            ProcessPanel processPanel = new ProcessPanel();
//            controls.add(processPanel);
//            controls.add(rowWords);
//            controls.add(rowWords);
//            JSeparator line = new JSeparator();
//            line.setSize(width, 1);
//            controls.add(line);
//            System.out.println("" + controls.getPreferredSize().getWidth() + " | " + controls.getPreferredSize().getHeight());
        }

        System.out.println("" + controls.getWidth() + " | " + controls.getHeight());

        JScrollPane sp = new JScrollPane(controls);
//        Dimension d = sp.getPreferredSize();
//        sp.setPreferredSize(new Dimension(d.width + 30, d.height < height ? d.height + 30 : height));
//        Dimension d = sp.getPreferredSize();
        sp.setSize(new Dimension(width, height));
//        sp.add(controls);

        sp.getVerticalScrollBar().setUnitIncrement(16);

//        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                sp.getVerticalScrollBar().setValue(0);
//            }
//        });
//        jPanel.removeAll();
//        jPanel.add(sp);
        return sp;
    }

    public void saveStore() {
//        String filePath = txtStoreFilePath.getText();
//
//        if (filePath.isEmpty()) {
//            return;
//        }
//        try {
//            ExcelUtils.updateStoreStatus(filePath, storePageInfo, true, totalProduct);
//        } catch (IOException | InvalidFormatException ex) {
//        }
//
//        for (BaseStoreInfo spi : listStorePage) {
//            if (spi.getOrginalStoreLink().equals(storePageInfo.getOrginalStoreLink())) {
//                spi.setState("Done");
//                break;
//            }
//        }
    }

    private void btnStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopActionPerformed
        // TODO add your handling code here:
        mainController.stopCrawl();

        btnStartCrawl.setText("Start");
//        btnStop.setEnabled(false);
        txtLogs.append("Stopped");
        txtLogs.append("\n");
    }//GEN-LAST:event_btnStopActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        choosertitle = "Select file:";
        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle(choosertitle);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        //
        // disable the "All files" option.
        //
        chooser.setAcceptAllFileFilterUsed(false);
        //
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            String path = chooser.getSelectedFile().getPath();
            Configs.changeProfilePath(path);
            txtProfilePath.setText(Configs.profilePath);
        } else {
            System.out.println("No Selection ");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        if (txtProfilePath.getText().trim().isEmpty()) {
            DialogUtil.showErrorMessage(topFrame, "", "Vui lòng nhập đường dẫn profile chrome!");
            return;
        }
        
        String path = txtProfilePath.getText().trim();
        
        File file = new File(path);
        if(!file.exists()) return;
        
        try {
            WindowsShortcut windowsShortcut = new WindowsShortcut(file);
            String params = windowsShortcut.getCommandLineArguments();
            String[] parts = params.split(Pattern.quote("="));
            String profileName = parts[1].substring(1, parts[1].length() - 1);
            
            AmzListingCrawlSvs.getInstance().initDriver(profileName);
//            AliexCrawlSvs.getInstance().goToPage("https://sellercentral.amazon.com/gestalt/managecustomization/index.html?sku=TLT96593_32602048616_3");
//            AmzListingCrawlSvs.getInstance().goToPage("https://sellercentral.amazon.com");
            AmzListingCrawlSvs.getInstance().goToPage("https://sellercentral.amazon.com/inventory");
            
        } catch (IOException ex) {
            Logger.getLogger(ClientHomePanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ClientHomePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        choosertitle = "Select file:";
        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle(choosertitle);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        //
        // disable the "All files" option.
        //
        chooser.setAcceptAllFileFilterUsed(false);
        //
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            String path = chooser.getSelectedFile().getPath();
            Configs.changeInfoCustomPathPath(path);
            txtInfoPath.setText(Configs.infoCustomPath);
        } else {
            System.out.println("No Selection ");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        choosertitle = "Select file:";
        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle(choosertitle);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        //
        // disable the "All files" option.
        //
        chooser.setAcceptAllFileFilterUsed(false);
        //
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            String path = chooser.getSelectedFile().getPath();
            Configs.changeImageFolderCustomPathPath(path);
            txtImageFolder.setText(Configs.imageCustomPath);
        } else {
            System.out.println("No Selection ");
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnStartCrawl;
    private javax.swing.JButton btnStop;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JLabel lbStatus;
    private javax.swing.JTextField txtImageFolder;
    private javax.swing.JTextField txtInfoPath;
    private javax.swing.JTextArea txtLogs;
    private javax.swing.JTextField txtProfilePath;
    // End of variables declaration//GEN-END:variables
}
