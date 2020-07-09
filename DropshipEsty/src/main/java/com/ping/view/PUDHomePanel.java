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
import com.models.aliex.store.BaseStoreInfo;
import com.models.aliex.store.inputdata.BaseStoreOrderInfo;
import com.ping.control.UploadImagelThread;
import com.ping.service.crawl.CrawlerMachine;
import com.ping.service.crawl.aliex.AliexCrawlSvs;
import com.ping.tcpclient.ResponseObj;
import com.utils.DialogUtil;
import com.utils.OSUtil;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class PUDHomePanel extends BasePanel {

    JFrame topFrame;
    JFileChooser chooser;
    String choosertitle;

    ArrayList<BaseStoreInfo> listStorePage = new ArrayList<>();

    MainController mainController;

    JPanel controls;

//    ProcessPannel processPannel;
    public PUDHomePanel() {
        initComponents();

        jComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(listCategories.toArray(new String[listCategories.size()])));

        setTitle("Home");
        setMenuActionCommand("MainHome");
        initData();

//        fakeData();
    }

    public void fakeData() {
//        txtStoreLink.setText("https://www.etsy.com/shop/cormaidesign");
        txtStoreLink.setText("dropshiptool.vn");

//        mainController.setListColor(colors);
//        mainController.setListSizes(sizes);
//        txtColors.setText(mainController.getColorStr());
//        txtSizes.setText(mainController.getSizeStr());
        txtDesciption.setText("The customer is best");
        txtBullets.setText("The customer is best");
        txtSubImageLinks.setText("http://dropshiptool.vn/IMG-0989.jpg\n"
                + "http://dropshiptool.vn/IMG-0987.jpg\n"
                + "http://dropshiptool.vn/IMG-0988.jpg");
        txtBasePrice.setText("23");
        txtImageFolder.setText("D:\\ImageTest");
    }

    public void initData() {

        mainController = new MainController();
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
        
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(ClientHomePanel.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        AliexCrawlSvs.getInstance().doAutoClick(null);
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
//        btnStop.setEnabled(false);

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
//        fakeData();
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
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtStoreLink = new javax.swing.JTextArea();
        jComboBox = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtBasePrice = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtBrandName = new javax.swing.JTextField();
        lbStatus = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtImageFolder = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtDesciption = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtBullets = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtSubImageLinks = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        btnStartCrawl = new javax.swing.JButton();
        btnStop = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        txtLogs = new javax.swing.JTextArea();

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Thuộc tính"));
        jPanel3.setFocusTraversalPolicyProvider(true);

        jLabel1.setText("Store Link (Esty) hoặc VPS IP");

        txtStoreLink.setColumns(20);
        txtStoreLink.setLineWrap(true);
        txtStoreLink.setRows(2);
        txtStoreLink.setWrapStyleWord(true);
        jScrollPane1.setViewportView(txtStoreLink);

        jComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));
        jComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxActionPerformed(evt);
            }
        });

        jLabel4.setText("Chọn category");

        jLabel8.setText("Giá cơ bản");

        jLabel9.setText("Nhập brand");

        txtBrandName.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtBrandName.setText("Abbey Stores");

        jLabel2.setText("Image Folder (Dành cho cào data tự tạo)");

        jButton1.setText("Browse...");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtImageFolder, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtBasePrice)
                                    .addComponent(jComboBox, 0, 168, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(txtBrandName, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtImageFolder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(50, 50, 50)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBasePrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBrandName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Nội dung"));

        txtDesciption.setColumns(20);
        txtDesciption.setLineWrap(true);
        txtDesciption.setRows(5);
        txtDesciption.setWrapStyleWord(true);
        jScrollPane4.setViewportView(txtDesciption);

        jLabel5.setText("Description");

        jLabel10.setText("Bullets (phân biệt link bằng kí tự xuống dòng)");

        txtBullets.setColumns(20);
        txtBullets.setLineWrap(true);
        txtBullets.setRows(2);
        txtBullets.setWrapStyleWord(true);
        jScrollPane7.setViewportView(txtBullets);

        jLabel6.setText("Link ảnh (phân biệt link bằng kí tự xuống dòng)");

        txtSubImageLinks.setColumns(20);
        txtSubImageLinks.setLineWrap(true);
        txtSubImageLinks.setRows(2);
        txtSubImageLinks.setWrapStyleWord(true);
        jScrollPane5.setViewportView(txtSubImageLinks);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel10)
                    .addComponent(jLabel6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnStartCrawl, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(btnStop, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnStartCrawl, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnStop, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        if(!mainController.isStop()) return;
        
        if (txtStoreLink.getText().trim().isEmpty()) {
            DialogUtil.showErrorMessage(topFrame, "", "Vui lòng nhập link store!");
            return;
        }

        mainController.setLinkStore(txtStoreLink.getText().trim());

        if (txtBrandName.getText().trim().isEmpty()) {
            DialogUtil.showErrorMessage(topFrame, "", "Vui lòng nhập brand name!");
            return;
        }

        mainController.setBrandName(txtBrandName.getText().trim());

//        if(mainController.getListColor() == null) {
//            DialogUtil.showErrorMessage(topFrame, "", "Vui lòng chọn màu!");
//            return;
//        }
//        
//        if(mainController.getListSizes() == null) {
//            DialogUtil.showErrorMessage(topFrame, "", "Vui lòng chọn size!");
//            return;
//        }
        if (txtDesciption.getText().trim().isEmpty()) {
            DialogUtil.showErrorMessage(topFrame, "", "Vui lòng nhập description!");
            return;
        }
        mainController.setDescription(txtDesciption.getText().trim());

        if (txtSubImageLinks.getText().trim().isEmpty()) {
            DialogUtil.showErrorMessage(topFrame, "", "Vui lòng nhập link ảnh!");
            return;
        }
        mainController.setImageLinks(txtSubImageLinks.getText().trim());

        if (txtBasePrice.getText().trim().isEmpty()) {
            DialogUtil.showErrorMessage(topFrame, "", "Vui lòng nhập giá !");
            return;
        }
        String priceStr = txtBasePrice.getText().trim();
        try {
            float price = Float.parseFloat(priceStr);
            mainController.setBasePrice(price);

        } catch (NumberFormatException ex) {
            DialogUtil.showErrorMessage(topFrame, "", "Thông tin giá không hợp lệ !");
            return;
        }

        String select = (String) jComboBox.getSelectedItem();
//        mainController.setCategory(Configs.hashMapCateType.get(select));
        mainController.setCategory(select);

        if (txtBullets.getText().trim().isEmpty()) {
            DialogUtil.showErrorMessage(topFrame, "", "Vui lòng nhập bullet points !");
            return;
        }
        mainController.setBullets(txtBullets.getText().trim());

        if (!mainController.isEtsy()) {
            if (txtImageFolder.getText().trim().isEmpty()) {
                DialogUtil.showErrorMessage(topFrame, "", "Vui lòng nhập đường dẫn ảnh !");
                return;
            }
            mainController.setImageFolder(txtImageFolder.getText().trim());
        }

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
            actionListener.onStateChange(MainController.STATE.STOP);
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
        public void onStateChange(MainController.STATE state) {
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

    private void jComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxActionPerformed
        // TODO add your handling code here:
        String select = (String) jComboBox.getSelectedItem();
        mainController.setCategory(Configs.hashMapCateType.get(select));
    }//GEN-LAST:event_jComboBoxActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        choosertitle = "Select folder:";
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
            txtImageFolder.setText(path);
        } else {
            System.out.println("No Selection ");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnStartCrawl;
    private javax.swing.JButton btnStop;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JLabel lbStatus;
    private javax.swing.JTextField txtBasePrice;
    private javax.swing.JTextField txtBrandName;
    private javax.swing.JTextArea txtBullets;
    private javax.swing.JTextArea txtDesciption;
    private javax.swing.JTextField txtImageFolder;
    private javax.swing.JTextArea txtLogs;
    private javax.swing.JTextArea txtStoreLink;
    private javax.swing.JTextArea txtSubImageLinks;
    // End of variables declaration//GEN-END:variables
}
