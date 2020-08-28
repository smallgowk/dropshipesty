/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ping.view;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author duy.phan
 */
public class CustomImageDesignPannel extends JPanel {

    private int containSize;
    private int bgSize;
    private int itemSize;
    private int itemSizeDisplay;
    BufferedImage imgBackground;
    BufferedImage imgItem;
    private Point oldPoint = null;
    private Point itemPoint = null;
    private float ratio;
    private int x, y;
    private double sin, cos;
    private int a = 10;
    private int t = 1;
    
    public void setImgBackground(BufferedImage imgBackground) {
        this.imgBackground = imgBackground;
        bgSize = imgBackground.getWidth();
        ratio = containSize * 1f / bgSize;
    }

    public void setContainSize(int containSize) {
        this.containSize = containSize;
    }

    public void setItemSize(int itemSize) {
        this.itemSize = itemSize;
        
        if (itemPoint == null) {
            itemPoint = new Point(itemSize / 2, itemSize / 2);
        }
        this.oldPoint = this.itemPoint;
    }
    
    public void setItemSizeDisplay(int itemSizeDisplay) {
        this.itemSizeDisplay = itemSizeDisplay;
        this.itemSize = (int)(itemSizeDisplay / ratio);
        if (itemPoint == null) {
            itemPoint = new Point(itemSize / 2, itemSize / 2);
        }
        this.oldPoint = this.itemPoint;
    }
    
    public void calculateSinCos() {
        if (itemPoint == null || oldPoint == null) {
            sin = 1;
            cos = 1;
            return;
        }
        
        if ((itemPoint.x == oldPoint.x) && (itemPoint.y == oldPoint.y)) {
            sin = 1;
            cos = 1;
            return;
        }
        
        double xOffset = Math.abs(itemPoint.x * 1f - oldPoint.x * 1f);
        double yOffset = Math.abs(itemPoint.y * 1f - oldPoint.y * 1f);
        double s = Math.sqrt(xOffset * xOffset + yOffset * yOffset);
        
        sin = yOffset / s;
        cos = xOffset / s;
    }
    
    public void setPoint(Point point) {
        this.oldPoint = this.itemPoint;
        this.itemPoint = point;
        t = 1;
        calculateSinCos();
    }
    
    public void updateCoorDinate() {
        double s = (a * a) / (2 * t);
    }
    
    public void setImgItem(BufferedImage imgItem) {
        this.imgItem = imgItem;
    }
    
    public int getItemDisplaySize() {
        return (int) (itemSize * ratio);
    }

    public int getItemSize() {
        return itemSize;
    }

    public BufferedImage getImgBackground() {
        return imgBackground;
    }

    public BufferedImage getImgItem() {
        return imgItem;
    }

    public Point getItemPoint() {
        int x = (int)((itemPoint.x - itemSizeDisplay / 2) / ratio);
        int y = (int)((itemPoint.y - itemSizeDisplay / 2) / ratio);
        return new Point(x, y);
    }
    
    public void updateCoordinate() {
        if (x == itemPoint.x && y == itemPoint.y) {
            return;
        }
        
        if (x )
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        if (imgBackground != null) {
            
            g.drawImage(imgBackground, 0, 0, containSize, containSize, this);

            if (imgItem != null && itemPoint != null) {
                g.drawImage(imgItem, itemPoint.x - itemSizeDisplay / 2, itemPoint.y - itemSizeDisplay / 2, itemSizeDisplay, itemSizeDisplay, this);
            }
        }
    }

}
