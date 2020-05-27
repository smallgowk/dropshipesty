/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ping.view;

import com.config.Configs;
import static com.config.Configs.listCategories;
import com.ping.control.ActionListener;
import com.ping.control.CrawlProcessListener;
import com.ping.control.MainController;
import com.models.state.ProcessState;
import com.models.aliex.store.BaseStoreInfo;
import com.models.aliex.store.inputdata.BaseStoreOrderInfo;
import com.models.aliex.store.inputdata.SnakeBaseStoreOrderInfo;
import com.ping.service.crawl.aliex.AliexCrawlSvs;
import com.ping.tcpclient.ResponseObj;
import com.utils.DialogUtil;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
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

    MainController mainController;

    JPanel controls;

    ProcessPannel processPannel;

    public ClientHomePanel() {
        initComponents();
        
        jComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(listCategories));
        
        setTitle("Home");
        setMenuActionCommand("MainHome");
        initData();

    }
    
    public void fakeData() {
        txtStoreLink.setText("https://www.etsy.com/shop/cormaidesign");
        txtLink1.setText("https://cdn.pixabay.com/photo/2015/12/01/20/28/road-1072823_960_720.jpg");
        txtLink2.setText("https://image.shutterstock.com/image-photo/fabulous-spring-view-cameo-island-600w-790346161.jpg");
        
        Set<String> colors = new HashSet<>();
        colors.add(Configs.listColor[0]);
        Set<String> sizes = new HashSet<>();
        sizes.add(Configs.listSize[0]);
        mainController.setListColor(colors);
        mainController.setListSizes(sizes);
        
        jTextAreaColor.setText(mainController.getColorStr());
        jTextAreaSizes.setText(mainController.getSizeStr());
        txtBrandName.setText("Fake Brand");
        txtDesciption.setText("Fake Description");
        txtBasePrice.setText("23");
    }

    public void initData() {

        mainController = new MainController();
        mainController.setCrawlProcessListener(crawlProcessListener);
        mainController.setActionListener(actionListener);

        topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);

//        String pathStr = null;
//        if (OSUtil.isWindows()) {
//            pathStr = Configs.CONFIG_FOLDER_PATH + "chromedriver.exe";
//        } else {
//            pathStr = Configs.CONFIG_FOLDER_PATH + "chromedriver";
//        }
//
//        if (pathStr != null) {
//            System.setProperty("webdriver.chrome.driver", pathStr);
//        }
//        AliexCrawlSvs.getInstance().initDriver();
//        if (!AliexCrawlSvs.getInstance().isHasCookies()) {
////            AliexCrawlSvs.getInstance().initDriver();
////            MerchantSearchSvs.getInstance().login();
//            boolean login = AliexCrawlSvs.getInstance().autoLoginAliex();
//            if(!login) {
//                int option = DialogUtil.showOptionsQuestionDialog(null, null, "Phiên bản trình duyệt chrome và phiên bản chromedriver không tương thích. Vui lòng cập nhật chromedriver theo phiên bản trình duyệt chrome trên máy tính!",
//                        "Cập nhật", "Đóng");
//                if (option == 0) {
//
//                    if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
//                        try {
//                            Desktop.getDesktop().browse(new URI("https://chromedriver.chromium.org/downloads?fbclid=IwAR1I_wfvE-sipnM0-ZsU-nTBZhLYX3exGq9u1ive6mEDZ8922fWQQ_B1p1M"));
//                        } catch (IOException ex) {
//                            Logger.getLogger(StartClientApp.class.getName()).log(Level.SEVERE, null, ex);
//                        } catch (URISyntaxException ex) {
//                            Logger.getLogger(StartClientApp.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//                    }
//                }
//            }
//        }
//        MerchantSearchSvs.getInstance().login();
//        AliexCrawlSvs.getInstance().autoLoginAliex();
        btnStop.setEnabled(false);

