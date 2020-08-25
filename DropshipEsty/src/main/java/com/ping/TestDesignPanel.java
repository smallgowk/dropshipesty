/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ping;

import com.utils.OSUtil;
import com.ping.view.BasePanel;
import com.ping.view.DesignPanel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 *
 * @author duyuno
 */
public class TestDesignPanel {

    JFrame jFrame;

    JPanel container;

    public void init() {
        initPannel();
    }
    
    public void hideCurrentMode() {
        container.removeAll();
        jFrame.dispatchEvent(new WindowEvent(jFrame, WindowEvent.WINDOW_CLOSING));
    }
    
    public void initPannel() {
        jFrame = new JFrame();
//        container = new JPanel();
        OSUtil.setAppTitle(jFrame);
        DesignPanel designPanel = new DesignPanel();
        jFrame.add(designPanel);
//        CustomImageDesignPannel customImageDesignPannel = new CustomImageDesignPannel();
//        jFrame.add(customImageDesignPannel);
//        
//        try {
//            BufferedImage img1 = ImageIO.read(new File("3.jpg"));
//            BufferedImage img2 = ImageIO.read(new File("1.png"));
//            
//            customImageDesignPannel.setContainWidth(600);
//            customImageDesignPannel.setContainHeight(600);
//            customImageDesignPannel.setPreferredSize(new Dimension(600, 600));
//            
//            customImageDesignPannel.setImgBackground(img1);
//            customImageDesignPannel.setImgItem(img2);
//            customImageDesignPannel.setItemWidth(100);
//            customImageDesignPannel.setItemHeight(100);
//            customImageDesignPannel.repaint();
//        } catch (IOException ex) {
//            Logger.getLogger(DesignPanel.class.getName()).log(Level.SEVERE, null, ex);
//        }
        

        jFrame.pack();
        jFrame.setResizable(false);
        jFrame.setVisible(true);
        
        jFrame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent evt) {
                if (OSUtil.isWindows()) {
                    String sc = "taskkill /F /IM chromedriver.exe";

                    try {
                        Process p = Runtime.getRuntime().exec(sc);
                    } catch (IOException ex) {
                        Logger.getLogger(TestDesignPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }

    public static void main(String args[]) {
        TestDesignPanel startClientApp = new TestDesignPanel();
        startClientApp.init();
    }

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
