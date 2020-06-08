/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ping.control;

import com.ping.control.MainController.STATE;

/**
 *
 * @author duyuno
 */
public interface ActionListener {
    public void onStateChange(STATE state);
    public void onNotAuthen();
    public void onLicenseInvalid();
}