//        if (!AliexCrawlSvs.getInstance().isHasCookies()) {
//            AliexCrawlSvs.getInstance().autoLoginAliex();
//        }
//        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                processPannel = new ProcessPannel(statePannel.getWidth(), statePannel.getHeight());
//                processPannel.setBackground(Color.red);
//                statePannel.add(processPannel);
//                statePannel.validate();
//            }
//        });

        fakeData();
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

        storePanel = new javax.swing.JPanel();
        btnStartCrawl = new javax.swing.JButton();
        btnStop = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtStoreLink = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaColor = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaSizes = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();
        jComboBox = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtDesciption = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        txtLink1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtLink2 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtBasePrice = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtBrandName = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();

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

        javax.swing.GroupLayout storePanelLayout = new javax.swing.GroupLayout(storePanel);
        storePanel.setLayout(storePanelLayout);
        storePanelLayout.setHorizontalGroup(
            storePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(storePanelLayout.createSequentialGroup()
                .addGroup(storePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnStartCrawl, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(btnStop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 1, Short.MAX_VALUE))
        );
        storePanelLayout.setVerticalGroup(
            storePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(storePanelLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(btnStartCrawl, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnStop, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(276, Short.MAX_VALUE))
        );

        jPanel3.setFocusTraversalPolicyProvider(true);

        jLabel1.setText("Store Link");

        jLabel2.setText("Màu sắc");

        txtStoreLink.setColumns(20);
        txtStoreLink.setRows(2);
        txtStoreLink.setWrapStyleWord(true);
        jScrollPane1.setViewportView(txtStoreLink);

        jTextAreaColor.setEditable(false);
        jTextAreaColor.setColumns(20);
        jTextAreaColor.setRows(2);
        jTextAreaColor.setWrapStyleWord(true);
        jScrollPane2.setViewportView(jTextAreaColor);

        jButton1.setText("Click để chọn màu");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setText("Kích thước");

        jTextAreaSizes.setEditable(false);
        jTextAreaSizes.setColumns(20);
        jTextAreaSizes.setRows(2);
        jTextAreaSizes.setWrapStyleWord(true);
        jScrollPane3.setViewportView(jTextAreaSizes);

        jButton2.setText("Click để chọn kích thước");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));
        jComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxActionPerformed(evt);
            }
        });

        jLabel4.setText("Chọn category");

        jLabel5.setText("Description");

        txtDesciption.setColumns(20);
        txtDesciption.setRows(5);
        jScrollPane4.setViewportView(txtDesciption);

        jLabel6.setText("Link ảnh 1");

        jLabel7.setText("Link ảnh 2");

        jLabel8.setText("Giá cơ bản");

        jLabel9.setText("Nhập brand");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 159, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addComponent(jScrollPane4)
                    .addComponent(txtLink1)
                    .addComponent(txtLink2)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(txtBasePrice, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtBrandName)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBrandName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtLink1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtLink2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBasePrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(storePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(storePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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

        if (txtStoreLink.getText().isEmpty()) {
            DialogUtil.showErrorMessage(topFrame, "", "Vui lòng nhập link store!");
            return;
        }
        
        mainController.setLinkStore(txtStoreLink.getText());
        
        if(mainController.getListColor() == null) {
            DialogUtil.showErrorMessage(topFrame, "", "Vui lòng chọn màu!");
            return;
        }
        
        if(mainController.getListSizes() == null) {
            DialogUtil.showErrorMessage(topFrame, "", "Vui lòng chọn size!");
            return;
        }
        
        if (txtDesciption.getText().isEmpty()) {
            DialogUtil.showErrorMessage(topFrame, "", "Vui lòng nhập description!");
            return;
        }
        mainController.setDescription(txtDesciption.getText());
        
        if (txtLink1.getText().isEmpty()) {
            DialogUtil.showErrorMessage(topFrame, "", "Vui lòng nhập link ảnh 1!");
            return;
        }
        mainController.setLink1(txtLink1.getText());
        
        if (txtLink2.getText().isEmpty()) {
            DialogUtil.showErrorMessage(topFrame, "", "Vui lòng nhập link ảnh 2!");
            return;
        }
        mainController.setLink2(txtLink2.getText());

        String select = (String) jComboBox.getSelectedItem();
        mainController.setCategory(Configs.hashMapCateType.get(select));
        
        mainController.doAction();

    }//GEN-LAST:event_btnStartCrawlActionPerformed

    CrawlProcessListener crawlProcessListener = new CrawlProcessListener() {

        @Override
        public void onPushState(String storeSign, String state) {
            if (processPannel != null) {
                processPannel.updateStatus(storeSign, state);
            }
        }

        @Override
        public void onStop(String storeSign) {
//            mainController.pause();
//            mainController.stopCrawl();
        }

        @Override
        public void onStartProcess(String storeSign, String accNo) {
            ProcessState processState = ProcessState.createInstance(storeSign, accNo);
            processPannel.addData(processState);
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
            actionListener.onFinish(MainController.STATE.STOP);
        }

        @Override
        public void onPushErrorRequest(String storeSign, ResponseObj responseObj) {
            DialogUtil.showErrorMessage(topFrame, "", responseObj.getMessage());
        }

    };

    ActionListener actionListener = new ActionListener() {
        @Override
        public void onFinish(MainController.STATE state) {
            switch (state) {
                case STOP:
//                    btnStartCrawl.setText("Start");
                    btnStartCrawl.setEnabled(true);
                    btnStop.setEnabled(false);
                    break;
                case RUNNING:
//                    btnStartCrawl.setText("Pause");
//                    btnStop.setEnabled(true);
                    btnStartCrawl.setEnabled(false);
                    btnStop.setEnabled(true);
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
        btnStop.setEnabled(false);
    }//GEN-LAST:event_btnStopActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Set<String> setColors = DialogUtil.showListViewValues(0, Configs.listColor, mainController.getListColor());
        if(setColors != null) {
            mainController.setListColor(setColors);
            jTextAreaColor.setText(mainController.getColorStr());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Set<String> setSizes = DialogUtil.showListViewValues(1, Configs.listSize, mainController.getListSizes());
        if(setSizes != null) {
            mainController.setListSizes(setSizes);
            jTextAreaSizes.setText(mainController.getSizeStr());
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxActionPerformed
        // TODO add your handling code here:
        String select = (String) jComboBox.getSelectedItem();
        mainController.setCategory(Configs.hashMapCateType.get(select));
    }//GEN-LAST:event_jComboBoxActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnStartCrawl;
    private javax.swing.JButton btnStop;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea jTextAreaColor;
    private javax.swing.JTextArea jTextAreaSizes;
    private javax.swing.JPanel storePanel;
    private javax.swing.JTextField txtBasePrice;
    private javax.swing.JTextField txtBrandName;
    private javax.swing.JTextArea txtDesciption;
    private javax.swing.JTextField txtLink1;
    private javax.swing.JTextField txtLink2;
    private javax.swing.JTextArea txtStoreLink;
    // End of variables declaration//GEN-END:variables
}
