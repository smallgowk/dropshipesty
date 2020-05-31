/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ping;

import com.api.dropship.DropApiCall;
import static com.config.Configs.downloadUrl;
import com.ping.tcpclient.ResponseObj;
import com.utils.DialogUtil;
import com.utils.OSUtil;
import com.ping.view.AboutPannel;
import com.ping.view.BasePanel;
import com.ping.view.ClientHomePanel;
import com.utils.Constants;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 *
 * @author duyuno
 */
public class StartClientApp {

    static ArrayList<BasePanel> listPanel = new ArrayList<>();
    JFrame jFrame;

    JPanel container;

//    public static ArrayList<String> listSigns = new ArrayList<>();
//    
//    static {
//        listSigns.add("WL1LG7H3");
//        listSigns.add("1YBJ05D10KKK----2SWR9JCA143322");
//        listSigns.add("WD-WCC2F2209523");
//        listSigns.add("191360803441");
//    };
//    public static String CLIENT_SIGN = "191360803441";
    public void init() {

//        PropertyConfigurator.configure(com.utils.Constants.ConfigPaths.LOG4J_PROPERTY);
//        String diskNumber = ComputerIdentifier.getDiskSerialNumber().replaceAll(" ", "-");
//        if(!listSigns.contains(diskNumber)) {
//            System.out.println("Computer info invalid!");
//            return;
//        }
        ResponseObj responseObj = DropApiCall.doSendGetInfo(null);
//
        if (responseObj == null) {
            System.out.println("Can not check info!");
            return;
        }
//
        switch (responseObj.getResultCode()) {
            case Constants.ResultCode.SERIAL_INVALID:
                DialogUtil.showInfoMessage(null, "Máy tính cài đặt không hợp lệ. Liên hệ 0972071089 để được xác thực!");
                return;
            case Constants.ResultCode.VERSION_INVALID:
                int option = DialogUtil.showOptionsQuestionDialog(null, null, responseObj.getMessage() != null ? responseObj.getMessage() : "Version app không hợp lệ!",
                        "Cập nhật", "Đóng");
                if (option == 0) {

                    if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                        try {
                            Desktop.getDesktop().browse(new URI(downloadUrl));
                        } catch (IOException | URISyntaxException ex) {
                            Logger.getLogger(StartClientApp.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                return;
        }
        if (responseObj.getMessage() != null) {
            DialogUtil.showInfoMessage(null, responseObj.getMessage());
        }

//        String data = responseObj.getData();

//        Gson gson = new Gson();
//        ClientInfo clientInfo = gson.fromJson(data, ClientInfo.class);

//        AuthenConfig.userLvel = clientInfo.getUserLv();
//        AuthenConfig.merchantUser = EncryptUtil.decrypt(clientInfo.getMerchantUser());
//        AuthenConfig.merchantPassword = EncryptUtil.decrypt(clientInfo.getMerchantPassword());
//        AuthenConfig.apiKey = EncryptUtil.decrypt(clientInfo.getApiKey());
//
//        AppConfig.randomPort();

//        System.out.println("" + AuthenConfig.userLvel);
//        System.out.println("" + AuthenConfig.merchantUser);
//        System.out.println("" + AuthenConfig.merchantPassword);
//        System.out.println("" + AuthenConfig.apiKey);
        
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
//        if (!AliexCrawlSvs.getInstance().isHasCookies()) {
////            AliexCrawlSvs.getInstance().initDriver();
////            MerchantSearchSvs.getInstance().login();
////            AliexCrawlSvs.getInstance().autoLoginAliex();
//            if(!AliexCrawlSvs.getInstance().autoLoginAliex()) {
////                int option = DialogUtil.showOptionsQuestionDialog(null, null, "Phiên bản trình duyệt chrome và phiên bản chromedriver không tương thích.\n Vui lòng cập nhật chromedriver theo phiên bản trình duyệt chrome trên máy tính!",
////                        "Cập nhật", "Đóng");
////                if (option == 0) {
////
////                    if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
////                        try {
////                            Desktop.getDesktop().browse(new URI("https://chromedriver.chromium.org/downloads?fbclid=IwAR1I_wfvE-sipnM0-ZsU-nTBZhLYX3exGq9u1ive6mEDZ8922fWQQ_B1p1M"));
////                        } catch (IOException ex) {
////                            Logger.getLogger(StartClientApp.class.getName()).log(Level.SEVERE, null, ex);
////                        } catch (URISyntaxException ex) {
////                            Logger.getLogger(StartClientApp.class.getName()).log(Level.SEVERE, null, ex);
////                        }
////                    }
////                }
////                
//                return;
//            } else {
//                AliexCrawlSvs.getInstance().autoLoginAliex();
//            }
//        }

//        HttpServerWrapper startApp = new HttpServerWrapper();
//        startApp.start();

        jFrame = new JFrame();
        
//        if(!StringUtils.isEmpty(CLIENT_SIGN)) {
//            if(!ComputerIdentifier.getDiskSerialNumber().equals(CLIENT_SIGN)) {
//                return;
//            }
//        }
        container = new JPanel();

//        AliexParameterFactory.initClientId("");
        OSUtil.setAppTitle(jFrame);

        ClientHomePanel mainHomePanel = new ClientHomePanel();
//        SettingPannel settingPannel = new SettingPannel();
        AboutPannel aboutPannel = new AboutPannel();

        listPanel.add(mainHomePanel);
//        listPanel.add(settingPannel);
        listPanel.add(aboutPannel);

        JMenuBar mb = new JMenuBar();

        JMenu menu = new JMenu("Menu");
        JMenu helpMenu = new JMenu("Help");

//        JMenuItem menuItem = createMenuItem(mainHomePanel);
//        menu.add(menuItem);
//        JMenuItem settingMenuItem = createMenuItem(settingPannel);
//        menu.add(settingMenuItem);

        JMenuItem jItem = new JMenuItem("Exit");
        jItem.setActionCommand("exit");
        menu.add(jItem);

        JMenuItem aboutMenuItem = createMenuItem(aboutPannel);
        helpMenu.add(aboutMenuItem);

//        menuItem.addActionListener(actionListener);
//        settingMenuItem.addActionListener(actionListener);
        aboutMenuItem.addActionListener(actionListener);
        jItem.addActionListener(actionListener);

        addBasePanel(jFrame, container, mainHomePanel);

        mb.add(menu);
        mb.add(helpMenu);

        jFrame.setJMenuBar(mb);
        jFrame.add(container);

        jFrame.pack();
        jFrame.setResizable(false);
        jFrame.setVisible(true);
        
        jFrame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent evt) {
//                System.out.println("windowClosing");
                if (OSUtil.isWindows()) {
                    String sc = "taskkill /F /IM chromedriver.exe";

                    try {
                        Process p = Runtime.getRuntime().exec(sc);
                    } catch (IOException ex) {
                        Logger.getLogger(StartClientApp.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        
        
//        LoginThread loginThread = new LoginThread(true, null);
//        loginThread.start();

    }

    public static void main(String args[]) {

//        AliexParameterFactory.initClientId();
        StartClientApp startClientApp = new StartClientApp();
        startClientApp.init();
    }

    java.awt.event.ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String s = e.getActionCommand();

            if (s.equals("exit")) {
                System.exit(0);
                return;
            }

            if (s.equals("MainHome")) {
                return;
            }

            for (BasePanel basePanel : listPanel) {
                if (basePanel.getMenuActionCommand().equals(s)) {
                    DialogUtil.showPannelDialog(jFrame, basePanel, basePanel.getTitle());
                    break;
                }
            }
//            jFrame.pack();
//            jFrame.revalidate();//refresh ui and layout
//            jFrame.repaint();
        }
    };

    public static void addBasePanel(JFrame jFrame, JPanel container, BasePanel basePanel) {
        container.add(basePanel);
        jFrame.setTitle(basePanel.getTitle());
    }

    public static JMenuItem createMenuItem(BasePanel basePanel) {
        JMenuItem jItem = new JMenuItem(basePanel.getTitle());
        jItem.setActionCommand(basePanel.getMenuActionCommand());

        return jItem;
    }
}
