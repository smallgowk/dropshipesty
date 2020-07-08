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
import com.ping.view.PUDHomePanel;
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

        initPLModule();
    }
    
    public void hideCurrentMode() {
        container.removeAll();
        jFrame.dispatchEvent(new WindowEvent(jFrame, WindowEvent.WINDOW_CLOSING));
    }
    
    public void initCustomizationModule() {
        jFrame = new JFrame();
        container = new JPanel();
        OSUtil.setAppTitle(jFrame);
        ClientHomePanel mainHomePanel = new ClientHomePanel();
        AboutPannel aboutPannel = new AboutPannel();

        listPanel.add(mainHomePanel);
        listPanel.add(aboutPannel);

        JMenuBar mb = new JMenuBar();

        JMenu menu = new JMenu("Menu");
        JMenu helpMenu = new JMenu("Help");

//        JMenuItem menuItem = createMenuItem(mainHomePanel);
//        menu.add(menuItem);
//        JMenuItem settingMenuItem = createMenuItem(settingPannel);
//        menu.add(settingMenuItem);

        JMenuItem jItemPL = new JMenuItem("PL tool");
        jItemPL.setActionCommand("pltool");
        menu.add(jItemPL);

        JMenuItem jItemExit = new JMenuItem("Exit");
        jItemExit.setActionCommand("exit");
        menu.add(jItemExit);

        JMenuItem aboutMenuItem = createMenuItem(aboutPannel);
        helpMenu.add(aboutMenuItem);

//        menuItem.addActionListener(actionListener);
//        settingMenuItem.addActionListener(actionListener);
        jItemPL.addActionListener(actionListener);
        aboutMenuItem.addActionListener(actionListener);
        jItemExit.addActionListener(actionListener);

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
    }
    
    public void initPLModule() {
        jFrame = new JFrame();
        container = new JPanel();
        OSUtil.setAppTitle(jFrame);
        PUDHomePanel mainHomePanel = new PUDHomePanel();
        AboutPannel aboutPannel = new AboutPannel();

        listPanel.add(mainHomePanel);
        listPanel.add(aboutPannel);

        JMenuBar mb = new JMenuBar();

        JMenu menu = new JMenu("Menu");
        JMenu helpMenu = new JMenu("Help");

//        JMenuItem menuItem = createMenuItem(mainHomePanel);
//        menu.add(menuItem);
//        JMenuItem settingMenuItem = createMenuItem(settingPannel);
//        menu.add(settingMenuItem);

        JMenuItem jItemPL = new JMenuItem("Customize tool");
        jItemPL.setActionCommand("customTool");
        menu.add(jItemPL);

        JMenuItem jItemExit = new JMenuItem("Exit");
        jItemExit.setActionCommand("exit");
        menu.add(jItemExit);

        JMenuItem aboutMenuItem = createMenuItem(aboutPannel);
        helpMenu.add(aboutMenuItem);

//        menuItem.addActionListener(actionListener);
//        settingMenuItem.addActionListener(actionListener);
        jItemPL.addActionListener(actionListener);
        aboutMenuItem.addActionListener(actionListener);
        jItemExit.addActionListener(actionListener);

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
            
            if (s.equals("pltool")) {
                hideCurrentMode();
                initPLModule();
                return;
            }
            
            if (s.equals("customTool")) {
                hideCurrentMode();
                initCustomizationModule();
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
