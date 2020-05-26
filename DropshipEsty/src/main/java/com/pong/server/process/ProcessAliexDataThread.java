/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pong.server.process;

import java.util.Random;

/**
 *
 * @author PhanDuy
 */
public class ProcessAliexDataThread implements Runnable{

    private int index;
    long sleep;
    long count;
    public ProcessAliexDataThread(int i) {
        index = i;
        sleep = new Random().nextInt(1000) * 100;
    }
    
    @Override
    public void run() {
        
        while (count < sleep) {
            System.out.println("Thread: " + index + " | " + count ++);
        }
        System.out.println("End Thread: " + index);
    }
    
}
