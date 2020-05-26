/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pong.control;

import com.pong.server.obj.outcome.BaseOutComeObj;

/**
 *
 * @author PhanDuy
 */
public interface ProcessInputLister {
    public void responseToClient(BaseOutComeObj baseOutComeObj);
    public void responseToClient(int resultCode);
}
