/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ping.view;

import com.config.Configs;
import java.io.IOException;
import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;

/**
 *
 * @author Admin
 */
public class SettingPannel extends BasePanel {

    /**
     * Creates new form SettingPannel
     */
    JFileChooser chooser;
    String choosertitle;

    public SettingPannel() {
        initComponents();

        setTitle("Setting");
        setMenuActionCommand("Setting");

        comboBoxPrice.setModel(new javax.swing.DefaultComboBoxModel(Configs.listPrice));

        if (Configs.lastPriceLimitIndex >= 0 && Configs.lastPriceLimitIndex < Configs.listPrice.length) {
            comboBoxPrice.setSelectedIndex(Configs.lastPriceLimitIndex);
        } else {
            Configs.lastPriceLimitIndex = 5;
            comboBoxPrice.setSelectedIndex(5);
        }

//        jComboBoxMarket.setModel(new javax.swing.DefaultComboBoxModel(listMarket));
        Configs.priceLimit = Configs.listPriceValue[Configs.lastPriceLimitIndex];

        comboPriceRate.setModel(new javax.swing.DefaultComboBoxModel(Configs.listPriceRate));

        if (Configs.lastPriceRateIndex >= 0 && Configs.lastPriceRateIndex < Configs.listPriceRate.length) {
            comboPriceRate.setSelectedIndex(Configs.lastPriceRateIndex);
        } else {
            Configs.lastPriceRateIndex = 2;
            comboPriceRate.setSelectedIndex(2);
        }

//        ButtonGroup groupDataLv = new ButtonGroup();
//        groupDataLv.add(jRadioButtonStoreDataNormal);
//        groupDataLv.add(jRadioButtonStoreDataAdvanLite);
//        groupDataLv.add(jRadioButtonStoreDataAdvanFull);
//        switch (Configs.dataLevel) {
//            case 1:
//                jRadioButtonStoreDataNormal.setSelected(true);
//                break;
//            case 5:
//                jRadioButtonStoreDataAdvanLite.setSelected(true);
//                break;
//            case 6:
//                jRadioButtonStoreDataAdvanFull.setSelected(true);
//                break;
////            case 3:
////                jRadioButtonStoreDataLv3.setSelected(true);
////                break;
//        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        comboBoxPrice = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        comboPriceRate = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        checkBoxOnlyOUS = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        checkBoxEpacket = new javax.swing.JCheckBox();
        checkBoxAliexpressStandard = new javax.swing.JCheckBox();
        checkBoxDHL = new javax.swing.JCheckBox();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Price Config"));

        jLabel1.setText("Price Limit");

        comboBoxPrice.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1000$", "Item 2", "Item 3", "Item 4" }));
        comboBoxPrice.setToolTipText("");
        comboBoxPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxPriceActionPerformed(evt);
            }
        });

        jLabel5.setText("Price Rate:");

        comboPriceRate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "x2.5", "Item 2", "Item 3", "Item 4" }));
        comboPriceRate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboPriceRateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(comboBoxPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboPriceRate, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 11, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBoxPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5)
                    .addComponent(comboPriceRate))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Store Config"));

        checkBoxOnlyOUS.setText("Only Ship From US");
        checkBoxOnlyOUS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxOnlyOUSActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(checkBoxOnlyOUS, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                .addGap(212, 212, 212))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(checkBoxOnlyOUS)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Shipping Methods"));
        jPanel2.setFocusCycleRoot(true);

        checkBoxEpacket.setSelected(true);
        checkBoxEpacket.setText("ePacket");
        checkBoxEpacket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxEpacketActionPerformed(evt);
            }
        });

        checkBoxAliexpressStandard.setSelected(true);
        checkBoxAliexpressStandard.setText("AliExpress Standard");
        checkBoxAliexpressStandard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxAliexpressStandardActionPerformed(evt);
            }
        });

        checkBoxDHL.setText("DHL");
        checkBoxDHL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxDHLActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(checkBoxEpacket)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(checkBoxAliexpressStandard)
                .addGap(18, 18, 18)
                .addComponent(checkBoxDHL)
                .addGap(8, 8, 8))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkBoxEpacket)
                    .addComponent(checkBoxAliexpressStandard)
                    .addComponent(checkBoxDHL))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void comboBoxPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxPriceActionPerformed
        // TODO add your handling code here:
        int index = comboBoxPrice.getSelectedIndex();

        //        int index = comboBoxProductTypes.getSelectedIndex();
        try {
            Configs.updatePriceLimitConfig(index);
        } catch (IOException ex) {
        } finally {
            Configs.priceLimit = Configs.listPriceValue[Configs.lastPriceLimitIndex];
        }
    }//GEN-LAST:event_comboBoxPriceActionPerformed

    private void checkBoxOnlyOUSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxOnlyOUSActionPerformed
        // TODO add your handling code here:
        Configs.isOnlyUS = checkBoxOnlyOUS.isSelected();
    }//GEN-LAST:event_checkBoxOnlyOUSActionPerformed

    private void comboPriceRateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboPriceRateActionPerformed
        // TODO add your handling code here:
        int index = comboPriceRate.getSelectedIndex();

        try {
            Configs.updatePriceRateConfig(index);
        } catch (IOException ex) {
        } finally {
            Configs.priceRate = Configs.listPriceRateValue[Configs.lastPriceRateIndex];
        }
    }//GEN-LAST:event_comboPriceRateActionPerformed

    private void checkBoxEpacketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxEpacketActionPerformed
        // TODO add your handling code here:
        Configs.isFilterEpacket = checkBoxEpacket.isSelected();
    }//GEN-LAST:event_checkBoxEpacketActionPerformed

    private void checkBoxAliexpressStandardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxAliexpressStandardActionPerformed
        // TODO add your handling code here:
        Configs.isFilterAliexpress = checkBoxAliexpressStandard.isSelected();
    }//GEN-LAST:event_checkBoxAliexpressStandardActionPerformed

    private void checkBoxDHLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxDHLActionPerformed
        // TODO add your handling code here:
        Configs.isFilterDHL = checkBoxDHL.isSelected();
    }//GEN-LAST:event_checkBoxDHLActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox checkBoxAliexpressStandard;
    private javax.swing.JCheckBox checkBoxDHL;
    private javax.swing.JCheckBox checkBoxEpacket;
    private javax.swing.JCheckBox checkBoxOnlyOUS;
    private javax.swing.JComboBox<String> comboBoxPrice;
    private javax.swing.JComboBox<String> comboPriceRate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}
